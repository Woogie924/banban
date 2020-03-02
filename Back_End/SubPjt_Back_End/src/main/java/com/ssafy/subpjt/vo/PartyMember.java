package com.ssafy.subpjt.vo;

public class PartyMember {
	private int pmnum;
	private int pnum;
	private String id;
	
	public PartyMember() {}

	public PartyMember(int pmnum, int pnum, String id) {
		super();
		this.pmnum = pmnum;
		this.pnum = pnum;
		this.id = id;
	}
	
	public PartyMember(int pnum, String id) {
		super();
		this.pnum = pnum;
		this.id = id;
	}

	
	public int getPmnum() {
		return pmnum;
	}

	public void setPmnum(int pmnum) {
		this.pmnum = pmnum;
	}

	public int getPnum() {
		return pnum;
	}

	public void setPnum(int pnum) {
		this.pnum = pnum;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "PartyMember [pmnum=" + pmnum + ", pnum=" + pnum + ", id=" + id + "]";
	}


	
	
}
