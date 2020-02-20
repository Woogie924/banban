package com.ssafy.subpjt.service;

import java.net.URI;
import java.net.URISyntaxException;

import com.ssafy.subpjt.dao.OrderDAO;
import com.ssafy.subpjt.vo.KakaoPayApprovalVO;
import com.ssafy.subpjt.vo.KakaoPayReadyVO;
import com.ssafy.subpjt.vo.OrderFood;
import com.ssafy.subpjt.vo.kakaoPayVO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
 
import lombok.extern.java.Log;

@Service
@Log
public class KakaoPay {

	private static final String HOST = "https://kapi.kakao.com";

	private KakaoPayReadyVO kakaoPayReadyVO;
	
	private KakaoPayApprovalVO kakaoPayApprovalVO;
	
	private kakaoPayVO kakaoPay;
	
	@Autowired
	private OrderDAO orderDao;

	public String kakaoPayReady(kakaoPayVO kakaoPayVo) {

		RestTemplate restTemplate = new RestTemplate();

		// 서버로 요청할 Header
		HttpHeaders headers = new HttpHeaders();
		headers.add("Authorization", "KakaoAK " + "d1a782db0651de96195c28fa0178e545");
		headers.add("Accept", MediaType.APPLICATION_JSON_UTF8_VALUE);
		headers.add("Content-Type", MediaType.APPLICATION_FORM_URLENCODED_VALUE + ";charset=UTF-8");

		// 서버로 요청할 Body
		MultiValueMap<String, String> params = new LinkedMultiValueMap<String, String>();
		params.add("cid", "TC0ONETIME");
		params.add("partner_order_id", kakaoPayVo.getPartner_order_id());
		params.add("partner_user_id", kakaoPayVo.getPartner_user_id());
		params.add("item_name", kakaoPayVo.getItem_name());
		params.add("quantity", "1");
		params.add("total_amount", kakaoPayVo.getTotal_amount());
		params.add("tax_free_amount", "100");
		params.add("approval_url", "http://localhost:8080/kakaoPaySuccess?partner_order_id=" + kakaoPayVo.getPartner_order_id());
		params.add("cancel_url", "http://localhost:8080/kakaoPayCancel");
		params.add("fail_url", "http://localhost:8080/kakaoPaySuccessFail");

		HttpEntity<MultiValueMap<String, String>> body = new HttpEntity<MultiValueMap<String, String>>(params, headers);

		try {
			kakaoPayReadyVO = restTemplate.postForObject(new URI(HOST + "/v1/payment/ready"), body, KakaoPayReadyVO.class);
			kakaoPay = kakaoPayVo;
			log.info("" + kakaoPayReadyVO);
			System.out.println(kakaoPayReadyVO.getTid());
			 System.out.println("주문 상태 변경");
		        String onum = kakaoPayVo.getPartner_order_id();
		        System.out.println(onum);
		        try {
					OrderFood orderfood = orderDao.getOrderByOnum(Integer.parseInt(onum));
					if(orderfood.getOrdercheck() == 0) {
						int ans = orderDao.zerotoone(Integer.parseInt(onum));
						System.out.println("0 -> 1 : " + ans );
					}
				} catch (NumberFormatException e) {
					e.printStackTrace();
				} catch (Exception e) {
					e.printStackTrace();
				}
		        
			
			return kakaoPayReadyVO.getNext_redirect_pc_url();

		} catch (RestClientException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return "/pay";

	}
	
	 public String kakaoPayInfo(String pg_token) {
		 
	        log.info("KakaoPayInfoVO............................................");
	        log.info("-----------------------------");
	        
	        RestTemplate restTemplate = new RestTemplate();
	 
	        // 서버로 요청할 Header
	        HttpHeaders headers = new HttpHeaders();
	        headers.add("Authorization", "KakaoAK " + "d1a782db0651de96195c28fa0178e545");
	        headers.add("Accept", MediaType.APPLICATION_JSON_UTF8_VALUE);
	        headers.add("Content-Type", MediaType.APPLICATION_FORM_URLENCODED_VALUE + ";charset=UTF-8");
	 
	        // 서버로 요청할 Body
	        MultiValueMap<String, String> params = new LinkedMultiValueMap<String, String>();
	        params.add("cid", "TC0ONETIME");
	        params.add("tid", kakaoPayReadyVO.getTid());
	        params.add("partner_order_id", kakaoPay.getPartner_order_id());
	        params.add("partner_user_id", kakaoPay.getPartner_user_id());
	        params.add("pg_token", pg_token);
	        params.add("total_amount", kakaoPay.getTotal_amount());
	        
	        HttpEntity<MultiValueMap<String, String>> body = new HttpEntity<MultiValueMap<String, String>>(params, headers);
	        System.out.println(body);
	        try {
	            kakaoPayApprovalVO = restTemplate.postForObject(new URI(HOST + "/v1/payment/approve"), body, KakaoPayApprovalVO.class);
	            log.info("" + kakaoPayApprovalVO);
	            System.out.println(kakaoPayApprovalVO);
	            return kakaoPay.getPartner_order_id();
	        
	        } catch (RestClientException e) {
	            // TODO Auto-generated catch block
	            e.printStackTrace();
	        } catch (URISyntaxException e) {
	            // TODO Auto-generated catch block
	            e.printStackTrace();
	        }
	        
	        return null;
	    }

}

