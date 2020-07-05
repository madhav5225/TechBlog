package com.tech.blog.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

import com.tech.blog.entities.Categories;
import com.tech.blog.entities.Posts;
import com.tech.blog.entities.User;
import com.tech.blog.entities.votes;
import com.tech.blog.helper.ConnectionProvider;

public class PostDao {
	private Connection con;

	public PostDao(Connection con) {
		super();
		this.con = con;
	}
	
	public Vector<Categories> getAllCategories()
	{
		Connection con=ConnectionProvider.getConnection();
		String query="select * from categories;";
		Vector<Categories>data=new Vector<Categories>();
		try {
			Statement stmt=con.createStatement();
			ResultSet rs=stmt.executeQuery(query);
			
			
			 
			while(rs.next())
			{ 
				
			Categories cat=new Categories(rs.getInt(1),rs.getString(2));	
			data.add(cat);	
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return data;
	}
	public boolean savePost(Posts post) 
	{   
		boolean flag=false;
		
		String query="insert into posts(ptitle,pcontent,pimage,userid,cid) values ('"
				+ post.getPtitle()+"','"
				+ post.getPcontent()+"','"
				+ post.getPimage()+"',"
				+ post.getUid()+","
				+ post.getCid()+") "; 
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
	public boolean doVote(votes vote) 
	{   
		boolean flag=false;
		String query1="select * from votes where (uid="+vote.getUid()+" and pid="+ vote.getPid() +");";
			
		String query2="insert into votes(uid,pid,vote) values ("
				+ vote.getUid()+","
				+ vote.getPid()+","
				+ vote.getVote()+") "; 
		int voteValue=vote.getVote();

			
		String query3="update votes set vote= "+voteValue+" where (pid = "+vote.getPid()+" && uid="+vote.getUid()+");"; 
		
				
		try
		{
		Statement stmt=con.createStatement();
		ResultSet rs=stmt.executeQuery(query1);
		if(rs.next())
		{
			stmt.executeUpdate(query3);
			flag=true;
		}
		else
		{
			stmt.executeUpdate(query2);
			flag=true;
		}
		}
		catch(Exception e)
		{
         e.printStackTrace();
		}
		System.out.println(query3);
		System.out.println(vote.getVote());
		System.out.println(voteValue);
		
		return flag;
   }
	public int getLastPid(int uid ,int cid)
	{
		Connection con=ConnectionProvider.getConnection();
		String query="select * from posts where (userid="+uid+" and cid="+ cid +");";
		int pid=0;
		try {
			Statement stmt=con.createStatement();
			ResultSet rs=stmt.executeQuery(query);
			
			
			 
			while(rs.next())
			{ 
	        pid=rs.getInt(1);			
		}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return pid;
	}
	public int isVoted(int uid ,int pid)
	{
		int vote=0;
		Connection con=ConnectionProvider.getConnection();
		String query="select * from votes where (uid="+uid+" and pid="+ pid +");";
		try {
			Statement stmt=con.createStatement();
			ResultSet rs=stmt.executeQuery(query);
			
			
			 
			if(rs.next())
			{ 
	        vote=rs.getInt(4);			
		}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return vote;
	}
	public Vector<Posts> getAllPosts()
	{
		Connection con=ConnectionProvider.getConnection();
		String query="select * from posts order by regdate desc;";
		Vector<Posts>data=new Vector<Posts>();
		try {
			Statement stmt=con.createStatement();
			ResultSet rs=stmt.executeQuery(query);
			
			
			 
			while(rs.next())
			{ 
				
	Posts post=new Posts(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getTimestamp(5),rs.getInt(6),rs.getInt(7),rs.getInt(8));	
						data.add(post);	
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return data;
	}
	public Vector<Posts> getAllPosts(int id)
	{
		Connection con=ConnectionProvider.getConnection();
		String query="select * from posts where cid="+id+" order by regdate desc;";
		Vector<Posts>data=new Vector<Posts>();
		try {
			Statement stmt=con.createStatement();
			ResultSet rs=stmt.executeQuery(query);
			
			
			 
			while(rs.next())
			{ 
				
		Posts post=new Posts(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getTimestamp(5),rs.getInt(6),rs.getInt(7),rs.getInt(8));	
			data.add(post);	
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return data;
	}
	public boolean updateVotes(String action,int id) 
	{   
		boolean flag=false;
		String query;
		if(action.equals("upvote"))
		 query="update posts set votecount=votecount +1 where pid="+id+";"; 
		else
		 query="update posts set votecount=votecount -1 where pid="+id+";"; 
				
		System.out.println(query);
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
	
}
