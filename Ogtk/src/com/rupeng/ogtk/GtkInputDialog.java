package com.rupeng.ogtk;

import com.rupeng.gtk4j.IGCallBack;

public class GtkInputDialog extends GtkDialog
{
	private GtkEntry entry;
	
	public GtkInputDialog(GtkWindow parent)
	{
		super("������ֵ",parent);
		
		entry = new GtkEntry();
		entry.show();
		getContentArea().add(entry);
		
		/*
		GtkBox boxBtns = new GtkBox(GtkOrientation.HORIZONTAL);
		boxBtns.show();
		getActionArea().add(boxBtns);
		*/
		GtkButton btnOK = new GtkButton("ȷ��");
		btnOK.addClickedListener(new IGCallBack() {
			
			@Override
			public void execute(int instance, int eventData, Object object)
			{
				response(GtkResponseType.OK);
			}
		});
		btnOK.show();
		
		//boxBtns.packStart(btnOK);
		
		GtkButton btnCancel = new GtkButton("ȡ��");
		btnCancel.addClickedListener(new IGCallBack() {
			
			@Override
			public void execute(int instance, int eventData, Object object)
			{
				response(GtkResponseType.CANCEL);
			}
		});
		btnCancel.show();
		//boxBtns.packStart(btnCancel);
		
		getActionArea().add(btnOK);
		getActionArea().add(btnCancel);
	}

	public String getValue()
	{
		return entry.getText();
	}
	
	public void setValue(String value)
	{
		entry.setText(value);
	}
}
