package com.bumimang.gtk4j.notepad;

import com.rupeng.gtk4j.GTK;
import com.rupeng.gtk4j.IGCallBack;

public class MyNotepad
{
	static int editorTV;
	static int mainWindow ;

	
	public static void main(String[] args)
	{
		GTK.gtk_init();
		
		mainWindow = GTK.gtk_window_new(GTK.GTK_WINDOW_TOPLEVEL);
		GTK.gtk_window_set_title(mainWindow, "Notepad");
		//GTK.gtk_window_set_default_size(mainWindow, 500, 500);
		GTK.gtk_window_maximize(mainWindow);
		
		GTK.g_signal_connect(mainWindow, "destroy", new IGCallBack() {
			
			@Override
			public void execute(int instance,  int eventData, Object object)
			{
				GTK.gtk_main_quit();				
			}
		}, null);
				
		GTK.gtk_widget_show(mainWindow);
		
		int mainVBox = GTK.gtk_box_new(GTK.GTK_ORIENTATION_VERTICAL, 0);
		GTK.gtk_widget_show(mainVBox);
		GTK.gtk_container_add(mainWindow, mainVBox);
		
		//Main Menubar
		int mainMenuBar = GTK.gtk_menu_bar_new();
		GTK.gtk_box_pack_start(mainVBox, mainMenuBar, false, true, 0);
		GTK.gtk_widget_show(mainMenuBar);
		
		//File Menu
		int filemenu = GTK.gtk_menu_new();
		int filemi = GTK.gtk_menu_item_new_with_label("File");
		GTK.gtk_widget_show(filemi);
		GTK.gtk_menu_item_set_submenu(filemi, filemenu);
		GTK.gtk_menu_shell_append(mainMenuBar, filemi);
		
		int newMI = GTK.gtk_menu_item_new_with_label("New");
		GTK.gtk_widget_show(newMI);
		GTK.gtk_menu_shell_append(filemenu, newMI);
		
		int openMI = GTK.gtk_menu_item_new_with_label("Open");
		GTK.gtk_widget_show(openMI);
		GTK.gtk_menu_shell_append(filemenu, openMI);
		GTK.g_signal_connect(openMI, "activate", new IGCallBack() {
			
			@Override
			public void execute(int instance,  int eventData, Object object)
			{
				doOpen();				
			}
		}, null);
		
		int savemi = GTK.gtk_menu_item_new_with_label("Save");
		GTK.gtk_widget_show(savemi);
		GTK.gtk_menu_shell_append(filemenu, savemi);
		
		int saveasmi = GTK.gtk_menu_item_new_with_label("Save as");
		GTK.gtk_widget_show(saveasmi);
		GTK.gtk_menu_shell_append(filemenu, saveasmi);
		
		int quitmi = GTK.gtk_menu_item_new_with_label("quit");
		GTK.gtk_widget_show(quitmi);
		GTK.gtk_menu_shell_append(filemenu, quitmi);
		GTK.g_signal_connect(quitmi, "activate", new IGCallBack() {			
			@Override
			public void execute(int instance,  int eventData, Object object)
			{
				GTK.gtk_main_quit();				
			}
		}, null);
		
		int editmenu = GTK.gtk_menu_new();
		int editmi = GTK.gtk_menu_item_new_with_label("Edit");
		GTK.gtk_widget_show(editmi);
		GTK.gtk_menu_item_set_submenu(editmi,editmenu);
		GTK.gtk_menu_shell_append(mainMenuBar, editmi);
		
		int copymi = GTK.gtk_menu_item_new_with_label("Copy");
		GTK.gtk_widget_show(copymi);
		GTK.gtk_menu_shell_append(editmenu, copymi);
		
		int pastemi = GTK.gtk_menu_item_new_with_label("Paste");
		GTK.gtk_widget_show(pastemi);
		GTK.gtk_menu_shell_append(editmenu, pastemi);
		
		//scrollbar of textview
		int editorScroll = GTK.gtk_scrolled_window_new();
		GTK.gtk_box_pack_end(mainVBox, editorScroll, true, true, 0);
		GTK.gtk_widget_show(editorScroll);
				
		editorTV = GTK.gtk_text_view_new();
		GTK.gtk_widget_show(editorTV);
		GTK.gtk_container_add(editorScroll, editorTV);		
		
		
		
		GTK.gtk_main();
	}
	
	static void doOpen()
	{
		int fc = GTK.gtk_file_chooser_dialog_new("Open File", mainWindow, GTK.GTK_FILE_CHOOSER_ACTION_OPEN, "´ò¿ª");
		
		int filterTxt = GTK.gtk_file_filter_new();
		GTK.gtk_file_filter_set_name(filterTxt, "Text File");
		GTK.gtk_file_filter_add_pattern(filterTxt, "*.txt");
		GTK.gtk_file_filter_add_pattern(filterTxt, "*.htm");
		GTK.gtk_file_chooser_add_filter(fc, filterTxt);
		
		int filterAll = GTK.gtk_file_filter_new();
		GTK.gtk_file_filter_set_name(filterAll, "All files");
		GTK.gtk_file_filter_add_pattern(filterAll, "*.*");
		GTK.gtk_file_chooser_add_filter(fc, filterAll);
		
		if(GTK.gtk_dialog_run(fc)==GTK.GTK_RESPONSE_OK)
		{
			String filename = GTK.gtk_file_chooser_get_filename(fc);
			int buffer = GTK.gtk_text_view_get_buffer(editorTV);
			GTK.gtk_text_buffer_set_text(buffer, filename);
			
			GTK.gtk_widget_destroy(fc);
		}
		else
		{
			GTK.gtk_widget_destroy(fc);
		}
	}

}
