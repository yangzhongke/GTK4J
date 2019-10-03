package com.rupeng.ogtk;

import com.rupeng.gtk4j.GTK;


public enum WindowPosition
{
	NONE(GTK.GTK_WIN_POS_NONE),
	CENTER(GTK.GTK_WIN_POS_CENTER),
	MOUSE(GTK.GTK_WIN_POS_MOUSE),
	CENTER_ALWAYS(GTK.GTK_WIN_POS_CENTER_ALWAYS),
	CENTER_ON_PARENT(GTK.GTK_WIN_POS_CENTER_ON_PARENT);
	
	private int value;
	
	private  WindowPosition(int value)
	{
		this.value = value;
	}
	
	public int getValue()
	{
		return value;
	}
}
