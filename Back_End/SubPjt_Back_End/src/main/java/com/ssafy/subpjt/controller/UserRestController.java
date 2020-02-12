package com.ssafy.subpjt.controller;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.subpjt.service.JwtService;
import com.ssafy.subpjt.service.StoreService;
import com.ssafy.subpjt.service.UserService;
import com.ssafy.subpjt.vo.EmailAuthorization;
import com.ssafy.subpjt.vo.Menu;
import com.ssafy.subpjt.vo.Result;
import com.ssafy.subpjt.vo.User;
import com.ssafy.subpjt.vo.Usercart;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;


@RestController
@CrossOrigin(origins = {"*"}, maxAge = 6000)
@RequestMapping("/api")
public class UserRestController {
	@Autowired
	private UserService userService;

	@Autowired
	private JwtService jwtService;
	
	@Autowired
	private StoreService storeService;


	@GetMapping("/allusers")
	public ResponseEntity<List<User>> allUsers() throws Exception{
		List<User> list = null;
		try {
			list = userService.allUsers();
			System.out.println(list);
			return new ResponseEntity<List<User>>(list, HttpStatus.OK);
		}catch(Exception e) {
			return new ResponseEntity<List<User>>(HttpStatus.NO_CONTENT);
		}
	}
	
	
	@PostMapping("/cart")
	public ResponseEntity insertcart(@RequestBody Usercart usercart) throws Exception{
		User user = null;
		String memberId = null;
		try {
			System.out.println("장바구니 추가 controller");
			System.out.println(usercart);
			memberId = jwtService.getMemberId();
			user = userService.getUser(memberId);
			if(user != null) {
				int ans = userService.insertUsercart(usercart);
				System.out.println("장바구니 추가  : " + ans);
				return new ResponseEntity(HttpStatus.OK);
			}
			else {
				return new ResponseEntity(HttpStatus.BAD_REQUEST);
			}
		}catch(Exception e) {
			return new ResponseEntity(HttpStatus.NO_CONTENT);
		}
	}
	
	@GetMapping("/cart")
	public ResponseEntity<List<Usercart>> getUsercart() throws Exception{
		User user = null;
		String memberId = null;
		List<Usercart> list = null;
		try {
			System.out.println("장바구니 리스트 controller");
			memberId = jwtService.getMemberId();
			user = userService.getUser(memberId);
			if(user != null) {
				list = userService.getUsercart(memberId);
				System.out.println(list);
				return new ResponseEntity<List<Usercart>>(list,HttpStatus.OK);
			}
			else {
				return new ResponseEntity(HttpStatus.BAD_REQUEST);
			}
		}catch(Exception e) {
			return new ResponseEntity(HttpStatus.NO_CONTENT);
		}
	}
	
	
	@DeleteMapping("/cart/{unum}")
	public ResponseEntity deleteUsercart(@PathVariable int unum) throws Exception{
		User user = null;
		String memberId = null;
		try {
			System.out.println("장바구니 삭제 controller");
			memberId = jwtService.getMemberId();
			user = userService.getUser(memberId);
			if(user != null) {
				int ans = userService.deleteUsercart(unum);
				System.out.println("삭제 : " + ans);
				return new ResponseEntity(HttpStatus.OK);
			}
			else {
				return new ResponseEntity(HttpStatus.BAD_REQUEST);
			}
		}catch(Exception e) {
			return new ResponseEntity(HttpStatus.NO_CONTENT);
		}
	}

	@PostMapping("/loginCheck")
	public ResponseEntity<Result> loginCheck(@RequestBody User user, HttpServletResponse response) throws Exception{
		User loginMember = null;
		Result result = Result.successInstance();
		try {
			System.out.println("사용자 로그인 Controller");
			loginMember = userService.loginCheck(user);
			System.out.println("로그인 : " + loginMember);
			if(loginMember != null) {
				String token = jwtService.create("member", loginMember, "user");
				//System.out.println(token);
				response.setHeader("Authorization", token);
				result.setToken(token);
				result.setData(loginMember);
				System.out.println(result);
				return new ResponseEntity<Result>(result, HttpStatus.OK);
			}else {
				return new ResponseEntity<Result>(HttpStatus.NO_CONTENT);
			}
		}catch(Exception e) {
			return new ResponseEntity<Result>( HttpStatus.NO_CONTENT);
		}
	}

