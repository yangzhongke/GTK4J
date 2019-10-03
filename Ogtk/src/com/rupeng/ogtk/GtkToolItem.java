package com.rupeng.ogtk;

import com.rupeng.gtk4j.GTK;

public class GtkToolItem extends GtkBin
{
	
	public GtkToolItem(int id)
	{
		super(id);
	}

	public GtkToolItem()
	{
		super(GTK.gtk_tool_item_new());
	}
	
	public void setTooltipText(String text)
	{
		GTK.gtk_tool_item_set_tooltip_text(getId(), text);
	}
}
