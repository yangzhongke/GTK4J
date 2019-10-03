package com.rupeng.gtk4j;

import java.util.LinkedList;
import java.util.List;

/**
 * ԭ���GCallBackManagerһ�������ٶ�˵
 * @author yzk
 *
 */
public class TimeoutManager
{
	public final static TimeoutManager Instance = new TimeoutManager();

	private TimeoutManager()
	{
	}

	private int timeoutIdCounter = 0;//signal������ 
	private List<TimeoutInfo> infos = new LinkedList<TimeoutInfo>();//���֡�˳����ʡ���̬���ӡ���LinkedList��ArrayList��

	public synchronized int add(int interval,IGSourceFunc function,Object object)
	{		
		if(timeoutIdCounter==Integer.MAX_VALUE)
		{
			throw new RuntimeException("timeoutIdCounter�Ѵ����ֵ");
		}
		timeoutIdCounter++;//����һ���µ��ź�Id
		
		TimeoutInfo info = new TimeoutInfo();
		info.object = object;
		info.function = function;
		info.timeoutId = timeoutIdCounter;
		infos.add(info);

		info.timeoutHandler = GTK._g_timeout_add(interval, info.timeoutId);
		
		return info.timeoutId;
	}
	
	/**
	 * ���� jni�����лص�֪ͨ���ã����
	 * @param widget
	 * @param signalId �ź�id
	 */
	public static boolean trigger(int timeoutlId)
	{
		//Ѱ��signalId��Ӧ��CallBackInfo
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
		throw new RuntimeException("δע���timeoutId"+timeoutlId);
	}

	class TimeoutInfo
	{
		public int timeoutId;//Manager�ڲ�ʹ�õ�Id
		public int timeoutHandler;//GTKΪtimeout���ɵľ��
		public Object object;//���ӵĶ���
		public IGSourceFunc function;//Ҫ֪ͨ�Ļص�
	}
}


