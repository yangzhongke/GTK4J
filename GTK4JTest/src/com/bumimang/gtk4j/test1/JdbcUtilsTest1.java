package com.bumimang.gtk4j.test1;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class JdbcUtilsTest1
{
	public static void main(String[] args)
	{
		Connection conn=null;
		PreparedStatement ps =null;
		ResultSet rs = null;
		try
		{
			conn = JdbcUtils.createConnection();
			ps = conn.prepareStatement("select * from T_Persons");
			rs = ps.executeQuery();
			while(rs.next())
			{
				System.out.println(rs.getInt(1));
				System.out.println(rs.getString(2));
			}			
		} catch (SQLException e)
		{
			e.printStackTrace();
		}
		finally
		{
			JdbcUtils.close(rs);
			JdbcUtils.close(ps);
			JdbcUtils.close(conn);
		}
	}
}
