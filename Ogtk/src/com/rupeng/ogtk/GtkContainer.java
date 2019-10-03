package com.rupeng.ogtk;

import com.rupeng.gtk4j.GTK;

public class GtkContainer extends GtkWidget
{
	public GtkContainer(int id)
	{
		setId(id);
	}
	
	public void add(GtkWidget child)
	{
		GTK.gtk_container_add(this.getId(), child.getId());
	}
	
	public void remove(GtkWidget widget)
	{
		GTK.gtk_container_remove(getId(), widget.getId());
	}
	
	public void removeAll()
	{
		for(int widget : GTK.gtk_container_get_children(getId()))
		{
			GTK.gtk_container_remove(getId(), widget);
		}
	}
}
