package com.ssafy.subpjt.dao;

import java.util.List;

import com.ssafy.subpjt.vo.BillingHistory;
import com.ssafy.subpjt.vo.Likes;
import com.ssafy.subpjt.vo.Menu;
import com.ssafy.subpjt.vo.Starpoint;
import com.ssafy.subpjt.vo.Store;
import com.ssafy.subpjt.vo.Storeinfo;
import com.ssafy.subpjt.vo.Storestarpoint;

public interface StoreDAO {
	
	int addStore(Store store) throws Exception;
	int insertMenu(Menu menu) throws Exception;
	int insertStoreinfo(Storeinfo storeinfo) throws Exception;
	int insertStarpoint(Starpoint starpoint) throws Exception;
	int insertStorestarpoint(Storestarpoint storestarpoint) throws Exception;
	int updateStoreinfo(Storeinfo storeinfo) throws Exception;
	int updateStarpoint(Starpoint starpoint) throws Exception;
	int updateStorestarpoint(Storestarpoint storestarpoint) throws Exception;
	int deleteStarpoint(Starpoint starpoint) throws Exception;
	int deleteStorestarpoint(Storestarpoint storestarpoint) throws Exception;
	int upLikes(Likes likes) throws Exception;
	int downLikes(Likes likes) throws Exception;
	Store loginCheck(Store store) throws Exception;
	Store socialLogin(String email) throws Exception;
	Store getStore(String id) throws Exception;
	Storeinfo getStoreinfo(String storeid) throws Exception;
	List<Likes> countLikes() throws Exception;
	List<Likes> getAllLikes(String userid) throws Exception;
	List<Starpoint> getAllStarpoint(String storeid) throws Exception;
	List<Storestarpoint> getAllStorestarpoint(String storeid) throws Exception;
	List<Store> getAllStores() throws Exception;
	List<Menu> getMenu(String id) throws Exception;
	List<BillingHistory> getRecentProfit(String id) throws Exception; 
}