package com.bumimang.gtk4j.BMICalc;

import com.rupeng.gtk4j.GTK;
import com.rupeng.gtk4j.IGCallBack;

public class Main
{
	static int entryWeight;
	static int entryHeight;
	static int labelResult;
	
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
		GTK.gtk_window_set_title(win, "����ָ������");
		
		int box = GTK.gtk_box_new(GTK.GTK_ORIENTATION_HORIZONTAL, 0);
		GTK.gtk_widget_show(box);
		GTK.gtk_container_add(win, box);		
		
		int labelWeight = GTK.gtk_label_new("����(kg)");
		GTK.gtk_widget_show(labelWeight);
		GTK.gtk_box_pack_start(box, labelWeight, false, false, 0);
		
		entryWeight = GTK.gtk_entry_new();
		GTK.gtk_widget_show(entryWeight);
		GTK.gtk_box_pack_start(box, entryWeight, false, false, 0);
		
		int labelHeight = GTK.gtk_label_new("���(m)");
		GTK.gtk_widget_show(labelHeight);
		GTK.gtk_box_pack_start(box, labelHeight, false, false, 0);
		
		entryHeight = GTK.gtk_entry_new();
		GTK.gtk_widget_show(entryHeight);
		GTK.gtk_box_pack_start(box, entryHeight, false, false, 0);
		
		int btnSubmit = GTK.gtk_button_new_with_label("��һ��");
		GTK.gtk_widget_show(btnSubmit);
		GTK.gtk_box_pack_start(box, btnSubmit, false, false, 0);
		
		GTK.g_signal_connect(btnSubmit, "clicked", new IGCallBack() {
			
			@Override
			public void execute(int instance, int eventData, Object object)
			{
				btnSubmiOnClick();			
			}
		}, null);
		
		labelResult = GTK.gtk_label_new("");
		GTK.gtk_widget_show(labelResult);
		GTK.gtk_box_pack_start(box, labelResult, false, false, 0);
		
		GTK.gtk_window_set_position(win, GTK.GTK_WIN_POS_CENTER);
		GTK.gtk_widget_show(win);
		
		GTK.gtk_main();
	}
	
	static void btnSubmiOnClick()
	{
		String strWeight = GTK.gtk_entry_get_text(entryWeight);
		if(strWeight==null||strWeight.length()<=0)
		{
			GTK.gtk_label_set_text(labelResult, "���ز���Ϊ��");
			return;
		}
		
		String strHeight = GTK.gtk_entry_get_text(entryHeight);
		if(strHeight==null||strHeight.length()<=0)
		{
			GTK.gtk_label_set_text(labelResult, "��߲���Ϊ��");
			return;
		}
		
		double weight = Double.parseDouble(strWeight);//��Ϊ�漰���쳣��������ʱ������Ƿ��ǺϷ�����ֵ		
		if(weight<25)
		{
			GTK.gtk_label_set_text(labelResult, "���Ǵ�������ģ�С����ȥ�԰����ǣ�");
			return;
		}
		if(weight>200)
		{
			GTK.gtk_label_set_text(labelResult, "��ȷ���������˵���ǹ��ﲻ���н���");
			return;
		}
		
		double height = Double.parseDouble(strHeight);
		if(height<0.5)
		{
			GTK.gtk_label_set_text(labelResult, "��֪����ѩ����������������ȥ�Ķ�����");
			return;
		}
		if(height>2.5)
		{
			GTK.gtk_label_set_text(labelResult, "������ȱ����");
			return;
		}
		
		double bmi = weight/(height*height);
		if(bmi<18.5)
		{
			GTK.gtk_label_set_text(labelResult, "���ز��㣬��Ҫ�ಹ��Ӫ��ѽ��");
		}
		else if(bmi>=18.5&&bmi<23)
		{
			GTK.gtk_label_set_text(labelResult, "��̫�����ˣ��ú�Ŭ����������ְ��н������CEO��Ӯȡ�׸����������������۷壡");
		}
		else if(bmi>=23&&bmi<25)
		{
			GTK.gtk_label_set_text(labelResult, "�е㳬�أ�Ҫ�ٳԵع���");
		}
		else if(bmi>=25&&bmi<30)
		{
			GTK.gtk_label_set_text(labelResult, "���Ѿ������ˣ�С�ı�ը��");
		}
		else
		{
			GTK.gtk_label_set_text(labelResult, "���Ѿ������ط����ˣ����Ǻ��������Ķ�ʦ����");
		}
	}
}
