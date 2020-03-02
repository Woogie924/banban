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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.subpjt.service.JwtService;
import com.ssafy.subpjt.service.StoreService;
import com.ssafy.subpjt.service.TransactionService;
import com.ssafy.subpjt.service.UserService;
import com.ssafy.subpjt.vo.EmailAuthorization;
import com.ssafy.subpjt.vo.Menu;
import com.ssafy.subpjt.vo.OrderFood;
import com.ssafy.subpjt.vo.Result;
import com.ssafy.subpjt.vo.Starpoint;
import com.ssafy.subpjt.vo.Store;
import com.ssafy.subpjt.vo.Storeinfo;
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
	
	@Autowired
	private TransactionService transactionService;


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
				return new ResponseEntity("회원가입 실패",HttpStatus.BAD_REQUEST);	
			}
		}catch(Exception e) {
			return new ResponseEntity("회원가입 중 에러가 발생했습니다.",HttpStatus.NO_CONTENT);
		}
	}
	
	@PutMapping("/user")
	public ResponseEntity updateUser(@RequestBody User user) throws Exception{
		try {
			System.out.println("회원 수정 Controller");
			System.out.println(user);
			int ans = userService.updateUser(user);
			System.out.println("수정 성공 : " + ans);
			if(ans == 1) {
				return new ResponseEntity(HttpStatus.OK);				
			}else {
				return new ResponseEntity(HttpStatus.OK);	
			}
		}catch(Exception e) {
			return new ResponseEntity(HttpStatus.NO_CONTENT);
		}
	}
	
	@DeleteMapping("/user/{id}/{pw}")
	public ResponseEntity deleteUser(@PathVariable String id, @PathVariable String pw) throws Exception{
		try {
			System.out.println("회원 삭제 Controller");
			User user = new User();
			user.setId(id);
			user.setPw(pw);
			int ans = userService.deleteUser(user);
			System.out.println("회원 탈퇴  : " + ans);
			if(ans == 1) {
				return new ResponseEntity(HttpStatus.OK);				
			}else {
				return new ResponseEntity(HttpStatus.BAD_REQUEST);	
			}
		}catch(Exception e) {
			return new ResponseEntity(HttpStatus.NO_CONTENT);
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
	
	@GetMapping("/address/{id}")
	public ResponseEntity<String> getAddress(@PathVariable String id) throws Exception{
		String memberId = null;
		User user = null;
		try {
			System.out.println("user 주소 정보");
			memberId = jwtService.getMemberId();
			user = userService.getUser(memberId);
			if(user != null) {
				String address = userService.getAddress(id);
				System.out.println("주소 : "  + address);
				return new ResponseEntity<String> (address,HttpStatus.OK);				
			}else {
				return new ResponseEntity<String> (HttpStatus.NO_CONTENT);		
			}
		}catch(Exception e) {
			return new ResponseEntity<String> (HttpStatus.NO_CONTENT);
		}
	}
	
	@GetMapping("/info")
	public ResponseEntity<User> getUserInfo() throws Exception{
		String memberId = null;
		User user = null;
		try {
			System.out.println("user 정보");
			memberId = jwtService.getMemberId();
			user = userService.getUser(memberId);
			//System.out.println(user);
			if(user != null) {
				System.out.println("현재 유저 : " + user);
				return new ResponseEntity<User> (user,HttpStatus.OK);				
			}else {
				return new ResponseEntity<User> (HttpStatus.NO_CONTENT);		
			}
		}catch(Exception e) {
			return new ResponseEntity<User> (HttpStatus.NO_CONTENT);
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
	
	@GetMapping("/storeinfo/{id}")
	public ResponseEntity<Storeinfo> getStoreinfo(@PathVariable String id) throws Exception{
		Storeinfo storeinfo = null;
		User ans = null;
		String memberId = null;
		try {
			System.out.println("유저측 storeinfo Controller");
			memberId = jwtService.getMemberId();
			ans = userService.getUser(memberId);
			if(ans != null) {
				storeinfo = transactionService.getStoreinfo(id);
				System.out.println("storeinfo : " + storeinfo);
				return new ResponseEntity<Storeinfo>(storeinfo,HttpStatus.OK);
			}
			else {
				return new ResponseEntity<Storeinfo>(HttpStatus.BAD_REQUEST);
			}
		}catch(Exception e) {
			return new ResponseEntity<Storeinfo>(HttpStatus.BAD_REQUEST);
		}
	}
	
	@PostMapping("/starpoint")
	public ResponseEntity insertStarpoint(@RequestBody Starpoint starpoint) throws Exception{
		User user = null;
		String memberId = null;
		try {
			System.out.println("가게 별점 입력 Controller");
			System.out.println(starpoint);
			memberId = jwtService.getMemberId();
			user = userService.getUser(memberId);
			if(user != null) {
				int ans = storeService.insertStarpoint(starpoint);
				System.out.println("입력 : "+ ans);
				return new ResponseEntity(true,HttpStatus.OK);
			}else {
				return new ResponseEntity(false,HttpStatus.NO_CONTENT);
			}
		}catch(Exception e) {
			return new ResponseEntity(false,HttpStatus.NO_CONTENT);
		}
	}
	
	@PutMapping("/starpoint")
	public ResponseEntity updateStarpoint(@RequestBody Starpoint starpoint) throws Exception{
		User user = null;
		String memberId = null;
		try {
			System.out.println("가게 별점 수정 Controller");
			System.out.println(starpoint);
			memberId = jwtService.getMemberId();
			user = userService.getUser(memberId);
			if(user != null) {
				int ans = storeService.updateStarpoint(starpoint);
				System.out.println("수정 : "+ ans);
				return new ResponseEntity(true,HttpStatus.OK);
			}else {
				return new ResponseEntity(false,HttpStatus.NO_CONTENT);
			}
		}catch(Exception e) {
			return new ResponseEntity(false,HttpStatus.NO_CONTENT);
		}
	}
	
	@DeleteMapping("/starpoint")
	public ResponseEntity deleteStarpoint(@RequestBody Starpoint starpoint) throws Exception{
		User user = null;
		String memberId = null;
		try {
			System.out.println("가게 별점 삭제 Controller");
			System.out.println(starpoint);
			memberId = jwtService.getMemberId();
			user = userService.getUser(memberId);
			if(user != null) {
				int ans = storeService.deleteStarpoint(starpoint);
				System.out.println("삭제 : "+ ans);
				return new ResponseEntity(true,HttpStatus.OK);
			}else {
				return new ResponseEntity(false,HttpStatus.NO_CONTENT);
			}
		}catch(Exception e) {
			return new ResponseEntity(false,HttpStatus.NO_CONTENT);
		}
	}
	
	@GetMapping("/order")
	public ResponseEntity<List<OrderFood>> getOrderByUserid() throws Exception{
		String memberId = null;
		User user = null;
		List<OrderFood> list = null;
		Store store = null;
		try {
			System.out.println("user 주문내역 정보");
			memberId = jwtService.getMemberId();
			user = userService.getUser(memberId);
			//System.out.println(user);
			if(user != null) {
				list = userService.getOrderByUserid(memberId);
				for(int i = 0; i < list.size(); i++) {
					store = storeService.getStore(list.get(i).getStoreid());
					list.get(i).setStorename(store.getName());
				}
				return new ResponseEntity<List<OrderFood>> (list,HttpStatus.OK);				
			}else {
				return new ResponseEntity<List<OrderFood>> (HttpStatus.BAD_REQUEST);		
			}
		}catch(Exception e) {
			return new ResponseEntity<List<OrderFood>> (HttpStatus.NO_CONTENT);
		}
	}
}
