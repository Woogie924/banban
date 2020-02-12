package com.ssafy.subpjt.vo;

public class Likes {
	private String userid;
	private String storeid;
	public Likes(String userid, String storeid) {
		super();
		this.userid = userid;
		this.storeid = storeid;
	}
	public Likes() {
		super();
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getStoreid() {
		return storeid;
	}
	public void setStoreid(String storeid) {
		this.storeid = storeid;
	}
	@Override
	public String toString() {
		return "Likes [userid=" + userid + ", storeid=" + storeid + "]";
	}
	
	
}
