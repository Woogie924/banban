package com.ssafy.subpjt.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ssafy.subpjt.vo.Likes;
import com.ssafy.subpjt.vo.Menu;
import com.ssafy.subpjt.vo.OrderFood;
import com.ssafy.subpjt.vo.Starpoint;
import com.ssafy.subpjt.vo.Store;
import com.ssafy.subpjt.vo.Storeinfo;
import com.ssafy.subpjt.vo.Storestarpoint;

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
	public List<String> countLikes() throws Exception {
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
	public List<OrderFood> getRecentProfit(String storeid) throws Exception {
		return sqlSession.selectList(ns + "getRecentProfit" , storeid);
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

	@Override
	public int insertStoreinfo(Storeinfo storeinfo) throws Exception {
		return sqlSession.insert(ns+"insertStoreinfo", storeinfo);
	}

	@Override
	public int updateStoreinfo(Storeinfo storeinfo) throws Exception {
		return sqlSession.update(ns+"updateStoreinfo", storeinfo);
	}

	@Override
	public int insertStarpoint(Starpoint starpoint) throws Exception {
		return sqlSession.insert(ns+"insertStarpoint",starpoint);
	}

	@Override
	public int updateStarpoint(Starpoint starpoint) throws Exception {
		return sqlSession.insert(ns+"updateStarpoint", starpoint);
	}

	@Override
	public List<Starpoint> getAllStarpoint(String storeid) throws Exception {
		return sqlSession.selectList(ns+"getAllStarpoint", storeid);
	}

	@Override
	public Storeinfo getStoreinfo(String storeid) throws Exception {
		return sqlSession.selectOne(ns + "getStoreinfo", storeid);
	}

	@Override
	public List<Storestarpoint> getAllStorestarpoint(String storeid) throws Exception {
		return sqlSession.selectList(ns+"getAllStorestarpoint", storeid);
	}

	@Override
	public int insertStorestarpoint(Storestarpoint storestarpoint) throws Exception {
		return sqlSession.insert(ns+"insertStorestarpoint", storestarpoint);
	}

	@Override
	public int updateStorestarpoint(Storestarpoint storestarpoint) throws Exception {
		return sqlSession.update(ns+"updateStorestarpoint", storestarpoint);
	}

	@Override
	public int deleteStarpoint(Starpoint starpoint) throws Exception {
		return sqlSession.delete(ns + "deleteStarpoint", starpoint);
	}

	@Override
	public int deleteStorestarpoint(Storestarpoint storestarpoint) throws Exception {
		return sqlSession.delete(ns+"deleteStorestarpoint", storestarpoint);
	}

	@Override
	public List<OrderFood> getOrder(String storeid) throws Exception {
		return sqlSession.selectList(ns+"getOrder", storeid);
	}

	@Override
	public int updateStore(Store store) throws Exception {
		return sqlSession.update(ns+"updateStore", store);
	}

	@Override
	public int deleteStore(Store store) throws Exception {
		return sqlSession.delete(ns + "deleteStore", store);
	}

}
