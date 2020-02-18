package com.ssafy.subpjt.controller;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.subpjt.service.BoardService;
import com.ssafy.subpjt.service.JwtService;
import com.ssafy.subpjt.service.OrderService;
import com.ssafy.subpjt.service.StoreService;
import com.ssafy.subpjt.service.UserService;
import com.ssafy.subpjt.vo.OrderFood;
import com.ssafy.subpjt.vo.User;


@RestController
@CrossOrigin(origins = {"*"}, maxAge = 6000)
public class OrderRestController {


	@Autowired
	private UserService userService;

	@Autowired
	private JwtService jwtService;

	@Autowired
	private OrderService orderService;

	@PostMapping("/order")
	public ResponseEntity<Integer> insertOrder(@RequestBody OrderFood orderfood) throws Exception{
		String memberId = null;
		User user = null;
		OrderFood order = null;
		Random random = new Random();
		try {
			System.out.println("주문 controller");
			System.out.println(orderfood);
			memberId = jwtService.getMemberId();
			user = userService.getUser(memberId);
			if(user != null) {
				int iValue = random.nextInt(100000);
				System.out.println(iValue);
				order = orderService.getOrderByOnum(iValue);
				if(order == null) {
					orderfood.setOnum(iValue);
					int ans = orderService.insertOrder(orderfood);
					System.out.println("주문 입력 : " + ans);
					return new ResponseEntity(iValue, HttpStatus.OK);
				}else {
					insertOrder(orderfood);
					return new ResponseEntity(HttpStatus.OK);
				}
			}else {
				return new ResponseEntity(HttpStatus.NO_CONTENT);
			}

		}catch(Exception e) {
			return new ResponseEntity(HttpStatus.NO_CONTENT);
		}
	}

	@DeleteMapping("/order/{onum}")
	public ResponseEntity deleteOrder(@PathVariable int onum) throws Exception{
		String memberId = null;
		User user = null;
		try {
			System.out.println("주문 삭제 controller");
			memberId = jwtService.getMemberId();
			user = userService.getUser(memberId);
			if(user != null) {
				int ans = orderService.deleteOrderFood(onum);
				System.out.println("삭제 : " + ans);
				return new ResponseEntity(HttpStatus.OK);
			}else {
				return new ResponseEntity(HttpStatus.NO_CONTENT);
			}

		}catch(Exception e) {
			return new ResponseEntity(HttpStatus.NO_CONTENT);
		}
	}


	@DeleteMapping("/payment/{pnum}")
	public ResponseEntity deletePayment(@PathVariable int pnum) throws Exception{
		String memberId = null;
		User user = null;
		try {
			System.out.println("주문 삭제 controller");
			memberId = jwtService.getMemberId();
			user = userService.getUser(memberId);
			if(user != null) {
				int ans = orderService.deletePayment(pnum);
				System.out.println("삭제 : " + ans);
				return new ResponseEntity(HttpStatus.OK);
			}else {
				return new ResponseEntity(HttpStatus.NO_CONTENT);
			}

		}catch(Exception e) {
			return new ResponseEntity(HttpStatus.NO_CONTENT);
		}
	}


}
