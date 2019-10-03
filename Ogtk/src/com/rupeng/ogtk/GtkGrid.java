package com.rupeng.ogtk;

import com.rupeng.gtk4j.GTK;

public class GtkGrid extends GtkContainer
{
	public GtkGrid()
	{
		super(GTK.gtk_grid_new());
	}
	
	public void insertRow(int position)
	{
		GTK.gtk_grid_insert_row(this.getId(), position);
	}
	
	public void insertColumn(int position)
	{
		GTK.gtk_grid_insert_column(this.getId(), position);
	}
	
	public void attach(GtkWidget child, int left,
			int top, int width, int height)
	{
		GTK.gtk_grid_attach(this.getId(),child.getId(), left, top, width, height);
	}
	
	public void attach(GtkWidget child, int left,
			int top)
	{
		attach(child,left,top,1,1);
	}

	public void setRowSpacing(int spacing)
	{
		GTK.gtk_grid_set_row_spacing(this.getId(), spacing);
	}

	public void setColumnSpacing(int spacing)
	{
		GTK.gtk_grid_set_column_spacing(this.getId(), spacing);
	}

	public void setRowHomogeneous(boolean homogeneous)
	{
		GTK.gtk_grid_set_row_homogeneous(getId(), homogeneous);
	}

	public void gtk_grid_set_column_homogeneous(boolean homogeneous)
	{
		GTK.gtk_grid_set_column_homogeneous(getId(), homogeneous);
	}
}
