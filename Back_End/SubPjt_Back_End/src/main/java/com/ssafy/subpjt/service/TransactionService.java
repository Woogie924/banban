package com.ssafy.subpjt.service;


import java.util.List;

import com.ssafy.subpjt.vo.Board;
import com.ssafy.subpjt.vo.Store;

public interface TransactionService {
	int insertBoard(Board board, int max) throws Exception;
	int insertPartyMember( String id, int bnum) throws Exception;
	int updateBoard(Board board,int max) throws Exception;
	int deletePartyMember(int bnum, String id) throws Exception;
	List<Store> getAllLikedStore(String memberId) throws Exception;
	double distance(double lat1, double lon1, double lat2, double lon2);
	Store getDetailStore(String id) throws Exception;
}
