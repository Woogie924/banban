package com.ssafy.subpjt.service;


import com.ssafy.subpjt.vo.OrderFood;

public interface OrderService {
	int insertOrder(OrderFood orderFood) throws Exception;
	int deleteOrderFood(int onum) throws Exception;
	int deletePayment(int pnum) throws Exception;
	OrderFood getOrderByOnum(int onum) throws Exception;
	int zerotoone(int onum) throws Exception;
	int onetotwo(int onum) throws Exception;
	int twotothree(int onum) throws Exception;
}
