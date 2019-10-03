package com.bumimang.gtk4j.ledclock;

import java.util.Calendar;

import com.rupeng.gtk4j.GTK;
import com.rupeng.gtk4j.IGCallBack;
import com.rupeng.gtk4j.IGSourceFunc;

public class LedClock
{
	static String resPrefix ="com/bumimang/gtk4j/ledclock/";
	static int imgHour1;	
	static int imgHour2;
	static int imgcolon1;
	
	static int imgMin1;
	static int imgMin2;			
	static int imgcolon2;
	
	static int imgSec1;
	static int imgSec2;

	public static void main(String[] args)
	{
		GTK.gtk_init();
		int window = GTK.gtk_window_new(GTK.GTK_WINDOW_TOPLEVEL);
		GTK.gtk_window_set_title(window, "数字时钟");
		GTK.gtk_widget_show(window);
		GTK.g_signal_connect(window, "destroy",new IGCallBack() {
			
			@Override
			public void execute(int instance,  int eventData, Object object)
			{
				GTK.gtk_main_quit();				
			}
		}, null);
		
		int hbox = GTK.gtk_box_new(GTK.GTK_ORIENTATION_HORIZONTAL, 0);
		GTK.gtk_container_add(window, hbox);
		GTK.gtk_widget_show(hbox);
		
		imgHour1 = GTK.gtk_image_new_from_resource(resPrefix+"8.png");	
		imgHour2 = GTK.gtk_image_new_from_resource(resPrefix+"8.png");
		imgcolon1 = GTK.gtk_image_new_from_resource(resPrefix+"colon.png");
		
		imgMin1 = GTK.gtk_image_new_from_resource(resPrefix+"8.png");
		imgMin2 = GTK.gtk_image_new_from_resource(resPrefix+"8.png");			
		imgcolon2 = GTK.gtk_image_new_from_resource(resPrefix+"colon.png");
		
		imgSec1 = GTK.gtk_image_new_from_resource(resPrefix+"8.png");
		imgSec2 = GTK.gtk_image_new_from_resource(resPrefix+"8.png");

		GTK.gtk_widget_show(imgHour1);
		GTK.gtk_widget_show(imgHour2);
		GTK.gtk_widget_show(imgcolon1);
		GTK.gtk_widget_show(imgMin1);
		GTK.gtk_widget_show(imgMin2);
		GTK.gtk_widget_show(imgcolon2);
		GTK.gtk_widget_show(imgSec1);
		GTK.gtk_widget_show(imgSec2);
		
		GTK.gtk_box_pack_start(hbox, imgHour1, false, false, 0);
		GTK.gtk_box_pack_start(hbox, imgHour2, false, false, 0);
		GTK.gtk_box_pack_start(hbox, imgcolon1, false, false, 0);
		GTK.gtk_box_pack_start(hbox, imgMin1, false, false, 0);
		GTK.gtk_box_pack_start(hbox, imgMin2, false, false, 0);
		GTK.gtk_box_pack_start(hbox, imgcolon2, false, false, 0);
		GTK.gtk_box_pack_start(hbox, imgSec1, false, false,0);
		GTK.gtk_box_pack_start(hbox, imgSec2, false, false,0);
		
		showTime();//立即显示一次
		GTK.g_timeout_add(1000, new IGSourceFunc() {			
			@Override
			public boolean execute(Object object)
			{
				showTime();				
				return true;
			}
		}, null);
		
		GTK.gtk_main();
	}

	private static void showTime() {
		Calendar calendar = Calendar.getInstance();
		int hour = calendar.get(Calendar.HOUR_OF_DAY);
		int min = calendar.get(Calendar.MINUTE);
		int sec = calendar.get(Calendar.SECOND);
		
		GTK.gtk_image_set_from_resource(imgHour1, resPrefix+(hour/10)+".png");//hour/10取十位
		GTK.gtk_image_set_from_resource(imgHour2, resPrefix+(hour%10)+".png");//hour%10取个位
		
		GTK.gtk_image_set_from_resource(imgcolon1, resPrefix+(sec%2==0?"colon":"empty")+".png");
		GTK.gtk_image_set_from_resource(imgcolon2, resPrefix+(sec%2==0?"colon":"empty")+".png");
		
		GTK.gtk_image_set_from_resource(imgMin1, resPrefix+(min/10)+".png");
		GTK.gtk_image_set_from_resource(imgMin2, resPrefix+(min%10)+".png");					
		
		GTK.gtk_image_set_from_resource(imgSec1, resPrefix+(sec/10)+".png");
		GTK.gtk_image_set_from_resource(imgSec2, resPrefix+(sec%10)+".png");
	}

}
