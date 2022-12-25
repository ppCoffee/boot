/**
 * JwtHelper.java
 * Copyright(JAVA) EnRich DTV Group co.,Ltd
 * 功能描述：
 *   
 * 创建者：zhangzy@evmtv.com 
 * 编辑者: zhangzy@evmtv.com
 * 2022年12月19日
 */



package com.evmtv.boot.util;

import java.security.Key;
import java.security.SecureRandom;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.util.StringUtils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.CompressionCodecs;
import io.jsonwebtoken.JwtParserBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Encoders;
import io.jsonwebtoken.security.Keys;
import lombok.var;



public class JwtHelper {
	
	private static long TOKEN_EXPIRE = 30 * 60 * 1000;
	
	private static String SIGN = "shanghai_Enrich_limit_company_2015-07-20";
	
	private static String SUB = "evm";
	
	private static Key key = Keys.hmacShaKeyFor(Encoders.BASE64.encode(SIGN.getBytes()).getBytes());
	
	
	
	
	public static String createToken(Map<String, Object> map) throws Exception{
		
		String token = Jwts.builder().setSubject(SUB).
				setExpiration(new Date(System.currentTimeMillis() + TOKEN_EXPIRE))
				.addClaims(map).
				signWith(key, SignatureAlgorithm.HS256).
				compressWith(CompressionCodecs.GZIP).
				compact();
		
		return token;

	}
	
	public static Map<String, Object>  getMessage(String token,String... keys) throws Exception{
		
		if(!StringUtils.hasText(token)) {
			return null;
		}
		JwtParserBuilder builder = Jwts.parserBuilder();
		
		Claims claims = builder.setSigningKey(key).build().parseClaimsJws(token).getBody();
		
		Map<String, Object> map = new HashMap<String,Object>();
		
		for(String key : keys){
			map.put(key, claims.get(key));
		}
		
		return map;
	}
	
	public static Map<String, Object>  getMessage(String token,List<String> list) throws Exception{
		
		if(!StringUtils.hasText(token)) {
			return null;
		}
		JwtParserBuilder builder = Jwts.parserBuilder();
		
		Claims claims = builder.setSigningKey(key).build().parseClaimsJws(token).getBody();
		
		Map<String, Object> map = new HashMap<String,Object>();
		
		list.forEach(item -> {
			map.put(item, claims.get(item));
		});
		
		return map;
	}
	
	
	@SuppressWarnings("unused")
	@Deprecated
	private static String generateSafeToken() {
	    SecureRandom random = new SecureRandom();
	    byte[] bytes = new byte[36]; // 36 bytes * 8 = 288 bits, a little bit more than
	                                 // the 256 required bits 
	    random.nextBytes(bytes);
	    var encoder = Base64.getUrlEncoder().withoutPadding();
	    return encoder.encodeToString(bytes);
	}

}
