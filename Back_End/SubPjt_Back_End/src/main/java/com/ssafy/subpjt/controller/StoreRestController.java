package com.ssafy.subpjt.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

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
import com.ssafy.subpjt.vo.BillingHistory;
import com.ssafy.subpjt.vo.Likes;
import com.ssafy.subpjt.vo.Menu;
import com.ssafy.subpjt.vo.Result;
import com.ssafy.subpjt.vo.Starpoint;
import com.ssafy.subpjt.vo.Store;
import com.ssafy.subpjt.vo.Storeinfo;
import com.ssafy.subpjt.vo.Storestarpoint;
import com.ssafy.subpjt.vo.User;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController
@CrossOrigin(origins = {"*"}, maxAge = 6000)
@RequestMapping("/shopkeeper")
public class StoreRestController {

	@Autowired
	private StoreService storeService;

	@Autowired
	private UserService userService;

	@Autowired
	private JwtService jwtService;

	@Autowired
	private TransactionService transactionService;



	@PostMapping("/store")
	public ResponseEntity addStore(@RequestBody Store store) throws Exception{
		try {
			System.out.println("가게 회원가입 Controller");
			System.out.println(store);
			int ans = storeService.addStore(store);
			System.out.println(ans);
			return new ResponseEntity(true,HttpStatus.OK);
		}catch(Exception e) {
			return new ResponseEntity(false,HttpStatus.NO_CONTENT);
		}
	}


	@GetMapping("/likedstore")
	public ResponseEntity<List<Store>> getAllLikedStore() throws Exception{
		User user = null;
		String memberId = null;
		List<Store> sList = null;
		try {
			System.out.println("좋아요한 가게 정보 Controller");
			memberId = jwtService.getMemberId();
			user = userService.getUser(memberId);
			if(user != null) {
				sList = transactionService.getAllLikedStore(memberId);
				return new ResponseEntity<List<Store>>(sList,HttpStatus.OK);
			}else {
				return new ResponseEntity<List<Store>>(HttpStatus.NO_CONTENT);
			}
		}catch(Exception e) {
			return new ResponseEntity<List<Store>>(HttpStatus.NO_CONTENT);
		}
	}

	@GetMapping("/store/{id}")
	public ResponseEntity<Store> getStoreById(@PathVariable String id) throws Exception{
		User user = null;
		String memberId = null;
		try {
			System.out.println("가게 상세 정보 Controller");
			memberId = jwtService.getMemberId();
			user = userService.getUser(memberId);
			if(user != null) {
				Store store = transactionService.getDetailStore(id);
				return new ResponseEntity<Store>(store,HttpStatus.OK);
			}else {
				return new ResponseEntity<Store>(HttpStatus.NO_CONTENT);
			}
		}catch(Exception e) {
			return new ResponseEntity<Store>(HttpStatus.NO_CONTENT);
		}
	}


	@GetMapping("/store")
	public ResponseEntity<Store> getStore() throws Exception{
		Store store = null;
		String memberId = null;
		try {
			System.out.println("가게 정보 Controller");
			memberId = jwtService.getMemberId();
			store = storeService.getStore(memberId);
			System.out.println(store);
			if(store != null) {
				return new ResponseEntity<Store>(store,HttpStatus.OK);
			}else {
				return new ResponseEntity<Store>(HttpStatus.NO_CONTENT);
			}
		}catch(Exception e) {
			return new ResponseEntity<Store>(HttpStatus.NO_CONTENT);
		}
	}

	@GetMapping("/countLikes")
	public ResponseEntity<List<Likes>> countLikes() throws Exception{
		List<Likes> list = null;
		User ans = null;
		String memberId = null;
		try {
			//System.out.println("가게 랭킹 Controller");	
			//			memberId = jwtService.getMemberId();
			//			ans = userService.getUser(memberId);
			//			if(ans != null) {
			list = storeService.countLikes();
			return new ResponseEntity<List<Likes>>(list,HttpStatus.OK);				
			//			}else {
			//				return new ResponseEntity<List<Store>>(HttpStatus.BAD_REQUEST);
			//			}
		}catch(Exception e) {
			return new ResponseEntity<List<Likes>>(HttpStatus.BAD_REQUEST);
		}
	}

