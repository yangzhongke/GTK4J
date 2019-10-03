package com.rupeng.ogtk;

import com.rupeng.gtk4j.GTK;

public class GtkListStore
{
	private int iter;
	private int id;
	private int n_columns;
	
	public GtkListStore(int n_columns)
	{
		this.n_columns = n_columns;
		id = GTK.gtk_list_store_new(n_columns);
		iter = GTK.gtk_tree_iter_new();
	}
	
	public void append()
	{
		GTK.gtk_list_store_append(id, iter);
	}
	
	public void clear()
	{
		GTK.gtk_list_store_clear(id);
	}

	public void setValue(int columnIndex, String value)
	{
		if(columnIndex<0)
		{
			throw new IllegalArgumentException("columnIndex的值不能小于0："+columnIndex);
		}
		if(columnIndex>=n_columns)
		{
			throw new IllegalArgumentException("columnIndex的值"+columnIndex+">=了列数"+n_columns);
		}
		//传给GTK时，最后一个参数不能是null
		GTK.gtk_list_store_set_value(id, iter, columnIndex, value==null?"":value);
	}
		
	public String getValue(int rowIndex,int columnIndex)
	{
		//指针移动到第一行之前，如果一开始就没有，就说明没有
		if(!GTK.gtk_tree_model_get_iter_first(getId(), iter))
		{
			return null;
		}
		
		for(int i=0;i<rowIndex;i++)
		{
			if(!GTK.gtk_tree_model_iter_next(getId(), iter))
			{
				return null;
			}
		}
		return GTK.gtk_tree_model_get_value(getId(), iter, columnIndex);
	}
	
	protected int getId()
	{
		return this.id;
	}
}
