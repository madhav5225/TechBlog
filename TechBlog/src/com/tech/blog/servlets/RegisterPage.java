package com.tech.blog.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.sql.*;

import com.tech.blog.dao.UserDao;
import com.tech.blog.entities.MessageClass;
import com.tech.blog.entities.User;
import com.tech.blog.helper.ConnectionProvider;

/**
 * Servlet implementation class RegisterPage
 */
@WebServlet("/RegisterPage")
public class RegisterPage extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegisterPage() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */


	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
		User user=new User(
        		request.getParameter("user_name"),
        		request.getParameter("user_email"),
        		request.getParameter("user_password"),
        		request.getParameter("gender"),
        		request.getParameter("about")
        		); 
		Connection con=ConnectionProvider.getConnection();
		UserDao usd=new UserDao(con);
		usd.saveUser(user);
		 MessageClass msg=new MessageClass("Your have been successfully registered!!!",
				 "Success",
				 "alert alert-success alert-dismissible fade show");

		HttpSession session=request.getSession();
		session.setAttribute("message", msg);
		
		
		response.sendRedirect("index.jsp");
	}

}
