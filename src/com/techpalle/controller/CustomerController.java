package com.techpalle.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.techpalle.dao.AdminDao;
import com.techpalle.dao.CustomerDao;
import com.techpalle.model.Admin;
import com.techpalle.model.Customer;


@WebServlet("/")
public class CustomerController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		String path = request.getServletPath();
		
		switch(path)
		{
		case"/delete":
			deleteCustomer(request, response);
			break;
		case"/update":
			updateCustomer(request, response);
			break;
		case"/editForm":
			getEditForm(request, response);
			break;
		case"/insertForm":
			getInsertForm(request, response);
			break;
		case "/insert":
			insertCustomer(request, response);
			break;
		case"/list":
			validateAdmin(request, response);
			break;
		case"/table":
			getCustomerListPage(request, response);
			break;
			default:
				getStartUpPage(request, response);
				break;
		}
	}

	private void getCustomerListPage(HttpServletRequest request, HttpServletResponse response)
	{
		try 
		{
			ArrayList<Customer> alCustomer = CustomerDao.getAllCustomers();
			
			RequestDispatcher rd = request.getRequestDispatcher("Customer_list.jsp");
			request.setAttribute("al", alCustomer);
			rd.forward(request, response);
		} 
		catch (ServletException e) 
		{
			e.printStackTrace();
		}
		catch (IOException e) 
		{
			e.printStackTrace();
		}
	}


	private void validateAdmin(HttpServletRequest request, HttpServletResponse response) 
	{
		String u = request.getParameter("tbUserName");
		String p = request.getParameter("tbPass");
		
		boolean res = AdminDao.validateAdmin(u, p);
		
		if(res)
		{
			getCustomerListPage(request, response);
		}
		else
		{
			try 
			{
				response.sendRedirect(request.getContextPath()+"/default");
			}
			catch (IOException e) 
			{
				e.printStackTrace();
			}
		}

	}




	private void deleteCustomer(HttpServletRequest request, HttpServletResponse response) 
	{
		int i = Integer.parseInt(request.getParameter("id"));
		
		CustomerDao.deleteCustomer(i);
		
		getCustomerListPage(request, response);

	}


	private void updateCustomer(HttpServletRequest request, HttpServletResponse response) 
	{
		int i = Integer.parseInt(request.getParameter("tbId"));
		String n = request.getParameter("tbName");
		String e = request.getParameter("tbEmail");
		long m = Long.parseLong(request.getParameter("tbMobile"));
		
		Customer c = new Customer(i, n, e, m);
		
		CustomerDao.updateCustomer(c);
		
		getCustomerListPage(request, response);
	}


	private void getEditForm(HttpServletRequest request, HttpServletResponse response) 
	{
		int i= Integer.parseInt(request.getParameter("id"));
		
		Customer c = CustomerDao.getOneCustomer(i);
		
		try 
		{
			RequestDispatcher rd = request.getRequestDispatcher("Customer_form.jsp");
			request.setAttribute("customer", c);
			
			rd.forward(request, response);
		} 
		catch (ServletException | IOException e) 
		{
			e.printStackTrace();
		}
	}


	private void getInsertForm(HttpServletRequest request, HttpServletResponse response)
	{
		try 
		{
			RequestDispatcher rd = request.getRequestDispatcher("Customer_form.jsp");

			rd.forward(request, response);
		} 
		catch (ServletException e) 
		{
			e.printStackTrace();
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
	}


	private void insertCustomer(HttpServletRequest request, HttpServletResponse response) 
	{
		String n = request.getParameter("tbName");	
		String e = request.getParameter("tbEmail");
		long m = Long.parseLong(request.getParameter("tbMobile"));

		Customer c = new Customer(n, e, m);
		
		CustomerDao.insertCustomer(c);
		
		getCustomerListPage(request, response);
	}


	private void getStartUpPage(HttpServletRequest request, HttpServletResponse response) 
	{
		try 
		{
			RequestDispatcher rd = request.getRequestDispatcher("Admin_login.jsp");
			rd.forward(request, response);			
		} 
		catch (ServletException e)
		{
			e.printStackTrace();
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		doGet(request, response);
	}

}
