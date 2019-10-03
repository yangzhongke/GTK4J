package com.rupeng.ogtk;

import com.rupeng.gtk4j.GTK;

public enum GtkWrapMode
{
	NONE(GTK.GTK_WRAP_NONE),
	CHAR(GTK.GTK_WRAP_CHAR),
	WORD(GTK.GTK_WRAP_WORD),
	WORD_CHAR(GTK.GTK_WRAP_WORD_CHAR);
	
	private int value;
	
	private GtkWrapMode(int value)
	{
		this.value = value;
	}
	
	public int getValue()
	{
		return this.value;
	}
}
