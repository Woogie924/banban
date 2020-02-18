package com.ssafy.subpjt.dao;


import com.ssafy.subpjt.vo.OrderFood;

public interface OrderDAO {
	int insertOrder(OrderFood orderFood) throws Exception;
	int deleteOrderFood(int onum) throws Exception;
	int deletePayment(int pnum) throws Exception;
	OrderFood getOrderByOnum(int onum) throws Exception;
}
