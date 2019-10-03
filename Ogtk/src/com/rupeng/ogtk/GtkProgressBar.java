package com.rupeng.ogtk;

import com.rupeng.gtk4j.GTK;

public class GtkProgressBar extends GtkWidget
{
	public GtkProgressBar()
	{
		setId(GTK.gtk_progress_bar_new());
	}
	
	public void setFraction(double fraction)
	{
		GTK.gtk_progress_bar_set_fraction(this.getId(), fraction);
	}
	
	public double getFraction()
	{
		return GTK.gtk_progress_bar_get_fraction(getId());
	}
	
	public void setText(String text)
	{
		GTK.gtk_progress_bar_set_text(this.getId(), text);
	}
	
	public String getText()
	{
		return GTK.gtk_progress_bar_get_text(this.getId());
	}
	
	public void setShowText(boolean showText)
	{
		GTK.gtk_progress_bar_set_show_text(this.getId(), showText);
	}
	
	public boolean getShowText()
	{
		return GTK.gtk_progress_bar_get_show_text(getId());
	}
}
