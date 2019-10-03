package com.rupeng.ogtk;

import com.rupeng.gtk4j.GTK;

public abstract class GtkBin extends GtkContainer
{

	public GtkBin(int id)
	{
		super(id);
	}

	@Override
	public void add(GtkWidget child)
	{
		int[] children = GTK.gtk_container_get_children(getId());
		if(children.length>0)
		{
			throw new IllegalArgumentException("容器中已经有一个子元素，GtkBin最多只能允许一个子元素");
		}
		super.add(child);
	}

	
}
