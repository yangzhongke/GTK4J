package com.bumimang.gtk4j.test1;

import java.util.Calendar;

import com.rupeng.gtk4j.Cairo;
import com.rupeng.gtk4j.GTK;
import com.rupeng.gtk4j.IGCallBack;
import com.rupeng.gtk4j.IGSourceFunc;
import com.rupeng.ogtk.GtkApplication;
import com.rupeng.ogtk.GtkBox;
import com.rupeng.ogtk.GtkButton;
import com.rupeng.ogtk.GtkButtonsType;
import com.rupeng.ogtk.GtkCalendar;
import com.rupeng.ogtk.GtkComboBoxText;
import com.rupeng.ogtk.GtkContainer;
import com.rupeng.ogtk.GtkEntry;
import com.rupeng.ogtk.GtkFileChooserAction;
import com.rupeng.ogtk.GtkFileChooserDialog;
import com.rupeng.ogtk.GtkIconSize;
import com.rupeng.ogtk.GtkImage;
import com.rupeng.ogtk.GtkListStore;
import com.rupeng.ogtk.GtkListView;
import com.rupeng.ogtk.GtkMenu;
import com.rupeng.ogtk.GtkMenuBar;
import com.rupeng.ogtk.GtkMenuItem;
import com.rupeng.ogtk.GtkMessageDialog;
import com.rupeng.ogtk.GtkMessageType;
import com.rupeng.ogtk.GtkNoteBook;
import com.rupeng.ogtk.GtkOrientation;
import com.rupeng.ogtk.GtkPositionType;
import com.rupeng.ogtk.GtkProgressBar;
import com.rupeng.ogtk.GtkRadioButton;
import com.rupeng.ogtk.GtkResponseType;
import com.rupeng.ogtk.GtkScale;
import com.rupeng.ogtk.GtkStateType;
import com.rupeng.ogtk.GtkStatusIcon;
import com.rupeng.ogtk.GtkTreeStore;
import com.rupeng.ogtk.GtkTreeView;
import com.rupeng.ogtk.GtkWidget;
import com.rupeng.ogtk.GtkWindow;
import com.rupeng.ogtk.WindowPosition;
import com.rupeng.ogtk.impl.ExitAppCallBack;

