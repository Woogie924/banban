package com.ssafy.subpjt.vo;

public class Payment {
	private int pnum;
	private int peonum;
	private String request;
	private int price;
	private String orderer;
	private int paytype;
	private String storeid;
	private int onum;
	public Payment(int pnum, int peonum, String request, int price, String orderer, int paytype, String storeid,
			int onum) {
		super();
		this.pnum = pnum;
		this.peonum = peonum;
		this.request = request;
		this.price = price;
		this.orderer = orderer;
		this.paytype = paytype;
		this.storeid = storeid;
		this.onum = onum;
	}
	public Payment() {}
	public int getPnum() {
		return pnum;
	}
	public void setPnum(int pnum) {
		this.pnum = pnum;
	}
	public int getPeonum() {
		return peonum;
	}
	public void setPeonum(int peonum) {
		this.peonum = peonum;
	}
	public String getRequest() {
		return request;
	}
	public void setRequest(String request) {
		this.request = request;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public String getOrderer() {
		return orderer;
	}
	public void setOrderer(String orderer) {
		this.orderer = orderer;
	}
	public int getPaytype() {
		return paytype;
	}
	public void setPaytype(int paytype) {
		this.paytype = paytype;
	}
	public String getStoreid() {
		return storeid;
	}
	public void setStoreid(String storeid) {
		this.storeid = storeid;
	}
	public int getOnum() {
		return onum;
	}
	public void setOnum(int onum) {
		this.onum = onum;
	}
	@Override
	public String toString() {
		return "Payment [pnum=" + pnum + ", peonum=" + peonum + ", request=" + request + ", price=" + price
				+ ", orderer=" + orderer + ", paytype=" + paytype + ", storeid=" + storeid + ", onum=" + onum + "]";
	}
	
	
	
}
