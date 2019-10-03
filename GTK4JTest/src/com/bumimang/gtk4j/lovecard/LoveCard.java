package com.bumimang.gtk4j.lovecard;

import com.rupeng.gtk4j.GTK;
import com.rupeng.gtk4j.IGCallBack;
import com.rupeng.gtk4j.IGSourceFunc;

public class LoveCard
{

	static boolean leaving = true;
	static String words = "谢谢你给我的所有关怀和理解，尤其是那些孤立无助的时刻你温暖的陪伴，它让我始终强大坚定！\n"
			+ "我要让你成为世界上最幸福的女人，不是因为这一生积累的名望，地位与财富，而仅仅因为我默默恒久的爱！\n"
			+ "今天，说出这些话语是那么艰难却又那么快乐，这都是我这么长时间以来埋在心底的话语！\n"
			+ "这一切只是因为下面我要唱给你听的这首歌的名字：我如此爱你！";
	static int txtMSg;

	public static void main(String[] args)
	{
		GTK.gtk_init();
		int mainWin = GTK.gtk_window_new(GTK.GTK_WINDOW_TOPLEVEL);
		GTK.gtk_window_set_title(mainWin, "爱的明信片");
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

		// 当然前提是你要有一个女朋友

		// 充满了深深的恶意，没事“单身狗不哭，站起来撸”

		txtMSg = GTK.gtk_text_view_new();
		GTK.gtk_widget_set_size_request(txtMSg, 800, 200);
		GTK.gtk_widget_show(txtMSg);
		GTK.gtk_box_pack_end(vbox, txtMSg, false, false, 0);

		// 改成打字效果

		GTK.g_timeout_add(100, new IGSourceFunc() {

			@Override
			public boolean execute(Object object)
			{
				int buffer = GTK.gtk_text_view_get_buffer(txtMSg);
				String oldText = GTK.gtk_text_buffer_get_text(buffer);
				if(oldText.length()>=words.length())
				{
					return false;//停止
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
				.mci_open_from_resource("com/bumimang/gtk4j/lovecard/火风 - 老婆老婆我爱你.mp3");
		GTK.mci_play(bgMusic, true);
		*/
		
		GTK.gtk_main();
	}

}
