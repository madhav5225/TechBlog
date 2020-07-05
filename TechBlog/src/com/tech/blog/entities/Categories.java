package com.tech.blog.entities;

public class Categories {
	
	private  int cid;
	private  String cname;
	public int getCid() {
		return cid;
	}
	public void setCid(int cid) {
		this.cid = cid;
	}
	public String getCname() {
		return cname;
	}
	public void setCname(String cname) {
		this.cname = cname;
	}
	
	//Constructors
	
	public Categories(int cid, String cname) {
		super();
		this.cid = cid;
		this.cname = cname;
	}
	public Categories(String cname) {
		super();
		this.cname = cname;
	}
	public Categories() {
		super();
	}
	

}
