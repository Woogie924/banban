package com.ssafy.subpjt.vo;

public class Usercart {
	private int unum;
	private String userid;
	private String menuname;
	private String storeid;
	private int price;
	private int quantity;
	public Usercart(int unum, String userid, String menuname, String storeid, int price, int quantity) {
		super();
		this.unum = unum;
		this.userid = userid;
		this.menuname = menuname;
		this.storeid = storeid;
		this.price = price;
		this.quantity = quantity;
	}
	public Usercart() {
		super();
	}
	public int getUnum() {
		return unum;
	}
	public void setUnum(int unum) {
		this.unum = unum;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getMenuname() {
		return menuname;
	}
	public void setMenuname(String menuname) {
		this.menuname = menuname;
	}
	public String getStoreid() {
		return storeid;
	}
	public void setStoreid(String storeid) {
		this.storeid = storeid;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	@Override
	public String toString() {
		return "Usercart [unum=" + unum + ", userid=" + userid + ", menuname=" + menuname + ", storeid=" + storeid
				+ ", price=" + price + ", quantity=" + quantity + "]";
	}
	
	
}
