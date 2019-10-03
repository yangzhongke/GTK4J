package com.bumimang.gtk4j.test1;
import java.util.ArrayList;

import com.rupeng.gtk4j.GTK;
import com.rupeng.gtk4j.IGCallBack;
import com.rupeng.gtk4j.IGSourceFunc;
import com.rupeng.ogtk.GtkContainer;
import com.rupeng.ogtk.GtkWidget;

public class Test1 extends GTK
{

	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		gtk_init();

		final int window;
		window = gtk_window_new(GTK_WINDOW_TOPLEVEL);
		gtk_window_set_title(window, "我的程序");
		gtk_window_set_default_size(window, 500, 500);
		g_signal_connect(window, "destroy",new IGCallBack() {

			@Override
			public void execute(int widget, int eventData, Object object)
			{
				GTK.gtk_main_quit();
			}
		}, window);

		
		int fixed = gtk_fixed_new();
		gtk_widget_show(fixed);
		gtk_container_add(window, fixed);
		

		/*
		 int vbox = gtk_box_new(GTK_ORIENTATION_VERTICAL,1);
		 gtk_widget_show(vbox);
		 gtk_container_add(window,vbox);
		 
		 int btn1 = gtk_button_new_with_label("btn1"); 
		 int btn2 = gtk_button_new_with_label("btn2"); 
		 int btn3 =  gtk_button_new_with_label("btn3");
		 gtk_widget_show(btn1);
		 gtk_widget_show(btn2);
		 gtk_widget_show(btn3);
		 
		 gtk_box_pack_start(vbox, btn1, true, true, 0);//最后三个参数中分别是expand,fill和padding。值得注意的是如果fill对应的参数是FALSE,则按钮就不会充满整个vbox构件。
		 gtk_box_pack_start(vbox, btn2, true, true, 0);
		 gtk_box_pack_start(vbox, btn3, true, true, 0);
		 */

		/*
		int grid = gtk_grid_new(); 
		gtk_grid_set_column_homogeneous(grid, true);
		gtk_grid_set_row_homogeneous(grid, true);		
		
		gtk_container_add(window,grid);
		gtk_grid_set_row_spacing(grid,2);//行列间距
		gtk_grid_set_column_spacing(grid,2);
		gtk_widget_show(grid);

		int btn1 = gtk_button_new_with_label("btn1");
		gtk_grid_attach(grid,btn1,0,0,1,1);
		gtk_widget_show(btn1);

		int btn2 = gtk_button_new_with_label("btn2");
		gtk_grid_attach(grid,btn2,1,2,2,2);//最后四个参数意思分别是：左侧单元格序号、右侧单元格序号、上侧单元格序号、底部单元格序号
		gtk_widget_show(btn2);
		*/

		/*
		int checkbox = gtk_check_button_new_with_label("VIP");
		gtk_widget_show(checkbox);
		gtk_toggle_button_set_active(checkbox,true); 
		gtk_fixed_put(fixed,checkbox, 20, 20);
		*/

		/*
		final int cmbBox = gtk_combo_box_text_new();
		gtk_combo_box_text_append_text(cmbBox, "Ubuntu");
		gtk_combo_box_text_append_text(cmbBox, "Mandriva");
		gtk_combo_box_text_append_text(cmbBox, "Fedora"); 
		gtk_widget_show(cmbBox);
		gtk_fixed_put(fixed,cmbBox, 20, 20);

		g_signal_connect(cmbBox, "changed", new IGCallBack() {			
			@Override
			public void doAction(int instance,  Object object)
			{
				String s = gtk_combo_box_text_get_active_text(instance);	
				System.out.println(s);
			}
		},
		null);
		 */
		/*
		final int txt1 = gtk_entry_new(); gtk_entry_set_max_length(txt1, 6);
		gtk_entry_set_text(txt1, "你好"); gtk_fixed_put(fixed,txt1,20,20);
		gtk_widget_show(txt1);
		g_signal_connect(txt1, "changed", new IGCallBack() {
			
			@Override
			public void doAction(int instance,  Object object)
			{
				System.out.println(gtk_entry_get_text(instance));				
			}
		}, null);
		*/
		
