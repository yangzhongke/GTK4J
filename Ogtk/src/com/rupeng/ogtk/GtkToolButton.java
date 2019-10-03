package com.rupeng.ogtk;

import com.rupeng.gtk4j.GTK;

public class GtkToolButton extends GtkToolItem
{
	public GtkToolButton(int id)
	{
		super(id);
	}

	/**
	 * 
	 * @param icon_widget ©иртн╙©у
	 * @param label
	 */
	public GtkToolButton(GtkWidget icon_widget,String label)
	{
		setId(icon_widget==null?GTK.gtk_tool_button_new(0, label)
				:GTK.gtk_tool_button_new(icon_widget.getId(), label));
	}

	public GtkToolButton(String label)
	{
		this(null,label);
	}
	
	public void setLabel(String label)
	{
		GTK.gtk_tool_button_set_label(getId(), label);
	}
	public void setStockId(String stock_id)
	{
		GTK.gtk_tool_button_set_stock_id(getId(), stock_id);
	}
	public void setIconWidget(GtkWidget widget)
	{
		GTK.gtk_tool_button_set_icon_widget(getId(), widget.getId());
	}
}
