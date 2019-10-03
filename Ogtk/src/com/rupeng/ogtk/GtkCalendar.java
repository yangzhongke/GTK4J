package com.rupeng.ogtk;

import java.util.Calendar;
import java.util.Date;

import com.rupeng.gtk4j.GTK;
import com.rupeng.gtk4j.IGCallBack;

public class GtkCalendar extends GtkWidget
{
	public GtkCalendar()
	{
		 setId(GTK.gtk_calendar_new());
	}
	
	public void setDate(Date date)
	{
		//要记得考虑用户如果传递null怎么办。
		if(date==null)
		{
			return;
		}
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		
		int year = calendar.get(Calendar.YEAR);
		int month = calendar.get(Calendar.MONTH);
		int day = calendar.get(Calendar.DAY_OF_MONTH);
		GTK.gtk_calendar_select_month(getId(),month,year);
		GTK.gtk_calendar_select_day(getId(), day);
	}
	
	public Date getDate()
	{
		int year = GTK.gtk_calendar_get_year(getId());
		int month = GTK.gtk_calendar_get_month(getId());
		int day = GTK.gtk_calendar_get_day(getId());
		Calendar cal = Calendar.getInstance();
		cal.clear();//所有段清零
		cal.set(Calendar.YEAR, year);
		cal.set(Calendar.MONTH, month);
		cal.set(Calendar.DAY_OF_MONTH, day);
		
		return cal.getTime();
	}
	
	public void addDaySelectedListener(IGCallBack callback)
	{
		GTK.g_signal_connect(getId(),  "day-selected",callback,null);
	}
	
	public void addDaySelectedDoubleClickListener(IGCallBack callback)
	{
		GTK.g_signal_connect(getId(),  "day-selected-double-click",callback,null);
	}
}
