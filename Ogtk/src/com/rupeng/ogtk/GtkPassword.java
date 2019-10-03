package com.rupeng.ogtk;

import com.rupeng.gtk4j.GTK;

public class GtkPassword extends GtkEntry
{
	public GtkPassword()
	{
		GTK.gtk_entry_set_invisible_char(getId(), '*');
		GTK.gtk_entry_set_visibility(getId(), false);
	}
}
