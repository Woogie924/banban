package com.ssafy.subpjt.vo;

public class Starpoint {
	private int starnum;
	private String userid;
	private String storeid;
	private double starpoint;
	private String menu;
	private String body;
	private String regDate;
	public Starpoint(int starnum, String userid, String storeid, double starpoint, String menu, String body,
			String regDate) {
		super();
		this.starnum = starnum;
		this.userid = userid;
		this.storeid = storeid;
		this.starpoint = starpoint;
		this.menu = menu;
		this.body = body;
		this.regDate = regDate;
	}
	public Starpoint() {
		super();
	}
	public int getStarnum() {
		return starnum;
	}
	public void setStarnum(int starnum) {
		this.starnum = starnum;
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
	public double getStarpoint() {
		return starpoint;
	}
	public void setStarpoint(double starpoint) {
		this.starpoint = starpoint;
	}
	public String getMenu() {
		return menu;
	}
	public void setMenu(String menu) {
		this.menu = menu;
	}
	public String getBody() {
		return body;
	}
	public void setBody(String body) {
		this.body = body;
	}
	public String getRegDate() {
		return regDate;
	}
	public void setRegDate(String regDate) {
		this.regDate = regDate;
	}
	@Override
	public String toString() {
		return "Starpoint [starnum=" + starnum + ", userid=" + userid + ", storeid=" + storeid + ", starpoint="
				+ starpoint + ", menu=" + menu + ", body=" + body + ", regDate=" + regDate + "]";
	}
	
	
	
	
	
	
}
