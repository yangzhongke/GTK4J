package com.rupeng.ogtk;

import com.rupeng.gtk4j.GTK;
import com.rupeng.gtk4j.IGCallBack;

/*
GtkTreeStore ts1 = new GtkTreeStore();
int itRoot = GTK.gtk_tree_iter_new();
ts1.append(itRoot, 0);
ts1.setValue(itRoot, "�й�");

//����д��ɡ�JB����Ȼ��˵���ҵĽڲ�ѽ���������ഺ��ȥ��
int itBJ = GTK.gtk_tree_iter_new();
ts1.append(itBJ, itRoot);
ts1.setValue(itBJ, "����");


int itHB = GTK.gtk_tree_iter_new();
ts1.append(itHB, itRoot);
ts1.setValue(itHB, "�ӱ�");

final GtkTreeView tree1 = new GtkTreeView();
tree1.show();
tree1.setModel(ts1);
*/


/**
 * todo������Ҫ���ƣ�getSelectionIndices�����У�
 * ��Ҫ��Java_com_rupeng_gtk4j_GTK_gtk_1tree_1view_1get_1selection_1indices�ġ�gtk_tree_path_get_indices ( path ) ;
 * //���ַ���ֻ�ʺ���ͨ��ListView���ģ����ַ����һ��TreePathֻ��һ��ѡ���buf�ĳ���һֱֻ��һ��Ԫ�ء�
 * �����˾ͺ���
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
		//��ӹ�����
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
