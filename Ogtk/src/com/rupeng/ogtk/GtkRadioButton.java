package com.rupeng.ogtk;

import com.rupeng.gtk4j.GTK;

public class GtkRadioButton extends GtkCheckButton
{
	public GtkRadioButton()
	{
		setId(GTK.gtk_radio_button_new(0));
	}
	
	public GtkRadioButton(String label)
	{
		setId( GTK.gtk_radio_button_new_with_label(0, label));
	}
	
	public GtkRadioButton createSibling(String label)
	{
		int siblingId = GTK.gtk_radio_button_new_with_label_from_widget(getId(),label);
		GtkRadioButton newBtn = new GtkRadioButton();
		newBtn.setId(siblingId);
		return newBtn;
	}
}
