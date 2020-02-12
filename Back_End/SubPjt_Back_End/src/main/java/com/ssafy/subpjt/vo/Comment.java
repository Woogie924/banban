package com.ssafy.subpjt.vo;


public class Comment {
	private int cnum;
	private int bnum;
	private String writer;
	private String body;
	private String regDate;
	
	public Comment() {
		
	}
	public Comment(int cnum, int bnum, String writer, String body, String regDate) {
		super();
		this.cnum = cnum;
		this.bnum = bnum;
		this.writer = writer;
		this.body = body;
		this.regDate = regDate;
	}
	public int getCnum() {
		return cnum;
	}
	public void setCnum(int cnum) {
		this.cnum = cnum;
	}
	public int getBnum() {
		return bnum;
	}
	public void setBnum(int bnum) {
		this.bnum = bnum;
	}
	public String getWriter() {
		return writer;
	}
	public void setWriter(String writer) {
		this.writer = writer;
	}
	public String getBody() {
		return body;
	}
	public void setBody(String body) {
		this.body = body;
	}
	public String getRegDate() {
		return regDate;
	}
	public void setRegDate(String regDate) {
		this.regDate = regDate;
	}
	@Override
	public String toString() {
		return "Comment [cnum=" + cnum + ", bnum=" + bnum + ", writer=" + writer + ", body=" + body + ", regDate="
				+ regDate + "]";
	}
	
	
}
