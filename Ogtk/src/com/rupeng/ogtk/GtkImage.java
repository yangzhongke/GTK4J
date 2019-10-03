package com.rupeng.ogtk;

import com.rupeng.gtk4j.GTK;

public class GtkImage extends GtkWidget
{
	public GtkImage()
	{
		setId(GTK.gtk_image_new());
	}
	
	public void setFromStock(String stockId,GtkIconSize size)
	{
		GTK.gtk_image_set_from_stock(getId(), stockId,size.getValue());
	}
	
	public void setFromFile(String filename)
	{
		GTK.gtk_image_set_from_file(getId(), filename);
	}
	
	public void setFromResource(String resName)
	{
		GTK.gtk_image_set_from_resource(getId(), resName);
	}
	
	public void clear()
	{
		GTK.gtk_image_clear(getId());
	}
}
