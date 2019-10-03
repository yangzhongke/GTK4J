package com.rupeng.ogtk;

import com.rupeng.gtk4j.GTK;

public enum GtkSelectionMode
{
	NONE(GTK.GTK_SELECTION_NONE),
	SINGLE(GTK.GTK_SELECTION_SINGLE),
	BROWSE(GTK.GTK_SELECTION_BROWSE),
	MULTIPLE(GTK.GTK_SELECTION_MULTIPLE);
	
	private int value;
	
	private GtkSelectionMode(int value)
	{
		this.value = value;
	}
	
	public int getValue()
	{
		return this.value;
	}
}
