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
		//��ӹ�����
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
	 * �����пɼ���
	 * @param columnIndex
	 * @param visible
	 */
	public void setColumnVisible(int columnIndex,boolean visible)
	{
		int column = GTK.gtk_tree_view_get_column(treeviewId, columnIndex);
		GTK.gtk_tree_view_column_set_visible(column, visible);
	}
	
	/**
	 * ���Ըı��п��
	 * @param columnIndex
	 * @param resizable
	 */
	public void setColumnResizable(int columnIndex,boolean resizable)
	{
		int column = GTK.gtk_tree_view_get_column(treeviewId, columnIndex);
		GTK.gtk_tree_view_column_set_resizable(column, resizable);
	}
	
	/**
	 * �����϶��ı���˳��
	 * @param columnIndex
	 * @param reorderable
	 */
	public void setColumnReorderable(int columnIndex,boolean reorderable)
	{
		int column = GTK.gtk_tree_view_get_column(treeviewId, columnIndex);
		GTK.gtk_tree_view_column_set_reorderable(column, reorderable);
	}
	
	/**
	 * �����а���sort_column_id��������
	 * @param columnIndex
	 * @param sort_column_id
	 */
	public void setSortColumnId(int columnIndex,int sort_column_id)
	{
		int column = GTK.gtk_tree_view_get_column(treeviewId, columnIndex);
		GTK.gtk_tree_view_column_set_sort_column_id(column, sort_column_id);
	}

	//Ŀǰû�˵���
	public void removeAllColumn()
	{
		int n = GTK.gtk_tree_view_get_n_columns(treeviewId);
		for(int i =n-1;i>=0;i--)//ע��Ҫ�Ӻ���ǰɾ
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
		throw new RuntimeException("selectֵ�д�");
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
		//��Ϊ����������ScrollWindow������Ĭ�ϵĸ���addDoubleClickListener��ʵ���Ǽ���ScrollWindow��
		//����˫��TreeView�ͻ�û���¼���Ӧ�����Ҫ��Ϊ����treeviewId
		
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
