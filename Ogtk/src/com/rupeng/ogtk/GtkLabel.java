package com.rupeng.ogtk;

import com.rupeng.gtk4j.GTK;

public class GtkLabel extends GtkWidget
{	
	public GtkLabel(String label)
	{
		setId(GTK.gtk_label_new(label));
	}
	
	public String getText()
	{
		return GTK.gtk_label_get_text(getId());
	}
	
	public void setText(String text)
	{
		GTK.gtk_label_set_text(getId(), text);
	}
}
