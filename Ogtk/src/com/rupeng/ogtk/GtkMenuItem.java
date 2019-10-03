package com.rupeng.ogtk;

import com.rupeng.gtk4j.GTK;
import com.rupeng.gtk4j.IGCallBack;

public class GtkMenuItem extends GtkBin
{

	public GtkMenuItem()
	{
		super(GTK.gtk_menu_item_new());
	}

	public GtkMenuItem(String label)
	{
		this(label,true);
	}
	
	public GtkMenuItem(String label,boolean isMnemonic)
	{
		super(isMnemonic?GTK.gtk_menu_item_new_with_mnemonic(label):GTK.gtk_menu_item_new_with_label(label));
	}
	
	public void setSubMenu(GtkWidget submenu)
	{
		GTK.gtk_menu_item_set_submenu(this.getId(), submenu.getId());
	}
	
	public void addActivateListener(IGCallBack callback)
	{
		GTK.g_signal_connect(getId(), "activate", callback, null);
	}
}
