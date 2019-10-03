package com.rupeng.ogtk;

import com.rupeng.gtk4j.GTK;

public class GtkBox extends GtkContainer
{
	public GtkBox(GtkOrientation orientation)
	{
		this(orientation,0);
	}
	
	public GtkBox(GtkOrientation orientation, int spacing)
	{
		super(GTK.gtk_box_new(orientation.getValue(), spacing));
	}
	
	public void packStart(GtkWidget child,
			boolean expand, boolean fill, int padding)
	{
		GTK.gtk_box_pack_start(this.getId(), child.getId(), expand, fill, padding);
	}
	
	public void packStart(GtkWidget child)
	{
		GTK.gtk_box_pack_start(this.getId(), child.getId(), false, false, 0);
	}
	
	public void packEnd(GtkWidget child,
			boolean expand, boolean fill, int padding)
	{
		GTK.gtk_box_pack_end(this.getId(), child.getId(), expand, fill, padding);
	}
	
	public void packEnd(GtkWidget child)
	{
		GTK.gtk_box_pack_end(this.getId(), child.getId(), false, false, 0);
	}
}
