package com.ssafy.subpjt.service;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.ssafy.subpjt.error.UnauthorizedException;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j; 

@Slf4j
@Service("jwtService")
public class JwtServiceImpl implements JwtService{

	private static final String SALT =  "luvookSecret";

	@Override
	public <T> String create(String key, T data, String subject){
		String jwt = Jwts.builder()
				.setHeaderParam("typ", "JWT")
				.setHeaderParam("regDate", System.currentTimeMillis())
				.setSubject(subject)
				.setExpiration(new Date(System.currentTimeMillis() + 86400 * 1000 * 2))
				.claim(key, data)
				.signWith(SignatureAlgorithm.HS256, this.generateKey())
				.compact();
		return jwt;
	}

	private byte[] generateKey(){
		byte[] key = null;
		try {
			key = SALT.getBytes("UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();

		}

		return key;
	}

	@Override
	public boolean isUsable(String jwt) {
		try{
			Jws<Claims> claims = Jwts.parser()
					.setSigningKey(this.generateKey())
					.parseClaimsJws(jwt);
			return true;

		}catch (Exception e) {
			e.printStackTrace();
			throw new UnauthorizedException();

			/*개발환경!!!
			 * return false;*/

		}
	}

	@Override
	public Map<String, Object> get(String key) {
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
		String jwt = request.getHeader("Authorization").split(" ")[1];
		System.out.println("jwt : " + jwt);
		Jws<Claims> claims = null;
		try {
			claims = Jwts.parser()
					.setSigningKey(SALT.getBytes("UTF-8"))
					.parseClaimsJws(jwt);
		} catch (Exception e) {

			e.printStackTrace();

			throw new UnauthorizedException();
		}
		@SuppressWarnings("unchecked")
		Map<String, Object> value = (LinkedHashMap<String, Object>)claims.getBody().get(key);
		return value;
	}

	@Override
	public String getMemberId() {
		return (String) this.get("member").get("id");
	}
}