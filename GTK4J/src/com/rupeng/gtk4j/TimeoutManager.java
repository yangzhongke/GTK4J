package com.rupeng.gtk4j;

import java.util.LinkedList;
import java.util.List;

/**
 * 原理和GCallBackManager一样，不再多说
 * @author yzk
 *
 */
public class TimeoutManager
{
	public final static TimeoutManager Instance = new TimeoutManager();

	private TimeoutManager()
	{
	}

	private int timeoutIdCounter = 0;//signal计数器 
	private List<TimeoutInfo> infos = new LinkedList<TimeoutInfo>();//这种“顺序访问、动态增加”用LinkedList比ArrayList好

	public synchronized int add(int interval,IGSourceFunc function,Object object)
	{		
		if(timeoutIdCounter==Integer.MAX_VALUE)
		{
			throw new RuntimeException("timeoutIdCounter已达最大值");
		}
		timeoutIdCounter++;//生成一个新的信号Id
		
		TimeoutInfo info = new TimeoutInfo();
		info.object = object;
		info.function = function;
		info.timeoutId = timeoutIdCounter;
		infos.add(info);

		info.timeoutHandler = GTK._g_timeout_add(interval, info.timeoutId);
		
		return info.timeoutId;
	}
	
	/**
	 * 用于 jni代码中回调通知调用，详见
	 * @param widget
	 * @param signalId 信号id
	 */
	public static boolean trigger(int timeoutlId)
	{
		//寻找signalId对应的CallBackInfo
		for (TimeoutInfo info : Instance.infos)
		{
			if (info.timeoutId == timeoutlId)
			{
				try
				{
					return info.function.execute(info.object);		
				}
				catch(Throwable ex)
				{
					ex.printStackTrace();
					//Utils.showError(ex);
				}
			}
		}
		throw new RuntimeException("未注册的timeoutId"+timeoutlId);
	}

	class TimeoutInfo
	{
		public int timeoutId;//Manager内部使用的Id
		public int timeoutHandler;//GTK为timeout生成的句柄
		public Object object;//附加的对象
		public IGSourceFunc function;//要通知的回调
	}
}


