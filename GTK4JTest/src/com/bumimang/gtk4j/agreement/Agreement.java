package com.bumimang.gtk4j.agreement;

import java.io.IOException;

import com.rupeng.gtk4j.GTK;
import com.rupeng.gtk4j.IGCallBack;

public class Agreement {

	static int win;
	static int btnInstall;
	
	public static void main(String[] args) throws IOException {
		GTK.gtk_init();
		win = GTK.gtk_window_new(GTK.GTK_WINDOW_TOPLEVEL);
		GTK.gtk_window_set_title(win, "Installing");
		GTK.gtk_window_set_position(win, GTK.GTK_WIN_POS_CENTER_ALWAYS);
		
		GTK.g_signal_connect(win, "destroy", new IGCallBack() {
			
			@Override
			public void execute(int instance, int eventData, Object object) {
				GTK.gtk_main_quit();				
			}
		}, null);
		
		/*
		InputStream inStream = ClassLoader.getSystemResourceAsStream("com/bumimang/gtk4j/agreement/eula.txt");
		if (inStream == null)
		{
			throw new RuntimeException("找不到eula.txt");
		}*/
		
		String msg = "1. Account Controls\r\n" + 
				"Users. Subject to these Terms, you retain ultimate administrative control over your User Account and the Content within it.\r\n" + 
				"\r\n" + 
				"Organizations. The \"owner\" of an Organization that was created under these Terms has ultimate administrative control over that Organization and the Content within it. Within the Service, an owner can manage User access to the Organization’s data and projects. An Organization may have multiple owners, but there must be at least one User Account designated as an owner of an Organization. If you are the owner of an Organization under these Terms, we consider you responsible for the actions that are performed on or through that Organization.\r\n" + 
				"\r\n" + 
				"2. Required Information\r\n" + 
				"You must provide a valid email address in order to complete the signup process. Any other information requested, such as your real name, is optional, unless you are accepting these terms on behalf of a legal entity (in which case we need more information about the legal entity) or if you opt for a paid Account, in which case additional information will be necessary for billing purposes.\r\n" + 
				"\r\n3. Account Requirements\r\n" + 
				"We have a few simple rules for User Accounts on GitHub's Service.\r\n" + 
				"\r\n" + 
				"You must be a human to create an Account. Accounts registered by \"bots\" or other automated methods are not permitted. We do permit machine accounts:\r\n" + 
				"A machine account is an Account set up by an individual human who accepts the Terms on behalf of the Account, provides a valid email address, and is responsible for its actions. A machine account is used exclusively for performing automated tasks. Multiple users may direct the actions of a machine account, but the owner of the Account is ultimately responsible for the machine's actions. You may maintain no more than one free machine account in addition to your free User Account.\r\n" + 
				"One person or legal entity may maintain no more than one free Account (if you choose to control a machine account as well, that's fine, but it can only be used for running a machine).";
		int vbox = GTK.gtk_box_new(GTK.GTK_ORIENTATION_VERTICAL, 0);
		GTK.gtk_container_add(win, vbox);
		GTK.gtk_widget_show(vbox);
	
		int scrollMsg = GTK.gtk_scrolled_window_new();
		GTK.gtk_widget_show(scrollMsg);
		GTK.gtk_box_pack_start(vbox, scrollMsg, true, true, 0);
		
		
		int txtMsg = GTK.gtk_text_view_new();
		GTK.gtk_text_view_set_wrap_mode(txtMsg,GTK.GTK_WRAP_WORD_CHAR);		
		GTK.gtk_container_add(scrollMsg, txtMsg);
		int buff = GTK.gtk_text_view_get_buffer(txtMsg);
		GTK.gtk_text_buffer_set_text(buff, msg);
		GTK.gtk_widget_show(txtMsg);
		
		int cbAgree = GTK.gtk_check_button_new_with_label("Agree terms");
		GTK.gtk_box_pack_start(vbox, cbAgree, false, false, 0);
		GTK.gtk_widget_show(cbAgree);
		
		GTK.g_signal_connect(cbAgree, "clicked", new IGCallBack() {
			
			@Override
			public void execute(int instance,  int eventData, Object object) {
				boolean isAgree = GTK.gtk_toggle_button_get_active(instance);
				GTK.gtk_widget_set_sensitive(btnInstall, isAgree);
			}
		}, null);
		
		int boxBtns = GTK.gtk_box_new(GTK.GTK_ORIENTATION_HORIZONTAL, 0);
		GTK.gtk_box_pack_end(vbox, boxBtns, false, true, 0);
		GTK.gtk_widget_show(boxBtns);
		
		btnInstall = GTK.gtk_button_new_with_label("Install");
		GTK.gtk_box_pack_start(boxBtns, btnInstall, false,false, 0);
		GTK.gtk_widget_set_sensitive(btnInstall, false);
		GTK.gtk_widget_show(btnInstall);
		GTK.g_signal_connect(btnInstall, "clicked", new IGCallBack() {
			
			@Override
			public void execute(int instance,  int eventData, Object object) {
				int msgDlg = GTK.gtk_message_dialog_new(win, GTK.GTK_DIALOG_MODAL|GTK.GTK_DIALOG_DESTROY_WITH_PARENT,
						GTK.GTK_MESSAGE_INFO, GTK.GTK_BUTTONS_OK, "你被骗了，装不上！");
				GTK.gtk_dialog_run(msgDlg);
				GTK.gtk_widget_destroy(msgDlg);
			}
		}, null);
		
		

		int btnCancel = GTK.gtk_button_new_with_label("Cancel");
		GTK.gtk_box_pack_start(boxBtns, btnCancel, false,false, 0);
		GTK.gtk_widget_show(btnCancel);
		GTK.g_signal_connect(btnCancel, "clicked", new IGCallBack() {
			
			@Override
			public void execute(int instance,  int eventData, Object object) {
				GTK.gtk_main_quit();				
			}
		}, null);
		
		GTK.gtk_widget_set_size_request(scrollMsg, 500,500);
		GTK.gtk_widget_show(win);
		GTK.gtk_main();
	}

}
