package com.rupeng.ogtk;

import com.rupeng.gtk4j.GTK;

public enum GtkIconSize
{
	INVALID(GTK.GTK_ICON_SIZE_INVALID),
	MENU(GTK.GTK_ICON_SIZE_MENU),
	SMALL_TOOLBAR(GTK.GTK_ICON_SIZE_SMALL_TOOLBAR),
	LARGE_TOOLBAR(GTK.GTK_ICON_SIZE_LARGE_TOOLBAR),
	BUTTON(GTK.GTK_ICON_SIZE_BUTTON),
	DND(GTK.GTK_ICON_SIZE_DND),
	DIALOG(GTK.GTK_ICON_SIZE_DIALOG);
	
	private int value;
	
	GtkIconSize(int value)
	{
		this.value = value;
	}
	
	public int getValue()
	{
		return this.value;
	}
}
