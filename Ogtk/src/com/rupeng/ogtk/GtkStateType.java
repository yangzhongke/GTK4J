package com.rupeng.ogtk;

import com.rupeng.gtk4j.GTK;

public enum GtkStateType
{
	NORMAL(GTK.GTK_STATE_NORMAL),
	ACTIVE(GTK.GTK_STATE_ACTIVE),
	PRELIGHT(GTK.GTK_STATE_PRELIGHT),
	SELECTED(GTK.GTK_STATE_SELECTED),
	INSENSITIVE(GTK.GTK_STATE_INSENSITIVE);
	
	private int value;
	
	GtkStateType(int value)
	{
		this.value = value;
	}
	
	public int getValue()
	{
		return this.value;
	}
}
