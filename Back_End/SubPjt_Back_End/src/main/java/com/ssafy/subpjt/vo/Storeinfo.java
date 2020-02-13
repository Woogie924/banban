package com.ssafy.subpjt.vo;

import java.util.List;

public class Storeinfo {
	private int snum;
	private String storeid;
	private int minprice;
	private int deliverytip;
	private String deliverytime;
	
	private List<Starpoint> starpoint;
	private List<Storestarpoint> storestarpoint;
	private double point;
	private String storename;
	
	
	
	
	public List<Storestarpoint> getStorestarpoint() {
		return storestarpoint;
	}
	public void setStorestarpoint(List<Storestarpoint> storestarpoint) {
		this.storestarpoint = storestarpoint;
	}
	public String getStorename() {
		return storename;
	}
	public void setStorename(String storename) {
		this.storename = storename;
	}
	public List<Starpoint> getStarpoint() {
		return starpoint;
	}
	public void setStarpoint(List<Starpoint> starpoint) {
		this.starpoint = starpoint;
	}
	public double getPoint() {
		return point;
	}
	public void setPoint(double point) {
		this.point = point;
	}
	public Storeinfo(int snum, String storeid, int minprice, int deliverytip, String deliverytime) {
		super();
		this.snum = snum;
		this.storeid = storeid;
		this.minprice = minprice;
		this.deliverytip = deliverytip;
		this.deliverytime = deliverytime;
	}
	public Storeinfo() {
		super();
	}
	public int getSnum() {
		return snum;
	}
	public void setSnum(int snum) {
		this.snum = snum;
	}
	public String getStoreid() {
		return storeid;
	}
	public void setStoreid(String storeid) {
		this.storeid = storeid;
	}
	public int getMinprice() {
		return minprice;
	}
	public void setMinprice(int minprice) {
		this.minprice = minprice;
	}
	public int getDeliverytip() {
		return deliverytip;
	}
	public void setDeliverytip(int deliverytip) {
		this.deliverytip = deliverytip;
	}
	public String getDeliverytime() {
		return deliverytime;
	}
	public void setDeliverytime(String deliverytime) {
		this.deliverytime = deliverytime;
	}
	@Override
	public String toString() {
		return "Storeinfo [snum=" + snum + ", storeid=" + storeid + ", minprice=" + minprice + ", deliverytip="
				+ deliverytip + ", deliverytime=" + deliverytime + ", starpoint=" + starpoint + ", storestarpoint="
				+ storestarpoint + ", point=" + point + ", storename=" + storename + "]";
	}

	
	
}
