package com.rupeng.ogtk;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;

import com.rupeng.gtk4j.GTK;

public class GtkMessageDialog extends GtkDialog
{
	public GtkMessageDialog(GtkWindow parent, 
			GtkMessageType type, GtkButtonsType buttons, String message)
	{
		int id = GTK.gtk_message_dialog_new(parent==null?0:parent.getId(),
				GTK.GTK_DIALOG_DESTROY_WITH_PARENT|GTK.GTK_DIALOG_MODAL, 
				type.getValue(), buttons.getValue(), message);
		GTK.gtk_window_set_position(id, GTK.GTK_WIN_POS_CENTER_ALWAYS);
		setId(id);
	}
	
	public static void showInfo(GtkWindow parent, String title,String message)
	{
		GtkMessageDialog dlg = new GtkMessageDialog(parent,GtkMessageType.INFO,
				GtkButtonsType.OK,message);
		dlg.setTitle(title);
		dlg.run();
		dlg.destroy();
	}
	
	public static void showError(GtkWindow parent, String title,String message)
	{
		GtkMessageDialog dlg = new GtkMessageDialog(parent,GtkMessageType.ERROR,
				GtkButtonsType.OK,message);
		dlg.setTitle(title);
		dlg.run();
		dlg.destroy();
	}
	
	public static void showError(GtkWindow parent,Exception exception)
	{
		showError(parent, "程序运行出错",exception);
	}
	
	public static void showError(GtkWindow parent,String title,Exception exception)
	{
		showError(parent, title,toFullString(exception));
	}
	
	public static boolean showYesNo(GtkWindow parent, String title,String message)
	{
		GtkMessageDialog dlg = new GtkMessageDialog(parent,GtkMessageType.QUESTION,
				GtkButtonsType.YES_NO,message);
		dlg.setTitle(title);
		GtkResponseType response = dlg.run();
		dlg.destroy();
		return response==GtkResponseType.YES;
	}
	
	public static boolean showOKCancel(GtkWindow parent, String title,String message)
	{
		GtkMessageDialog dlg = new GtkMessageDialog(parent,GtkMessageType.QUESTION,
				GtkButtonsType.OK_CANCEL,message);
		dlg.setTitle(title);
		GtkResponseType response = dlg.run();
		dlg.destroy();
		return response==GtkResponseType.OK;
	}
	
	static String toFullString(Throwable throwable)
	{
		StringWriter sw = null;
		PrintWriter pw = null;
		try
		{
			sw = new StringWriter();
			pw = new PrintWriter(sw);
			throwable.printStackTrace(pw);
			return sw.toString();
		} finally
		{
			try
			{
				sw.close();
			} catch (IOException e)
			{

			}
			pw.close();
		}
	}
}
