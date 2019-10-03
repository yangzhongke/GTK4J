package com.rupeng.ogtk;

import com.rupeng.gtk4j.GTK;

public enum GtkMessageType
{
	INFO(GTK.GTK_MESSAGE_INFO),
	WARNING(GTK.GTK_MESSAGE_WARNING),
	QUESTION(GTK.GTK_MESSAGE_QUESTION),
	ERROR(GTK.GTK_MESSAGE_ERROR),
	OTHER(GTK.GTK_MESSAGE_OTHER);
	
	private int value;
	
	private GtkMessageType(int value)
	{
		this.value = value;
	}
	
	public int getValue()
	{
		return this.value;
	}
}
