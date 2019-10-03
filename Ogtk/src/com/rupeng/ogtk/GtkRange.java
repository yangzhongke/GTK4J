package com.rupeng.ogtk;

import com.rupeng.gtk4j.GTK;
import com.rupeng.gtk4j.IGCallBack;

public abstract class GtkRange extends GtkWidget
{
	public double getValue()
	{
		return GTK.gtk_range_get_value(getId());
	}
	
	public void setValue(double value)
	{
		GTK.gtk_range_set_value(getId(), value);
	}
	
	public void addValueChangedListener(IGCallBack callback)
	{
		addValueChangedListener(callback,null);
	}
	public void addValueChangedListener(IGCallBack callback,Object object)
	{
		GTK.g_signal_connect(getId(), "value-changed", callback, object);
	}
}
