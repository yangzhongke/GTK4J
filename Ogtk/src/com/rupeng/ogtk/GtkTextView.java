package com.rupeng.ogtk;

import com.rupeng.gtk4j.GTK;

public class GtkTextView extends GtkContainer
{
	private int textviewId;
	
	public GtkTextView()
	{
		super(GTK.gtk_scrolled_window_new());
		
		//Ìí¼Ó¹ö¶¯Ìõ
		textviewId = GTK.gtk_text_view_new();
		setWrapMode(GtkWrapMode.WORD_CHAR);
		GTK.gtk_widget_show(textviewId);
		GTK.gtk_container_add(getId(), textviewId);
	}
	
	public void setText(String text)
	{
		if(text==null)
		{
			text = "";
		}
		int buffer = GTK.gtk_text_view_get_buffer(textviewId);
		GTK.gtk_text_buffer_set_text(buffer, text);
	}
	
	public String getText()
	{
		int buffer = GTK.gtk_text_view_get_buffer(textviewId);
		return GTK.gtk_text_buffer_get_text(buffer);
	}
	
	public void setWrapMode(GtkWrapMode wrap_mode)
	{
		GTK.gtk_text_view_set_wrap_mode(textviewId, wrap_mode.getValue());
	}
	
	public GtkWrapMode getWrapMode()
	{
		int mode = GTK.gtk_text_view_get_wrap_mode(textviewId);
		for(GtkWrapMode wm : GtkWrapMode.values())
		{
			if(wm.getValue() == mode)
			{
				return wm;
			}
		}
		throw new RuntimeException("GtkWrapMode´íÎó");
	}
	
	public void setEditable(boolean setting)
	{
		GTK.gtk_text_view_set_editable(textviewId, setting);
	}
	
	public boolean getEditable()
	{
		return GTK.gtk_text_view_get_editable(textviewId);
	}
	
	@Override
	public void destroy()
	{
		super.destroy();
		GTK.gtk_widget_destroy(textviewId);
	}
}
