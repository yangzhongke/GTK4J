package com.bumimang.gtk4j.test1;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Test5
{

	public static void main(String[] args)
	{
		Connection conn = null;
		PreparedStatement ps1 = null;
		PreparedStatement ps2 = null;
try
{
	conn = JdbcUtils.createConnection();
	conn.setAutoCommit(false);
	ps1 = conn.prepareStatement("Update T_Employees Set Salary=Salary-10 where Name='Tom'");
	ps1.executeUpdate();
	ps2 = conn.prepareStatement("Update T_Employees Set1 Salary=Salary+10 where Name='Jerry'");
	ps2.executeUpdate();
	conn.commit();
} catch (SQLException e)
{
	System.err.println("执行失败，回滚");
	try
	{
		conn.rollback();
	} catch (SQLException e1)
	{
		System.err.println("回滚失败" + e1);
	}
} finally
		{
			JdbcUtils.close(ps1);
			JdbcUtils.close(ps2);
			JdbcUtils.close(conn);
		}
		// 没用用批量前1w条用30秒
	}

}