		/*
		int img1 = gtk_image_new_from_resource("com/bumimang/gtk4j/test/csdn.png"); 
		int img2 = gtk_image_new_from_stock(GTK_STOCK_MEDIA_PLAY,GTK_ICON_SIZE_LARGE_TOOLBAR); 
		gtk_widget_show(img1);
		gtk_widget_show(img2);
		gtk_fixed_put(fixed,img1,20,20);
		gtk_fixed_put(fixed,img2,200,200);
		*/

		/*
		 * //带图片的按钮 int btn = gtk_button_new(); int hbox = gtk_hbox_new(true,1);
		 * int icon =
		 * gtk_image_new_from_stock(GTK_STOCK_OPEN,GTK_ICON_SIZE_MENU); int
		 * label = gtk_label_new("保存");
		 * gtk_box_pack_start(hbox,icon,true,true,0);
		 * gtk_box_pack_start(hbox,label,true,true,0);
		 * gtk_container_add(btn,hbox); gtk_fixed_put(fixed,btn,50,20);
		 */
		
		/*
		  int tb1 = gtk_toggle_button_new_with_label("运行");
		  gtk_fixed_put(fixed,tb1,50,20);
		  gtk_widget_show(tb1);
		*/
		
		/*
		int vbox = gtk_box_new(GTK_ORIENTATION_VERTICAL,1);
		gtk_widget_show(vbox);
		gtk_fixed_put(fixed,vbox,0,0);

		int radio1 =
		gtk_radio_button_new_with_label(0,"ms");//创建一个新的组，并且创建一个radio
		//gtk_radio_button_new_with_label_from_widget在radio1的组中创建一个radio 
		int radio2 = gtk_radio_button_new_with_label_from_widget(radio1,"oracle");
		int radio3 = gtk_radio_button_new_with_label_from_widget(radio1,"apple");
		gtk_widget_show(radio1);
		gtk_widget_show(radio2);
		gtk_widget_show(radio3);
		gtk_box_pack_start(vbox,radio1,true,true,0);
		gtk_box_pack_start(vbox,radio2,true,true,0);
		gtk_box_pack_start(vbox,radio3,true,true,0);
		*/
	
		/*		
		int fc1 = gtk_file_chooser_dialog_new("请选择文件", window, GTK_FILE_CHOOSER_ACTION_OPEN, "打开");
		gtk_file_chooser_set_select_multiple(fc1, false);	
		
		int filter1 = gtk_file_filter_new();
		gtk_file_filter_set_name(filter1, "文本文件");
		gtk_file_filter_add_pattern(filter1, "*.txt");
		gtk_file_chooser_add_filter(fc1, filter1);
		
		int filter2 = gtk_file_filter_new();
		gtk_file_filter_set_name(filter2, "图片文件");
		gtk_file_filter_add_pattern(filter2, "*.png");
		gtk_file_filter_add_pattern(filter2, "*.jpeg");
		gtk_file_filter_add_pattern(filter2, "*.jpg");
		gtk_file_chooser_add_filter(fc1, filter2);
		
		if(gtk_dialog_run(fc1)==GTK_RESPONSE_OK)
		{
			String filename = gtk_file_chooser_get_filename(fc1);
			gtk_widget_destroy(fc1);
			System.out.println(filename);
		}
		else
		{
			gtk_widget_destroy(fc1);
		}
		*/
		
		/*
		int fc1 = gtk_file_chooser_dialog_new("另存为", window, GTK_FILE_CHOOSER_ACTION_SAVE, "保存");
		gtk_file_chooser_set_current_name(fc1, "noname.txt");
		if(gtk_dialog_run(fc1)==GTK_RESPONSE_OK)
		{
			String filename = gtk_file_chooser_get_filename(fc1);
			gtk_widget_destroy(fc1);
			System.out.println(filename);
		}
		else
		{
			gtk_widget_destroy(fc1);
		}*/
		
