package com.ssafy.subpjt.vo;


public class BillingHistory {
	private String id;
	private int bnum;
	private int price;
	private int onum;
	private String menuname;
	private String address;
	private String orderer;
	private String paymentDate;
	public BillingHistory() {
		super();
	}
	public BillingHistory(String id, int bnum, int price, int onum, String menuname, String address, String orderer,
			String paymentDate) {
		super();
		this.id = id;
		this.bnum = bnum;
		this.price = price;
		this.onum = onum;
		this.menuname = menuname;
		this.address = address;
		this.orderer = orderer;
		this.paymentDate = paymentDate;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public int getBnum() {
		return bnum;
	}
	public void setBnum(int bnum) {
		this.bnum = bnum;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public int getOnum() {
		return onum;
	}
	public void setOnum(int onum) {
		this.onum = onum;
	}
	public String getMenuname() {
		return menuname;
	}
	public void setMenuname(String menuname) {
		this.menuname = menuname;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getOrderer() {
		return orderer;
	}
	public void setOrderer(String orderer) {
		this.orderer = orderer;
	}
	public String getPaymentDate() {
		return paymentDate;
	}
	public void setPaymentDate(String paymentDate) {
		this.paymentDate = paymentDate;
	}
	@Override
	public String toString() {
		return "BillingHistory [id=" + id + ", bnum=" + bnum + ", price=" + price + ", onum=" + onum + ", menuname="
				+ menuname + ", address=" + address + ", orderer=" + orderer + ", paymentDate=" + paymentDate + "]";
	}
	
	
}
