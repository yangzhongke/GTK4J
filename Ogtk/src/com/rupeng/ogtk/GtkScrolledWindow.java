package com.rupeng.ogtk;

import com.rupeng.gtk4j.GTK;

public class GtkScrolledWindow extends GtkContainer
{

	public GtkScrolledWindow()
	{
		super(GTK.gtk_scrolled_window_new());
	}

}
