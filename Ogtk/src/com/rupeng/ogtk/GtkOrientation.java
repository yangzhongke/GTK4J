package com.rupeng.ogtk;

import com.rupeng.gtk4j.GTK;

public enum GtkOrientation
{
	HORIZONTAL(GTK.GTK_ORIENTATION_HORIZONTAL),
	VERTICAL(GTK.GTK_ORIENTATION_VERTICAL);
	
	private int value;
	
	private GtkOrientation(int value)
	{
		this.value = value;
	}
	
	public int getValue()
	{
		return this.value;
	}
}
