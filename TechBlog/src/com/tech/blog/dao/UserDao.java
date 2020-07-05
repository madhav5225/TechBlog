package com.tech.blog.dao;
import java.sql.*;

import com.tech.blog.entities.User;
import com.tech.blog.helper.ConnectionProvider;
public class UserDao {

	private Connection con;

	public UserDao(Connection con) {
		super();
		this.con = con;
	}
	public boolean saveUser(User user) 
	{   
		boolean flag=false;
		
		String query="insert into user(name,email,password,gender,about) values ('"
				+ user.getName()+"','"
				+ user.getEmail()+"','"
				+ user.getPassword()+"','"
				+ user.getGender()+"','"
				+ user.getAbout()+"') "; 
		try
		{
		Statement stmt=con.createStatement();
		stmt.executeUpdate(query);
		flag=true;
		}
		catch(Exception e)
		{
         e.printStackTrace();
		}
		return flag;
   }
	public User getUser(String email,String password)
	{
		User user=null;
		Connection con=ConnectionProvider.getConnection();
		String query="select * from user where(email='"
				+ email+"' and password='"
				+ password+"');";
		try {
			Statement stmt=con.createStatement();
			ResultSet rs=stmt.executeQuery(query);
			
			if(rs.next())
			{  user=new User();
				user.setId(rs.getInt(1));
				user.setName(rs.getString(2));
				user.setEmail(rs.getString(3));
				user.setPassword(rs.getString(4));
				user.setGender(rs.getString(5));
				user.setAbout(rs.getString(6));
				user.setDate(rs.getTimestamp(7));
				user.setProfile(rs.getString(8));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return user;
	}
	public User getUser(int id)
	{
		User user=null;
		Connection con=ConnectionProvider.getConnection();
		String query="select * from user where(id="+id+");";
			
		try {
			Statement stmt=con.createStatement();
			ResultSet rs=stmt.executeQuery(query);
			
			if(rs.next())
			{  user=new User();
				user.setId(rs.getInt(1));
				user.setName(rs.getString(2));
				user.setEmail(rs.getString(3));
				user.setPassword(rs.getString(4));
				user.setGender(rs.getString(5));
				user.setAbout(rs.getString(6));
				user.setDate(rs.getTimestamp(7));
				user.setProfile(rs.getString(8));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return user;
	}
	public boolean updateUser(User user) 
	{   
		boolean flag=false;
		
		String query="update user set name='"
				+ user.getName()+"',password='"
				+  user.getPassword()+"',about='"
				+  user.getAbout()+"',profile='"
				+  user.getProfile()+"' where id="
				+  user.getId()+";";
		try
		{
		Statement stmt=con.createStatement();
		stmt.executeUpdate(query);
		System.out.println(query);
		flag=true;
		}
		catch(Exception e)
		{
         e.printStackTrace();
		}
		return flag;
   }
	public boolean validateEmail(String email)
	{
		boolean flag=true;
		Connection con=ConnectionProvider.getConnection();
		String query="select * from user where(email='"+email+"');";
			
		try {
			Statement stmt=con.createStatement();
			ResultSet rs=stmt.executeQuery(query);
			
			if(!rs.next())
			{
				flag=false;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return flag;
	}
}