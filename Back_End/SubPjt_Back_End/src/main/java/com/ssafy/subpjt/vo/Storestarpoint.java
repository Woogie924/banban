package com.ssafy.subpjt.vo;

public class Storestarpoint {
	private int storenum;
	private int starnum;
	private String storeid;
	private String body;
	private String regDate;
	
	public Storestarpoint(int storenum, int starnum, String storeid, String body) {
		super();
		this.storenum = storenum;
		this.starnum = starnum;
		this.storeid = storeid;
		this.body = body;
	}

	public Storestarpoint() {
		super();
	}

	public int getStorenum() {
		return storenum;
	}

	public void setStorenum(int storenum) {
		this.storenum = storenum;
	}

	public int getStarnum() {
		return starnum;
	}

	public void setStarnum(int starnum) {
		this.starnum = starnum;
	}

	public String getStoreid() {
		return storeid;
	}

	public void setStoreid(String storeid) {
		this.storeid = storeid;
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
		return "Storestarpoint [storenum=" + storenum + ", starnum=" + starnum + ", storeid=" + storeid + ", body="
				+ body + ", regDate=" + regDate + "]";
	}
	
	

}
