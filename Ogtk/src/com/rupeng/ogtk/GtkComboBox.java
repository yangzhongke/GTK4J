package com.rupeng.ogtk;

import com.rupeng.gtk4j.GTK;
import com.rupeng.gtk4j.IGCallBack;

public class GtkComboBox extends GtkBin
{
	public GtkComboBox()
	{
		super(GTK.gtk_combo_box_new());
	}
	
	public int getActive()
	{
		return GTK.gtk_combo_box_get_active(getId());
	}
	public void setActive(int index)
	{
		GTK.gtk_combo_box_set_active(getId(), index);
	}
	
	public void addChangedListener(IGCallBack callback)
	{
		GTK.g_signal_connect(getId(), "changed", callback, null);
	}
	
	public void setActiveId(String active_id)
	{
		GTK.gtk_combo_box_set_active_id(getId(), active_id);
	}
	
	public String getActiveId()
	{
		return GTK.gtk_combo_box_get_active_id(getId());
	}
}
