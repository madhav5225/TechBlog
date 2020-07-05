package com.tech.blog.servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.tech.blog.dao.UserDao;
import com.tech.blog.entities.MessageClass;
import com.tech.blog.entities.User;
import com.tech.blog.helper.ConnectionProvider;

import java.sql.*;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		Connection con=ConnectionProvider.getConnection();
		UserDao usd=new UserDao(con);
		User user=usd.getUser(request.getParameter("email"), request.getParameter("password"));
		if(user==null)
		{
			MessageClass msg=new MessageClass("Wrong Credentials!! Try with another","Error","alert alert-danger");
			
			HttpSession session=request.getSession();
			session.setAttribute("message", msg);
			
			
			response.sendRedirect("LoginPage.jsp");
			
		}
		else
		{
			HttpSession session=request.getSession();
			session.setAttribute("user", user);
			response.sendRedirect("index.jsp");
		}
	}

}
