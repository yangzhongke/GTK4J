package com.rupeng.ogtk;

import com.rupeng.gtk4j.GTK;

public class GtkFixed extends GtkContainer
{
	public GtkFixed()
	{
		super(GTK.gtk_fixed_new());
	}
	
	public void put(GtkWidget widget, int x, int y)
	{
		GTK.gtk_fixed_put(this.getId(), widget.getId(), x, y);
	}

}
