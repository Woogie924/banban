package com.ssafy.subpjt.vo;

import lombok.Data;

@Data
public class Usercart {
	private int unum;
	private String userid;
	private String menuname;
	private String storeid;
	private String storename;
	private int price;
	private int quantity;
	
	
}
