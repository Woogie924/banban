package com.ssafy.subpjt.error;



public class LoginFailException extends Exception{
	public LoginFailException() {
		super("아이디나 비밀번호가 일치하지않습니다.\n다시 로그인을 해주세요.");
	}
}
