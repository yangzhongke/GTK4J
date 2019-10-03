package com.rupeng.ogtk;

import com.rupeng.gtk4j.GTK;

public abstract class GtkMenuShell extends GtkContainer
{

	public GtkMenuShell(int id)
	{
		super(id);
	}
	
	public void append(GtkMenuItem child)
	{
		GTK.gtk_menu_shell_append(this.getId(), child.getId());
	}

	
}
