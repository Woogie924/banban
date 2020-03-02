package com.ssafy.subpjt.service;

import java.util.List;

import com.ssafy.subpjt.vo.Board;
import com.ssafy.subpjt.vo.Comment;
import com.ssafy.subpjt.vo.Party;
import com.ssafy.subpjt.vo.PartyMember;

public interface BoardService {
	List<Board> getAllBoards() throws Exception;
	List<Comment> getComment(int bnum) throws Exception;
	List<PartyMember> getPartMembers(int bnum) throws Exception;
	List<PartyMember> getAllPartyMember(int pnum) throws Exception;
	Board getBoardByNum(int num) throws Exception;
	Board getBoardById(Board board) throws Exception;
	Party getPartyByBnum(int bnum) throws Exception;
	int updateComment(Comment comment) throws Exception;
	int insertComment(Comment comment) throws Exception;
	int deleteBoard(int num) throws Exception;
	int deleteComment(int cnum) throws Exception;
	int deletePartyMember(PartyMember partyMember) throws Exception;
}
