package com.rupeng.ogtk;

import com.rupeng.gtk4j.GTK;

public enum GtkFileChooserAction
{
	OPEN(GTK.GTK_FILE_CHOOSER_ACTION_OPEN),
	SAVE(GTK.GTK_FILE_CHOOSER_ACTION_SAVE),
	SELECT_FOLDER(GTK.GTK_FILE_CHOOSER_ACTION_SELECT_FOLDER),
	CREATE_FOLDER(GTK.GTK_FILE_CHOOSER_ACTION_CREATE_FOLDER);
	
	private int value;
	
	private GtkFileChooserAction(int value)
	{
		this.value = value;
	}
	
	public int getValue()
	{
		return this.value;
	}
}
