package com.bumimang.gtk4j.phonepos;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class JdbcUtils
{
	private static final String dbdrivername;
	private static final String dburl;
	private static final String dbusername;
	private static final String dbpassword;
	static
	{
		InputStream stream = JdbcUtils.class.getClassLoader()
				.getResourceAsStream(
						"com/bumimang/gtk4j/phonepos/config.properties");
		try
		{
			Properties prop = new Properties();
			prop.load(stream);
			dbdrivername = prop.getProperty("dbdrivername");
			dburl = prop.getProperty("dburl");
			dbusername = prop.getProperty("dbusername");
			dbpassword = prop.getProperty("dbpassword");
		} catch (IOException e)
		{
			throw new ExceptionInInitializerError(e);
		} finally
		{
			try
			{
				stream.close();
			} catch (IOException e)
			{
				throw new ExceptionInInitializerError(e);
			}
		}

		try
		{
			Class.forName(dbdrivername);
		} catch (ClassNotFoundException e)
		{
			throw new ExceptionInInitializerError(e);
		}

	}

	public static int executeUpdate(String sql, Object... params)
			throws SQLException
	{
		Connection conn = createConnection();
		PreparedStatement stmt = null;
		try
		{
			stmt = conn.prepareStatement(sql);
			for (int i = 0; i < params.length; i++)
			{
				stmt.setObject(i+1, params[i]);
			}
			return stmt.executeUpdate();
		} finally
		{
			close(stmt);
			close(conn);
		}
	}

	public static Connection createConnection() throws SQLException
	{
		Connection conn = DriverManager.getConnection(dburl, dbusername,
				dbpassword);
		return conn;
	}

	public static ResultSet executeQuery(String sql, Object... params)
			throws SQLException
	{
		Connection conn = createConnection();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try
		{
			stmt = conn.prepareStatement(sql);
			for (int i = 0; i < params.length; i++)
			{
				stmt.setObject(i+1, params[i]);
			}
			rs = stmt.executeQuery();
			return rs;
		} catch (SQLException e)
		{
			close(rs);
			close(stmt);
			close(conn);
			throw e;
		}
	}

	public static void close(Connection conn)
	{
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

	/**
	 * 把Result以及他的Statement和Connection都关掉
	 * 
	 * @param rs
	 */
	public static void closeAll(ResultSet rs)
	{
		close(rs);
		if (rs != null)
		{
			Statement stmt;
			try
			{
				stmt = rs.getStatement();
				close(stmt);
				if (stmt != null)
				{
					close(stmt.getConnection());
				}
			} catch (SQLException e)
			{
			}
		}
	}
}
