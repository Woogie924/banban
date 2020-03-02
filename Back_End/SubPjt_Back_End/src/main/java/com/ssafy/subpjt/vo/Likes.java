package com.ssafy.subpjt.vo;

import lombok.Data;

@Data
public class Likes {
	private String userid;
	private String storeid;
	public Likes(String userid, String storeid) {
		super();
		this.userid = userid;
		this.storeid = storeid;
	}
	public Likes() {
		super();
	}

	
}
