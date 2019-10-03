package com.rupeng.ogtk;

import com.rupeng.gtk4j.GTK;

public class GtkDrawingArea extends GtkWidget
{
	public GtkDrawingArea()
	{
		setId(GTK.gtk_drawing_area_new());
	}
}
