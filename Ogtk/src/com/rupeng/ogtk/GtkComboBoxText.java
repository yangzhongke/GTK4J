package com.rupeng.ogtk;

import com.rupeng.gtk4j.GTK;

public class GtkComboBoxText extends GtkComboBox
{
	public GtkComboBoxText()
	{
		setId(GTK.gtk_combo_box_text_new());
	}	

	/**
	 * 
	 * @param text
	 */
	public void appendText(String text)
	{
		GTK.gtk_combo_box_text_append_text(getId(), text);
	}
	
	public void append(String id,String text)
	{
		GTK.gtk_combo_box_text_append(getId(), id, text);
	}
	
	public void remove(int position)
	{
		GTK.gtk_combo_box_text_remove(getId(), position);
	}
	
	public String getActiveText()
	{
		return GTK.gtk_combo_box_text_get_active_text(getId());
	}
	
	public void removeAll()
	{
		GTK.gtk_combo_box_text_remove_all(getId());
	}
}
