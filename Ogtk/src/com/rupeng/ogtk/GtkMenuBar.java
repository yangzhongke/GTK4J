package com.rupeng.ogtk;

import com.rupeng.gtk4j.GTK;

public class GtkMenuBar extends GtkMenuShell
{

	public GtkMenuBar()
	{
		super(GTK.gtk_menu_bar_new());
	}

}
