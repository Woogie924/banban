package com.ssafy.socket.domain;

public class Message {
	private String sender;
	private String receiver;
	private String message;
	private Object data;
	public Message(String sender, String receiver, String message, Object data) {
		super();
		this.sender = sender;
		this.receiver = receiver;
		this.message = message;
		this.data = data;
	}
	
	public Message() {}

	public String getSender() {
		return sender;
	}

	public void setSender(String sender) {
		this.sender = sender;
	}

	public String getReceiver() {
		return receiver;
	}

	public void setReceiver(String receiver) {
		this.receiver = receiver;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	@Override
	public String toString() {
		return "Message [sender=" + sender + ", receiver=" + receiver + ", message=" + message + ", data=" + data + "]";
	}
	
	
	
	
}
