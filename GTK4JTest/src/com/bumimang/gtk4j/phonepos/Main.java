package com.bumimang.gtk4j.phonepos;

import java.io.BufferedReader;
import java.io.Closeable;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.rupeng.gtk4j.GTK;
import com.rupeng.gtk4j.IGCallBack;

//在开发GTK4J的时候只要设定调试选项中环境变量增加“Path”指向D:\gtk\bin即可
public class Main
{
	// 所有小案例都用面向过程写，到crm项目再用封装的控件，只有大量的过程化编程的经验，才能真正领会并且真正掌握编程的真谛并且为面向对象打下基础
	// 面向过程就是蹲马步

	static int entryNum;
	static int labelResult;
	static int win;

	//这个放到项目之后讲JDBC高级用
	
	public static void main(String[] args)
	{
		GTK.gtk_init();

		win = GTK.gtk_window_new(GTK.GTK_WINDOW_TOPLEVEL);
		GTK.gtk_window_set_title(win, "手机号码归属地查询");
		GTK.gtk_window_set_position(win, GTK.GTK_WIN_POS_CENTER);
		GTK.g_signal_connect(win, "destroy", new IGCallBack() {

			@Override
			public void execute(int instance, int eventData, Object object)
			{
				GTK.gtk_main_quit();
			}
		}, null);

		int box = GTK.gtk_box_new(GTK.GTK_ORIENTATION_HORIZONTAL, 0);
		GTK.gtk_container_add(win, box);
		GTK.gtk_widget_show(box);

		int labelNum = GTK.gtk_label_new("手机号");
		GTK.gtk_widget_show(labelNum);
		GTK.gtk_box_pack_start(box, labelNum, false, false, 0);

		entryNum = GTK.gtk_entry_new();
		GTK.gtk_box_pack_start(box, entryNum, false, false, 0);
		GTK.gtk_widget_show(entryNum);

		int btnQuery = GTK.gtk_button_new_with_label("查询");
		GTK.gtk_box_pack_start(box, btnQuery, false, false, 0);
		GTK.gtk_widget_show(btnQuery);
		GTK.g_signal_connect(btnQuery, "clicked", new IGCallBack() {

			@Override
			public void execute(int instance, int eventData, Object object)
			{
				btnQueryOnClicked();
			}
		}, null);

		labelResult = GTK.gtk_label_new("");
		GTK.gtk_box_pack_start(box, labelResult, false, false, 0);
		GTK.gtk_widget_show(labelResult);

		int btnImport = GTK.gtk_button_new_with_label("导入初始数据");
		GTK.gtk_box_pack_start(box, btnImport, false, false, 0);
		GTK.gtk_widget_show(btnImport);
		GTK.g_signal_connect(btnImport, "clicked", new IGCallBack() {

			@Override
			public void execute(int instance, int eventData, Object object)
			{
				try
				{
					btnImportOnClicked();
				} catch (SQLException e)
				{
					showError(win, "数据导入出错："+e.getMessage());
				}
			}
		}, null);

		GTK.gtk_widget_show(win);
		GTK.gtk_main();
	}
	
	
	static void btnQueryOnClicked()
	{
		String noToQuery = GTK.gtk_entry_get_text(entryNum);
		String prefix = noToQuery.substring(0,7);
		ResultSet rs = null;
		try
		{
			rs = JdbcUtils.executeQuery("select * from t_phoneareas where MobileNumber=?", prefix);
			if(!rs.next())
			{
				GTK.gtk_label_set_text(labelResult, "没有查到，您的手机号好新呀");
			}
			else
			{
				String mobileArea = rs.getString("MobileArea");
				String mobileType = rs.getString("MobileType");
				GTK.gtk_label_set_text(labelResult, mobileType+" "+mobileArea);
			}
		} catch (SQLException e)
		{
			showError(win, e.getMessage());
		}
		finally
		{
			JdbcUtils.closeAll(rs);
		}
	}

	
	static void showInfo(int parent,String message)
	{
		int dlg = GTK.gtk_message_dialog_new(parent,
				GTK.GTK_DIALOG_DESTROY_WITH_PARENT | GTK.GTK_DIALOG_MODAL,
				GTK.GTK_MESSAGE_INFO, GTK.GTK_BUTTONS_OK, message);
		GTK.gtk_dialog_run(dlg);
		GTK.gtk_widget_destroy(dlg);
	}

	static void showError(int parent, String message)
	{
		int dlg = GTK.gtk_message_dialog_new(parent,
				GTK.GTK_DIALOG_DESTROY_WITH_PARENT | GTK.GTK_DIALOG_MODAL,
				GTK.GTK_MESSAGE_ERROR, GTK.GTK_BUTTONS_OK, message);
		GTK.gtk_dialog_run(dlg);
		GTK.gtk_widget_destroy(dlg);
	}

