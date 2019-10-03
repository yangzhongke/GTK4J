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
		appendMessageLine("����"+now+"˵��");
		appendMessageLine(value);
		
		appendMessageLine("С����"+now+"˵��");
		if(value.contains("���"))
		{
			appendMessageLine("�����ã�");
		}
		else if(value.contains("��")&&value.contains("����"))
		{
			appendMessageLine("�ҽ�С��");
		}
		else if(value.contains("�е�")&&value.contains("Ů��"))
		{
			appendMessageLine("�˼���һ��Ů��ֽ����");
		}
		else if(value.contains("��")||value.contains("����")||value.contains("��")||value.contains("fuck"))
		{
			appendMessageLine("��������ʲô����ó�ѽ��������������");
		}
		else if(value.contains("��"))
		{
			if(value.contains("������"))
			{
				int mci = GTK.mci_open_from_resource("com/bumimang/gtk4j/xiaobing/�ҵĸ�����.mp3");
				GTK.mci_play(mci,false);				
				appendMessageLine("�õģ�����������������Ժ���׸���ң�ϣ������ϲ����");
			}
			else if(value.contains("ƻ��"))
			{
				int mci = GTK.mci_open_from_resource("com/bumimang/gtk4j/xiaobing/�����ֵ�-Сƻ��.mp3");
				GTK.mci_play(mci,false);				
				appendMessageLine("�����");
			}
			else
			{
				appendMessageLine("���ᳪ");
			}
		}
		else if(value.startsWith("����ʶ")&&value.endsWith("��"))
		{
			String[] words ={"ΰ��","���","˧��","ή","�ߴ���"};
			
			String name = value.substring(3,value.length()-1);
			appendMessageLine(name+"�Ǹ���"+words[new Random().nextInt(words.length)]+"���ˣ�");
		}
		else
		{
			appendMessageLine("�Ǻ�");
		}
	}

}
