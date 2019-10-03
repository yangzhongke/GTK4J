package com.rupeng.ogtk.impl;

import com.rupeng.gtk4j.IGCallBack;
import com.rupeng.ogtk.GtkDialog;
import com.rupeng.ogtk.GtkResponseType;

public class CloseDialogCallBack implements IGCallBack
{
	private GtkDialog dialog;
	private GtkResponseType response_id;
	
	public CloseDialogCallBack(GtkDialog dialog)
	{
		this(dialog,GtkResponseType.CLOSE);
	}
	public CloseDialogCallBack(GtkDialog dialog, GtkResponseType response_id)
	{
		super();
		this.dialog = dialog;
		this.response_id = response_id;
	}

	@Override
	public void execute(int instance, int eventData, Object object)
	{
		dialog.response(response_id);
	}

}
