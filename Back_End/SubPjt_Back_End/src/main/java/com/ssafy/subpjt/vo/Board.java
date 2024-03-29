package com.ssafy.subpjt.vo;

import java.util.List;

public class Board {
	private int num;
	private String category;
	private String title;
	private String body;
	private String writer;
	private String regDate;
	private int party;
	
	private int nowmember;
	private int remain;
	private List<PartyMember> partymember;
	private User user;
	private double distance;
	
	
	
	public int getRemain() {
		return remain;
	}

	public void setRemain(int remain) {
		this.remain = remain;
	}

	public int getNowmember() {
		return nowmember;
	}

	public void setNowmember(int nowmember) {
		this.nowmember = nowmember;
	}

	public List<PartyMember> getPartymember() {
		return partymember;
	}

	public void setPartymember(List<PartyMember> partymember) {
		this.partymember = partymember;
	}

	public double getDistance() {
		return distance;
	}

	public void setDistance(double distance) {
		this.distance = distance;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Board() {
		super();
	}

	public Board(int num, String category, String title, String body, String writer, String regDate, int party,
			User user) {
		super();
		this.num = num;
		this.category = category;
		this.title = title;
		this.body = body;
		this.writer = writer;
		this.regDate = regDate;
		this.party = party;
		this.user = user;
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public String getWriter() {
		return writer;
	}

	public void setWriter(String writer) {
		this.writer = writer;
	}

	public String getRegDate() {
		return regDate;
	}

	public void setRegDate(String regDate) {
		this.regDate = regDate;
	}

	public int getParty() {
		return party;
	}

	public void setParty(int party) {
		this.party = party;
	}

	@Override
	public String toString() {
		return "Board [num=" + num + ", category=" + category + ", title=" + title + ", body=" + body + ", writer="
				+ writer + ", regDate=" + regDate + ", party=" + party + ", nowmember=" + nowmember + ", remain="
				+ remain + ", partymember=" + partymember + ", user=" + user + ", distance=" + distance + "]";
	}


	




	
	
}
