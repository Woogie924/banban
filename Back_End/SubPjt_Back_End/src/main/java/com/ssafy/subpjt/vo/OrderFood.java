package com.ssafy.subpjt.vo;

import lombok.Data;

@Data
public class OrderFood {
	private int onum;
	private String menu;
	private int price;
	private String userid1;
	private String userid2;
	private String address1;
	private String address2;
	private String tel1;
	private String tel2;
	private int peonum;
	private String storeid;
	private int ordercheck;
	private String orderDate;
	
	private int totalprice;
	private String storename;
	
	public OrderFood() {
		super();
	}


	public OrderFood(int onum, String menu, int price, String userid1, String userid2, String address1, String address2,
			String tel1, String tel2, int peonum, String storeid, int ordercheck, String orderDate) {
		super();
		this.onum = onum;
		this.menu = menu;
		this.price = price;
		this.userid1 = userid1;
		this.userid2 = userid2;
		this.address1 = address1;
		this.address2 = address2;
		this.tel1 = tel1;
		this.tel2 = tel2;
		this.peonum = peonum;
		this.storeid = storeid;
		this.ordercheck = ordercheck;
		this.orderDate = orderDate;
	}
	
	
	
	
	
	
}
