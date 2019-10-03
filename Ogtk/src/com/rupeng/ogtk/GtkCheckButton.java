package com.rupeng.ogtk;

import com.rupeng.gtk4j.GTK;

public class GtkCheckButton extends GtkToggleButton
{
	public GtkCheckButton()
	{
		setId(GTK.gtk_check_button_new());
	}
	
	public GtkCheckButton(String label,boolean ismnemonic)
	{
		int id;
		if(ismnemonic)
		{
			id = GTK.gtk_check_button_new_with_mnemonic(label);
		}
		else
		{
			id = GTK.gtk_check_button_new_with_label(label);
		}
		setId(id);
	}
}
