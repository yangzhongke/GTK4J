package com.bumimang.gtk4j.test1;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.rupeng.gtk4j.IGCallBack;
import com.rupeng.ogtk.GtkBox;
import com.rupeng.ogtk.GtkButton;
import com.rupeng.ogtk.GtkComboBoxText;
import com.rupeng.ogtk.GtkDialog;
import com.rupeng.ogtk.GtkEntry;
import com.rupeng.ogtk.GtkGrid;
import com.rupeng.ogtk.GtkLabel;
import com.rupeng.ogtk.GtkMessageDialog;
import com.rupeng.ogtk.GtkOrientation;
import com.rupeng.ogtk.GtkResponseType;
import com.rupeng.ogtk.GtkTextView;
import com.rupeng.ogtk.GtkWindow;
import com.rupeng.ogtk.GtkWrapMode;
import com.rupeng.ogtk.impl.CloseDialogCallBack;

public class PersonEditDialog extends GtkDialog
{
	private String action;
	private int id;
	
	private GtkEntry entryName;
	private GtkEntry entryAge;
	private GtkComboBoxText cbGender;
	private GtkTextView tvResume;
	
	
	public PersonEditDialog(String action,int id)
	{
		this.action = action;
		this.id = id;
		
		setResizable(false);
		
		GtkGrid gridMain = new GtkGrid();
		
		GtkLabel labelName = new GtkLabel("姓名");
		labelName.show();		
		gridMain.attach(labelName, 0, 0);
		
		entryName = new GtkEntry();
		entryName.show();
		gridMain.attach(entryName, 1, 0);
		
		GtkLabel labelAge = new GtkLabel("年龄");
		labelAge.show();		
		gridMain.attach(labelAge, 0, 1);
		
		entryAge = new GtkEntry();
		entryAge.show();
		gridMain.attach(entryAge, 1, 1);
		
		GtkLabel labelGender = new GtkLabel("性别");
		labelGender.show();		
		gridMain.attach(labelGender, 0, 2);
		
		cbGender = new GtkComboBoxText();
		cbGender.appendText("男");
		cbGender.appendText("女");
		cbGender.show();
		gridMain.attach(cbGender, 1, 2);		
		
		GtkLabel labelResume = new GtkLabel("简历");
		labelResume.show();		
		gridMain.attach(labelResume, 0, 3);
		
		tvResume = new GtkTextView();
		tvResume.setWrapMode(GtkWrapMode.WORD);
		tvResume.setSizeRequest(500,500);
		tvResume.show();
		gridMain.attach(tvResume, 1, 3);
		
		gridMain.show();
		getContentArea().add(gridMain);
		
		GtkBox boxBtns = new GtkBox(GtkOrientation.HORIZONTAL,0);
		boxBtns.show();
		getActionArea().add(boxBtns);
		
		GtkButton btnSave = new GtkButton();
		btnSave.setLabel("保存");
		btnSave.addClickedListener(new IGCallBack() {
			
			@Override
			public void execute(int instance, int eventData, Object object)
			{
				doSave();
			}
		});
		btnSave.show();
		boxBtns.packStart(btnSave);
				
		GtkButton btnClose = new GtkButton();
		btnClose.setLabel("关闭");
		btnClose.addClickedListener(new CloseDialogCallBack(this));
		btnClose.show();
		boxBtns.packStart(btnClose);
		
		if(action.equals("edit"))
		{
			loadEdit();
		}
	}
	
	protected void doSave()
	{
		if(action.equals("addnew"))
		{
			saveAddNew();
		}
		else if(action.equals("edit"))
		{
			saveEdit();
		}
		else
		{
			throw new RuntimeException("action错误");
		}		
	}

	void saveAddNew()
	{
		String name = entryName.getText();
		int age = Integer.parseInt(entryAge.getText());
		boolean gender = cbGender.getActive()==0;
		String resume = tvResume.getText();
		
		try
		{
			JdbcUtils.executeNonQuery("Insert into T_Persons(Name,Age,Gender,Resume) values(?,?,?,?)",
					name,age,gender,resume);
		}
		catch(SQLException ex)
		{
			GtkMessageDialog.showError(this, ex);
		}
		response(GtkResponseType.OK);
	}
	
	void saveEdit()
	{
		String name = entryName.getText();
		int age = Integer.parseInt(entryAge.getText());
		boolean gender = cbGender.getActive()==0;
		String resume = tvResume.getText();
		
		try
		{
			JdbcUtils.executeNonQuery("Update T_Persons set Name=?,Age=?,Gender=?,Resume=? where Id=?",
					name,age,gender,resume,id);
		}
		catch(SQLException ex)
		{
			GtkMessageDialog.showError(this, ex);
		}
		response(GtkResponseType.OK);
	}
	
	void loadEdit()
	{
		ResultSet rs=null;
		try
		{
			rs = JdbcUtils.executeQuery("select * from T_Persons where Id=?",id);
			if(rs.next())
			{
				String name = rs.getString("Name");
				int age = rs.getInt("Age");
				boolean gender = rs.getBoolean("Gender");
				String resume = rs.getString("Resume");
				
				entryName.setText(name);
				entryAge.setText(Integer.toString(age));				
				cbGender.setActive(gender?0:1);				
				tvResume.setText(resume);
			}
			else
			{
				GtkMessageDialog.showError(this, "错误", "找不到id="+id+"的人员");
			}
		}
		catch(SQLException ex)
		{
			GtkMessageDialog.showError(this, ex);
		}
		finally
		{
			JdbcUtils.close(rs);
		}
	}
}
