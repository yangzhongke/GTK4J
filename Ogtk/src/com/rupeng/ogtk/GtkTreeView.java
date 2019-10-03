package com.rupeng.ogtk;

import com.rupeng.gtk4j.GTK;
import com.rupeng.gtk4j.IGCallBack;

/*
GtkTreeStore ts1 = new GtkTreeStore();
int itRoot = GTK.gtk_tree_iter_new();
ts1.append(itRoot, 0);
ts1.setValue(itRoot, "中国");

//故意写错成“JB”，然后说“我的节操呀，已随着青春逝去”
int itBJ = GTK.gtk_tree_iter_new();
ts1.append(itBJ, itRoot);
ts1.setValue(itBJ, "北京");


int itHB = GTK.gtk_tree_iter_new();
ts1.append(itHB, itRoot);
ts1.setValue(itHB, "河北");

final GtkTreeView tree1 = new GtkTreeView();
tree1.show();
tree1.setModel(ts1);
*/


/**
 * todo：还需要完善，getSelectionIndices还不行，
 * 主要是Java_com_rupeng_gtk4j_GTK_gtk_1tree_1view_1get_1selection_1indices的“gtk_tree_path_get_indices ( path ) ;
 * //这种方法只适合普通的ListView风格的，这种风格下一个TreePath只有一个选择项，buf的长度一直只有一个元素”
 * 处理了就好了
 * @author yzk
 *
 */
@Deprecated
public class GtkTreeView extends GtkContainer
{
	private int treeviewId;
	private GtkTreeStore model;

	public GtkTreeView()
	{
		super(GTK.gtk_scrolled_window_new());
		//添加滚动条
		treeviewId = GTK.gtk_tree_view_new();
		GTK.gtk_container_add(getId(), treeviewId);
		GTK.gtk_widget_show(treeviewId);
		
		appendColumn("aa", 0);
		
		GTK.gtk_tree_view_set_headers_visible(treeviewId, false);
	}
	
	public void appendColumn(String title, int columnIndex)
	{
		int cell_renderer = GTK.gtk_cell_renderer_text_new();

		int column = GTK.gtk_tree_view_column_new_with_attributes(title,
				cell_renderer, columnIndex);
		
		GTK.gtk_tree_view_append_column(treeviewId, column);
	}
	
	public void setModel(GtkTreeStore model)
	{
		GTK.gtk_tree_view_set_model(treeviewId, model.getId());
		this.model = model;
	}

	public GtkTreeStore getModel()
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
