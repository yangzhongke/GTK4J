package com.rupeng.ogtk.impl;

import com.rupeng.gtk4j.IGCallBack;
import com.rupeng.ogtk.GtkApplication;

public class ExitAppCallBack implements IGCallBack
{

	@Override
	public void execute(int instance, int eventData, Object object)
	{
		GtkApplication.getInstance().quit();				
	}

}