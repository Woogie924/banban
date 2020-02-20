package com.ssafy.subpjt.controller;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.subpjt.service.BoardService;
import com.ssafy.subpjt.service.JwtService;
import com.ssafy.subpjt.service.OrderService;
import com.ssafy.subpjt.service.StoreService;
import com.ssafy.subpjt.service.UserService;
import com.ssafy.subpjt.vo.OrderFood;
import com.ssafy.subpjt.vo.Store;
import com.ssafy.subpjt.vo.User;


@RestController
@CrossOrigin(origins = {"*"}, maxAge = 6000)
public class OrderRestController {


	@Autowired
	private UserService userService;
	
	@Autowired
	private StoreService storeService;

	@Autowired
	private JwtService jwtService;

	@Autowired
	private OrderService orderService;

	@PostMapping("/order")
	public ResponseEntity<String> insertOrder(@RequestBody OrderFood orderfood) throws Exception{
		String memberId = null;
		User user = null;
		OrderFood order = null;
		Random random = new Random();
		int iValue = 0;
		try {
			System.out.println("주문 입력 controller");
			System.out.println(orderfood);
//			memberId = jwtService.getMemberId();
//			user = userService.getUser(memberId);
//			if(user != null) {
				while(true) {
					iValue = random.nextInt(1000000);
					System.out.println(iValue);
					order = orderService.getOrderByOnum(iValue);
					if(order == null) {
						break;
					}
				}
				orderfood.setOnum(iValue);
				String address = userService.getAddress(orderfood.getUserid2());
				String tel = userService.getTel(orderfood.getUserid2());
				orderfood.setTel2(tel);
				orderfood.setAddress2(address);
				System.out.println("변경후 orderfood" + orderfood);
				int ans = orderService.insertOrder(orderfood);
				System.out.println("주문 입력 : " + ans);
				return new ResponseEntity(Integer.toString(iValue), HttpStatus.OK);

//			}else {
//				return new ResponseEntity(HttpStatus.NO_CONTENT);
//			}

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

	@PutMapping("/order/{onum}")
	public ResponseEntity updateOrder(@PathVariable int onum) throws Exception{
		String memberId = null;
		Store user = null;
		int ans = 0;
		try {
			System.out.println("주문 상태 변경 controller");
			memberId = jwtService.getMemberId();
			user = storeService.getStore(memberId);
			if(user != null) {
				OrderFood orderfood = orderService.getOrderByOnum(onum);
				if(orderfood.getOrdercheck() == 0) {
					System.out.println("0");
					System.out.println(orderfood);
					ans = orderService.zerotoone(onum);
				}else if(orderfood.getOrdercheck() == 1) {
					System.out.println("1");
					System.out.println(orderfood);
					ans = orderService.onetotwo(onum);
				}else if(orderfood.getOrdercheck() == 2) {
					System.out.println("2");
					System.out.println(orderfood);
					ans = orderService.twotothree(onum);
				}

				System.out.println("수정 : " + ans);
				return new ResponseEntity(HttpStatus.OK);
			}else {
				return new ResponseEntity(HttpStatus.NO_CONTENT);
			}

		}catch(Exception e) {
			return new ResponseEntity(HttpStatus.NO_CONTENT);
		}
	}
	
	@GetMapping("/order/{onum}")
	public ResponseEntity<OrderFood> getOrder(@PathVariable String onum) throws Exception{
		String memberId = null;
		User user = null;
		OrderFood food = null;
		try {
			System.out.println("주문 정보 controller");
			memberId = jwtService.getMemberId();
			user = userService.getUser(memberId);
			if(user != null) {
				food = orderService.getOrderByOnum(Integer.parseInt(onum));
				System.out.println(food);
				return new ResponseEntity(food,HttpStatus.OK);
			}else {
				return new ResponseEntity(HttpStatus.NO_CONTENT);
			}

		}catch(Exception e) {
			return new ResponseEntity(HttpStatus.NO_CONTENT);
		}
	}
	




}
