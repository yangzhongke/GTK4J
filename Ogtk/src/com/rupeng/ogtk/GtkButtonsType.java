package com.rupeng.ogtk;

import com.rupeng.gtk4j.GTK;

public enum GtkButtonsType
{
	NONE(GTK.GTK_BUTTONS_NONE),
	OK(GTK.GTK_BUTTONS_OK),
	CLOSE(GTK.GTK_BUTTONS_CLOSE),
	CANCEL(GTK.GTK_BUTTONS_CANCEL),
	YES_NO(GTK.GTK_BUTTONS_YES_NO),
	OK_CANCEL(GTK.GTK_BUTTONS_OK_CANCEL);
	
	private int value;
	
	private GtkButtonsType(int value)
	{
		this.value = value;
	}
	
	public int getValue()
	{
		return this.value;
	}
}
