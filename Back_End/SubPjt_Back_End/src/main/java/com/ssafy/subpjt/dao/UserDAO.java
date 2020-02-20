package com.ssafy.subpjt.dao;

import java.util.List;

import com.ssafy.subpjt.vo.OrderFood;
import com.ssafy.subpjt.vo.User;
import com.ssafy.subpjt.vo.Usercart;

public interface UserDAO {
	List<User> allUsers() throws Exception;
	List<Usercart> getUsercart(String userid) throws Exception;
	List<OrderFood> getOrderByUserid(String id) throws Exception;
	User loginCheck(User user) throws Exception;
	User socialLogin(User email) throws Exception;
	User getUser(String id) throws Exception;
	String getAddress(String id) throws Exception;
	String getTel(String id) throws Exception;
	int addUser(User user) throws Exception;
	int updateUser(User user) throws Exception;
	int insertUsercart(Usercart usercart) throws Exception;
	int deleteUsercart(int unum) throws Exception;
	int deleteUser(User user) throws Exception;
}
