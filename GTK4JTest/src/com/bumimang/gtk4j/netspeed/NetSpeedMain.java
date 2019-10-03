package com.bumimang.gtk4j.netspeed;

import com.rupeng.gtk4j.GTK;
import com.rupeng.gtk4j.IGCallBack;

public class NetSpeedMain
{

	public static void main(String[] args)
	{
		GTK.gtk_init();
		int window = GTK.gtk_window_new(GTK.GTK_WINDOW_TOPLEVEL);
		GTK.gtk_window_set_title(window, "Õ¯ÀŸ≤‚ ‘");
		GTK.g_signal_connect(window, "destroy", new IGCallBack() {
			
			@Override
			public void execute(int instance, int eventData, Object object)
			{
				GTK.gtk_main_quit();				
			}
		}, null);
		
		int box = GTK.gtk_box_new(GTK.GTK_ORIENTATION_VERTICAL, 0);
		GTK.gtk_container_add(window, box);
		GTK.gtk_widget_show(box);
		
		
		
		GTK.gtk_widget_show(window);
		GTK.gtk_main();
	}

}
