package com.tech.blog.servlets;

import java.io.IOException;
import java.sql.Connection;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import com.tech.blog.dao.PostDao;
import com.tech.blog.dao.UserDao;
import com.tech.blog.entities.MessageClass;
import com.tech.blog.entities.Posts;
import com.tech.blog.entities.User;
import com.tech.blog.helper.ConnectionProvider;
import com.tech.blog.helper.ImageHelper;

/**
 * Servlet implementation class AddPostServlet
 */
@WebServlet("/AddPostServlet")
@MultipartConfig
public class AddPostServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddPostServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		 String title=request.getParameter("post_title");
		 String content=request.getParameter("post_content");
		 Part part=request.getPart("post_image");
    	 String image=part.getSubmittedFileName();
    	 int cid=Integer.parseInt(request.getParameter("post_cid"));
		
		// retrieving user id 
		 HttpSession session =request.getSession();
		 User user=(User)session.getAttribute("user");
		 int uid=user.getId();
		 Posts post=new Posts(title, content, image, uid, cid);
		 ImageHelper imH=new ImageHelper();
		 PostDao psd=new PostDao(ConnectionProvider.getConnection());
		
			 	
			  if(psd.savePost(post))
			 {
			  
				 String path=request.getRealPath("/")+"img/postPics/"+""+psd.getLastPid(uid,cid)+image;
				 
			     if(image.equals(""))
			     {
			    	 MessageClass msg=new MessageClass("Your Data has been updated sucessfully!!!",
							 "Success",
							 "alert alert-success alert-dismissible fade show");
	     	         session.setAttribute("message",msg);
		 
			     }
				 
				 else if(!imH.saveImage(part.getInputStream(),path))
			      {
			    	//error Message
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
