package com.bumimang.gtk4j.phonesuanming;

import com.rupeng.gtk4j.GTK;
import com.rupeng.gtk4j.IGCallBack;

public class SuanMing
{

	static int entryPhoneNum;
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
		GTK.gtk_window_set_title(win, "手机号算命（仅供娱乐）");
		
		int box = GTK.gtk_box_new(GTK.GTK_ORIENTATION_HORIZONTAL, 0);
		GTK.gtk_container_add(win, box);
		GTK.gtk_widget_show(box);
		
		int labelTel = GTK.gtk_label_new("请输入手机号：");		
		GTK.gtk_box_pack_start(box, labelTel, false, false, 0);
		GTK.gtk_widget_show(labelTel);
		
		entryPhoneNum = GTK.gtk_entry_new();		
		GTK.gtk_box_pack_start(box, entryPhoneNum, false, false, 0);
		GTK.gtk_widget_show(entryPhoneNum);
		
		int btnSubmit = GTK.gtk_button_new_with_label("大师算一卦");		
		GTK.gtk_box_pack_start(box, btnSubmit, false, false, 0);
		GTK.gtk_widget_show(btnSubmit);
		
		GTK.g_signal_connect(btnSubmit, "clicked", new IGCallBack() {
			
			@Override
			public void execute(int instance, int eventData, Object object)
			{
				btnSubmitOnClick();				
			}
		}, null);
		
		labelResult = GTK.gtk_label_new("");		
		GTK.gtk_box_pack_start(box, labelResult, false, false, 0);
		GTK.gtk_widget_show(labelResult);//不要创建出来立即show，越晚show越好
		