		/*
		int menu_bar = gtk_menu_bar_new();// 即创建一个工具栏,把file_item放在上面
		gtk_widget_show(menu_bar);

		int file_menu = gtk_menu_new();// open,save,quit都要放在file_menu中; 
		gtk_widget_show(file_menu);
		int file_item = gtk_menu_item_new_with_label("File");//file_item用于存放file_menu; // 设置子菜单,把file_menu设为file_item的子菜单
		gtk_widget_show(file_item);
		gtk_menu_item_set_submenu(file_item, file_menu); //把file_item放在menu_bar上面 
		gtk_menu_shell_append(menu_bar, file_item);

		int open_item = gtk_menu_item_new_with_label("Open"); 
		int save_item = gtk_menu_item_new_with_label("Save");
		int quit_item = gtk_menu_item_new_with_label("Quit");
		gtk_widget_show(open_item);
		gtk_widget_show(save_item);
		gtk_widget_show(quit_item);

		// 将open_item,save_item,quit_item放在file_menu上面
		gtk_menu_shell_append(file_menu, open_item);
		gtk_menu_shell_append(file_menu, save_item);
		gtk_menu_shell_append(file_menu, quit_item);

		g_signal_connect(quit_item, "activate", new IGCallBack() {
			
			@Override
			public void doAction(int instance,  Object object)
			{
				gtk_main_quit();
			}
		}, null);
		
		gtk_fixed_put(fixed, menu_bar, 50, 20);
		*/

		/*
		int btn1 = gtk_color_button_new();
		gtk_color_button_set_title(btn1, "1");
		gtk_color_button_set_rgba(btn1, 250, 0, 0);
		gtk_widget_show(btn1);
		gtk_fixed_put(fixed, btn1, 50,50);
		*/
		
		/*
		int sw1 = gtk_switch_new();
		gtk_widget_show(sw1);
		gtk_fixed_put(fixed, sw1, 50,50);*/
		
		/*		
		final int tv1 = gtk_text_view_new();
		int buffer = gtk_text_view_get_buffer(tv1);
		gtk_text_buffer_set_text(buffer, "北京天健，我要\n成功");
		gtk_text_view_set_buffer(tv1, buffer);
		
		gtk_widget_show(tv1);
		gtk_fixed_put(fixed, tv1, 50,50);
		
		String s = gtk_text_buffer_get_text(buffer);
		System.out.println(s);
		*/
		
		/*
		int mci1 = GTK.mci_open_from_resource("com/bumimang/gtk4j/notepad/枪声.mp3");
		//int mci1 = GTK.mci_open("H:\\KuGou\\test ha\\aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa fffffffffffffffffffffffffffffffff\\2222我爱 2222222222222222222222222222222222222222222222222222222222222222\\我的小宝 贝.mp3");
		GTK.mci_play(mci1, true);*/

		ArrayList<Person> list = new ArrayList<Person>();
		Person p1 = new Person();
		p1.Name = "杨中科rrrrrrrrrrrrr";
		p1.Age = 30;
		p1.Weight = 77;
		list.add(p1);

		Person p2 = new Person();
		p2.Name = "都敏俊";
		p2.Age = 500;
		p2.Weight = 3;
		list.add(p2);

		final int treeview = gtk_tree_view_new();
		gtk_widget_show(treeview);
		gtk_fixed_put(fixed, treeview, 0, 0);
		gtk_widget_set_size_request(treeview, 200, 100);
		int cell_renderer = gtk_cell_renderer_text_new();

		int columnName = gtk_tree_view_column_new_with_attributes("姓名",
				cell_renderer, 0);
		gtk_tree_view_append_column(treeview, columnName);

		int columnAge = gtk_tree_view_column_new_with_attributes("年龄",
				cell_renderer, 1);
		gtk_tree_view_append_column(treeview, columnAge);

		int columnWeight = gtk_tree_view_column_new_with_attributes("身高",
				cell_renderer, 2);
		gtk_tree_view_append_column(treeview, columnWeight);

		int store = gtk_list_store_new(3);
		int iter = gtk_tree_iter_new();
		for (Person p : list)
		{
			gtk_list_store_append(store, iter);
			gtk_list_store_set_value(store, iter, 0, p.Name);
			gtk_list_store_set_value(store, iter, 1, Integer.toString(p.Age));
			gtk_list_store_set_value(store, iter, 2, Double.toString(p.Weight));
		}

		gtk_tree_view_set_model(treeview, store);
		gtk_tree_iter_free(iter);
		
		int btn1 = gtk_button_new_with_label("test");
		gtk_fixed_put(fixed, btn1, 200, 200);
		gtk_widget_show(btn1);		
		gtk_widget_override_background_color(btn1,GTK_STATE_NORMAL, 0,100,100);
		
