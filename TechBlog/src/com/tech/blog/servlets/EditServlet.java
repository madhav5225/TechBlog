package com.tech.blog.servlets;

import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import com.tech.blog.dao.UserDao;
import com.tech.blog.entities.MessageClass;
import com.tech.blog.entities.User;
import com.tech.blog.helper.ConnectionProvider;
import com.tech.blog.helper.ImageHelper;

@WebServlet("/EditServlet")
@MultipartConfig
public class EditServlet extends HttpServlet {
       

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		 
		 String name=request.getParameter("user_name");
		 String password=request.getParameter("user_password");
		 String about=request.getParameter("user_about");
		 Part part=request.getPart("user_image");
     	 String profile=part.getSubmittedFileName();
		 
		 
		 
		 HttpSession session =request.getSession();
		 User user=(User)session.getAttribute("user");
		 String oldProfile=user.getProfile();;
		 String oldProfilePath=request.getRealPath("/")+"img/pics/"+""+user.getId()+user.getProfile();
		 user.setName(name);
		 user.setPassword(password);
		 user.setAbout(about);
		 user.setProfile(profile);

		 UserDao usd=new UserDao(ConnectionProvider.getConnection());
		 ImageHelper imH=new ImageHelper();
		
		 if(profile.equals(""))
		 {    user.setProfile(oldProfile);

			 if(! usd.updateUser(user))
			 {
				 MessageClass msg=new MessageClass("A problem has been occurred while submitting your data, Plz try again"
						 ,"Error"
						 ,"alert alert-danger alert-dismissible fade show");
				 session.setAttribute("message",msg);
			  
			 }
			 else
			 {
				 MessageClass msg=new MessageClass("Your Data has been updated sucessfully!!!",
						 "Success",
						 "alert alert-success alert-dismissible fade show");
				 session.setAttribute("message",msg);
			  
			 }
			
		 }
			 
			 else if(usd.updateUser(user) && imH.delete(oldProfilePath))
			 {
				 MessageClass msg=new MessageClass("Your Data has been updated sucessfully!!!",
						 "Success",
						 "alert alert-success alert-dismissible fade show");
     	 session.setAttribute("message",msg);
			  
				 String path=request.getRealPath("/")+"img/pics/"+""+user.getId()+profile;
			 boolean bs= imH.saveImage(part.getInputStream(),path);
			
			 }
			 
		 
		 else
		 {
			//error Message
			 MessageClass msg=new MessageClass("A problem has been occurred while submitting your data, Plz try again"
					 ,"Error"
					 ,"alert alert-danger alert-dismissible fade show");
			 session.setAttribute("message",msg);
		   
		 }
		 response.sendRedirect("index.jsp");
	}

}
