package com.ssafy.subpjt.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.subpjt.dao.StoreDAO;
import com.ssafy.subpjt.vo.BillingHistory;
import com.ssafy.subpjt.vo.Likes;
import com.ssafy.subpjt.vo.Menu;
import com.ssafy.subpjt.vo.Store;

@Service
public class StoreServiceImpl implements StoreService{

	@Autowired
	private StoreDAO storeDao;
	
	@Override
	public int addStore(Store store) throws Exception {
		return storeDao.addStore(store) ;
	}

	@Override
	public Store loginCheck(Store store) throws Exception {
		return storeDao.loginCheck(store);
	}

	@Override
	public List<Likes> countLikes() throws Exception {
		return storeDao.countLikes();
	}

	@Override
	public List<Menu> getMenu(String id) throws Exception {
		return storeDao.getMenu(id);
	}

	@Override
	public Store socialLogin(String email) throws Exception {
		return storeDao.socialLogin(email);
	}

	@Override
	public Store getStore(String id) throws Exception {
		return storeDao.getStore(id);
	}

	@Override
	public int insertMenu(Menu menu) throws Exception {
		return storeDao.insertMenu(menu);
	}

	@Override
	public List<Store> getAllStores() throws Exception {
		return storeDao.getAllStores();
	}

	@Override
	public List<BillingHistory> getRecentProfit(String id) throws Exception {
		return storeDao.getRecentProfit(id);
	}

	@Override
	public int upLikes(Likes likes) throws Exception {
		return storeDao.upLikes(likes);
	}

	@Override
	public int downLikes(Likes likes) throws Exception {
		return storeDao.downLikes(likes);
	}

}
