package com.bumimang.gtk4j.lovecard;

import com.rupeng.gtk4j.GTK;
import com.rupeng.gtk4j.IGCallBack;
import com.rupeng.gtk4j.IGSourceFunc;

public class LoveCard
{

	static boolean leaving = true;
	static String words = "лл����ҵ����йػ�����⣬��������Щ����������ʱ������ů����飬������ʼ��ǿ��ᶨ��\n"
			+ "��Ҫ�����Ϊ���������Ҹ���Ů�ˣ�������Ϊ��һ�����۵���������λ��Ƹ�����������Ϊ��ĬĬ��õİ���\n"
			+ "���죬˵����Щ��������ô����ȴ����ô���֣��ⶼ������ô��ʱ�����������ĵ׵Ļ��\n"
			+ "��һ��ֻ����Ϊ������Ҫ�������������׸�����֣�����˰��㣡";
	static int txtMSg;

	public static void main(String[] args)
	{
		GTK.gtk_init();
		int mainWin = GTK.gtk_window_new(GTK.GTK_WINDOW_TOPLEVEL);
		GTK.gtk_window_set_title(mainWin, "��������Ƭ");
		GTK.gtk_window_set_default_size(mainWin, 500, 500);
		GTK.gtk_widget_show(mainWin);
		GTK.g_signal_connect(mainWin, "destroy", new IGCallBack() {

			@Override
			public void execute(int instance, int eventData, Object object)
			{
				GTK.gtk_main_quit();
			}
		}, null);

		int vbox = GTK.gtk_box_new(GTK.GTK_ORIENTATION_VERTICAL, 0);
		GTK.gtk_container_add(mainWin, vbox);
		GTK.gtk_widget_show(vbox);

		int img = GTK
				.gtk_image_new_from_resource("com/bumimang/gtk4j/lovecard/cjk.jpg");
		GTK.gtk_box_pack_start(vbox, img, true, true, 0);
		GTK.gtk_widget_show(img);

		// ��Ȼǰ������Ҫ��һ��Ů����

		// ����������Ķ��⣬û�¡��������ޣ�վ����ߣ��

		txtMSg = GTK.gtk_text_view_new();
		GTK.gtk_widget_set_size_request(txtMSg, 800, 200);
		GTK.gtk_widget_show(txtMSg);
		GTK.gtk_box_pack_end(vbox, txtMSg, false, false, 0);

		// �ĳɴ���Ч��

		GTK.g_timeout_add(100, new IGSourceFunc() {

			@Override
			public boolean execute(Object object)
			{
				int buffer = GTK.gtk_text_view_get_buffer(txtMSg);
				String oldText = GTK.gtk_text_buffer_get_text(buffer);
				if(oldText.length()>=words.length())
				{
					return false;//ֹͣ
				}
				char newChar = words.charAt(oldText.length());
				
				
				int endIter = GTK.gtk_text_iter_new();
				GTK.gtk_text_buffer_get_end_iter(buffer, endIter);
				GTK.gtk_text_buffer_insert(buffer, endIter, Character.toString(newChar));
				
				return true;
			}
		}, null);

		/*
		int bgMusic = GTK
				.mci_open_from_resource("com/bumimang/gtk4j/lovecard/��� - ���������Ұ���.mp3");
		GTK.mci_play(bgMusic, true);
		*/
		
		GTK.gtk_main();
	}

}
