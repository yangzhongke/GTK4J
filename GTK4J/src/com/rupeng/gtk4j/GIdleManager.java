package com.rupeng.gtk4j;

import java.util.LinkedList;
import java.util.List;

/**
 * ԭ���GCallBackManagerһ�������ٶ�˵
 * @author yzk
 *
 */
public class GIdleManager
{
	public final static GIdleManager Instance = new GIdleManager();

	private GIdleManager()
	{
	}

	private int idleIdCounter = 0;//signal������ 
	private List<IdleInfo> infos = new LinkedList<IdleInfo>();//���֡�˳����ʡ���̬���ӡ���LinkedList��ArrayList��

	public synchronized int add(IGSourceFunc function,Object object)
	{		
		if(idleIdCounter==Integer.MAX_VALUE)
		{
			throw new RuntimeException("timeoutIdCounter�Ѵ����ֵ");
		}
		idleIdCounter++;//����һ���µ��ź�Id
		
		IdleInfo info = new IdleInfo();
		info.object = object;
		info.function = function;
		info.idleId = idleIdCounter;
		infos.add(info);

		info.idleHandler = GTK._g_idle_add(info.idleId);
		
		return info.idleId;
	}
	
	/**
	 * ���� jni�����лص�֪ͨ���ã����
	 * @param widget
	 * @param signalId �ź�id
	 */
	public static boolean trigger(int idleId)
	{
		//Ѱ��signalId��Ӧ��IdleInfo
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
		throw new RuntimeException("δע���timeoutId"+idleId);
	}

	class IdleInfo
	{
		public int idleId;//Manager�ڲ�ʹ�õ�Id
		public int idleHandler;//GTKΪtimeout���ɵľ��
		public Object object;//���ӵĶ���
		public IGSourceFunc function;//Ҫ֪ͨ�Ļص�
	}
}


