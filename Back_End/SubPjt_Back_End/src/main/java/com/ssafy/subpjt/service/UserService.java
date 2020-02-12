package com.ssafy.subpjt.service;

import java.util.List;

import com.ssafy.subpjt.vo.User;
import com.ssafy.subpjt.vo.Usercart;

public interface UserService {
	List<User> allUsers() throws Exception;
	List<Usercart> getUsercart(String userid) throws Exception;
	User loginCheck(User user) throws Exception;
	User socialLogin(User email) throws Exception;
	User getUser(String id) throws Exception;
	int addUser(User user) throws Exception;
	int insertUsercart(Usercart usercart) throws Exception;
	int deleteUsercart(int unum) throws Exception;
}
