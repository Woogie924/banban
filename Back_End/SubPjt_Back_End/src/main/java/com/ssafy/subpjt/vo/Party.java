package com.ssafy.subpjt.vo;

import lombok.Data;

@Data
public class Party {
	private int bnum;
	private int pnum;
	private int max;
	private int nowmember;
	public Party(int bnum, int pnum, int max, int nowmember) {
		super();
		this.bnum = bnum;
		this.pnum = pnum;
		this.max = max;
		this.nowmember = nowmember;
	}

	public Party(int bnum, int max) {
		super();
		this.bnum = bnum;
		this.max = max;
	}
	public Party() {
		super();
	}







}
