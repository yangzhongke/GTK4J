package com.rupeng.ogtk;

import com.rupeng.gtk4j.GTK;

public class GtkMenu extends GtkMenuShell
{

	public GtkMenu()
	{
		super(GTK.gtk_menu_new());
	}

}
