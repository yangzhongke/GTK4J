package com.rupeng.ogtk;

import com.rupeng.gtk4j.GTK;

public enum GtkPositionType
{
	TOP(GTK.GTK_POS_TOP),
	BOTTOM(GTK.GTK_POS_BOTTOM),
	LEFT(GTK.GTK_POS_LEFT),
	RIGHT(GTK.GTK_POS_RIGHT);
	
	private int value;
	
	private GtkPositionType(int value)
	{
		this.value = value;
	}
	
	public int getValue()
	{
		return this.value;
	}
}

