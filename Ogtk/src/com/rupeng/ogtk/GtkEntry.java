package com.rupeng.ogtk;

import com.rupeng.gtk4j.GTK;

public class GtkEntry extends GtkWidget
{
	public GtkEntry()
	{
		setId(GTK.gtk_entry_new());
	}
	
	public void setMaxLength(int max)
	{
		GTK.gtk_entry_set_max_length(getId(), max);
	}
	
	public int getMaxLength()
	{
		return GTK.gtk_entry_get_max_length(getId());
	}
	
	public String getText()
	{
		return GTK.gtk_entry_get_text(getId());
	}
	
	public void setText(String text)
	{
		if(text==null)
		{
			text ="";
		}
		GTK.gtk_entry_set_text(getId(), text);
	}
	
	public void setEditable(boolean isEditable)
	{
		GTK.gtk_editable_set_editable(getId(), isEditable);
	}
	
	public boolean getEditable()
	{
		return GTK.gtk_editable_get_editable(getId());
	}
}
