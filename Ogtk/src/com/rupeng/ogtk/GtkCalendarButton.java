package com.rupeng.ogtk;

import java.text.DateFormat;
import java.util.Date;

import com.rupeng.gtk4j.IGCallBack;

public class GtkCalendarButton extends GtkButton
{
	private Date value;
	
	public Date getValue()
	{
		return value;
	}

	public void setValue(Date value)
	{
		this.value = value;
		if(value!=null)
		{
			this.setLabel(DateFormat.getDateInstance(DateFormat.MEDIUM).format(value));
		}
	}

	public GtkCalendarButton()
	{
		addClickedListener(new IGCallBack() {
			
			@Override
			public void execute(int instance, int eventData, Object object)
			{
				GtkWindow parentWin = GtkCalendarButton.this.getParentWindow();
				GtkCalendarDialog dlg = new GtkCalendarDialog(parentWin);
				if(value!=null)
				{
					dlg.setValue(value);
				}
				if(dlg.run()==GtkResponseType.OK)
				{
					setValue(dlg.getValue());
					dlg.destroy();
				}
				else
				{
					dlg.destroy();
				}
			}
		});
	}
}
