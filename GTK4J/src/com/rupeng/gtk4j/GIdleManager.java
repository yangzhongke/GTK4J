package com.rupeng.gtk4j;

import java.util.LinkedList;
import java.util.List;

/**
 * 原理和GCallBackManager一样，不再多说
 * @author yzk
 *
 */
public class GIdleManager
{
	public final static GIdleManager Instance = new GIdleManager();

	private GIdleManager()
	{
	}

	private int idleIdCounter = 0;//signal计数器 
	private List<IdleInfo> infos = new LinkedList<IdleInfo>();//这种“顺序访问、动态增加”用LinkedList比ArrayList好

	public synchronized int add(IGSourceFunc function,Object object)
	{		
		if(idleIdCounter==Integer.MAX_VALUE)
		{
			throw new RuntimeException("timeoutIdCounter已达最大值");
		}
		idleIdCounter++;//生成一个新的信号Id
		
		IdleInfo info = new IdleInfo();
		info.object = object;
		info.function = function;
		info.idleId = idleIdCounter;
		infos.add(info);

		info.idleHandler = GTK._g_idle_add(info.idleId);
		
		return info.idleId;
	}
	
	/**
	 * 用于 jni代码中回调通知调用，详见
	 * @param widget
	 * @param signalId 信号id
	 */
	public static boolean trigger(int idleId)
	{
		//寻找signalId对应的IdleInfo
		for (IdleInfo info : Instance.infos)
		{
			if (info.idleId == idleId)
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
		throw new RuntimeException("未注册的timeoutId"+idleId);
	}

	class IdleInfo
	{
		public int idleId;//Manager内部使用的Id
		public int idleHandler;//GTK为timeout生成的句柄
		public Object object;//附加的对象
		public IGSourceFunc function;//要通知的回调
	}
}


