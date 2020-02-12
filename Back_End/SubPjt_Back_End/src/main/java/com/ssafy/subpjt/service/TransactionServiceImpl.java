package com.ssafy.subpjt.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssafy.subpjt.dao.BoardDAO;
import com.ssafy.subpjt.dao.StoreDAO;
import com.ssafy.subpjt.vo.Board;
import com.ssafy.subpjt.vo.Likes;
import com.ssafy.subpjt.vo.Menu;
import com.ssafy.subpjt.vo.Party;
import com.ssafy.subpjt.vo.PartyMember;
import com.ssafy.subpjt.vo.Store;


@Service
public class TransactionServiceImpl implements TransactionService{

	@Autowired
	private BoardDAO boardDao;
	
	@Autowired
	private StoreDAO storeDao;
	
	@Transactional
	@Override
	public int insertBoard(Board board, int max) throws Exception {
		boardDao.insertBoard(board);
		Board bo = boardDao.getBoardById(board);
		Party party = new Party( bo.getNum(),max);
		boardDao.insertParty(party);                                                                                                                                                                                                                                                                                                                                                                                                                                   
		party = boardDao.getPartyByBnum(bo.getNum());
		PartyMember partyMember = new PartyMember(party.getPnum(), board.getWriter());
		int ans = boardDao.insertPartyMember(partyMember);
		return ans;
	}


	@Transactional
	@Override
	public int insertPartyMember(String id, int bnum) throws Exception {
		List<PartyMember> list = null;
		Party party = boardDao.getPartyByBnum(bnum);
		list = boardDao.getAllPartyMember(party.getPnum());
		System.out.println(list);
		if(list.size() < party.getMax()) {
			PartyMember partyMember = new PartyMember(party.getPnum(), id);
			int ans = boardDao.insertPartyMember(partyMember);
			return ans;		
		}else {
			return 0;
		}
	}


	@Transactional
	@Override
	public int deletePartyMember(int bnum, String id) throws Exception {
		Party party = boardDao.getPartyByBnum(bnum);
		PartyMember partyMember = new PartyMember(party.getPnum(), id);
		int ans = boardDao.deletePartyMember(partyMember);
		return ans;
	}


	@Transactional
	@Override
	public List<Store> getAllLikedStore(String memberId) throws Exception {
		List<Store> sList = null;
		List<Likes> lList = null;
		sList = storeDao.getAllStores();
		lList = storeDao.getAllLikes(memberId);
		for(Store s : sList) {
			for(Likes l : lList) {
				if(s.getId().equals(l.getStoreid()) && !s.isLiked()) {
					System.out.println(s.getId());
					System.out.println(l.getStoreid());
					s.setLiked(true);
				}
			}
		}
		return sList;
	}
	
	 public double distance(double lat1, double lon1, double lat2, double lon2) {
			double R = 6372.8; 
			double dLat = Math.toRadians(lat2 - lat1);
			double dLon = Math.toRadians(lon2 - lon1);
			lat1 = Math.toRadians(lat1);
			lat2 = Math.toRadians(lat2);

			double a = Math.pow(Math.sin(dLat / 2),2) + Math.pow(Math.sin(dLon / 2),2) * Math.cos(lat1) * Math.cos(lat2);
			double c = 2 * Math.asin(Math.sqrt(a));
			return R * c;

		}


	@Transactional
	@Override
	public int updateBoard(Board board, int max) throws Exception {
		int ans = boardDao.updateBoard(board);
		Party party = new Party(board.getNum(), max);
		ans = boardDao.updateParty(party);
		System.out.println("파티 수정 : " + ans);
		return ans;
	}


	@Transactional
	@Override
	public Store getDetailStore(String id) throws Exception {
		Store store = storeDao.getStore(id);
		List<Menu> lList = storeDao.getMenu(id);
		store.setMenu(lList);
		System.out.println(store);
		return store;
	}

}
