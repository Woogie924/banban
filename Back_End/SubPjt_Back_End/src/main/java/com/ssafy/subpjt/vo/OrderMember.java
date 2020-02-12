package com.ssafy.subpjt.vo;

public class OrderMember {
	private int onum;
	private int omnum;
	private String userid;
	private int cnt;
	public OrderMember(int onum, int omnum, String userid, int cnt) {
		super();
		this.onum = onum;
		this.omnum = omnum;
		this.userid = userid;
		this.cnt = cnt;
	}
	public OrderMember() {
		super();
	}
	public int getOnum() {
		return onum;
	}
	public void setOnum(int onum) {
		this.onum = onum;
	}
	public int getOmnum() {
		return omnum;
	}
	public void setOmnum(int omnum) {
		this.omnum = omnum;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public int getCnt() {
		return cnt;
	}
	public void setCnt(int cnt) {
		this.cnt = cnt;
	}
	@Override
	public String toString() {
		return "OrderMember [onum=" + onum + ", omnum=" + omnum + ", userid=" + userid + ", cnt=" + cnt + "]";
	}
	
	
}
