package com.bumimang.gtk4j.test1;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import javax.sql.rowset.CachedRowSet;
import javax.sql.rowset.RowSetProvider;

public class JdbcUtils
{
	private static String dbdriver;
	private static String dburl;
	private static String dbusername;
	private static String dbpassword;

	static
	{
		Properties prop = new Properties();
		InputStream in = JdbcUtils.class.getClassLoader().getResourceAsStream(
				"com/bumimang/gtk4j/test1/config.properties");
		try
		{
			prop.load(in);
		} catch (IOException e1)
		{
			throw new RuntimeException("加载config.properties失败", e1);
		}
		dbdriver = prop.getProperty("dbdriver");
		dburl = prop.getProperty("dburl");
		dbusername = prop.getProperty("dbusername");
		dbpassword = prop.getProperty("dbpassword");

		try
		{
			in.close();
		} catch (IOException e1)
		{
			throw new RuntimeException(e1);
		}
		
		try
		{
			Class.forName(dbdriver);
		} catch (ClassNotFoundException e)
		{
			throw new RuntimeException(e);
		}
	}

	public static Connection createConnection() throws SQLException
	{
		return DriverManager.getConnection(dburl, dbusername, dbpassword);
	}

	public static void close(Connection connection)
	{
		if (connection != null)
		{
			try
			{
				connection.close();
			} catch (SQLException e)
			{

			}
		}
	}

	public static void close(Statement stmt)
	{
		if (stmt != null)
		{
			try
			{
				stmt.close();
			} catch (SQLException e)
			{

			}
		}
	}

	public static void close(ResultSet rs)
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
	}

	public static void close(ResultSet rs, Statement stmt, Connection conn)
	{
		close(rs);
		close(stmt);
		close(conn);
	}

	public static void close(Statement stmt, Connection conn)
	{
		close(stmt);
		close(conn);
	}

	public static void executeNonQuery(String sql, Object... parameters)
			throws SQLException
	{
		Connection conn = null;
		PreparedStatement ps = null;
		try
		{
			conn = createConnection();
			ps = conn.prepareStatement(sql);
			for (int i = 0; i < parameters.length; i++)
			{
				ps.setObject(i + 1, parameters[i]);
			}
			ps.execute();
		} finally
		{
			ps.close();
			conn.close();
		}
	}

	//CachedRowSet也作为选学内容去讲
	public static CachedRowSet executeQuery(String sql, Object... parameters)
			throws SQLException
	{
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try
		{
			conn = createConnection();
			ps = conn.prepareStatement(sql);
			for (int i = 0; i < parameters.length; i++)
			{
				ps.setObject(i + 1, parameters[i]);
			}
			rs = ps.executeQuery();
			// CachedRowSet crs = new CachedRowSetImpl();
			//RowSetProvider在1.7之后才支持
			CachedRowSet crs = RowSetProvider.newFactory().createCachedRowSet();
			crs.populate(rs);
			return crs;
		} finally
		{
			close(rs, ps, conn);
		}
	}
	
	public static Object executeScalar(String sql, Object... parameters)
			throws SQLException
	{
		CachedRowSet rowset = executeQuery(sql, parameters);
		rowset.next();
		Object value = rowset.getObject(1);
		rowset.close();
	    return value;
	}
}
