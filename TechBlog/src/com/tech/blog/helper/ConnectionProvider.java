package com.tech.blog.helper;
import java.sql.*;
public class ConnectionProvider {

  private static Connection con;
  public static Connection getConnection() {
	  try
	  {
	  if(con==null)
	  {
		  String url="jdbc:mysql://localhost:3306/techblog";
	      String usr="root";
	      String pss="root";
	      Class.forName("com.mysql.cj.jdbc.Driver");
	     	Connection con=DriverManager.getConnection(url,usr,pss);
		       return con;
			   	
  }
	  }
	  catch(Exception e)
	  {
		  e.printStackTrace();
	  }	
	  return con;
  }
}
