package com.rupeng.ogtk;

import com.rupeng.gtk4j.GTK;

public class GtkFileFilter
{
	private int id;
	public int getId()
	{
		return id;
	}
	
	public GtkFileFilter()
	{
		this.id = GTK.gtk_file_filter_new();
	}
	
	public void setName(String name)
	{
		GTK.gtk_file_filter_set_name(getId(), name);
	}

	public void addPattern(String pattern)
	{
		GTK.gtk_file_filter_add_pattern(getId(), pattern);
	}
}
