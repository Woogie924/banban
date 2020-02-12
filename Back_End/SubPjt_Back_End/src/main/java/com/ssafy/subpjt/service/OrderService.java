package com.ssafy.subpjt.service;


import com.ssafy.subpjt.vo.OrderFood;
import com.ssafy.subpjt.vo.Payment;

public interface OrderService {
	int insertOrder(OrderFood orderFood) throws Exception;
	int insertPayment(Payment payment) throws Exception;
	int deleteOrderFood(int onum) throws Exception;
	int deletePayment(int pnum) throws Exception;
	OrderFood getOrderByOnum(int onum) throws Exception;
}
