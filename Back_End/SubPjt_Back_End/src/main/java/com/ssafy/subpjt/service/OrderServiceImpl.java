package com.ssafy.subpjt.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.subpjt.dao.OrderDAO;
import com.ssafy.subpjt.vo.OrderFood;

@Service
public class OrderServiceImpl implements OrderService{

	@Autowired
	private OrderDAO orderDAO;
	
	@Override
	public int insertOrder(OrderFood orderFood) throws Exception {
		return orderDAO.insertOrder(orderFood);
	}

	@Override
	public OrderFood getOrderByOnum(int onum) throws Exception {
		return orderDAO.getOrderByOnum(onum);
	}


	@Override
	public int deleteOrderFood(int onum) throws Exception {
		return orderDAO.deleteOrderFood(onum);
	}

	@Override
	public int deletePayment(int pnum) throws Exception {
		return orderDAO.deletePayment(pnum);
	}

}
