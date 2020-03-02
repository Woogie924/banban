package com.ssafy.subpjt.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.subpjt.service.KakaoPay;
import com.ssafy.subpjt.service.OrderService;
import com.ssafy.subpjt.vo.kakaoPayVO;

import lombok.Setter;
import lombok.extern.java.Log;

@CrossOrigin(origins = {"*"}, maxAge = 6000)
@Log
@RestController
public class KakaoPayController {

	@Setter(onMethod_ = @Autowired)
	private KakaoPay kakaopay;

	@Autowired
	private OrderService orderService;

	@GetMapping("/kakaoPay")
	public void kakaoPayGet() {

	}

	@PostMapping("/kakaoPay")
	public String kakaoPay(@RequestBody kakaoPayVO kakaoPayVo ) {
		log.info("kakaoPay post............................................");
		System.out.println(kakaoPayVo);
		return kakaopay.kakaoPayReady(kakaoPayVo);

	}


//	@GetMapping("/Success") public String
//	kakaoPaySuccess(@RequestParam("partner_order_id") int
//			id,@RequestParam("pg_token") String pg_token, Model model) {
//		log.info("kakaoPaySuccess get............................................");
//		log.info("kakaoPaySuccess pg_token : " + pg_token);
//		System.out.println("주문 상태 변경"); System.out.println(id); String onum =
//				kakaopay.kakaoPayInfo(pg_token); return
//						"http://54.180.163.74:8080/#/kakaoPaySuccess?partner_order_id="+id+"&pg_token="
//						+pg_token; }

}
