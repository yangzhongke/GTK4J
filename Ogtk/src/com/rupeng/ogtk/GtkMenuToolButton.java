package com.rupeng.ogtk;

import com.rupeng.gtk4j.GTK;

public class GtkMenuToolButton extends GtkToolButton
{
	public GtkMenuToolButton(GtkWidget icon_widget, String label)
	{
		super(GTK.gtk_menu_tool_button_new(icon_widget==null?0:icon_widget.getId(), label));
	}
	
	public GtkMenuToolButton(String stock_id)
	{
		super(GTK.gtk_menu_tool_button_new_from_stock(stock_id));
	}

	public void setMenu(GtkMenu menu)
	{
		GTK.gtk_menu_tool_button_set_menu(getId(), menu.getId());
	}
}
