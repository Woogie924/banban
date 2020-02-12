package com.ssafy.subpjt.vo;

public class User {
	private String id;
	private String pw;
	private String tel;
	private String name;
	private String email;
	private String address;
	private double latitude;
	private double longitude;
	private int point;
	private String signupDate;
	private int chk;
	public User(String id, String pw, String tel, String name, String email, String address, double latitude,
			double longitude, int point, String signupDate, int chk) {
		super();
		this.id = id;
		this.pw = pw;
		this.tel = tel;
		this.name = name;
		this.email = email;
		this.address = address;
		this.latitude = latitude;
		this.longitude = longitude;
		this.point = point;
		this.signupDate = signupDate;
		this.chk = chk;
	}
	public User() {
		super();
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
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
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
		return "User [id=" + id + ", pw=" + pw + ", tel=" + tel + ", name=" + name + ", email=" + email + ", address="
				+ address + ", latitude=" + latitude + ", longitude=" + longitude + ", point=" + point + ", signupDate="
				+ signupDate + ", chk=" + chk + "]";
	}
	
	

	
}
