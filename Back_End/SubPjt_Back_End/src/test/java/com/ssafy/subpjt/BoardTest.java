package com.ssafy.subpjt;

import java.util.List;

import com.ssafy.subpjt.dao.BoardDAO;
import com.ssafy.subpjt.dao.BoardDAOImpl;
import com.ssafy.subpjt.vo.Board;

public class BoardTest {
	public static void main(String[] args) throws Exception {
		BoardDAO dao = new BoardDAOImpl();
		List<Board> list = dao.getAllBoards();
		
		for(Board d: list) {
			System.out.println(d);
		}
		
		
		
		
	}
}
