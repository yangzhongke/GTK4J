package com.rupeng.gtk4j;

import java.util.LinkedList;
import java.util.List;

/**
 * 思路：为了避免c和java代码之间传递过于复杂的结构体、object等对象
 * 所以在调用GTK的g_signal_connect时候，只是在java中生成一个唯一signalId传递给C代码
 * 在C代码回调的时候，再把signalId传递回来。然后在Java中我们根据signalId到infos中查询就可以拿到IGCallBack、附加的Object对象
 * 也就是说IGCallBack、附加Object对象都是由GCallBackManager保存的
 * @author 中科
 *
 */
public class GCallBackManager
{
	public final static GCallBackManager Instance = new GCallBackManager();

	private GCallBackManager()
	{
	}

	private int signalIdCounter = 0;//signal计数器 
	private List<CallBackInfo> infos = new LinkedList<CallBackInfo>();//这种“顺序访问、动态增加”用LinkedList比ArrayList好

	public synchronized void connect(int instance,String signalName, Object object,
			IGCallBack callback)
	{		
		if(signalIdCounter==Integer.MAX_VALUE)
		{
			throw new RuntimeException("signalIdCounter已达最大值");
		}
		signalIdCounter++;//生成一个新的信号Id

		CallBackInfo info = new CallBackInfo();
		info.object = object;
		info.signalId = signalIdCounter;
		info.listener = callback;
		infos.add(info);

		GTK.g_signal_connect(instance, signalName, signalIdCounter);
	}
	
	/**
	 * 用于 jni代码中回调通知调用，详见
	 * @param widget
	 * @param signalId 信号id
	 */
	public static void triggerSignal(int instance,int eventData,int signalId)
	{
		//寻找signalId对应的CallBackInfo
		for (CallBackInfo info : Instance.infos)
		{
			if (info.signalId == signalId)
			{
				try
				{
					//这句话只会被执行一次，因为不同的connect生成不同的signalId
					info.listener.execute(instance, eventData, info.object);
				}
				//如果不在这里Catch住，则要程序关闭后才能看到异常
				catch(Throwable ex)
				{
					ex.printStackTrace();
					//Utils.showError(ex); //有时候消息会弹不出来（比如在"draw"中），因此还是改成打印吧。
				}
				return;
			}
		}
		throw new RuntimeException("未注册的signalId"+signalId);
	}
	
	class CallBackInfo
	{
		public int signalId;//信号Id
		public Object object;//附加的对象
		public IGCallBack listener;//要通知的回调
	}

}