	@PostMapping("/socialLogin")
	public ResponseEntity<Result> socialLogin(@RequestBody User email, HttpServletResponse response) throws Exception{
		User loginMember = null;
		Result result = Result.successInstance();
		try {
			System.out.println("사용자 소셜 로그인 Controller");
			System.out.println(email);
			loginMember = userService.socialLogin(email);
			System.out.println("로그인 : " + loginMember);
			if(loginMember != null) {
				String token = jwtService.create("member", loginMember, "user");
				response.setHeader("Authorization", token);
				result.setToken(token);
				result.setData(loginMember);
				return new ResponseEntity<Result>(result, HttpStatus.OK);
			}else {
				return new ResponseEntity<Result>(HttpStatus.NO_CONTENT);
			}
		}catch(Exception e) {
			return new ResponseEntity<Result>( HttpStatus.NO_CONTENT);
		}
	}

	@PostMapping("/user")
	public ResponseEntity addUser(@RequestBody User user) throws Exception{
		try {
			System.out.println("회원가입 Controller");
			System.out.println(user);
			int ans = userService.addUser(user);
			System.out.println("회원가입 : " + ans);
			if(ans == 1) {
				return new ResponseEntity("회원가입 성공",HttpStatus.OK);				
			}else {
				return new ResponseEntity("입력양식이 맞지 않습니다.",HttpStatus.OK);	
			}
		}catch(Exception e) {
			return new ResponseEntity("회원가입 중 에러가 발생했습니다.",HttpStatus.NO_CONTENT);
		}
	}

	@GetMapping("/user")
	public ResponseEntity<String> getUser() throws Exception{
		String memberId = null;
		User user = null;
		try {
			System.out.println("user id 정보");
			memberId = jwtService.getMemberId();
			user = userService.getUser(memberId);
			//System.out.println(user);
			if(user != null) {
				System.out.println("현재 유저 : " + user);
				return new ResponseEntity<String> (user.getId(),HttpStatus.OK);				
			}else {
				return new ResponseEntity<String> (HttpStatus.NO_CONTENT);		
			}
		}catch(Exception e) {
			return new ResponseEntity<String> (HttpStatus.NO_CONTENT);
		}
	}
	
	@GetMapping("/menu/{id}")
	public ResponseEntity<List<Menu>> getMenu(@PathVariable String id) throws Exception{
		List<Menu> list = null;
		User ans = null;
		String memberId = null;
		try {
			System.out.println("메뉴 유저 list Controller");
			memberId = jwtService.getMemberId();
			ans = userService.getUser(memberId);
			if(ans != null) {
				list = storeService.getMenu(id);
				System.out.println(list);
				return new ResponseEntity<List<Menu>>(list,HttpStatus.OK);
			}
			else {
				return new ResponseEntity<List<Menu>>(HttpStatus.BAD_REQUEST);
			}
		}catch(Exception e) {
			return new ResponseEntity<List<Menu>>(HttpStatus.BAD_REQUEST);
		}
	}
	

	@PostMapping("/push")
	public ResponseEntity push(@RequestBody EmailAuthorization to, HttpServletResponse response) throws Exception{
		User loginMember = null;
		Result result = Result.successInstance();
		try {
			System.out.println("푸쉬 Controller");
			System.out.println(to.getTo());
			JSONObject ans = new JSONObject();
			JSONObject data = new JSONObject();
			JSONArray arr = new JSONArray();
			ans.put("to", to.getTo());
			data.put("status", "주문받아라");
			ans.put("data", data);
			arr.add(ans);
			response.setHeader("key", "AAAA8fvy-ls:APA91bGWO0UCXWPWot0DNUz0qBzNZdm1p8OVwscXSPm5EqZOse_tlMSuPAQw8D3ZEBkAq_a7Vd1BuzBHteUydouwhsWAKeycbxYjmnL9i_cXc9rEubsJqhksV3mX3fUqxWhG0SRDjsao");
			System.out.println(result);
			return new ResponseEntity(result, HttpStatus.OK);
		}catch(Exception e) {
			return new ResponseEntity( HttpStatus.NO_CONTENT);
		}
	}
}
