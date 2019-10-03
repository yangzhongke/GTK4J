package com.rupeng.ogtk;

import com.rupeng.gtk4j.GTK;
import com.rupeng.gtk4j.IGCallBack;

public class GtkListView extends GtkContainer
{
	private int treeviewId;
	private GtkListStore model;

	public GtkListView()
	{
		super(GTK.gtk_scrolled_window_new());
		//添加滚动条
		treeviewId = GTK.gtk_tree_view_new();
		GTK.gtk_container_add(getId(), treeviewId);
		GTK.gtk_widget_show(treeviewId);
	}
	
	public void appendColumn(String title, int columnIndex)
	{
		int cell_renderer = GTK.gtk_cell_renderer_text_new();

		int column = GTK.gtk_tree_view_column_new_with_attributes(title,
				cell_renderer, columnIndex);
		
		GTK.gtk_tree_view_append_column(treeviewId, column);
	}
	
	/**
	 * 设置列可见性
	 * @param columnIndex
	 * @param visible
	 */
	public void setColumnVisible(int columnIndex,boolean visible)
	{
		int column = GTK.gtk_tree_view_get_column(treeviewId, columnIndex);
		GTK.gtk_tree_view_column_set_visible(column, visible);
	}
	
	/**
	 * 可以改变列宽度
	 * @param columnIndex
	 * @param resizable
	 */
	public void setColumnResizable(int columnIndex,boolean resizable)
	{
		int column = GTK.gtk_tree_view_get_column(treeviewId, columnIndex);
		GTK.gtk_tree_view_column_set_resizable(column, resizable);
	}
	
	/**
	 * 可以拖动改变列顺序
	 * @param columnIndex
	 * @param reorderable
	 */
	public void setColumnReorderable(int columnIndex,boolean reorderable)
	{
		int column = GTK.gtk_tree_view_get_column(treeviewId, columnIndex);
		GTK.gtk_tree_view_column_set_reorderable(column, reorderable);
	}
	
	/**
	 * 设置列按照sort_column_id进行排序
	 * @param columnIndex
	 * @param sort_column_id
	 */
	public void setSortColumnId(int columnIndex,int sort_column_id)
	{
		int column = GTK.gtk_tree_view_get_column(treeviewId, columnIndex);
		GTK.gtk_tree_view_column_set_sort_column_id(column, sort_column_id);
	}

	//目前没人调用
	public void removeAllColumn()
	{
		int n = GTK.gtk_tree_view_get_n_columns(treeviewId);
		for(int i =n-1;i>=0;i--)//注意要从后往前删
		//for(int i=0;i<n;i++)
		{
			int column = GTK.gtk_tree_view_get_column(treeviewId, i);
			GTK.gtk_tree_view_remove_column(treeviewId, column);
		}
	}
	
	public void setModel(GtkListStore model)
	{
		GTK.gtk_tree_view_set_model(treeviewId, model.getId());
		this.model = model;
	}

	public GtkListStore getModel()
	{
		return this.model;
	}

	public int[] getSelectionIndices()
	{
		return GTK.gtk_tree_view_get_selection_indices(treeviewId);
	}

	public void setSelectionMode(GtkSelectionMode mode)
	{
		int selection = GTK.gtk_tree_view_get_selection(treeviewId);
		GTK.gtk_tree_selection_set_mode(selection, mode.getValue());
	}

	public GtkSelectionMode getSelectionMode()
	{
		int selection = GTK.gtk_tree_view_get_selection(treeviewId);
		for (GtkSelectionMode mode : GtkSelectionMode.values())
		{
			if (mode.getValue() == GTK.gtk_tree_selection_get_mode(selection))
			{
				return mode;
			}
		}
		throw new RuntimeException("select值有错");
	}

	public void addChangedListener(IGCallBack callback)
	{
		int selection = GTK.gtk_tree_view_get_selection(treeviewId);
		GTK.g_signal_connect(selection, "changed", callback, null);
	}

	@Override
	public void addClickedListener(IGCallBack callback)
	{
		GTK.g_signal_connect(treeviewId, "clicked",callback, null);
	}
	
	@Override
	public void addDoubleClickListener(final IGCallBack callback)
	{
		//因为外层包裹的是ScrollWindow，这样默认的父类addDoubleClickListener的实现是监听ScrollWindow的
		//这样双击TreeView就会没有事件响应，因此要换为监听treeviewId
		
		GTK.g_signal_connect(treeviewId, "button-press-event",new IGCallBack() {			
			@Override
			public void execute(int instance, int eventData, Object object)
			{
				if(GTK.gdk_event_get_type(eventData)==GTK.GDK_2BUTTON_PRESS)
				{
					callback.execute(instance, eventData, object);
				}
			}
		}, null);
	}
}
