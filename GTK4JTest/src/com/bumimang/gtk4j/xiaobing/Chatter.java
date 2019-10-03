package com.bumimang.gtk4j.xiaobing;

import java.text.DateFormat;
import java.util.Date;
import java.util.Random;

import com.rupeng.gtk4j.GTK;
import com.rupeng.gtk4j.IGCallBack;

public class Chatter
{

	static int txtView;
	static int entrySend;
	
	public static void main(String[] args)
	{
		GTK.gtk_init();
		
		int win = GTK.gtk_window_new(GTK.GTK_WINDOW_TOPLEVEL);
		GTK.g_signal_connect(win, "destroy", new IGCallBack() {
			
			@Override
			public void execute(int instance, int eventData, Object object)
			{
				GTK.gtk_main_quit();				
			}
		}, null);
		
		int box = GTK.gtk_box_new(GTK.GTK_ORIENTATION_VERTICAL, 0);
		GTK.gtk_container_add(win, box);
		GTK.gtk_widget_show(box);
		
		int scrollView = GTK.gtk_scrolled_window_new();
		GTK.gtk_box_pack_start(box, scrollView, true, true, 0);
		GTK.gtk_widget_show(scrollView);	
		
		txtView = GTK.gtk_text_view_new();
		GTK.gtk_text_view_set_editable(txtView, false);
		GTK.gtk_container_add(scrollView, txtView);
		GTK.gtk_widget_show(txtView);
		
		entrySend = GTK.gtk_entry_new();
		GTK.g_signal_connect(entrySend, "key-press-event", new IGCallBack() {
			
			@Override
			public void execute(int instance, int eventData, Object object)
			{
				if(GTK.gdk_event_get_keycode(eventData)==13)
				{
					entrySendOnClick();
				}
			}
		}, null);
		GTK.gtk_box_pack_end(box, entrySend, false, false, 0);
		GTK.gtk_widget_show(entrySend);
		GTK.gtk_widget_grab_focus(entrySend);
		
		GTK.gtk_window_maximize(win);
		GTK.gtk_widget_show(win);
		
		GTK.gtk_main();
	}
	
	static void appendMessageLine(String text)
	{
		int buffer = GTK.gtk_text_view_get_buffer(txtView);	
		int iter = GTK.gtk_text_iter_new();
		GTK.gtk_text_buffer_get_end_iter(buffer, iter);
		GTK.gtk_text_buffer_insert(buffer, iter, text+"\n");
	}

	protected static void entrySendOnClick()
	{
		String value = GTK.gtk_entry_get_text(entrySend);
		GTK.gtk_entry_set_text(entrySend, "");
		String now = DateFormat.getDateTimeInstance().format(new Date(System.currentTimeMillis()));
		appendMessageLine("我在"+now+"说：");
		appendMessageLine(value);
		
		appendMessageLine("小冰在"+now+"说：");
		if(value.contains("你好"))
		{
			appendMessageLine("好你妹！");
		}
		else if(value.contains("你")&&value.contains("名字"))
		{
			appendMessageLine("我叫小冰");
		}
		else if(value.contains("男的")&&value.contains("女的"))
		{
			appendMessageLine("人家是一个女孩纸啦啦");
		}
		else if(value.contains("操")||value.contains("你妈")||value.contains("逼")||value.contains("fuck"))
		{
			appendMessageLine("你今天吃了什么，嘴好臭呀，厕所出来嚼益达！");
		}
		else if(value.contains("唱"))
		{
			if(value.contains("歌声里"))
			{
				int mci = GTK.mci_open_from_resource("com/bumimang/gtk4j/xiaobing/我的歌声里.mp3");
				GTK.mci_play(mci,false);				
				appendMessageLine("好的，《你存在我婶婶的脑海里》献给大家，希望你们喜欢！");
			}
			else if(value.contains("苹果"))
			{
				int mci = GTK.mci_open_from_resource("com/bumimang/gtk4j/xiaobing/筷子兄弟-小苹果.mp3");
				GTK.mci_play(mci,false);				
				appendMessageLine("火火火火！");
			}
			else
			{
				appendMessageLine("不会唱");
			}
		}
		else if(value.startsWith("你认识")&&value.endsWith("吗"))
		{
			String[] words ={"伟大","猥亵","帅气","萎","高大上"};
			
			String name = value.substring(3,value.length()-1);
			appendMessageLine(name+"是个很"+words[new Random().nextInt(words.length)]+"的人！");
		}
		else
		{
			appendMessageLine("呵呵");
		}
	}

}
