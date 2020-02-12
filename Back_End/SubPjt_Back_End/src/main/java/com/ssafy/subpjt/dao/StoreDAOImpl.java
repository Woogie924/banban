package com.ssafy.subpjt.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ssafy.subpjt.vo.BillingHistory;
import com.ssafy.subpjt.vo.Likes;
import com.ssafy.subpjt.vo.Menu;
import com.ssafy.subpjt.vo.Store;

@Repository
public class StoreDAOImpl implements StoreDAO{

	private final String ns = "Store.";
	
	@Autowired
	private SqlSession sqlSession;
	
	@Override
	public int addStore(Store store) throws Exception {
		return sqlSession.insert(ns + "addStore", store);
	}

	@Override
	public Store loginCheck(Store store) throws Exception {
		return sqlSession.selectOne(ns + "loginCheck",store);
	}

	@Override
	public List<Likes> countLikes() throws Exception {
		return sqlSession.selectList(ns+"countLikes");
	}

	@Override
	public List<Menu> getMenu(String id) throws Exception {
		return sqlSession.selectList(ns+"getMenu", id);
	}

	@Override
	public Store socialLogin(String email) throws Exception {
		return sqlSession.selectOne(ns+"socialLogin",email);
	}

	@Override
	public Store getStore(String id) throws Exception {
		return sqlSession.selectOne(ns + "getStore", id);
	}

	@Override
	public int insertMenu(Menu menu) throws Exception {
		return sqlSession.insert(ns+"insertMenu", menu);
	}

	@Override
	public List<Store> getAllStores() throws Exception {
		return sqlSession.selectList(ns+"getAllStores");
	}

	@Override
	public List<BillingHistory> getRecentProfit(String id) throws Exception {
		return sqlSession.selectList(ns + "getRecentProfit" , id);
	}

	@Override
	public int upLikes(Likes likes) throws Exception {
		return sqlSession.insert(ns+"upLikes", likes);
	}

	@Override
	public int downLikes(Likes likes) throws Exception {
		return sqlSession.delete(ns+"downLikes", likes);
	}

	@Override
	public List<Likes> getAllLikes(String userid) throws Exception {
		return sqlSession.selectList(ns+"getAllLikes", userid);
	}

}
