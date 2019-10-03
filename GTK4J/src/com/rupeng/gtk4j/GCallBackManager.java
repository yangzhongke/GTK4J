package com.rupeng.gtk4j;

import java.util.LinkedList;
import java.util.List;

/**
 * ˼·��Ϊ�˱���c��java����֮�䴫�ݹ��ڸ��ӵĽṹ�塢object�ȶ���
 * �����ڵ���GTK��g_signal_connectʱ��ֻ����java������һ��ΨһsignalId���ݸ�C����
 * ��C����ص���ʱ���ٰ�signalId���ݻ�����Ȼ����Java�����Ǹ���signalId��infos�в�ѯ�Ϳ����õ�IGCallBack�����ӵ�Object����
 * Ҳ����˵IGCallBack������Object��������GCallBackManager�����
 * @author �п�
 *
 */
public class GCallBackManager
{
	public final static GCallBackManager Instance = new GCallBackManager();

	private GCallBackManager()
	{
	}

	private int signalIdCounter = 0;//signal������ 
	private List<CallBackInfo> infos = new LinkedList<CallBackInfo>();//���֡�˳����ʡ���̬���ӡ���LinkedList��ArrayList��

	public synchronized void connect(int instance,String signalName, Object object,
			IGCallBack callback)
	{		
		if(signalIdCounter==Integer.MAX_VALUE)
		{
			throw new RuntimeException("signalIdCounter�Ѵ����ֵ");
		}
		signalIdCounter++;//����һ���µ��ź�Id

		CallBackInfo info = new CallBackInfo();
		info.object = object;
		info.signalId = signalIdCounter;
		info.listener = callback;
		infos.add(info);

		GTK.g_signal_connect(instance, signalName, signalIdCounter);
	}
	
	/**
	 * ���� jni�����лص�֪ͨ���ã����
	 * @param widget
	 * @param signalId �ź�id
	 */
	public static void triggerSignal(int instance,int eventData,int signalId)
	{
		//Ѱ��signalId��Ӧ��CallBackInfo
		for (CallBackInfo info : Instance.infos)
		{
			if (info.signalId == signalId)
			{
				try
				{
					//��仰ֻ�ᱻִ��һ�Σ���Ϊ��ͬ��connect���ɲ�ͬ��signalId
					info.listener.execute(instance, eventData, info.object);
				}
				//�����������Catchס����Ҫ����رպ���ܿ����쳣
				catch(Throwable ex)
				{
					ex.printStackTrace();
					//Utils.showError(ex); //��ʱ����Ϣ�ᵯ��������������"draw"�У�����˻��Ǹĳɴ�ӡ�ɡ�
				}
				return;
			}
		}
		throw new RuntimeException("δע���signalId"+signalId);
	}
	
	class CallBackInfo
	{
		public int signalId;//�ź�Id
		public Object object;//���ӵĶ���
		public IGCallBack listener;//Ҫ֪ͨ�Ļص�
	}

}

