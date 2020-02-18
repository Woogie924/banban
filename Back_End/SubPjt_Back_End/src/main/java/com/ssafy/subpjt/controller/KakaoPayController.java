package com.ssafy.subpjt.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.subpjt.service.KakaoPay;
import com.ssafy.subpjt.vo.KakaoPayApprovalVO;
import com.ssafy.subpjt.vo.kakaoPayVO;

import lombok.Setter;
import lombok.extern.java.Log;

@CrossOrigin(origins = {"*"}, maxAge = 6000)
@Log
@RestController
public class KakaoPayController {

	 	@Setter(onMethod_ = @Autowired)
	    private KakaoPay kakaopay;
	    
	    
	    @GetMapping("/kakaoPay")
	    public void kakaoPayGet() {
	        
	    }
	    
	    @PostMapping("/kakaoPay")
	    public String kakaoPay(@RequestBody kakaoPayVO kakaoPayVo ) {
	        log.info("kakaoPay post............................................");
	        System.out.println(kakaoPayVo);
	        return kakaopay.kakaoPayReady(kakaoPayVo);
	 
	    }
	    
	    @GetMapping("/kakaoPaySuccess")
	    public void kakaoPaySuccess(@RequestParam("pg_token") String pg_token, Model model) {
	        log.info("kakaoPaySuccess get............................................");
	        log.info("kakaoPaySuccess pg_token : " + pg_token);
	        
	        model.addAttribute("info", kakaopay.kakaoPayInfo(pg_token));
	       
	        //return "kakaoPaySuccess.html";
	        System.out.println(model);
	    }
}
