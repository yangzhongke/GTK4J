package com.rupeng.gtk4j;

public interface IGCallBack
{
	/**
	 * 
	 * @param eventData
	 * @param object g_signal_connectʱ�򸽼ӵĶ����Object�����һ�������ͻᴫ�ݵ�����
	 * @param widget �¼����widget
	 */
	public void execute(int instance,int eventData, Object object);
}
