package com.rupeng.ogtk;

import com.rupeng.gtk4j.GTK;

public class GtkDialog extends GtkWindow
{
	public GtkDialog()
	{
		setId(GTK.gtk_dialog_new());
		
		setPosition(WindowPosition.CENTER);
		setResizable(false);
	}
	
	public GtkDialog(String title,GtkWindow parent)
	{
		int id = GTK.gtk_dialog_new_with_buttons(title, parent==null?0:parent.getId(), 
				GTK.GTK_DIALOG_MODAL|GTK.GTK_DIALOG_DESTROY_WITH_PARENT);
		setId(id);
		
		setPosition(WindowPosition.CENTER);
		setResizable(false);
	}
	
	public GtkResponseType run()
	{
		int res = GTK.gtk_dialog_run(getId());
		for(GtkResponseType type : GtkResponseType.values())
		{
			if(type.getValue()==res)
			{
				return type;
			}
		}
		throw new RuntimeException("返回值不是GtkResponseType中正确类型");
	}
	
	public void response(GtkResponseType response_id)
	{
		GTK.gtk_dialog_response(getId(), response_id.getValue());
	}
	
	public GtkContainer getActionArea()
	{		
		return new GtkContainer(GTK.gtk_dialog_get_action_area(getId()));
	}
	
	public GtkContainer getContentArea()
	{
		return new GtkContainer(GTK.gtk_dialog_get_content_area(getId()));
	}
	
	public void addButton(String button_text, GtkResponseType response_id)
	{
		GTK.gtk_dialog_add_button(getId(), button_text, response_id.getValue());
	}
	
	@Override
	public void show()
	{
		//System.out.println("对话框请调用run");//不能抛异常，否则GtkWidget的setId会报错
		//throw new RuntimeException("对话框请调用run");
	}
}
