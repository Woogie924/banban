package com.ssafy.subpjt.service;

import java.util.List;

import com.ssafy.subpjt.vo.BillingHistory;
import com.ssafy.subpjt.vo.Likes;
import com.ssafy.subpjt.vo.Menu;
import com.ssafy.subpjt.vo.Store;

public interface StoreService {
	int addStore(Store store) throws Exception;
	int insertMenu(Menu menu) throws Exception;
	int upLikes(Likes likes) throws Exception;
	int downLikes(Likes likes) throws Exception;
	Store loginCheck(Store store) throws Exception;
	Store socialLogin(String email) throws Exception;
	List<Likes> countLikes() throws Exception;
	List<Store> getAllStores() throws Exception;
	List<Menu> getMenu(String id) throws Exception;
	Store getStore(String id) throws Exception;
	List<BillingHistory> getRecentProfit(String id) throws Exception; 
}
