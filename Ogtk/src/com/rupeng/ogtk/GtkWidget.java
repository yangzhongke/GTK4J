package com.rupeng.ogtk;

import com.rupeng.gtk4j.GTK;
import com.rupeng.gtk4j.IGCallBack;
import com.rupeng.ocairo.CCairo;

public abstract class GtkWidget
{

	private int id = Integer.MIN_VALUE;

	protected void setId(int id)
	{
		if (id <= 0)
		{
			this.id = Integer.MIN_VALUE;
		} else
		{
			this.id = id;
			show();// 设置后立即模式显示，降低编码的复杂
		}
	}

	// 讲课就不讲ContextMenu了，不讲GtkStatusIcon，留一个给老师答疑用
	public void setContextMenu(final GtkMenu ctxMenu)
	{
		// 右键菜单
		GTK.g_signal_connect(getId(), "button-press-event", new IGCallBack() {

			@Override
			public void execute(int instance, int eventData, Object object)
			{
				if (GTK.gdk_event_get_type(eventData) == GTK.GDK_BUTTON_PRESS
						&& GTK.gdk_event_get_button(eventData) == 3)
				{
					GTK.gtk_menu_attach_to_widget(ctxMenu.getId(), getId());
					GTK.gtk_menu_popup(ctxMenu.getId());
				}
			}
		}, null);
	}

	public int getId()
	{
		if (id == Integer.MIN_VALUE)
		{
			throw new RuntimeException("没有给id赋值");
		}
		return id;
	}

	public void show()
	{
		GTK.gtk_widget_show(getId());
	}

	public void hide()
	{
		GTK.gtk_widget_hide(getId());
	}

	public GtkWindow getParentWindow()
	{
		int ret = GTK.gtk_widget_get_toplevel(getId());
		if (GTK.gtk_widget_is_toplevel(ret))
		{
			return new GtkWindow(ret);
		} else
		{
			return null;
		}
	}

	public void setParent(GtkWidget parent)
	{
		GTK.gtk_widget_set_parent(getId(), parent.getId());
	}

	public void setCanFocus(boolean canFocus)
	{
		GTK.gtk_widget_set_can_focus(getId(), canFocus);
	}

	public boolean getCanFocus(boolean canFocus)
	{
		return GTK.gtk_widget_get_can_focus(getId());
	}

	public void grabFocus()
	{
		GTK.gtk_widget_grab_focus(getId());
	}

	public void setVisible(boolean visible)
	{
		GTK.gtk_widget_set_visible(getId(), visible);
	}

	public boolean getVisible()
	{
		return GTK.gtk_widget_get_visible(getId());
	}

	public void setSensitive(boolean sensitive)
	{
		GTK.gtk_widget_set_sensitive(getId(), sensitive);
	}

	public boolean getSensitive()
	{
		return GTK.gtk_widget_get_sensitive(getId());
	}

	public void setSizeRequest(int width, int height)
	{
		GTK.gtk_widget_set_size_request(getId(), width, height);
	}

	public void destroy()
	{
		GTK.gtk_widget_destroy(getId());
	}

	public void addDestroyListener(IGCallBack callback)
	{
		GTK.g_signal_connect(this.getId(), "destroy", callback, null);
	}

	public void addClickedListener(IGCallBack callback)
	{
		GTK.g_signal_connect(this.getId(), "clicked", callback, null);
	}

	public void addFocusInListener(IGCallBack callback)
	{
		GTK.g_signal_connect(this.getId(), "focus-in-event", callback, null);
	}

	public void addFocusOutListener(IGCallBack callback)
	{
		GTK.g_signal_connect(this.getId(), "focus-out-event", callback, null);
	}

	public void addDoubleClickListener(final IGCallBack callback)
	{
		GTK.g_signal_connect(this.getId(), "button-press-event",
				new IGCallBack() {

					@Override
					public void execute(int instance, int eventData,
							Object object)
					{
						if (GTK.gdk_event_get_type(eventData) == GTK.GDK_2BUTTON_PRESS
								&& GTK.gdk_event_get_button(eventData) == 1)
						{
							callback.execute(instance, eventData, object);
						}
					}
				}, null);
	}

	public void overrideBackgroundColor(GtkStateType state, int red, int green,
			int blue)
	{
		GTK.gtk_widget_override_background_color(getId(), state.getValue(),
				red, green, blue);
	}

	public void overrideColor(GtkStateType state, int red, int green, int blue)
	{
		GTK.gtk_widget_override_color(getId(), state.getValue(), red, green,
				blue);
	}

	public void setPaintable(boolean paintable)
	{
		GTK.gtk_widget_set_app_paintable(getId(), paintable);
	}

	/**
	 * 发送“draw”信号
	 */
	public void queueDraw()
	{
		GTK.gtk_widget_queue_draw(getId());
	}

	public void addDrawListener(final IDrawCallBack callback)
	{
		GTK.g_signal_connect(getId(), "draw", new IGCallBack() {

			@Override
			public void execute(final int instance, int cr, Object object)
			{
				CCairo cairo = new CCairo(cr);
				callback.execute(instance, cairo, object);
			}
		}, null);
	}

	public int getAllocatedWidth()
	{
		return GTK.gtk_widget_get_allocated_width(getId());
	}

	public int getAllocatedHeight()
	{
		return GTK.gtk_widget_get_allocated_height(getId());
	}
}