		GTK.gtk_window_set_position(win, GTK.GTK_WIN_POS_CENTER);
		GTK.gtk_widget_show(win);
		GTK.gtk_main();
	}

	protected static void btnSubmitOnClick()
	{
		String phoneNum = GTK.gtk_entry_get_text(entryPhoneNum);
		if(phoneNum==null||phoneNum.length()<=0)
		{
			GTK.gtk_label_set_text(labelResult, "空即是色，色即是空！然手机号是个空你让老衲算个毛线呀！");
			return;
		}
		if(phoneNum.length()!=11)
		{
			GTK.gtk_label_set_text(labelResult, "手机号不是11位！小灵通和海外手机恕不接客！");
			return;
		}
		if(phoneNum.charAt(0)!='1')
		{
			GTK.gtk_label_set_text(labelResult, "手机号第一位不是1？这是什么运营商的？老衲不造诶！");
			return;
		}
		for(int i=0;i<phoneNum.length();i++)
		{
			char ch = phoneNum.charAt(i);
			if(ch<'0'||ch>'9')
			{
				GTK.gtk_label_set_text(labelResult, "别闹！手机号里怎么还出了非数字了？");
				return;
			}
		}
		
		//“一堆貌似很玄幻的话，提前抄写到本子上。。。。。。”别问我为什么知道这么复杂的算命的东西，我也是瞎编的
		
		//手机号码最后四位除以80，再减去整数部分（只留小数），再乘以80，就会得到一个数
	    int last4 =Integer.parseInt(phoneNum.substring(phoneNum.length()-4));
	    double temp1 = last4/80.0;//不要写成80
	    double xiaoshu = temp1-(int)temp1;
	    int mingge = (int)(xiaoshu*80);
	    String[] data = {"拥有非常人的能力，能够跳出三界外，不在五行中 吉","大展鸿图，信用得固，无远弗界，可获成功 吉",
	    		"根基不固，摇摇欲坠，一盛一衰，劳而无功 凶","根深蒂固，蒸蒸日上，如意吉祥，百事顺遂 吉",
	    		"坎坷前途，苦难折磨，非有毅力，难望成功 凶","阴阳和合，生意欣荣，名利双收，后福重重 吉",
	    		"万宝云集，天降幸运，立志奋发，可成大功 吉","专心经营，和气致祥，排除万难，必获成功 吉",
	    		"努力发达，贯彻志望，不忘进退，成功可期 吉","虽抱奇才，有才无命，独营无力，财利无望 凶",
	    		"乌云遮月，暗淡无光，空费心力，徒劳无功 凶","草木逢春，枯叶沾露，稳健着实，必得人望 吉",
	    		"薄弱无力，孤立无摇，外祥内苦，谋事难成 凶","天赋吉运，能得人望，善用智慧，必获成功 吉",
	    		"忍得苦难，必有后福，是成是败，惟靠坚毅 凶","谦恭做事，必得人和，大事成就，一定兴隆 吉",
	    		"能获众望，成就大业，名利双收，盟主四方 吉","排除万难，有贵人助，把握时机，可得成功 吉",
	    		"经商做事，顺利昌隆，如能慎始，百事亨通 吉","成功虽早，慎防空亏，内外不合，障碍重重 凶",
	    		"智高志大，历尽艰难，焦心忧劳，进退两难 凶","专心经营，善用智慧，霜雪梅花，春来怒放 吉",
	    		"秋草逢霜，怀才不遇，忧愁怨苦，事不如意 凶","旭日升天，名显四方，渐次进展，终成大业 吉",
	    		"锦绣前程，须靠自力，多用智谋，能奏大功 吉","天时地利，再得人格，讲信修睦，即可成功 吉",
	    		"波澜起伏，千变万化，凌驾万难，必可成功 凶","一成一败，一盛一衰，惟靠谨慎，可守成功 凶带吉",
	    		"鱼临旱地，难逃恶运，此数大凶，不如更名 凶","如龙得云，青云直上，智谋奋进，才略奏功 吉",
	    		"吉凶参半，得失相伴，投机取巧，如赛一样 凶","此数大吉，名利双收，渐进向上，大业成就 吉",
	    		"池中之龙，风云际会，一跃上天，成功可望 吉","不可意气，善用智慧，如能慎始，必可昌隆 吉",
	    		"灾难不绝，难望成功，此数大凶，不如更名 凶","中吉之数，进退保守，生意安稳，成就可期 吉",
	    		"波澜重迭，常陷穷困，动不如静，有才无命 凶","逢凶化吉，吉人天相，风条雨顺，生意兴隆 吉",
	    		"名虽可得，利则难获，艺界发展，可望成功 凶带吉","云开见月，虽有劳碌，光明坦途，指日可期 吉",
	    		"一胜一衰，浮沉不定，知难而退，自获天佑 吉带凶","天赋吉运，德望兼备，继续努力，前途无限 吉",
	    		"事业不专，十九不成，专心进取，可望成功 吉带凶","雨夜之花，外祥内苦，忍耐自重，转凶为吉 吉带凶",
	    		"虽用心计，事难遂愿，贪功好进，必招失败 凶","杨柳遇春，绿叶发枝，冲破难关，一举成名 吉",
	    		"坎坷不平，艰难重重，若无耐心，难望有成 凶","有贵人助，可成大业，虽遇不幸，浮沉不大 吉",
	    		"美化丰实，鹤立鸡群，名利俱全，繁荣富贵 吉","遇吉则吉，遇凶则凶，惟靠谨慎，逢凶化吉 凶",
	    		"吉凶互见，一成一败，凶中有吉，吉中有凶 吉带凶","一盛一衰，浮沉不常，自重自处，可保平安 吉带凶",
	    		"草木逢春，雨过天晴，渡过难关，即获成功 吉","盛衰参半，外祥内苦，先吉后凶，先凶后吉 吉带凶",
	    		"虽倾全力，难望成功，此数大凶，最好改名 凶","外观隆昌，内隐祸患，克服难关，开出泰运 吉带凶",
	    		"事与愿违，终难成功，欲速不达，有始有终 凶","努力经营，时来运转，旷野枯草，春来花开 吉",
	    		"半凶半吉，浮沉多端，始凶终吉，能保成功 凶带吉","遇事犹疑，难望成事，大刀阔斧，始可有成 凶",
	    		"黑暗无光，心迷意乱，出尔反尔，难定方针 凶","云遮半月，百隐风波，应自谨慎，始保平安 吉带凶",
	    		"烦闷懊恼，事事难展，自防灾祸，始免困境 凶","万物化育，繁荣之象，专心一意，始能成功 吉",
	    		"见异思迁，十九不成，徒劳无功，不如更名 凶","吉运自来，能享盛名，把握机会，必获成功 吉",
	    		"黑夜漫长，进退维谷，内外不合，信用缺乏 凶","时来运转，事事如意，功成名就，富贵自来 吉",
	    		"思虑周详，计划力行，不失先机，可望成功 吉","动摇不安，常陷逆境，不得时运，难得利润 凶",
	    		"惨淡经营，难免贫困，此数不吉，最好改名 凶","吉凶参半，惟赖勇气，贯彻力行，始可成功 吉带凶",
	    		"利害混集，凶多吉少，得而复失，难以安顺 凶","安乐自来，自然吉祥，力行不懈，终必成功 吉",
	    		"利不及费，坐食山空，如无智谋，难望成功 凶","吉中带凶，欲速不达，进不如守，可保安详 吉带凶",
	    		"此数大凶，破产之象，宜速改名，以避厄运 凶","先苦后甘，先甘后苦，如能守成，不致失败 吉带凶",
	    		"有得有失，华而不实，须防劫财，始保平安 吉带凶","如走夜路，前途无光，希望不大，劳而无功 凶",
	    		"得而复失，枉费心机，守成无贪，可保安稳 吉带凶","最极之数，还本归元，能得繁业，发达成功 吉",
	    		};
	    String result = data[mingge];
	    String[] strs = result.split(" ");
	    GTK.gtk_label_set_text(labelResult, strs[1]+":"+strs[0]);
	}

}
