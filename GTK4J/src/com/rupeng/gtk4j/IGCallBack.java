package com.rupeng.gtk4j;

public interface IGCallBack
{
	/**
	 * 
	 * @param eventData
	 * @param object g_signal_connect时候附加的额外的Object，最后一个参数就会传递到这里
	 * @param widget 事件相关widget
	 */
	public void execute(int instance,int eventData, Object object);
}
