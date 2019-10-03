package com.rupeng.ogtk;

import com.rupeng.gtk4j.GTK;

public enum GtkResponseType
{
	NONE(GTK.GTK_RESPONSE_NONE),
	REJECT(GTK.GTK_RESPONSE_REJECT),
	ACCEPT(GTK.GTK_RESPONSE_ACCEPT),
	DELETE_EVENT(GTK.GTK_RESPONSE_DELETE_EVENT),
	OK(GTK.GTK_RESPONSE_OK),
	CANCEL(GTK.GTK_RESPONSE_CANCEL),
	CLOSE(GTK.GTK_RESPONSE_CLOSE),
	YES(GTK.GTK_RESPONSE_YES),
	NO(GTK.GTK_RESPONSE_NO),
	APPLY(GTK.GTK_RESPONSE_APPLY),
	HELP(GTK.GTK_RESPONSE_HELP);
	
	private int value;
	
	GtkResponseType(int value)
	{
		this.value = value;
	}
	
	public int getValue()
	{
		return this.value;
	}
}