	@PostMapping("/nearstores")
	public ResponseEntity<List<Store>> getAllStores(@RequestBody User user) throws Exception{
		List<Store> list = null;
		List<Store> res = new ArrayList<>();
		User ans = null;
		String memberId = null;
		try {
			System.out.println("주변 가게 Controller");	
			memberId = jwtService.getMemberId();
			ans = userService.getUser(memberId);
			if(ans != null) {
				list = storeService.getAllStores();
				//System.out.println(list);
				for(Store s : list) {
					//System.out.println(s);
					double distanceKiloMeter = 
							transactionService.distance(user.getLatitude(), user.getLongitude(), s.getLatitude(), s.getLongitude());
					//System.out.println(distanceKiloMeter);
					if(distanceKiloMeter <= 10) {
						//System.out.println("거리안에 들어옴 : " + s.toString());
						res.add(s);
					}
				}
				System.out.println(res);
				return new ResponseEntity<List<Store>>(res,HttpStatus.OK);

			}else {
				return new ResponseEntity<List<Store>>(HttpStatus.BAD_REQUEST);
			}
		}catch(Exception e) {
			return new ResponseEntity<List<Store>>(HttpStatus.BAD_REQUEST);
		}
	}





	@GetMapping("/menu/{id}")
	public ResponseEntity<List<Menu>> getMenu(@PathVariable String id) throws Exception{
		List<Menu> list = null;
		Store ans = null;
		String memberId = null;
		try {
			System.out.println("메뉴 list Controller");
			memberId = jwtService.getMemberId();
			ans = storeService.getStore(memberId);
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

	@GetMapping("/getprofit")
	public ResponseEntity<List<BillingHistory>> getRecentProfit() throws Exception{
		List<BillingHistory> list = null;
		Store ans = null;
		String memberId = null;
		try {
			System.out.println("최근 1주일간 수익 Controller");
			memberId = jwtService.getMemberId();
			ans = storeService.getStore(memberId);
			if(ans != null) {
				list = storeService.getRecentProfit(ans.getId());
				System.out.println("1주일간 수익 : " +  list);
				return new ResponseEntity<List<BillingHistory>>(list,HttpStatus.OK);
			}
			else {
				return new ResponseEntity<List<BillingHistory>>(HttpStatus.BAD_REQUEST);
			}
		}catch(Exception e) {
			return new ResponseEntity<List<BillingHistory>>(HttpStatus.BAD_REQUEST);
		}
	}

	@PostMapping("/menu")
	public ResponseEntity insertMenu(@RequestBody Menu menu) throws Exception{
		Store ans = null;
		String memberId = null;
		try {
			System.out.println("메뉴 insert Controller");
			memberId = jwtService.getMemberId();
			ans = storeService.getStore(memberId);
			System.out.println(menu);
			if(ans != null) {
				int res = storeService.insertMenu(menu);
				System.out.println("menu insert : " + res);
				return new ResponseEntity(HttpStatus.OK);
			}
			else {
				return new ResponseEntity(HttpStatus.BAD_REQUEST);
			}
		}catch(Exception e) {
			return new ResponseEntity(HttpStatus.BAD_REQUEST);
		}
	}

	@PostMapping("/loginCheck")
	public ResponseEntity<Result> loginCheck(@RequestBody Store store, HttpServletResponse response) throws Exception{
		Store ans = null;
		Result result = Result.successInstance();
		try {
			System.out.println("가게 로그인 Controller");
			ans = storeService.loginCheck(store);
			System.out.println(ans);
			if(ans != null) {
				String token = jwtService.create("member", ans, "user");
				response.setHeader("Authorization", token);
				result.setToken(token);
				result.setData(ans);
				return new ResponseEntity<Result>(result, HttpStatus.OK);
			}else {
				return new ResponseEntity<Result>(HttpStatus.NO_CONTENT);
			}
		}catch(Exception e) {
			return new ResponseEntity<Result>( HttpStatus.NO_CONTENT);
		}
	}


	@ApiImplicitParams({
		@ApiImplicitParam(name = "Authorization", value = "authorization header", required = true,
				dataType = "string", paramType = "header", defaultValue = "bearer eyJ0eXAiOiJKV1QiLCJyZWdEYXRlIjoxNTgxMzAyNDIyNTY5LCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ1c2VyIiwiZXhwIjoxNTgxNDc1MjIyLCJtZW1iZXIiOnsiaWQiOiJoc3c5MjQiLCJwdyI6IjEyMyIsInRlbCI6IjAxMDUwMDg5OTYyIiwibmFtZSI6Iu2ZjeyEseyasSIsImVtYWlsIjoiYXNpYTkyNEBuYXZlci5jb20iLCJhZGRyZXNzIjoi6rK967aBIOq1rOuvuOyLnCDqsoDshLHroZwgOSAo7J247J2Y64-ZKSIsImxhdGl0dWRlIjozNi4xMTE0NjM1NTYyMzc4OSwibG9uZ3RpdHVkZSI6MTI4LjQyNDQ1OTMzOTcxNDA1LCJwb2ludCI6MCwic2lnbnVwRGF0ZSI6MTU4MDk1MzcxMDAwMCwiY2hrIjoxfX0.38HuqGeOMUlAAiF-4CO-xjMeWAbFZYF5O7b3RMhirIA")
	})
	@ApiParam
	@ApiOperation(value = "좋아요 up", httpMethod = "POST", notes = "좋아요 up")
	@PostMapping("/likes")
	public ResponseEntity upLikes(@RequestBody Likes likes) throws Exception{
		User user = null;
		String memberId = null;
		int ans = 0;
		try {
			System.out.println("좋아요 up Controller");
			memberId = jwtService.getMemberId();
			user = userService.getUser(memberId);
			if(user != null) {
				ans = storeService.upLikes(likes);
				System.out.println("좋아요 up : " + ans);
				return new ResponseEntity(HttpStatus.OK);
			}
			else {
				return new ResponseEntity(HttpStatus.BAD_REQUEST);
			}
		}catch(Exception e) {
			return new ResponseEntity(HttpStatus.BAD_REQUEST);
		}
	}


	@ApiImplicitParams({
		@ApiImplicitParam(name = "Authorization", value = "authorization header", required = true,
				dataType = "string", paramType = "header", defaultValue = "bearer eyJ0eXAiOiJKV1QiLCJyZWdEYXRlIjoxNTgxMzAyNDIyNTY5LCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ1c2VyIiwiZXhwIjoxNTgxNDc1MjIyLCJtZW1iZXIiOnsiaWQiOiJoc3c5MjQiLCJwdyI6IjEyMyIsInRlbCI6IjAxMDUwMDg5OTYyIiwibmFtZSI6Iu2ZjeyEseyasSIsImVtYWlsIjoiYXNpYTkyNEBuYXZlci5jb20iLCJhZGRyZXNzIjoi6rK967aBIOq1rOuvuOyLnCDqsoDshLHroZwgOSAo7J247J2Y64-ZKSIsImxhdGl0dWRlIjozNi4xMTE0NjM1NTYyMzc4OSwibG9uZ3RpdHVkZSI6MTI4LjQyNDQ1OTMzOTcxNDA1LCJwb2ludCI6MCwic2lnbnVwRGF0ZSI6MTU4MDk1MzcxMDAwMCwiY2hrIjoxfX0.38HuqGeOMUlAAiF-4CO-xjMeWAbFZYF5O7b3RMhirIA")
	})
	@ApiParam
	@ApiOperation(value = "좋아요 down", httpMethod = "DELETE", notes = "좋아요 down")
	@DeleteMapping("/likes")
	public ResponseEntity downLikes(@RequestBody Likes likes) throws Exception{
		User user = null;
		String memberId = null;
		int ans = 0;
		try {
			System.out.println("좋아요 down Controller");
			memberId = jwtService.getMemberId();
			user = userService.getUser(memberId);
			if(user != null) {
				ans = storeService.downLikes(likes);
				System.out.println("좋아요 down : " + ans);
				return new ResponseEntity(HttpStatus.OK);
			}
			else {
				return new ResponseEntity(HttpStatus.BAD_REQUEST);
			}
		}catch(Exception e) {
			return new ResponseEntity(HttpStatus.BAD_REQUEST);
		}
	}

	@PostMapping("/storeinfo")
	public ResponseEntity insertStoreinfo(@RequestBody Storeinfo storeinfo) throws Exception{
		Store user = null;
		String memberId = null;
		try {
			System.out.println("가게 정보 입력 Controller");
			System.out.println(storeinfo);
			memberId = jwtService.getMemberId();
			user = storeService.getStore(memberId);
			if(user != null) {
				int ans = storeService.insertStoreinfo(storeinfo);
				System.out.println("입력 : "+ ans);
				return new ResponseEntity(true,HttpStatus.OK);
			}else {
				return new ResponseEntity(false,HttpStatus.NO_CONTENT);
			}
		}catch(Exception e) {
			return new ResponseEntity(false,HttpStatus.NO_CONTENT);
		}
	}

	@PutMapping("/storeinfo")
	public ResponseEntity updateStoreinfo(@RequestBody Storeinfo storeinfo) throws Exception{
		Store user = null;
		String memberId = null;
		try {
			System.out.println("가게 정보 수정 Controller");
			System.out.println(storeinfo);
			memberId = jwtService.getMemberId();
			user = storeService.getStore(memberId);
			if(user != null) {
				int ans = storeService.updateStoreinfo(storeinfo);
				System.out.println(ans);
				return new ResponseEntity(true,HttpStatus.OK);
			}else {
				return new ResponseEntity(false,HttpStatus.NO_CONTENT);
			}
		}catch(Exception e) {
			return new ResponseEntity(false,HttpStatus.NO_CONTENT);
		}
	}
	
	@PostMapping("/storestarpoint")
	public ResponseEntity insertStarpoint(@RequestBody Storestarpoint storestarpoint) throws Exception{
		Store user = null;
		String memberId = null;
		try {
			System.out.println("가게 사장님 리뷰 입력 Controller");
			System.out.println(storestarpoint);
			memberId = jwtService.getMemberId();
			user = storeService.getStore(memberId);
			if(user != null) {
				int ans = storeService.insertStorestarpoint(storestarpoint);
				System.out.println("입력 : "+ ans);
				return new ResponseEntity(true,HttpStatus.OK);
			}else {
				return new ResponseEntity(false,HttpStatus.NO_CONTENT);
			}
		}catch(Exception e) {
			return new ResponseEntity(false,HttpStatus.NO_CONTENT);
		}
	}
	
	@PutMapping("/storestarpoint")
	public ResponseEntity updateStorestarpoint(@RequestBody Storestarpoint storestarpoint) throws Exception{
		Store user = null;
		String memberId = null;
		try {
			System.out.println("가게 사장님 수정 Controller");
			System.out.println(storestarpoint);
			memberId = jwtService.getMemberId();
			user = storeService.getStore(memberId);
			if(user != null) {
				int ans = storeService.deleteStorestarpoint(storestarpoint);
				System.out.println("삭제 : "+ ans);
				return new ResponseEntity(true,HttpStatus.OK);
			}else {
				return new ResponseEntity(false,HttpStatus.NO_CONTENT);
			}
		}catch(Exception e) {
			return new ResponseEntity(false,HttpStatus.NO_CONTENT);
		}
	}
	
	@DeleteMapping("/storestarpoint")
	public ResponseEntity deleteStorestarpoint(@RequestBody Storestarpoint storestarpoint) throws Exception{
		Store user = null;
		String memberId = null;
		try {
			System.out.println("가게 사장님 삭제 Controller");
			System.out.println(storestarpoint);
			memberId = jwtService.getMemberId();
			user = storeService.getStore(memberId);
			if(user != null) {
				int ans = storeService.updateStorestarpoint(storestarpoint);
				System.out.println("수정 : "+ ans);
				return new ResponseEntity(true,HttpStatus.OK);
			}else {
				return new ResponseEntity(false,HttpStatus.NO_CONTENT);
			}
		}catch(Exception e) {
			return new ResponseEntity(false,HttpStatus.NO_CONTENT);
		}
	}
}
