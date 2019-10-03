package com.bumimang.gtk4j.test1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class MySQLTest1
{

	/**
	 * @param args
	 * @throws ClassNotFoundException
	 */
	public static void main(String[] args) throws ClassNotFoundException
	{
		Class.forName("com.mysql.jdbc.Driver");

		Connection connection = null;
		Statement statement = null;
		ResultSet rs = null;
		try
		{
			// create a database connection
			
			//文件路径中不要含有空格
			connection = DriverManager
					.getConnection( "jdbc:mysql://localhost:3306/test", "root", "root");
			statement = connection.createStatement();

			statement
					.executeUpdate("insert into t_persons(Name) values('leo')");
			rs = statement.executeQuery("select * from t_persons");
			while (rs.next())
			{
				// read the result set
				System.out.println("name = " + rs.getString("name"));
				System.out.println("id = " + rs.getInt("id"));
			}
		} catch (SQLException e)
		{
			System.err.println(e.getMessage());
		} finally
		{
			close(connection, statement, rs);
		}

	}

	static void close(Connection conn, Statement stmt, ResultSet rs)
	{
		if (rs != null)
		{
			try
			{
				rs.close();
			} catch (SQLException e)
			{

			}
		}
		if (stmt != null)
		{
			try
			{
				stmt.close();
			} catch (SQLException e)
			{

			}
		}
		if (conn != null)
		{
			try
			{
				conn.close();
			} catch (SQLException e)
			{

			}
		}
	}

}
