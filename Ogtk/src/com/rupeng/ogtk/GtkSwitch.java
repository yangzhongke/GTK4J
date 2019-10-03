package com.rupeng.ogtk;

import com.rupeng.gtk4j.GTK;

public class GtkSwitch extends GtkWidget
{
	public GtkSwitch()
	{
		setId(GTK.gtk_switch_new());
	}
	
	public void setActive(boolean is_active)
	{
		GTK.gtk_switch_set_active(getId(), is_active);
	}
	
	public boolean getActive()
	{
		return GTK.gtk_switch_get_active(getId());
	}
}