public class OOTest extends GTK
{
	public static void main(String[] args)
	{
		GtkApplication.getInstance().init();
		
		final GtkWindow win = new GtkWindow();
		win.setTitle("�ҵ�OO");
		win.addDestroyListener(new IGCallBack() {
			
			@Override
			public void execute(int instance,  int eventData, Object object)
			{
				GtkApplication.getInstance().quit();				
			}
		});
		
		final GtkButton btn1 =new GtkButton();
		btn1.setLabel("click");
		btn1.addClickedListener(new IGCallBack() {
			
			@Override
			public void execute(int instance,  int eventData, Object object)
			{
				GtkMessageDialog dlg = new GtkMessageDialog(win, 
						GtkMessageType.INFO, GtkButtonsType.YES_NO, "souga");
				GtkResponseType res = dlg.run();
				dlg.destroy();
				if(res== GtkResponseType.YES)
				{
					System.out.println("˵����");
				}
			}
		});
		btn1.overrideBackgroundColor(GtkStateType.NORMAL, 255, 0, 0);
		
		btn1.show();
		
		GtkBox box = new GtkBox(GtkOrientation.VERTICAL, 0);
		win.add(box);
		box.packStart(btn1, false, false, 0);
		box.show();
		
		GtkRadioButton rbMale = new GtkRadioButton("��");
		GtkRadioButton rbFemale =rbMale.createSibling("Ů");
		rbMale.show();
		rbFemale.show();
		box.packStart(rbMale, false, false, 0);
		box.packStart(rbFemale, false, false, 0);
		
		GtkRadioButton rbDX = new GtkRadioButton("��ѧ");
		GtkRadioButton rbGZ =rbDX.createSibling("����");
		GtkRadioButton rbCZ =rbDX.createSibling("����");
		rbDX.show();
		rbGZ.show();
		rbCZ.show();
		box.packStart(rbDX, false, false, 0);
		box.packStart(rbGZ, false, false, 0);
		box.packStart(rbCZ, false, false, 0);
		
		final GtkScale scale1 = new GtkScale(GtkOrientation.HORIZONTAL, 0, 100, 1);
		box.packStart(scale1, false,false, 0);
		scale1.show();
		scale1.addValueChangedListener(new IGCallBack() {
			
			@Override
			public void execute(int instance,  int eventData, Object object)
			{
				win.setTitle(Double.toString(scale1.getValue()));				
			}
		});
		
		final GtkProgressBar pbar1 = new GtkProgressBar();
		pbar1.setText("���ڼ���");
		pbar1.setShowText(true);
		pbar1.setFraction(0.5);		
		box.packStart(pbar1);
		pbar1.show();
		
		GtkNoteBook nb1 = new GtkNoteBook();
		nb1.setTabPos(GtkPositionType.LEFT);
	    GtkButton gb1 = new GtkRadioButton();
	    gb1.setLabel("����");
	    nb1.appendPage(gb1, "ͨ��");
	    gb1.show();
	   
	    GtkComboBoxText cmbt1 = new GtkComboBoxText();
	    cmbt1.appendText("1");
	    cmbt1.appendText("2");
	    cmbt1.appendText("3");
	    GtkImage imgIcon =new GtkImage();
	    imgIcon.setFromStock(GTK.GTK_STOCK_CDROM, GtkIconSize.SMALL_TOOLBAR);
	    nb1.appendPage(cmbt1, imgIcon);
	    cmbt1.show();
	    
	    nb1.show();
	    box.packStart(nb1);
	    
	    
	    GtkMenuBar menubar = new GtkMenuBar();
	    menubar.show();
	    box.packStart(menubar);
	    
	    GtkMenu menuFile = new GtkMenu();
	    menuFile.show();
	    GtkMenuItem miFile = new GtkMenuItem("�ļ�", false);
	    miFile.show();
	    miFile.setSubMenu(menuFile);
	    menubar.append(miFile);
	    
	    GtkMenuItem miOpen = new GtkMenuItem("��", false);
	    miOpen.show();
	    miOpen.addActivateListener(new IGCallBack() {
			
			@Override
			public void execute(int instance, int eventData, Object object)
			{
				GtkFileChooserDialog dlg = new GtkFileChooserDialog("���ļ�",win,
						GtkFileChooserAction.OPEN,"ѡ��");
				dlg.run();
			}
		});
	    menuFile.append(miOpen);
	    
	    final GtkListStore list1 = new GtkListStore(3);
	    list1.append();
	    list1.setValue(0, "���п�");
	    list1.setValue(1, "18");
	    list1.setValue(2, "180");
	    list1.append();
	    list1.setValue(0, "������");
	    list1.setValue(1, "400");
	    list1.setValue(2, "170");
	    
	    final GtkListView tv1 = new GtkListView();
	    tv1.appendColumn("����", 0);
	    tv1.appendColumn("����", 1);
	    tv1.appendColumn("���", 2);
	    tv1.addChangedListener(new IGCallBack() {
			
			@Override
			public void execute(int instance,  int eventData, Object object)
			{
				/*
				GtkMessageDialog.showError(win, "��Ϣ", "ɾ��ʧ��");
				if(GtkMessageDialog.showYesNo(win, "ѯ��", "�Ƿ�ɾ����"))
				{
					GtkMessageDialog.showInfo(win, "��Ϣ", "ɾ���ɹ�");
				}
				*/
				
				/*
				int[] indices = tv1.getSelectionIndices();
				if(indices.length>0)
				{
				System.out.println(list1.getTag(indices[0]));			
				}
				
				GtkFileFilter filter1 = new GtkFileFilter();
				filter1.setName("ͼƬ�ļ�");
				filter1.addPattern("*.jpg");
				filter1.addPattern("*.jpeg");
				filter1.addPattern("*.gif");
				filter1.addPattern("*.png");
				
				GtkFileFilter filter2 = new GtkFileFilter();
				filter2.setName("�����ļ�");
				filter2.addPattern("*.*");
				
				GtkFileChooserDialog fcd = new GtkFileChooserDialog("ѡ���ļ�", win, GtkFileChooserAction.SAVE,
						"ȷ��");
				fcd.addFilter(filter1);
				fcd.addFilter(filter2);
				fcd.run();
				fcd.destroy();*/
				
				
			}
		});
	    tv1.setModel(list1);
	    tv1.show();
	    
	    box.packStart(tv1);
	    
	    final GtkCalendar c1 = new GtkCalendar();
	    Calendar today = Calendar.getInstance();
	    today.roll(Calendar.DAY_OF_MONTH, 5);    
	    c1.setDate(today.getTime());
	    c1.addDaySelectedListener(new IGCallBack() {		
			@Override
			public void execute(int instance, int eventData, Object object)
			{
				GtkMessageDialog.showInfo(win, "ttt", c1.getDate().toString());				
			}
		});
	    
	    c1.show();
	    box.packStart(c1);
		
	    GtkEntry e1 = new GtkEntry();
	    e1.show();
	    box.packEnd(e1);
	    e1.addFocusOutListener(new IGCallBack() {
			
			@Override
			public void execute(int instance, int eventData, Object object)
			{
				System.out.println("��ү������");				
			}
		});
	    
	    e1.addFocusInListener(new IGCallBack() {
			
			@Override
			public void execute(int instance, int eventData, Object object)
			{
				System.out.println("��ү�����ˣ�");				
			}
		});
	    
	    /*
	    
	    //�Ҽ��˵�
	    {
	    	GtkMenu menuRoot = new GtkMenu();
			menuRoot.show();
			GtkMenuItem mi1 = new GtkMenuItem("��");
			mi1.show();
			menuRoot.append(mi1);
			btn1.setContextMenu(menuRoot);
	    }
	    
	    //״̬��
	    final GtkMenu menuRoot = new GtkMenu();
		menuRoot.show();
		GtkMenuItem miExit = new GtkMenuItem("�˳�");
		miExit.addActivateListener(new ExitAppCallBack());
		miExit.show();
		menuRoot.append(miExit);
		
		final GtkStatusIcon statusIcon = new GtkStatusIcon();
		statusIcon.setFromStock(GTK_STOCK_ABOUT);
		statusIcon.setContextMenu(menuRoot);
		statusIcon.addClickedListener(new IGCallBack() {
			
			@Override
			public void execute(int instance, int eventData, Object object)
			{
				win.maximize();				
			}
		});
	    
	    GTK.g_timeout_add(500, new IGSourceFunc() {
			
			@Override
			public boolean execute(Object userdata)
			{
				//statusIcon.setVisible(!statusIcon.getVisible());
				return true;
			}
		}, null);
	    */
	    
	    /*
	    final GtkComboBoxText cmbt = new GtkComboBoxText();
	    cmbt.show();
	    box.packStart(cmbt);
	    cmbt.append("1", "a");
	    cmbt.append("2", "b");
	    cmbt.append("3", "c");
	    cmbt.addChangedListener(new IGCallBack() {
			
			@Override
			public void execute(int instance, int eventData, Object object)
			{
				GtkMessageDialog.showError(win, "aa",cmbt.getActiveId());	
				cmbt.setActiveId("3");
			}
		});
	    */
	    /*
	    GtkTreeStore ts1 = new GtkTreeStore();
	    int itRoot = GTK.gtk_tree_iter_new();
	    ts1.append(itRoot, 0);
	    ts1.setValue(itRoot, "�й�");
	    
	    //����д��ɡ�JB����Ȼ��˵���ҵĽڲ�ѽ���������ഺ��ȥ��
	    int itBJ = GTK.gtk_tree_iter_new();
	    ts1.append(itBJ, itRoot);
	    ts1.setValue(itBJ, "����");
	    
	    
	    int itHB = GTK.gtk_tree_iter_new();
	    ts1.append(itHB, itRoot);
	    ts1.setValue(itHB, "�ӱ�");
	    
	    final GtkTreeView tree1 = new GtkTreeView();
	    tree1.show();
	    tree1.setModel(ts1);
	    tree1.addDoubleClickListener(new IGCallBack() {
			
			@Override
			public void execute(int instance, int eventData, Object object)
			{
				int[] ids = tree1.getSelectionIndices();
				System.out.println(ids.length);
			}
		});
	    
	    box.packStart(tree1);
	    */
	    
	    int drawArea = GTK.gtk_drawing_area_new();
	    GTK.gtk_widget_show(drawArea);
	    GTK.gtk_widget_set_app_paintable(drawArea, true);
	    GTK.gtk_box_pack_start(box.getId(), drawArea, true, true, 0);
	    GTK.gtk_widget_set_size_request(drawArea, 200, 200);
	    GTK.g_signal_connect(drawArea, "draw", new IGCallBack() {
			
			@Override
			public void execute(int instance, int cr, Object object)
			{
				/*
				Cairo.cairo_set_source_rgb(cr,0,1,0);//���û�����ɫ��Ҳ���Ǻ죬�̣������������ó���ɫ�� 
				Cairo.cairo_rectangle(cr,10,10,50,50);//��һ�����飬λ�ô�����(10,10)��ʼ����200����200 
				Cairo.cairo_fill(cr);//��䣬ʹ�õ���ɫ��Ȼ���������õ���ɫ�� 
				Cairo.cairo_move_to(cr,60,60);//�������ƶ���(250,200) 
				Cairo.cairo_set_font_size(cr, 20);
				Cairo.cairo_select_font_face(cr, "����", 
						Cairo.CAIRO_FONT_SLANT_NORMAL, 
						Cairo.CAIRO_FONT_WEIGHT_NORMAL);//Ϊcairo����һ�����壬������DongWen--Song����б�壬�Ǵ��塣 
				Cairo.cairo_show_text(cr,"hello world");//����һ���� 	
				*/
				
				/*
				Cairo.cairo_move_to(cr,0,0); 
				Cairo.cairo_line_to(cr,100,100); 
				Cairo.cairo_set_source_rgb(cr,1,0,0); 
				Cairo.cairo_stroke(cr); 
				*/
				
				/*
				Cairo.cairo_arc(cr,100,100,40,0,2*Math.PI); 
				Cairo.cairo_set_line_width(cr,3); 
				Cairo.cairo_set_source_rgb(cr,1,0,0); 
				Cairo.cairo_stroke(cr); 
				*/
				
				/*
				Cairo.cairo_rectangle(cr,10,10,100,100); 
				Cairo.cairo_set_line_width(cr,10) ;
				Cairo.cairo_set_source_rgb(cr,0,0,1); 
				Cairo.cairo_stroke(cr);
				*/
				
				/*
				Cairo.cairo_arc(cr,100,100,40,0,2*Math.PI);//��100,100����ΪԲ�Ļ�һ���뾶Ϊ40��Բ(��Բ)�� 
				Cairo.cairo_set_source_rgb(cr,1,0,0);//��ɫ 
				Cairo.cairo_fill(cr);//��ʱ��õ��ľ���һ����ɫ��ʵ��Բ��
				*/
				Cairo.cairo_move_to(cr,0,0); 
				Cairo.cairo_set_line_width(cr, 30);
				Cairo.cairo_set_line_cap(cr, Cairo.CAIRO_LINE_CAP_ROUND);
				Cairo.cairo_line_to(cr,100,100); 
				Cairo.cairo_set_source_rgb(cr,1,0,0); 
				Cairo.cairo_stroke(cr); 
				
				int surface = Cairo.cairo_get_target(cr);
				int status = Cairo.cairo_surface_write_to_png(surface, "d:/1.png");
				if(status!=Cairo.CAIRO_STATUS_SUCCESS)
				{
					System.out.println(Cairo.cairo_status_to_string(status));
				}
			}
		}, null);
	    
	    
		win.show();
		//win.fullscreen();
		
		System.out.println(WindowPosition.CENTER.name());
		System.out.println(WindowPosition.CENTER_ON_PARENT.getValue());
		GtkApplication.getInstance().start();
	}
}
