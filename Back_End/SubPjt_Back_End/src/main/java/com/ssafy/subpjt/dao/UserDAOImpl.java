package com.ssafy.subpjt.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ssafy.subpjt.vo.User;
import com.ssafy.subpjt.vo.Usercart;

@Repository
public class UserDAOImpl implements UserDAO{
	private final String ns = "User.";
	
	@Autowired
	private SqlSession sqlSession;
	@Override
	public List<User> allUsers() throws Exception {
		return sqlSession.selectList(ns + "allUsers");
	}
	
	@Override
	public User loginCheck(User user) throws Exception {
		return sqlSession.selectOne(ns+"loginCheck",user);
	}

	@Override
	public int addUser(User user) throws Exception {
		return sqlSession.insert(ns+"addUser", user);
	}

	@Override
	public User getUser(String id) throws Exception {
		return sqlSession.selectOne(ns+"getUser",id);
	}

	@Override
	public User socialLogin(User email) throws Exception {
		return sqlSession.selectOne(ns+"socialLogin", email);
	}

	@Override
	public int insertUsercart(Usercart usercart) throws Exception {
		return sqlSession.insert(ns+ "insertUsercart", usercart);
	}

	@Override
	public List<Usercart> getUsercart(String userid) throws Exception {
		return sqlSession.selectList(ns+"getUsercart", userid);
	}

	@Override
	public int deleteUsercart(int unum) throws Exception {
		return sqlSession.delete(ns+"deleteUsercart", unum);
	}

}
