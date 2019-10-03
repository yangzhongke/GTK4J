package com.rupeng.ogtk;

import com.rupeng.gtk4j.GTK;

public class GtkApplication {

	private static GtkApplication instance = new GtkApplication();
	private GtkApplication()
	{
		
	}
	
	public static GtkApplication getInstance()
	{
		return instance;
	}
	
	public void init()
	{
		GTK.gtk_init();
	}
	
	public void start()
	{
		GTK.gtk_main();
	}
	
	public void quit()
	{
		GTK.gtk_main_quit();
	}
	
	
}
