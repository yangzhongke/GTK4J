package com.rupeng.ogtk;

import com.rupeng.gtk4j.GTK;
import com.rupeng.gtk4j.IGCallBack;

public class GtkStatusIcon
{
	private int id;

	public GtkStatusIcon()
	{
		this.id = GTK.gtk_status_icon_new();
	}

	public void setFromFile(String filename)
	{
		GTK.gtk_status_icon_set_from_file(id, filename);
	}

	public void setFromStock(String stock_id)
	{
		GTK.gtk_status_icon_set_from_stock(id, stock_id);
	}

	public void setFromResource(String resName)
	{
		GTK.gtk_status_icon_set_from_resource(id, resName);
	}

	public void setTooltipText(String text)
	{
		GTK.gtk_status_icon_set_tooltip_text(id, text);
	}

	public void setVisible(boolean visible)
	{
		GTK.gtk_status_icon_set_visible(id, visible);
	}

	public boolean getVisible()
	{
		return GTK.gtk_status_icon_get_visible(id);
	}

	public void setContextMenu(final GtkMenu ctxMenu)
	{
		// ÓÒ¼ü²Ëµ¥
		GTK.g_signal_connect(id, "button-press-event", new IGCallBack() {

			@Override
			public void execute(int instance, int eventData, Object object)
			{
				if (GTK.gdk_event_get_type(eventData) == GTK.GDK_BUTTON_PRESS
						&& GTK.gdk_event_get_button(eventData) == 3)
				{
					GTK.gtk_menu_popup(ctxMenu.getId());
				}
			}
		}, null);
	}

	public void addClickedListener(final IGCallBack callback)
	{
		GTK.g_signal_connect(id, "button-press-event", new IGCallBack() {

			@Override
			public void execute(int instance, int eventData, Object object)
			{
				if (GTK.gdk_event_get_type(eventData) == GTK.GDK_BUTTON_PRESS
						&& GTK.gdk_event_get_button(eventData) == 1)
				{
					callback.execute(instance, eventData, object);
				}
			}
		}, null);
	}
}
