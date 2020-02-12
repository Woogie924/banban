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
import com.ssafy.subpjt.vo.Payment;
import com.ssafy.subpjt.vo.User;


@RestController
@CrossOrigin(origins = {"*"}, maxAge = 6000)
public class OrderRestController {

	@Autowired
	private BoardService boardService;

	@Autowired
	private UserService userService;

	@Autowired
	private StoreService storeService;

	@Autowired
	private JwtService jwtService;

	@Autowired
	private OrderService orderService;

	@PostMapping("/order")
	public ResponseEntity<Integer> insertOrder(@RequestBody OrderFood orderfood) throws Exception{
		String memberId = null;
		String[] memberList;
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
					System.out.println(orderfood.getUserid());
					memberList = orderfood.getUserid().split(",");
					int cost = (int)orderfood.getPrice()/orderfood.getPeonum();
					orderfood.setOnum(iValue);
					orderfood.setPrice(cost);
					for(String s : memberList) {
						user = userService.getUser(s);
						System.out.println(user);
						orderfood.setUserid(s);
						System.out.println(orderfood);
						orderfood.setAddress(user.getAddress());
						System.out.println(orderfood);
						int ans = orderService.insertOrder(orderfood);
						System.out.println("주문 입력 : " + ans);
					}
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

	@PostMapping("/payment")
	public ResponseEntity<Integer> insertPayment(@RequestBody Payment payment) throws Exception{
		String memberId = null;
		User user = null;
		OrderFood order = null;
		Random random = new Random();
		try {
			System.out.println("주문 controller");
			System.out.println(payment);
			memberId = jwtService.getMemberId();
			user = userService.getUser(memberId);
			if(user != null) {
				int iValue = random.nextInt(100000);
				payment.setPnum(iValue);
				System.out.println(iValue);
				orderService.insertPayment(payment);
				return new ResponseEntity(iValue, HttpStatus.OK);

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
