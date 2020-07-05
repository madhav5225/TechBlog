package com.tech.blog.servlets;

import java.io.IOException;
import java.util.Vector;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.tech.blog.dao.PostDao;
import com.tech.blog.entities.Categories;
import com.tech.blog.entities.Posts;
import com.tech.blog.helper.ConnectionProvider;

/**
 * Servlet implementation class LoadPage
 */
@WebServlet("/LoadPage")
public class LoadPage extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoadPage() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	int catid=Integer.parseInt(request.getParameter("catid"));
	HttpSession session =request.getSession();
	session.setAttribute("catid",catid);
	response.sendRedirect("index.jsp");
	}

}