		/*
		int selection = gtk_tree_view_get_selection(treeview);
		//changed信号要加到selection上，不要加到treeview上
		g_signal_connect(selection, "changed", false,new IGCallBack() {
			
			@Override
			public void doAction(int widget, Object object)
			{
				int[] indices = gtk_tree_view_get_selection_indices(treeview);				
				for(int i :indices)
				{
					System.out.print("选中改变");
					System.out.print(i+",");
					System.out.println();
				}
			}
		}, null);	
		*/
		

		gtk_widget_show(treeview);
		
		final int btn2 = gtk_button_new_with_label("aaa");
		gtk_widget_show(btn2);
		gtk_fixed_put(fixed, btn2, 300,300);
		
		g_signal_connect(btn2, "clicked", new IGCallBack() {
			
			@Override
			public void execute(int instance,  int eventData, Object object)
			{
				int dialog = gtk_dialog_new();
				gtk_window_set_modal(dialog,true);
				//gtk_window_set_resizable(dialog, false);
				gtk_window_set_title(dialog, "设置");
				gtk_dialog_add_button(dialog, "确定", GTK_RESPONSE_OK);
				gtk_dialog_add_button(dialog, "取消", GTK_RESPONSE_CANCEL);
				
				int area = gtk_dialog_get_content_area(dialog);
				int vbox2 = gtk_box_new(GTK_ORIENTATION_VERTICAL, 5);
				gtk_widget_show(vbox2);
				gtk_container_add(area, vbox2);
				
				int entry1 = gtk_entry_new();
				gtk_widget_show(entry1);
				gtk_box_pack_start(vbox2, entry1, true, true, 0);				
				
				gtk_widget_show(dialog);
				gtk_dialog_run(dialog);
			}
		}, null);
		
		//mci1 = GTK.mci_open("H:\\KuGou\\庞龙、李永波 - 兄弟干杯.mp3");
		mci1 = GTK.mci_open_from_resource("com/bumimang/gtk4j/lovecard/火风 - 老婆老婆我爱你.mp3");
		GTK.mci_play(mci1, true);
		
		final int progress = GTK.gtk_scale_new_with_range(GTK_ORIENTATION_HORIZONTAL, 0, 1, 0.0001);
		GTK.gtk_scale_set_draw_value(progress, false);
		GTK.gtk_fixed_put(fixed, progress,0,200);
		GTK.gtk_widget_set_size_request(progress, 200, 20);
		GTK.gtk_widget_show(progress);
		
		GTK.g_signal_connect(progress, "value-changed", new IGCallBack() {
			
			@Override
			public void execute(int instance,  int eventData, Object object)
			{
				double value = GTK.gtk_range_get_value(instance);
				long length = mci_get_status(mci1,GTK.MCI_STATUS_LENGTH);
				//防止卡死
				if(Math.abs(value*length-GTK.mci_get_status(mci1, GTK.MCI_STATUS_POSITION))>100)
				{
					GTK.mci_seek(mci1, (long)(length*value));
					GTK.mci_play(mci1, false);
				}
			}
		}, null);
		
		GTK.g_timeout_add(200, new IGSourceFunc() {			
			@Override
			public boolean execute(Object object)
			{
				//GTK.gtk_window_set_title(window, Long.toString(System.currentTimeMillis()));
				
				long length = GTK.mci_get_status(mci1, GTK.MCI_STATUS_LENGTH);
				long position = GTK.mci_get_status(mci1, GTK.MCI_STATUS_POSITION);
				double value = position*1.0/length;
				GTK.gtk_range_set_value(progress, value);
				
				return true;//返回false代表停止后面的执行，返回true代表下次继续执行
			}
		}, null);
		
		gtk_widget_show(window);

		/*
		int screen = GTK.gdk_screen_get_default();
		int nMonitor = GTK.gdk_screen_get_n_monitors(screen);
		System.out.println("显示器数量:"+nMonitor);
		int primaryNum = GTK.gdk_screen_get_primary_monitor(screen);
		System.out.println("主显示器编号："+primaryNum);
		int[] rect = GTK.gdk_screen_get_monitor_workarea(screen, primaryNum);
		System.out.println("工作区矩形："+rect[0]+","+rect[1]+","+rect[2]+","+rect[3]);
		*/
		
		gtk_main();
	}

	static int mci1;
}

class Person
{
	public String Name;
	public int Age;
	public double Weight;
}