package com.ssafy.subpjt.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.subpjt.dao.UserDAO;
import com.ssafy.subpjt.vo.User;
import com.ssafy.subpjt.vo.Usercart;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	private UserDAO userDao;
	
	@Override
	public List<User> allUsers() throws Exception {
		
		return userDao.allUsers();
	}

	@Override
	public User loginCheck(User user) throws Exception {
		return userDao.loginCheck(user);
	}

	@Override
	public int addUser(User user) throws Exception {
		return userDao.addUser(user);
	}

	@Override
	public User getUser(String id) throws Exception {
		return userDao.getUser(id);
	}

	@Override
	public User socialLogin(User email) throws Exception {
		return userDao.socialLogin(email);
	}

	@Override
	public int insertUsercart(Usercart usercart) throws Exception {
		return userDao.insertUsercart(usercart);
	}

	@Override
	public List<Usercart> getUsercart(String userid) throws Exception {
		return userDao.getUsercart(userid);
	}

	@Override
	public int deleteUsercart(int unum) throws Exception {
		return userDao.deleteUsercart(unum);
	}

	@Override
	public int updateUser(User user) throws Exception {
		return userDao.updateUser(user);
	}

	@Override
	public int deleteUser(User user) throws Exception {
		return userDao.deleteUser(user);
	}

	@Override
	public String getAddress(String id) throws Exception {
		return userDao.getAddress(id);
	}

}
