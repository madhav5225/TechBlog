package com.tech.blog.entities;
import java.sql.*;
public class Posts {
	private int pid;
	private String ptitle;
	private String pcontent;
	private String pimage;
	private Timestamp pdate;
	private int uid;
	private int cid;
	private int votecount;
	
	public int getVotecount() {
		return votecount;
	}
	public void setVotecount(int votecount) {
		this.votecount = votecount;
	}
	public Posts(int pid, String ptitle, String pcontent, String pimage, Timestamp pdate, int uid, int cid,
			int votecount) {
		super();
		this.pid = pid;
		this.ptitle = ptitle;
		this.pcontent = pcontent;
		this.pimage = pimage;
		this.pdate = pdate;
		this.uid = uid;
		this.cid = cid;
		this.votecount = votecount;
	}
	public Posts(int pid, String ptitle, String pcontent, String pimage, Timestamp pdate, int uid, int cid) {
		super();
		this.pid = pid;
		this.ptitle = ptitle;
		this.pcontent = pcontent;
		this.pimage = pimage;
		this.pdate = pdate;
		this.uid = uid;
	    this.cid=cid;
	}
	public Posts(String ptitle, String pcontent, String pimage, int uid, int cid) {
		super();
		this.ptitle = ptitle;
		this.pcontent = pcontent;
		this.pimage = pimage;
		this.uid = uid;
		this.cid=cid;
	}
	
	public int getCid() {
		return cid;
	}
	public void setCid(int cid) {
		this.cid = cid;
	}
	public int getPid() {
		return pid;
	}
	public void setPid(int pid) {
		this.pid = pid;
	}
	public String getPtitle() {
		return ptitle;
	}
	public void setPtitle(String ptitle) {
		this.ptitle = ptitle;
	}
	public String getPcontent() {
		return pcontent;
	}
	public void setPcontent(String pcontent) {
		this.pcontent = pcontent;
	}
	public String getPimage() {
		return pimage;
	}
	public void setPimage(String pimage) {
		this.pimage = pimage;
	}
	public Timestamp getPdate() {
		return pdate;
	}
	public void setPdate(Timestamp pdate) {
		this.pdate = pdate;
	}
	public int getUid() {
		return uid;
	}
	public void setUid(int uid) {
		this.uid = uid;
	}
	
	// constructors
	
	
	

}
