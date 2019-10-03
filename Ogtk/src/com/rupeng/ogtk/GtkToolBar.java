package com.rupeng.ogtk;

import com.rupeng.gtk4j.GTK;

public class GtkToolBar extends GtkContainer
{

	public GtkToolBar()
	{
		super(GTK.gtk_toolbar_new());
	}

	public void insert(GtkToolItem item,int pos)
	{
		GTK.gtk_toolbar_insert(getId(), item.getId(), pos);
	}
}
