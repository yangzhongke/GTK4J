package com.rupeng.ogtk;

import java.util.Date;

import com.rupeng.gtk4j.IGCallBack;

public class GtkCalendarDialog extends GtkDialog
{
private GtkCalendar calendar;
	
	public GtkCalendarDialog(GtkWindow parent)
	{
		super("请选择日期",parent);
		calendar = new GtkCalendar();
		calendar.show();
		calendar.addDaySelectedDoubleClickListener(new IGCallBack() {
			
			@Override
			public void execute(int instance, int eventData, Object object)
			{
				response(GtkResponseType.OK);		
			}
		});
		getContentArea().add(calendar);
		
		GtkBox boxBtns = new GtkBox(GtkOrientation.HORIZONTAL);
		boxBtns.show();
		getActionArea().add(boxBtns);
		
		GtkButton btnOK = new GtkButton("确定");
		btnOK.addClickedListener(new IGCallBack() {
			
			@Override
			public void execute(int instance, int eventData, Object object)
			{
				response(GtkResponseType.OK);
			}
		});
		btnOK.show();
		boxBtns.packStart(btnOK);
		
		GtkButton btnCancel = new GtkButton("取消");
		btnCancel.addClickedListener(new IGCallBack() {
			
			@Override
			public void execute(int instance, int eventData, Object object)
			{
				response(GtkResponseType.CANCEL);
			}
		});
		btnCancel.show();
		boxBtns.packStart(btnCancel);
	}

	public Date getValue()
	{
		return calendar.getDate();
	}
	
	public void setValue(Date value)
	{
		calendar.setDate(value);
	}
}
