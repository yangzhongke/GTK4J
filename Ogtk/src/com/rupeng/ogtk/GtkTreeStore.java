package com.rupeng.ogtk;

import com.rupeng.gtk4j.GTK;

/**
 * todo����Ҫ����
 * @author yzk
 *
 */
@Deprecated
public class GtkTreeStore
{
	private int id;
	
	public GtkTreeStore()//;int n_columns)
	{
		//this.n_columns = n_columns;
		id = GTK.gtk_tree_store_new(1);
		//iter = GTK.gtk_tree_iter_new();
	}
	
	public void append(int iter,int parentIter)
	{
		GTK.gtk_tree_store_append(id, iter, parentIter);
	}
	
	public void clear()
	{
		GTK.gtk_tree_store_clear(id);
	}
	
	
	public void setValue(int iter,String value)
	{
		/*
		if(columnIndex<0)
		{
			throw new IllegalArgumentException("columnIndex��ֵ����С��0��"+columnIndex);
		}
		if(columnIndex>=n_columns)
		{
			throw new IllegalArgumentException("columnIndex��ֵ"+columnIndex+">=������"+n_columns);
		}*/
		
		//����GTKʱ�����һ������������null
		GTK.gtk_tree_store_set_value(id, iter, 0, value==null?"":value);
	}
		
	/*
	public String getValue(int rowIndex,int columnIndex)
	{
		//ָ���ƶ�����һ��֮ǰ�����һ��ʼ��û�У���˵��û��
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
	*/
	
	protected int getId()
	{
		return this.id;
	}
}
