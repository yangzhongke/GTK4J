package com.rupeng.ogtk;

import com.rupeng.gtk4j.GTK;

public class GtkToggleButton extends GtkButton
{
	public GtkToggleButton()
	{
		setId(GTK.gtk_toggle_button_new());
	}
	
	public GtkToggleButton(String label,boolean ismnemonic)
	{
		setId(ismnemonic?GTK.gtk_toggle_button_new_with_mnemonic(label)
				:GTK.gtk_toggle_button_new_with_label(label));
	}
	
	public boolean getActive()
	{
		return GTK.gtk_toggle_button_get_active(getId());
	}
	
	public void setActive(boolean is_active)
	{
		GTK.gtk_toggle_button_set_active(getId(), is_active);
	}
}
