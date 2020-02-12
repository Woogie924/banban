package com.ssafy.subpjt.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.subpjt.dao.BoardDAO;
import com.ssafy.subpjt.vo.Board;
import com.ssafy.subpjt.vo.Comment;
import com.ssafy.subpjt.vo.Party;
import com.ssafy.subpjt.vo.PartyMember;

@Service
public class BoardServiceImpl implements BoardService {
	
	
	@Autowired
	private BoardDAO boardDao;
	

	@Override
	public List<Board> getAllBoards() throws Exception {
		return boardDao.getAllBoards();
	}


	@Override
	public int deleteBoard(int num) throws Exception {
		return boardDao.deleteBoard(num);
	}

	@Override
	public Board getBoardByNum(int num) throws Exception {
		return boardDao.getBoardByNum(num);
	}

	@Override
	public List<Comment> getComment(int bnum) throws Exception {
		return boardDao.getComment(bnum);
	}

	@Override
	public int insertComment(Comment comment) throws Exception {
		return boardDao.insertComment(comment);
	}

	@Override
	public int deleteComment(int cnum) throws Exception {
		return boardDao.deleteComment(cnum);
	}

	@Override
	public int updateComment(Comment comment) throws Exception {
		return boardDao.updateComment(comment);
	}

	@Override
	public Board getBoardById(Board board) throws Exception {
		return boardDao.getBoardById(board);
	}

	@Override
	public Party getPartyByBnum(int bnum) throws Exception {
		return boardDao.getPartyByBnum(bnum);
	}
	@Override
	public List<PartyMember> getPartMembers(int bnum) throws Exception {
		return boardDao.getPartMembers(bnum);
	}

	@Override
	public List<PartyMember> getAllPartyMember(int pnum) throws Exception {
		return boardDao.getAllPartyMember(pnum);
	}

	@Override
	public int deletePartyMember(PartyMember partyMember) throws Exception {
		return boardDao.deletePartyMember(partyMember);
	}
}
