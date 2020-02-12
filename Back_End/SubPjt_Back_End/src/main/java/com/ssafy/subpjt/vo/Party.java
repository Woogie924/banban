package com.ssafy.subpjt.vo;

public class Party {
	private int bnum;
	private int pnum;
	private int max;
	
	public Party() {
	}

	public Party(int bnum, int max) {
		this.max = max;
		this.bnum = bnum;
	}
	
	public Party(int bnum, int pnum, int max) {
		super();
		this.bnum = bnum;
		this.pnum = pnum;
		this.max = max;
	}

	public int getBnum() {
		return bnum;
	}

	public void setBnum(int bnum) {
		this.bnum = bnum;
	}

	public int getPnum() {
		return pnum;
	}

	public void setPnum(int pnum) {
		this.pnum = pnum;
	}

	public int getMax() {
		return max;
	}

	public void setMax(int max) {
		this.max = max;
	}

	@Override
	public String toString() {
		return "Party [bnum=" + bnum + ", pnum=" + pnum + ", max=" + max + "]";
	}
	
	
}
