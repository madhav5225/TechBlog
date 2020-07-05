package com.tech.blog.entities;

public class votes {
	private int vid;
	private int uid;
	private int pid;
	private int vote;
	
	public int getVote() {
		return vote;
	}
	public void setVote(int vote) {
		this.vote = vote;
	}
	public votes() {
		super();
	}

	public votes(int vid, int uid, int pid, int vote) {
		super();
		this.vid = vid;
		this.uid = uid;
		this.pid = pid;
		this.vote = vote;
	}
	public votes(int uid, int pid, int vote) {
		super();
		this.uid = uid;
		this.pid = pid;
		this.vote = vote;
	}
	public int getVid() {
		return vid;
	}
	public void setVid(int vid) {
		this.vid = vid;
	}
	public int getUid() {
		return uid;
	}
	public void setUid(int uid) {
		this.uid = uid;
	}
	public int getPid() {
		return pid;
	}
	public void setPid(int pid) {
		this.pid = pid;
	}
	

}
