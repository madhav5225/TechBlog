package com.tech.blog.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.tech.blog.dao.PostDao;
import com.tech.blog.entities.User;
import com.tech.blog.entities.votes;
import com.tech.blog.helper.ConnectionProvider;

/**
 * Servlet implementation class Voting
 */
@WebServlet("/Voting")
public class Voting extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Voting() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PostDao psd=new PostDao(ConnectionProvider.getConnection());
	    
		psd.updateVotes(request.getParameter("action"), Integer.parseInt(request.getParameter("id")));
		HttpSession session =request.getSession();
		User user=(User)session.getAttribute("user");
		votes vot;
		if(request.getParameter("action").equals("upvote"))
		 vot=new votes(user.getId(),Integer.parseInt(request.getParameter("id")),1);
		else
			 vot=new votes(user.getId(),Integer.parseInt(request.getParameter("id")),-1);
			
		psd.doVote(vot);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
