package com.ssafy.subpjt.dao;


import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ssafy.subpjt.vo.OrderFood;

@Repository
public class OrderDAOImpl implements OrderDAO{

	private final String ns = "OrderFood.";
	
	@Autowired
	private SqlSession sqlSession;
	
	@Override
	public int insertOrder(OrderFood orderFood) throws Exception {
		return sqlSession.insert(ns+"insertOrder", orderFood);
	}

	@Override
	public OrderFood getOrderByOnum(int onum) throws Exception {
		return sqlSession.selectOne(ns+"getOrderByOnum", onum);
	}


	@Override
	public int deleteOrderFood(int onum) throws Exception {
		return sqlSession.delete(ns+"deleteOrderFood", onum);
	}

	@Override
	public int deletePayment(int pnum) throws Exception {
		return sqlSession.delete(ns+"deletePayment", pnum);
	}

	@Override
	public int zerotoone(int onum) throws Exception {
		return sqlSession.update(ns+"zerotoone", onum);
	}

	@Override
	public int onetotwo(int onum) throws Exception {
		return sqlSession.update(ns+"onetotwo", onum);
	}

	@Override
	public int twotothree(int onum) throws Exception {
		return sqlSession.update(ns+"twotothree", onum);
	}

	
	

}
