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
		GTK.gtk_window_set_title(win, "安装协议");
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
		
		String msg = "请您在使用本软件前仔细阅读如下条款。包括免除或者限制作者责任的免责条款及对用户的权利限制。您的安装使用行为将视为对本《协议》的接受，并同意接受本《协议》各项条款的约束。\n"+
		"  本《用户许可协议》（以下称《协议》）是您（个人或单一机构团体）与上述 XXX软件（以下称“软件”或“软件产品”）版权所有 XX软件 之间的法律协议。在您使用本软件产品之前,请务必阅读此《协议》，任何与《协议》有关的软件、电子文档等都应是按本协议的条款而授予您的，同时本《协议》亦适用于任何有关本软件产品的后期发行和升级。您一旦安装、复制、下裁、访问或以其它方式使用本软件产品，即表示您同意接受本《协议》各项条款的约束。如果您拒绝接受本《协议》条款，请您停止下载、安装或使用本软件及其相关服务。 \n"+
				"  您不得对本软件产品进行反向工程、反向编译和反向汇编，不得删除本软件及其他副本上一切关于版权的信息，不得制作和提供该软件的注册机及破解程序。除非适用法律明文允许上述活动，否则您必须遵守此协议限制。";
		
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
		
		int cbAgree = GTK.gtk_check_button_new_with_label("我已阅读并同意以上协议");
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
		
		btnInstall = GTK.gtk_button_new_with_label("安装");
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
		
		

		int btnCancel = GTK.gtk_button_new_with_label("取消");
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
