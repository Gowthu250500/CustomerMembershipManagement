package com.techpalle.dao;

import java.sql.*;
import java.util.ArrayList;

import com.techpalle.model.Customer;

public class CustomerDao 
{
	private static final String dbUrl = "jdbc:mysql://localhost:3306/customer_management";
	private static final String dbUsername = "root";
	private static final String dbPassword = "admin";
	
	private static Connection con = null;
	private static Statement stm = null;
	private static PreparedStatement ps = null;
	private static ResultSet rs = null;
	
	private static final String customerListQry = "select * from customer";
	private static final String customerInsert = "insert into customer (name, email, mobile) values (?, ?, ?)";
	private static final String customerEditQry = "select * from customer where id=?";
	private static final String customerUpdateQry = "update customer set name=?, email=?, mobile=? where id=?";
	private static final String customerDeleteQry = "delete from customer where id=?";
	
	public static void deleteCustomer(int id)
	{
		
		try 
		{
			con = getConnectionDef();

			ps = con.prepareStatement(customerDeleteQry);
			ps.setInt(1, id);
			
			ps.executeUpdate();
		} 
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		finally
		{
			try
			{
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
	}
	
	public static void updateCustomer(Customer c)
	{
		
		try 
		{
			con = getConnectionDef();

			ps = con.prepareStatement(customerUpdateQry);
			ps.setString(1, c.getName());
			ps.setString(2, c.getEmail());
			ps.setLong(3, c.getMobile());
			ps.setInt(4, c.getId());
			
			ps.executeUpdate();
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		finally
		{
			try
			{
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
	}
	
	public static Customer getOneCustomer(int id)
	{
		Customer c = null;
		try 
		{
			con = getConnectionDef();

			ps = con.prepareStatement(customerEditQry);
			ps.setInt(1, id);
			
			rs = ps.executeQuery();
			
			rs.next();
			
			int i = rs.getInt("id");
			String n = rs.getString("name");
			String e = rs.getString("email");
			long m = rs.getLong("mobile");
			
			c = new Customer(i, n, e, m);
			
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
		return c;
	}
	
	public static void insertCustomer(Customer cus)
	{
		
		try 
		{
			con = getConnectionDef();

			ps = con.prepareStatement(customerInsert);
			ps.setString(1, cus.getName());
			ps.setString(2, cus.getEmail());
			ps.setLong(3, cus.getMobile());
						
			ps.executeUpdate();
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		finally
		{
			try
			{
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
	}
	
	public static Connection getConnectionDef()
	{
		try 
		{
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection(dbUrl, dbUsername, dbPassword);
		} 
		catch (ClassNotFoundException e) 
		{
			e.printStackTrace();
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		return con;
	}
	
	public static ArrayList<Customer> getAllCustomers()
	{
		ArrayList<Customer> al = new ArrayList<Customer>();
		try 
		{
			con = getConnectionDef();

			stm = con.createStatement();
			
			rs = stm.executeQuery(customerListQry);
			
			while(rs.next())
			{
				int i = rs.getInt("id");
				String n = rs.getString("name");
				String e = rs.getString("email");
				long m = rs.getLong("mobile");
				
				Customer c = new Customer(i, n, e, m);
				al.add(c);
			}
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
				if (stm != null)
				{
					stm.close();
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
		return al;
	}
}
