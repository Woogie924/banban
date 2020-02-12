package com.ssafy.subpjt.vo;

import java.util.List;

public class Store {
	private String id;
	private String pw;
	private String tel;
	private String name;
	private String address;
	private String category;
	private int point;
	private double latitude;
	private double longitude;
	private String signupDate;
	private int chk;
	private boolean liked;
	
	private List<Menu> menu;
	
	

	public List<Menu> getMenu() {
		return menu;
	}

	public void setMenu(List<Menu> lList) {
		this.menu = lList;
	}

	public boolean isLiked() {
		return liked;
	}

	public void setLiked(boolean liked) {
		this.liked = liked;
	}

	public Store() {
		super();
	}

	public Store(String id, String pw, String tel, String name, String address, String category, int point,
			double latitude, double longitude, String signupDate, int chk) {
		super();
		this.id = id;
		this.pw = pw;
		this.tel = tel;
		this.name = name;
		this.address = address;
		this.category = category;
		this.point = point;
		this.latitude = latitude;
		this.longitude = longitude;
		this.signupDate = signupDate;
		this.chk = chk;
	}

	

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPw() {
		return pw;
	}
	public void setPw(String pw) {
		this.pw = pw;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public int getPoint() {
		return point;
	}
	public void setPoint(int point) {
		this.point = point;
	}
	public String getSignupDate() {
		return signupDate;
	}
	public void setSignupDate(String signupDate) {
		this.signupDate = signupDate;
	}
	public int getChk() {
		return chk;
	}
	public void setChk(int chk) {
		this.chk = chk;
	}

	@Override
	public String toString() {
		return "Store [id=" + id + ", pw=" + pw + ", tel=" + tel + ", name=" + name + ", address=" + address
				+ ", category=" + category + ", point=" + point + ", latitude=" + latitude + ", longitude=" + longitude
				+ ", signupDate=" + signupDate + ", chk=" + chk + ", liked=" + liked + ", menu=" + menu + "]";
	}

	

	
}
