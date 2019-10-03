package com.rupeng.ogtk;

import com.rupeng.gtk4j.GTK;

public class GtkButton extends GtkBin
{
	public GtkButton()
	{
		super(GTK.gtk_button_new());
	}
	
	public GtkButton(String label)
	{
		super(GTK.gtk_button_new_with_label(label));
	}	
	
	public void setLabel(String label)
	{
		GTK.gtk_button_set_label(getId(), label);
	}
	
	public String getLabel()
	{
		return GTK.gtk_button_get_label(getId());
	}
	
	public void setImage(GtkWidget widget)
	{
		GTK.gtk_button_set_image(getId(), widget.getId());
	}
	
	public void setImagePostion(GtkPositionType position)
	{
		GTK.gtk_button_set_image_position(getId(), position.getValue());
	}
}
