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
		GTK.gtk_window_set_title(win, "体重指数计算");
		
		int box = GTK.gtk_box_new(GTK.GTK_ORIENTATION_HORIZONTAL, 0);
		GTK.gtk_widget_show(box);
		GTK.gtk_container_add(win, box);		
		
		int labelWeight = GTK.gtk_label_new("体重(kg)");
		GTK.gtk_widget_show(labelWeight);
		GTK.gtk_box_pack_start(box, labelWeight, false, false, 0);
		
		entryWeight = GTK.gtk_entry_new();
		GTK.gtk_widget_show(entryWeight);
		GTK.gtk_box_pack_start(box, entryWeight, false, false, 0);
		
		int labelHeight = GTK.gtk_label_new("身高(m)");
		GTK.gtk_widget_show(labelHeight);
		GTK.gtk_box_pack_start(box, labelHeight, false, false, 0);
		
		entryHeight = GTK.gtk_entry_new();
		GTK.gtk_widget_show(entryHeight);
		GTK.gtk_box_pack_start(box, entryHeight, false, false, 0);
		
		int btnSubmit = GTK.gtk_button_new_with_label("测一下");
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
			GTK.gtk_label_set_text(labelResult, "体重不能为空");
			return;
		}
		
		String strHeight = GTK.gtk_entry_get_text(entryHeight);
		if(strHeight==null||strHeight.length()<=0)
		{
			GTK.gtk_label_set_text(labelResult, "身高不能为空");
			return;
		}
		
		double weight = Double.parseDouble(strWeight);//因为涉及到异常，这里暂时不检查是否是合法的数值		
		if(weight<25)
		{
			GTK.gtk_label_set_text(labelResult, "这是大人玩儿的，小朋友去吃棒棒糖！");
			return;
		}
		if(weight>200)
		{
			GTK.gtk_label_set_text(labelResult, "你确定你的体重说的是公斤不是市斤吗？");
			return;
		}
		
		double height = Double.parseDouble(strHeight);
		if(height<0.5)
		{
			GTK.gtk_label_set_text(labelResult, "你知道白雪公主和另外六个人去哪儿了吗？");
			return;
		}
		if(height>2.5)
		{
			GTK.gtk_label_set_text(labelResult, "你现在缺氧吗？");
			return;
		}
		
		double bmi = weight/(height*height);
		if(bmi<18.5)
		{
			GTK.gtk_label_set_text(labelResult, "体重不足，需要多补充营养呀！");
		}
		else if(bmi>=18.5&&bmi<23)
		{
			GTK.gtk_label_set_text(labelResult, "你太健康了！好好努力，早日升职加薪、当上CEO、赢取白富美，走上人生的巅峰！");
		}
		else if(bmi>=23&&bmi<25)
		{
			GTK.gtk_label_set_text(labelResult, "有点超重，要少吃地沟油");
		}
		else if(bmi>=25&&bmi<30)
		{
			GTK.gtk_label_set_text(labelResult, "你已经肥胖了，小心爆炸！");
		}
		else
		{
			GTK.gtk_label_set_text(labelResult, "你已经是严重肥胖了！你是猴子请来的二师兄吗？");
		}
	}
}
