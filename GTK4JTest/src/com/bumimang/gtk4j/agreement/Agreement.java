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
		GTK.gtk_window_set_title(win, "��װЭ��");
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
			throw new RuntimeException("�Ҳ���eula.txt");
		}*/
		
		String msg = "������ʹ�ñ����ǰ��ϸ�Ķ������������������������������ε�����������û���Ȩ�����ơ����İ�װʹ����Ϊ����Ϊ�Ա���Э�顷�Ľ��ܣ���ͬ����ܱ���Э�顷���������Լ����\n"+
		"  �����û����Э�顷�����³ơ�Э�顷�����������˻�һ�������壩������ XXX��������³ơ�������������Ʒ������Ȩ���� XX��� ֮��ķ���Э�顣����ʹ�ñ������Ʒ֮ǰ,������Ķ��ˡ�Э�顷���κ��롶Э�顷�йص�����������ĵ��ȶ�Ӧ�ǰ���Э���������������ģ�ͬʱ����Э�顷���������κ��йر������Ʒ�ĺ��ڷ��к���������һ����װ�����ơ��²á����ʻ���������ʽʹ�ñ������Ʒ������ʾ��ͬ����ܱ���Э�顷���������Լ����������ܾ����ܱ���Э�顷�������ֹͣ���ء���װ��ʹ�ñ����������ط��� \n"+
				"  �����öԱ������Ʒ���з��򹤳̡��������ͷ����࣬����ɾ�������������������һ�й��ڰ�Ȩ����Ϣ�������������ṩ�������ע������ƽ���򡣳������÷�������������������������������ش�Э�����ơ�";
		
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
		
		int cbAgree = GTK.gtk_check_button_new_with_label("�����Ķ���ͬ������Э��");
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
		
		btnInstall = GTK.gtk_button_new_with_label("��װ");
		GTK.gtk_box_pack_start(boxBtns, btnInstall, false,false, 0);
		GTK.gtk_widget_set_sensitive(btnInstall, false);
		GTK.gtk_widget_show(btnInstall);
		GTK.g_signal_connect(btnInstall, "clicked", new IGCallBack() {
			
			@Override
			public void execute(int instance,  int eventData, Object object) {
				int msgDlg = GTK.gtk_message_dialog_new(win, GTK.GTK_DIALOG_MODAL|GTK.GTK_DIALOG_DESTROY_WITH_PARENT,
						GTK.GTK_MESSAGE_INFO, GTK.GTK_BUTTONS_OK, "�㱻ƭ�ˣ�װ���ϣ�");
				GTK.gtk_dialog_run(msgDlg);
				GTK.gtk_widget_destroy(msgDlg);
			}
		}, null);
		
		

		int btnCancel = GTK.gtk_button_new_with_label("ȡ��");
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
