package com.techpalle.dao;

import java.sql.*;

public class AdminDao 
{
	private static final String dbUrl = "jdbc:mysql://localhost:3306/customer_management";
	private static final String dbUsername = "root";
	private static final String dbPassword = "admin";
	
	private static Connection con = null;
	private static PreparedStatement ps = null;
	private static ResultSet rs = null;
	
	private static final String validAdminQry = "select username, password from admin where username=? and password=?";

	public static boolean validateAdmin(String user, String pass)
	{
		boolean b = false;
		try 
		{
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection(dbUrl, dbUsername, dbPassword);
			
			ps = con.prepareStatement(validAdminQry);
			ps.setString(1, user);
			ps.setString(2, pass);
			
			rs = ps.executeQuery();
			
			b = rs.next();
		} 
		catch (ClassNotFoundException e) 
		{
			e.printStackTrace();
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		finally
		{
			try
			{
				if (rs != null)
				{
					rs.close();
				}
				if (ps != null)
				{
					ps.close();
				}
				if (con != null)
				{
					con.close();
				}
			}
			catch (SQLException e) 
			{
				e.printStackTrace();
			}
		}
		return b;
	}
	
}
