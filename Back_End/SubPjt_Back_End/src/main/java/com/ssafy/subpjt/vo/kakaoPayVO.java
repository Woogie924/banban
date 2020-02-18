package com.ssafy.subpjt.vo;

import lombok.Data;

@Data
public class kakaoPayVO {
	private String partner_order_id;
	private String partner_user_id;
	private String item_name;
	private String total_amount;
	
}
