
package com.ssafy.subpjt.controller;

import java.util.ArrayList;
import java.util.List;

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

import com.ssafy.subpjt.service.BoardService;
import com.ssafy.subpjt.service.JwtService;
import com.ssafy.subpjt.service.TransactionService;
import com.ssafy.subpjt.service.UserService;
import com.ssafy.subpjt.vo.Board;
import com.ssafy.subpjt.vo.Comment;
import com.ssafy.subpjt.vo.Party;
import com.ssafy.subpjt.vo.PartyMember;
import com.ssafy.subpjt.vo.User;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController
@CrossOrigin(origins = {"*"}, maxAge = 6000)
@RequestMapping("/notice")
public class BoardRestController {

	@Autowired
	private BoardService boardService;

	@Autowired
	private UserService userService;

	@Autowired
	private JwtService jwtService;
	
	@Autowired
	private TransactionService transactionService;

	@ApiImplicitParams({
		@ApiImplicitParam(name = "Authorization", value = "authorization header", required = true,
				dataType = "string", paramType = "header", defaultValue = "bearer eyJ0eXAiOiJKV1QiLCJyZWdEYXRlIjoxNTgxMjk4Nzc0ODQxLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ1c2VyIiwiZXhwIjoxNTgxNDcxNTc0LCJtZW1iZXIiOnsiaWQiOiJoc3c5MjQiLCJwdyI6IjEyMyIsInRlbCI6IjAxMDUwMDg5OTYyIiwibmFtZSI6Iu2ZjeyEseyasSIsImVtYWlsIjoiYXNpYTkyNEBuYXZlci5jb20iLCJhZGRyZXNzIjoi6rK967aBIOq1rOuvuOyLnCDqsoDshLHroZwgOSAo7J247J2Y64-ZKSIsImxhdGl0dWRlIjozNi4xMTE0NjM1NTYyMzc4OSwibG9uZ3RpdHVkZSI6MTI4LjQyNDQ1OTMzOTcxNDA1LCJwb2ludCI6MCwic2lnbnVwRGF0ZSI6MTU4MDk1MzcxMDAwMCwiY2hrIjoxfX0.aYdsuBQZqcV0snOJEz-DQA4FtFfpehA4KjQe18Ufu_E")
	})
	@ApiParam
	@ApiOperation(value = "게시글 get", httpMethod = "GET", notes = "게시글 가져오는 부분.")
	@GetMapping("/allboards/{lon}/{lat}")
	public ResponseEntity<List<Board>> getAllBoards(@PathVariable double lat, @PathVariable double lon) throws Exception{
		List<Board> list = null;
		List<Board> ans = new ArrayList<>();
		String memberId = null;
		User loginuser = null;
		try {
			System.out.println("게시판 list Controller");
			memberId = jwtService.getMemberId();
			loginuser = userService.getUser(memberId);
			if(loginuser != null) {
				list = boardService.getAllBoards();
				System.out.println(list);
				for(Board s : list) {
					//System.out.println(s);
					double distanceKiloMeter = 
							transactionService.distance(lat, lon, s.getUser().getLatitude(), s.getUser().getLongitude());
					//System.out.println(distanceKiloMeter);
					if(distanceKiloMeter <= 10) {
						//System.out.println("거리안에 들어옴 : " + s.toString());
						ans.add(s);
					}
				}
				System.out.println("주위 사람 게시글 : " + ans);
				return new ResponseEntity<List<Board>>(ans, HttpStatus.OK);				
			}else {
				return new ResponseEntity<List<Board>>(HttpStatus.BAD_REQUEST);
			}
		}catch(Exception e) {
			return new ResponseEntity<List<Board>>(HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping("/board/{num}")
	public ResponseEntity<Board> getBoardByNum(@PathVariable int num) throws Exception{
		Board board = null;
		String memberId = null;
		User user = null;
		try {
			System.out.println("게시판 상세보기 Controller");
			memberId = jwtService.getMemberId();
			user = userService.getUser(memberId);
			if(user != null) {
				board = boardService.getBoardByNum(num);
				System.out.println(board);				
				return new ResponseEntity<Board>(board, HttpStatus.OK);
			}else {
				return new ResponseEntity<Board>(HttpStatus.BAD_REQUEST);				
			}
		}catch(Exception e) {
			return new ResponseEntity<Board>(HttpStatus.BAD_REQUEST);
		}
	}

	@PutMapping("/board/{max}")
	public ResponseEntity updateBoard(@RequestBody Board board, @PathVariable int max) throws Exception{
		String memberId = null;
		User user = null;
		Party party = null;
		try {
			System.out.println("게시글 수정 Controller");
			memberId = jwtService.getMemberId();
			user = userService.getUser(memberId);
			if(user != null) {
				transactionService.updateBoard(board, max);
				return new ResponseEntity(true,HttpStatus.OK);
			}else {
				return new ResponseEntity(HttpStatus.BAD_REQUEST);				
			}
		}catch(Exception e) {
			return new ResponseEntity(false,HttpStatus.BAD_REQUEST);
		}
	}

	@PostMapping("/board/{max}")
	public ResponseEntity insertBoard(@RequestBody Board board, @PathVariable int max) throws Exception{
		String memberId = null;
		User user = null;
		try {
			System.out.println("게시글 등록 Controller");
			memberId = jwtService.getMemberId();
			user = userService.getUser(memberId);
			if(user != null) {
				int ans = transactionService.insertBoard(board, max);
				System.out.println(ans + "파티 멤버 입력 완료");
				return new ResponseEntity(HttpStatus.OK);
			}else {
				return new ResponseEntity(HttpStatus.BAD_REQUEST);				
			}
		}catch(Exception e) {
			return new ResponseEntity(HttpStatus.BAD_REQUEST);
		}
	}

	@DeleteMapping("/board/{num}")
	public ResponseEntity deleteBoard(@PathVariable int num) throws Exception{
		String memberId = null;
		User user = null;
		try {
			System.out.println("게시글 삭제 Controller");
			memberId = jwtService.getMemberId();
			user = userService.getUser(memberId);
			if(user != null) {
				int ans = boardService.deleteBoard(num);
				System.out.println("게시글 삭제 : " + ans);
				return new ResponseEntity(true,HttpStatus.OK);
			}else {
				return new ResponseEntity(false,HttpStatus.BAD_REQUEST);
			}
		}catch(Exception e) {
			return new ResponseEntity(false,HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping("/comment/{bnum}")
	public ResponseEntity<List<Comment>> getComment(@PathVariable int bnum) throws Exception{
		String memberId = null;
		User user = null;
		List<Comment> list = null;
		try {
			System.out.println("댓글 Controller");
			memberId = jwtService.getMemberId();
			user = userService.getUser(memberId);
			if(user != null) {
				list = boardService.getComment(bnum);
				System.out.println(list);
				return new ResponseEntity<List<Comment>>(list, HttpStatus.OK);				
			}else {
				return new ResponseEntity<List<Comment>>(HttpStatus.BAD_REQUEST);

			}
		}catch(Exception e) {
			return new ResponseEntity<List<Comment>>(HttpStatus.BAD_REQUEST);
		}
	}

	@PostMapping("/comment")
	public ResponseEntity insertComment(@RequestBody Comment comment) throws Exception{
		String memberId = null;
		User user = null;
		int ans = 0;
		try {
			System.out.println("댓글 insert Controller");
			memberId = jwtService.getMemberId();
			user = userService.getUser(memberId);
			System.out.println(comment);
			if(user != null) {
				ans = boardService.insertComment(comment);
				System.out.println("댓글 입력 : " + ans);
				if(ans == 1) {
					return new ResponseEntity(true, HttpStatus.OK);
				}else {
					return new ResponseEntity(HttpStatus.NO_CONTENT);
				}
			}else {
				return new ResponseEntity(HttpStatus.BAD_REQUEST);
			}
		}catch (Exception e) {
			return new ResponseEntity(HttpStatus.BAD_REQUEST);
		}
	}

	@DeleteMapping("/comment/{cnum}")
	public ResponseEntity deleteComment(@PathVariable int cnum) throws Exception{
		String memberId = null;
		User user = null;
		int ans = 0;
		try {
			System.out.println("댓글 delete Controller");
			memberId = jwtService.getMemberId();
			user = userService.getUser(memberId);
			if(user != null) {
				ans = boardService.deleteComment(cnum);
				System.out.println("댓글 삭제 : " + ans);
				if(ans == 1) {
					return new ResponseEntity(true, HttpStatus.OK);
				}else {
					return new ResponseEntity(HttpStatus.NO_CONTENT);
				}
			}else {
				return new ResponseEntity(HttpStatus.BAD_REQUEST);
			}
		}catch (Exception e) {
			return new ResponseEntity(HttpStatus.BAD_REQUEST);
		}
	}

	@PutMapping("/comment")
	public ResponseEntity updateComment(@RequestBody Comment comment) throws Exception{
		String memberId = null;
		User user = null;
		int ans = 0;
		try {
			System.out.println("댓글 update Controller");
			memberId = jwtService.getMemberId();
			user = userService.getUser(memberId);
			if(user != null) {
				ans = boardService.updateComment(comment);
				System.out.println("댓글 수정 : " + ans);
				if(ans == 1) {
					return new ResponseEntity(true, HttpStatus.OK);
				}else {
					return new ResponseEntity(HttpStatus.NO_CONTENT);
				}
			}else {
				return new ResponseEntity(HttpStatus.BAD_REQUEST);
			}
		}catch (Exception e) {
			return new ResponseEntity(HttpStatus.BAD_REQUEST);
		}
	}

	@ApiImplicitParams({
		@ApiImplicitParam(name = "Authorization", value = "authorization header", required = true,
				dataType = "string", paramType = "header", defaultValue = "bearer eyJ0eXAiOiJKV1QiLCJyZWdEYXRlIjoxNTgxMDM0NTQ4MDE5LCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ1c2VyIiwiZXhwIjoxNTgxMjA3MzQ4LCJtZW1iZXIiOnsiaWQiOiJ0ZXN0OTQiLCJwdyI6IjEyMzEyMyIsInRlbCI6IjMyMTMxMjMxMiIsIm5hbWUiOiIzMTIzMTIzMTIiLCJlbWFpbCI6IjMxMjMxMjEyMyIsImFkZHJlc3MiOiLqsr3quLAg7ZmU7ISx7IucIOyasOygleydjSAzLjHrp4zshLjroZwgMSIsImxhdGl0dWRlIjozNy4wODExNDEzODA3MzE5NSwibG9uZ3RpdHVkZSI6MTI2LjgxNDQ3NDI1Nzk0NDgyLCJwb2ludCI6MCwic2lnbnVwRGF0ZSI6MTU4MDk1Mzc3NzAwMCwiY2hrIjoxfX0.2VFlypfekVT58L4ZNQ_JEDKalEtyv6-rtDu0LXYl4gQ")
	})
	@ApiParam
	@ApiOperation(value = "파티원 get", httpMethod = "GET", notes = "게시글 파티원 가져오는 부분.")
	@GetMapping("/members/{bnum}")
	public ResponseEntity<List<PartyMember>> getPartMembers(@PathVariable int bnum) throws Exception{
		String memberId = null;
		User user = null;
		List<PartyMember> list = null;
		try {
			System.out.println("파티 멤버 Controller");
			memberId = jwtService.getMemberId();
			user = userService.getUser(memberId);
			if(user != null) {
				list = boardService.getPartMembers(bnum);
				System.out.println(list);
				return new ResponseEntity<List<PartyMember>>(list, HttpStatus.OK);				
			}else {
				return new ResponseEntity<List<PartyMember>>(HttpStatus.BAD_REQUEST);

			}
		}catch(Exception e) {
			return new ResponseEntity<List<PartyMember>>(HttpStatus.BAD_REQUEST);
		}
	}

	@ApiImplicitParams({
		@ApiImplicitParam(name = "Authorization", value = "authorization header", required = true,
				dataType = "string", paramType = "header", defaultValue = "bearer eyJ0eXAiOiJKV1QiLCJyZWdEYXRlIjoxNTgxMzgwMTI5MDQyLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ1c2VyIiwiZXhwIjoxNTgxNTUyOTI5LCJtZW1iZXIiOnsiaWQiOiJ0ZXN0OTQiLCJwdyI6IjEyMzEyMyIsInRlbCI6IjMyMTMxMjMxMiIsIm5hbWUiOiIzMTIzMTIzMTIiLCJlbWFpbCI6IjMxMjMxMjEyMyIsImFkZHJlc3MiOiLqsr3quLAg7ZmU7ISx7IucIOyasOygleydjSAzLjHrp4zshLjroZwgMSIsImxhdGl0dWRlIjozNy4wODExNDEzODA3MzE5NSwibG9uZ2l0dWRlIjoxMjYuODE0NDc0MjU3OTQ0ODIsInBvaW50IjowLCJzaWdudXBEYXRlIjoxNTgwOTUzNzc3MDAwLCJjaGsiOjF9fQ.csmMYpwggUGuOOCeKgNMfpR4YYMobNyrKb5cbdZlpYg")
	})
	@ApiParam
	@ApiOperation(value = "멤버 insert", httpMethod = "POST", notes = "멤버 insert")
	@PostMapping("/members/{bnum}/{id}")
	public ResponseEntity insertPartMembers(@PathVariable int bnum, @PathVariable String id) throws Exception{
		String memberId = null;
		User user = null;
		
		try {
			System.out.println("파티 멤버 추가 Controller");
			memberId = jwtService.getMemberId();
			user = userService.getUser(memberId);
			if(user != null) {
				int ans = transactionService.insertPartyMember(memberId, bnum);
				if(ans == 1) {
					System.out.println("파티멤버 추가 : " + ans);
					return new ResponseEntity(HttpStatus.OK);		
				}else {
					return new ResponseEntity("파티정원을 초과했거나 파티 참가에 실패하셨습니다.",HttpStatus.NO_CONTENT);
				}
			}else {
				return new ResponseEntity(HttpStatus.BAD_REQUEST);

			}
		}catch(Exception e) {
			return new ResponseEntity(HttpStatus.BAD_REQUEST);
		}
	}
	
	@ApiImplicitParams({
		@ApiImplicitParam(name = "Authorization", value = "authorization header", required = true,
				dataType = "string", paramType = "header", defaultValue = "bearer eyJ0eXAiOiJKV1QiLCJyZWdEYXRlIjoxNTgxMzgwMTI5MDQyLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ1c2VyIiwiZXhwIjoxNTgxNTUyOTI5LCJtZW1iZXIiOnsiaWQiOiJ0ZXN0OTQiLCJwdyI6IjEyMzEyMyIsInRlbCI6IjMyMTMxMjMxMiIsIm5hbWUiOiIzMTIzMTIzMTIiLCJlbWFpbCI6IjMxMjMxMjEyMyIsImFkZHJlc3MiOiLqsr3quLAg7ZmU7ISx7IucIOyasOygleydjSAzLjHrp4zshLjroZwgMSIsImxhdGl0dWRlIjozNy4wODExNDEzODA3MzE5NSwibG9uZ2l0dWRlIjoxMjYuODE0NDc0MjU3OTQ0ODIsInBvaW50IjowLCJzaWdudXBEYXRlIjoxNTgwOTUzNzc3MDAwLCJjaGsiOjF9fQ.csmMYpwggUGuOOCeKgNMfpR4YYMobNyrKb5cbdZlpYg")
	})
	@ApiParam
	@ApiOperation(value = "멤버 delete", httpMethod = "DELETE", notes = "멤버 delete")
	@DeleteMapping("/members/{bnum}/{id}")
	public ResponseEntity deletePartMembers(@PathVariable int bnum, @PathVariable String id) throws Exception{
		String memberId = null;
		User user = null;
		List<PartyMember> list = null;
		try {
			System.out.println("파티 멤버 추가 Controller");
			memberId = jwtService.getMemberId();
			user = userService.getUser(memberId);
			if(user != null) {
				int ans = transactionService.deletePartyMember(bnum, id);
				System.out.println("파티멤버 삭제 : " + ans);
				return new ResponseEntity( HttpStatus.OK);		
			}else {
				return new ResponseEntity(HttpStatus.BAD_REQUEST);

			}
		}catch(Exception e) {
			return new ResponseEntity(HttpStatus.BAD_REQUEST);
		}
	}

}
