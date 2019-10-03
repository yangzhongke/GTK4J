package com.rupeng.ogtk;

import com.rupeng.gtk4j.GTK;

public class GtkWindow extends GtkBin
{
	public GtkWindow(int id)
	{
		super(id);
	}
	
	public GtkWindow()
	{
		super(GTK.gtk_window_new(GTK.GTK_WINDOW_TOPLEVEL));
	}

	public void setTitle(String title)
	{
		GTK.gtk_window_set_title(getId(), title);
	}

	public String getTitle()
	{
		return GTK.gtk_window_get_title(getId());
	}
	
	public void setResizable(boolean resizable)
	{
		GTK.gtk_window_set_resizable(getId(), resizable);
	}
	
	public boolean getResizable()
	{
		return GTK.gtk_window_get_resizable(getId());
	}
	
	public void setModal(boolean modal)
	{
		GTK.gtk_window_set_modal(getId(), modal);
	}
	
	public void setDefaultSize(int width, int height)
	{
		GTK.gtk_window_set_default_size(getId(), width,height);
	}
	
	public void setPosition( WindowPosition position)
	{
		GTK.gtk_window_set_position(getId(),position.getValue());
	}
	
	public void fullscreen()
	{
		GTK.gtk_window_fullscreen(getId());
	}
	
	public void unfullscreen()
	{
		GTK.gtk_window_unfullscreen(getId());
	}
	
	public void maximize()
	{
		GTK.gtk_window_maximize(getId());
	}
}
