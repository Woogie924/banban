package com.ssafy.subpjt.vo;

public class OrderFood {
	private int onum;
	private String menu;
	private int price;
	private String userid;
	private String address;
	private int peonum;
	private String storeid;
	public OrderFood(int onum, String menu, int price, String userid, String address, int peonum, String storeid) {
		super();
		this.onum = onum;
		this.menu = menu;
		this.price = price;
		this.userid = userid;
		this.address = address;
		this.peonum = peonum;
		this.storeid = storeid;
	}
	
	public OrderFood() {}

	public int getOnum() {
		return onum;
	}

	public void setOnum(int onum) {
		this.onum = onum;
	}

	public String getMenu() {
		return menu;
	}

	public void setMenu(String menu) {
		this.menu = menu;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public int getPeonum() {
		return peonum;
	}

	public void setPeonum(int peonum) {
		this.peonum = peonum;
	}

	public String getStoreid() {
		return storeid;
	}

	public void setStoreid(String storeid) {
		this.storeid = storeid;
	}

	@Override
	public String toString() {
		return "OrderFood [onum=" + onum + ", menu=" + menu + ", price=" + price + ", userid=" + userid + ", address="
				+ address + ", peonum=" + peonum + ", storeid=" + storeid + "]";
	}
	
	
	
}
