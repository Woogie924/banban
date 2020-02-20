package com.ssafy.subpjt.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.subpjt.dao.StoreDAO;
import com.ssafy.subpjt.vo.Likes;
import com.ssafy.subpjt.vo.Menu;
import com.ssafy.subpjt.vo.OrderFood;
import com.ssafy.subpjt.vo.Starpoint;
import com.ssafy.subpjt.vo.Store;
import com.ssafy.subpjt.vo.Storeinfo;
import com.ssafy.subpjt.vo.Storestarpoint;

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
	public List<String> countLikes() throws Exception {
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
	public List<OrderFood> getRecentProfit(OrderFood orderFood) throws Exception {
		return storeDao.getRecentProfit(orderFood);
	}

	@Override
	public int upLikes(Likes likes) throws Exception {
		return storeDao.upLikes(likes);
	}

	@Override
	public int downLikes(Likes likes) throws Exception {
		return storeDao.downLikes(likes);
	}

	@Override
	public int insertStoreinfo(Storeinfo storeinfo) throws Exception {
		return storeDao.insertStoreinfo(storeinfo);
	}

	@Override
	public int updateStoreinfo(Storeinfo storeinfo) throws Exception {
		return storeDao.updateStoreinfo(storeinfo);
	}

	@Override
	public int insertStarpoint(Starpoint starpoint) throws Exception {
		return storeDao.insertStarpoint(starpoint);
	}

	@Override
	public int updateStarpoint(Starpoint starpoint) throws Exception {
		return storeDao.updateStarpoint(starpoint);
	}

	@Override
	public int insertStorestarpoint(Storestarpoint storestarpoint) throws Exception {
		return storeDao.insertStorestarpoint(storestarpoint);
	}

	@Override
	public int updateStorestarpoint(Storestarpoint storestarpoint) throws Exception {
		return storeDao.updateStorestarpoint(storestarpoint);
	}

	@Override
	public int deleteStarpoint(Starpoint starpoint) throws Exception {
		return storeDao.deleteStarpoint(starpoint);
	}

	@Override
	public int deleteStorestarpoint(Storestarpoint storestarpoint) throws Exception {
		return storeDao.deleteStorestarpoint(storestarpoint);
	}

	@Override
	public List<OrderFood> getOrder(String storeid) throws Exception {
		return storeDao.getOrder(storeid);
	}

	@Override
	public int updateStore(Store store) throws Exception {
		return storeDao.updateStore(store);
	}

	@Override
	public int deleteStore(Store store) throws Exception {
		return storeDao.deleteStore(store);
	}

}
