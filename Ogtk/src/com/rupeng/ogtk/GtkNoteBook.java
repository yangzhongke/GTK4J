package com.rupeng.ogtk;

import com.rupeng.gtk4j.GTK;

public class GtkNoteBook extends GtkContainer
{

	public GtkNoteBook()
	{
		super(GTK.gtk_notebook_new());
	}

	public void appendPage(GtkWidget child,GtkWidget tab_label)
	{
		GTK.gtk_notebook_append_page(getId(), child.getId(), tab_label.getId());
	}
	
	public void appendPage(GtkWidget child,String tab_label)
	{
		GtkLabel label =new GtkLabel(tab_label);
		appendPage(child,label);
	}
	
	public void setTabPos(GtkPositionType position)
	{
		GTK.gtk_notebook_set_tab_pos(getId(), position.getValue());
	}
}