	// 这里的方法还没有反应“复用”，主要体现抽象出的方法可以降低方法的复杂程序，一个方法的代码不应该过多
	static boolean showYesNo(int parent, String message)
	{
		int dlg = GTK.gtk_message_dialog_new(parent,
				GTK.GTK_DIALOG_DESTROY_WITH_PARENT | GTK.GTK_DIALOG_MODAL,
				GTK.GTK_MESSAGE_QUESTION, GTK.GTK_BUTTONS_YES_NO, message);
		GTK.gtk_window_set_title(dlg, "提示");
		int ret = GTK.gtk_dialog_run(dlg);
		GTK.gtk_widget_destroy(dlg);
		return ret == GTK.GTK_RESPONSE_YES;
	}

	static void close(Closeable closeable)
	{
		try
		{
			closeable.close();
		} catch (IOException e)
		{
		}
	}

	static void btnImportOnClicked() throws SQLException
	{
		if (!showYesNo(win, "导入数据将会清空旧数据，是否继续？"))
		{
			return;
		}
		int fcDialog = GTK.gtk_file_chooser_dialog_new("选择要导入的数据文件", win,
				GTK.GTK_FILE_CHOOSER_ACTION_OPEN, "导入");
		if (GTK.gtk_dialog_run(fcDialog) == GTK.GTK_RESPONSE_OK)
		{
			String fileName = GTK.gtk_file_chooser_get_filename(fcDialog);
			GTK.gtk_widget_destroy(fcDialog);

			JdbcUtils.executeUpdate("delete from T_PhoneAreas");

			//如果每次操作一次连接要3940秒
			//保持一个Connection要942秒
			//采用批量提交后只要179秒（500条一个批量）,47秒（2000个一批）,29秒（整个一批）
			
			
			long startMS = System.currentTimeMillis();
			FileInputStream inStream = null;
			InputStreamReader streamReader = null;
			BufferedReader bufferReader = null;
			try
			{
				inStream = new FileInputStream(fileName);
				streamReader = new InputStreamReader(inStream);
				
				bufferReader = new BufferedReader(streamReader);
				
				bufferReader.readLine();//第一行是列名，跳过
				String line = null;
				
				/*
				while((line = bufferReader.readLine())!=null)
				{
					String[] strs = line.split(",");
					String mobileNum = strs[1].replaceAll("\"", "");
					String mobileArea = strs[2].replaceAll("\"", "");
					String mobileType = strs[3].replaceAll("\"", "");
					
					JdbcUtils.executeUpdate("insert into T_PhoneAreas(MobileNumber,MobileArea,MobileType) values(?,?,?)", 
							mobileNum,mobileArea,mobileType);
				}
				*/
				
				/*
				Connection conn = JdbcUtils.createConnection();
				PreparedStatement ps = conn.prepareStatement("insert into T_PhoneAreas(MobileNumber,MobileArea,MobileType) values(?,?,?)");
				while((line = bufferReader.readLine())!=null)
				{
					String[] strs = line.split(",");
					String mobileNum = strs[1].replaceAll("\"", "");
					String mobileArea = strs[2].replaceAll("\"", "");
					String mobileType = strs[3].replaceAll("\"", "");
					ps.clearParameters();
					ps.setString(1, mobileNum);
					ps.setString(2, mobileArea);
					ps.setString(3, mobileType);
					ps.executeUpdate();
				}
				JdbcUtils.close(ps);
				JdbcUtils.close(conn);*/
				Connection conn = JdbcUtils.createConnection();
				conn.setAutoCommit(false);
				PreparedStatement ps = conn.prepareStatement("insert into T_PhoneAreas(MobileNumber,MobileArea,MobileType) values(?,?,?)");
				int i=0;
				while((line = bufferReader.readLine())!=null)
				{
					String[] strs = line.split(",");
					String mobileNum = strs[1].replaceAll("\"", "");
					String mobileArea = strs[2].replaceAll("\"", "");
					String mobileType = strs[3].replaceAll("\"", "");
					ps.clearParameters();
					ps.setString(1, mobileNum);
					ps.setString(2, mobileArea);
					ps.setString(3, mobileType);
					ps.addBatch();
					
					i++;
					if(i%2000==0)
					{
						ps.executeBatch();
					}
					conn.commit();
				}
				ps.executeBatch();
				conn.commit();
				JdbcUtils.close(ps);
				JdbcUtils.close(conn);
				
				long secUsed = (System.currentTimeMillis()-startMS)/1000;
				showInfo(win, "导入成功，耗时"+secUsed+"秒");
			} catch (IOException e)
			{
				showError(win, "读取文件失败：" + e.getMessage());
			} finally
			{
				close(bufferReader);
				close(streamReader);
				close(inStream);
			}
		} else
		{
			GTK.gtk_widget_destroy(fcDialog);
		}

	}

}
