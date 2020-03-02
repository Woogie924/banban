package com.ssafy.subpjt.dao;


import com.ssafy.subpjt.vo.OrderFood;

public interface OrderDAO {
	int insertOrder(OrderFood orderFood) throws Exception;
	int deleteOrderFood(int onum) throws Exception;
	int deletePayment(int pnum) throws Exception;
	int zerotoone(int onum) throws Exception;
	int onetotwo(int onum) throws Exception;
	int twotothree(int onum) throws Exception;
	OrderFood getOrderByOnum(int onum) throws Exception;
}
