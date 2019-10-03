package com.bumimang.gtk4j.test1;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Test4
{

	public static void main(String[] args)
	{
		Connection conn = null;
		PreparedStatement ps = null;
		try
		{
			conn = JdbcUtils.createConnection();
			//conn.setAutoCommit(false);
			ps = conn
					.prepareStatement("Insert into T_Persons(Name,Age,Gender) values(?,?,?)");
			long startMS = System.currentTimeMillis();
			for (int i = 0; i < 10000; i++)
			{
				ps.clearParameters();
				ps.setString(1, "yzk" + i);
				ps.setInt(2, i);
				ps.setBoolean(3, true);
				// ps.executeUpdate();
				ps.addBatch();
			}
			ps.executeBatch();
			//conn.commit();
			long endMS = System.currentTimeMillis();
			System.out.println("耗时：" + (endMS - startMS));
		} catch (SQLException e)
		{
			try
			{
				conn.rollback();
			} catch (SQLException e1)
			{
				e1.printStackTrace();
			}
		} finally
		{
			JdbcUtils.close(ps);
			JdbcUtils.close(conn);
		}
		// 没用用批量前1w条用30秒
	}

}
