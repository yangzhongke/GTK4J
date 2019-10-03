package com.bumimang.gtk4j.test1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Test3
{
	static int sum(int... nums)
	{
		return 0;
	}

	public static void main(String[] args)
	{
		//sum(new int[]{5,3,9,10});
		//sum(5,9,10);

		try
		{
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException ex)
		{
			System.err.println("MYSQL驱动加载失败！" + ex);
			return;
		}
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try
		{
			conn = DriverManager
					.getConnection(
							"jdbc:mysql://localhost/study1?seUnicode=true&characterEncoding=UTF8",
							"root", "root");
			ps = conn.prepareStatement("select * from T_Persons");
			rs = ps.executeQuery();
			while(rs.next())
			{
				int id = rs.getInt("Id");
				String name = rs.getString("Name");
				int age = rs.getInt("Age");
				boolean gender = rs.getBoolean("Gender");
				System.out.println("id="+id+",姓名："+name+",年龄:"+age+",性别："+(gender?"男":"女"));
			}
		} catch (SQLException ex)
		{
			System.err.println("执行数据库出错" + ex);
		} finally
		{
			closeQuietly(rs);
			closeQuietly(ps);
			closeQuietly(conn);
		}
	}

	static void closeQuietly(Connection conn)
	{
		if (conn != null)
		{
			try
			{
				conn.close();
			} catch (SQLException e)
			{
				//
			}
		}
	}

	static void closeQuietly(PreparedStatement stmt)
	{
		if (stmt != null)
		{
			try
			{
				stmt.close();
			} catch (SQLException e)
			{
				//
			}
		}
	}

	static void closeQuietly(ResultSet rs)
	{
		if (rs != null)
		{
			try
			{
				rs.close();
			} catch (SQLException e)
			{
				//
			}
		}
	}
}
