package com.rupeng.ogtk;

import com.rupeng.gtk4j.GTK;

public class GtkScale extends GtkRange
{
	public GtkScale(GtkOrientation orientation,double min, double max, double step)
	{
		setId( GTK.gtk_scale_new_with_range(orientation.getValue(), min, max, step));
	}
	
	public boolean getDrawValue()
	{
		return GTK.gtk_scale_get_draw_value(getId());
	}
	
	public void setDrawValue(boolean draw_value)
	{
		GTK.gtk_scale_set_draw_value(getId(), draw_value);
	}
}
