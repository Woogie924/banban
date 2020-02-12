package com.ssafy.subpjt.vo;

public class EmailAuthorization {
	private String to;

	public EmailAuthorization(String to) {
		super();
		this.to = to;
	}

	public EmailAuthorization() {
		super();
	}

	public String getTo() {
		return to;
	}

	public void setTo(String to) {
		this.to = to;
	}

	@Override
	public String toString() {
		return "EmailAuthorization [to=" + to + "]";
	}

}
