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

//�ڿ���GTK4J��ʱ��ֻҪ�趨����ѡ���л����������ӡ�Path��ָ��D:\gtk\bin����
public class Main
{
	// ����С���������������д����crm��Ŀ���÷�װ�Ŀؼ���ֻ�д����Ĺ��̻���̵ľ��飬����������Ტ���������ձ�̵����в���Ϊ���������»���
	// ������̾��Ƕ���

	static int entryNum;
	static int labelResult;
	static int win;

	//����ŵ���Ŀ֮��JDBC�߼���
	
	public static void main(String[] args)
	{
		GTK.gtk_init();

		win = GTK.gtk_window_new(GTK.GTK_WINDOW_TOPLEVEL);
		GTK.gtk_window_set_title(win, "�ֻ���������ز�ѯ");
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

		int labelNum = GTK.gtk_label_new("�ֻ���");
		GTK.gtk_widget_show(labelNum);
		GTK.gtk_box_pack_start(box, labelNum, false, false, 0);

		entryNum = GTK.gtk_entry_new();
		GTK.gtk_box_pack_start(box, entryNum, false, false, 0);
		GTK.gtk_widget_show(entryNum);

		int btnQuery = GTK.gtk_button_new_with_label("��ѯ");
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

		int btnImport = GTK.gtk_button_new_with_label("�����ʼ����");
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
					showError(win, "���ݵ������"+e.getMessage());
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
				GTK.gtk_label_set_text(labelResult, "û�в鵽�������ֻ��ź���ѽ");
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

	// ����ķ�����û�з�Ӧ�����á�����Ҫ���ֳ�����ķ������Խ��ͷ����ĸ��ӳ���һ�������Ĵ��벻Ӧ�ù���
	static boolean showYesNo(int parent, String message)
	{
		int dlg = GTK.gtk_message_dialog_new(parent,
				GTK.GTK_DIALOG_DESTROY_WITH_PARENT | GTK.GTK_DIALOG_MODAL,
				GTK.GTK_MESSAGE_QUESTION, GTK.GTK_BUTTONS_YES_NO, message);
		GTK.gtk_window_set_title(dlg, "��ʾ");
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
		if (!showYesNo(win, "�������ݽ�����վ����ݣ��Ƿ������"))
		{
			return;
		}
		int fcDialog = GTK.gtk_file_chooser_dialog_new("ѡ��Ҫ����������ļ�", win,
				GTK.GTK_FILE_CHOOSER_ACTION_OPEN, "����");
		if (GTK.gtk_dialog_run(fcDialog) == GTK.GTK_RESPONSE_OK)
		{
			String fileName = GTK.gtk_file_chooser_get_filename(fcDialog);
			GTK.gtk_widget_destroy(fcDialog);

			JdbcUtils.executeUpdate("delete from T_PhoneAreas");

			//���ÿ�β���һ������Ҫ3940��
			//����һ��ConnectionҪ942��
			//���������ύ��ֻҪ179�루500��һ��������,47�루2000��һ����,29�루����һ����
			
			
			long startMS = System.currentTimeMillis();
			FileInputStream inStream = null;
			InputStreamReader streamReader = null;
			BufferedReader bufferReader = null;
			try
			{
				inStream = new FileInputStream(fileName);
				streamReader = new InputStreamReader(inStream);
				
				bufferReader = new BufferedReader(streamReader);
				
				bufferReader.readLine();//��һ��������������
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
				showInfo(win, "����ɹ�����ʱ"+secUsed+"��");
			} catch (IOException e)
			{
				showError(win, "��ȡ�ļ�ʧ�ܣ�" + e.getMessage());
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
