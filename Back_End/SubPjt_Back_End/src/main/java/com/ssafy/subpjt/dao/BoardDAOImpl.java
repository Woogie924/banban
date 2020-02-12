package com.ssafy.subpjt.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ssafy.subpjt.vo.Board;
import com.ssafy.subpjt.vo.Comment;
import com.ssafy.subpjt.vo.Party;
import com.ssafy.subpjt.vo.PartyMember;

@Repository
public class BoardDAOImpl implements BoardDAO {

	private final String ns = "Board.";
	
	@Autowired
	private SqlSession sqlSession;
	
	@Override
	public List<Board> getAllBoards() throws Exception {
		return sqlSession.selectList(ns + "getAllBoards");
	}

	@Override
	public int updateBoard(Board board) throws Exception {
		 return sqlSession.update(ns+"updateBoard", board);
		
	}

	@Override
	public int insertBoard(Board board) throws Exception {
		return sqlSession.insert(ns+"insertBoard", board);
	}

	@Override
	public int deleteBoard(int num) throws Exception {
		return sqlSession.delete(ns+"deleteBoard", num);
	}

	@Override
	public Board getBoardByNum(int num) throws Exception {
		return sqlSession.selectOne(ns+"getBoardByNum", num);
	}

	@Override
	public List<Comment> getComment(int bnum) throws Exception {
		return sqlSession.selectList(ns+"getComment", bnum);
	}

	@Override
	public int insertComment(Comment comment) throws Exception {
		return sqlSession.insert(ns+"insertComment", comment);
	}

	@Override
	public int deleteComment(int cnum) throws Exception {
		return sqlSession.delete(ns + "deleteComment", cnum);
	}

	@Override
	public int updateComment(Comment comment) throws Exception {
		return sqlSession.update(ns+"updateComment", comment);
	}

	@Override
	public Board getBoardById(Board board) throws Exception {
		return sqlSession.selectOne(ns+"getBoardById", board);
	}

	@Override
	public int insertParty(Party party) throws Exception {
		return sqlSession.insert(ns+"insertParty", party);
	}

	@Override
	public Party getPartyByBnum(int bnum) throws Exception {
		return sqlSession.selectOne(ns+"getPartyByBnum", bnum);
	}

	@Override
	public int insertPartyMember(PartyMember partyMember) throws Exception {
		return sqlSession.insert(ns + "insertPartyMember", partyMember);
	}

	@Override
	public int updateParty(Party party) throws Exception {
		return sqlSession.update(ns + "updateParty", party);
	}

	@Override
	public List<PartyMember> getPartMembers(int bnum) throws Exception {
		return sqlSession.selectList(ns+"getPartMembers", bnum);
	}

	@Override
	public List<PartyMember> getAllPartyMember(int pnum) throws Exception {
		return sqlSession.selectList(ns+"getAllPartyMember",pnum);
	}

	@Override
	public int deletePartyMember(PartyMember partyMember) throws Exception {
		return sqlSession.delete(ns+"deletePartyMember", partyMember);
	}

}
