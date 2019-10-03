package com.bumimang.gtk4j.test1;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.rupeng.gtk4j.GTK;
import com.rupeng.gtk4j.IGCallBack;
import com.rupeng.ogtk.GtkApplication;
import com.rupeng.ogtk.GtkBox;
import com.rupeng.ogtk.GtkListStore;
import com.rupeng.ogtk.GtkListView;
import com.rupeng.ogtk.GtkMessageDialog;
import com.rupeng.ogtk.GtkOrientation;
import com.rupeng.ogtk.GtkResponseType;
import com.rupeng.ogtk.GtkSelectionMode;
import com.rupeng.ogtk.GtkToolBar;
import com.rupeng.ogtk.GtkToolButton;
import com.rupeng.ogtk.GtkWindow;
import com.rupeng.ogtk.impl.ExitAppCallBack;

public class PersonListWindow extends GtkWindow
{
	private GtkListView tv;
	
	public PersonListWindow()
	{
		addDestroyListener(new ExitAppCallBack());
		
		setTitle("人员管理");
		maximize();
		GtkBox boxMain = new GtkBox(GtkOrientation.VERTICAL,0);
		boxMain.show();
		add(boxMain);
		
		GtkToolBar toolbar =new GtkToolBar();
		toolbar.show();
		boxMain.packStart(toolbar);
		
		GtkToolButton tbAdd =new GtkToolButton("新增");
		tbAdd.setStockId(GTK.GTK_STOCK_ADD);
		tbAdd.show();
		tbAdd.addClickedListener(new IGCallBack() {
			
			@Override
			public void execute(int instance, int eventData, Object object)
			{
				addnew();
			}
		});
		toolbar.insert(tbAdd, 0);
		
		GtkToolButton tbRemove =new GtkToolButton("删除");
		tbRemove.setStockId(GTK.GTK_STOCK_REMOVE);
		tbRemove.show();
		tbRemove.addClickedListener(new IGCallBack() {
			
			@Override
			public void execute(int instance, int eventData, Object object)
			{
				remove();
			}
		});
		toolbar.insert(tbRemove, 1);
		
		GtkToolButton tbRefresh =new GtkToolButton("刷新");
		tbRefresh.setStockId(GTK.GTK_STOCK_REFRESH);
		tbRefresh.show();
		tbRefresh.addClickedListener(new IGCallBack() {
			
			@Override
			public void execute(int instance, int eventData, Object object)
			{
				reloadData();
			}
		});
		toolbar.insert(tbRefresh, 2);
		
		tv = new GtkListView();
		tv.setSelectionMode(GtkSelectionMode.MULTIPLE);
		tv.appendColumn("Id", 0);
		tv.setColumnVisible(0, false);
		tv.appendColumn("姓名", 1);
		
		tv.setColumnResizable(1, true);
		tv.appendColumn("年龄",2);
		tv.setSortColumnId(2, 2);
		tv.setColumnReorderable(2,true);
		tv.appendColumn("性别", 3);
		
		tv.appendColumn("简历", 4);
		tv.addDoubleClickListener(new IGCallBack() {
			
			@Override
			public void execute(int instance, int eventData, Object object)
			{
				int[] selectIndices = tv.getSelectionIndices();
				if(selectIndices.length<=0)
				{
					return;
				}
				
				int id = Integer.parseInt(tv.getModel().getValue(selectIndices[0],0));		
				PersonEditDialog dlg = new PersonEditDialog("edit",id);
				dlg.setTitle("编辑人员");
				if(dlg.run()==GtkResponseType.OK)
				{
					reloadData();
				}
				dlg.destroy();
			}
		});
		tv.show();
		boxMain.packStart(tv,true,true,0);
		
		reloadData();
	}
	
	private void addnew()
	{
		PersonEditDialog dlg = new PersonEditDialog("addnew",0);
		dlg.setTitle("新增人员");
		if(dlg.run()==GtkResponseType.OK)
		{
			reloadData();
		}
		dlg.destroy();
	}
	
	private void remove()
	{
		int[] selections = tv.getSelectionIndices();
		if(selections.length<=0)
		{
			GtkMessageDialog.showError(this, "错误","没有选中任何行！");
			return;
		}
		if(!GtkMessageDialog.showYesNo(this, "确认删除", "确认删除这"+selections.length+"条数据？"))
		{
			return;
		}
		
		StringBuilder sb =new StringBuilder();
		sb.append(tv.getModel().getValue(selections[0],0));
		for(int i=1;i<selections.length;i++)
		{
			int index = selections[i];
			int id = Integer.parseInt(tv.getModel().getValue(index,0));
			sb.append(",").append(id);
		}
		String sql = "delete from T_Persons where Id in("+sb+")";
		
		try
		{
			JdbcUtils.executeNonQuery(sql);
		} catch (SQLException e)
		{
			GtkMessageDialog.showError(this, e);
			return;
		}
		reloadData();
	}
	
	private void reloadData()
	{
		GtkListStore list =new GtkListStore(5);
		
		ResultSet rs = null;
		try
		{
			rs = JdbcUtils.executeQuery("select * from T_Persons");
			while(rs.next())
			{
				int id = rs.getInt("Id");
				String name = rs.getString("Name");
				int age = rs.getInt("Age");
				boolean gender = rs.getBoolean("Gender");
				String resume = rs.getString("Resume");
				
				list.append();
				list.setValue(0, Integer.toString(id));
				list.setValue(1, name);
				list.setValue(2, Integer.toString(age));
				list.setValue(3, gender?"男":"女");
				list.setValue(4, resume);
			}
		}
		catch(SQLException ex)
		{
			GtkMessageDialog.showError(this, ex);
			return;
		}
		finally
		{
			JdbcUtils.close(rs);
		}
		
		tv.setModel(list);
	}
	
	public static void main(String[] args)
	{
		GtkApplication.getInstance().init();
		
		PersonListWindow mainWin = new PersonListWindow();
		mainWin.show();
		
		GtkApplication.getInstance().start();
	}
}
