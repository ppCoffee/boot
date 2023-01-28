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
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.util.StringUtils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtParserBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Encoders;
import io.jsonwebtoken.security.Keys;



public class JwtHelper {
	
	private static long TOKEN_EXPIRE = 30 * 60 * 1000;
	
	private static String SIGN = "shanghaiEnrichlimitcompany";
	
	private static String SUB = "evm";
	
	private static Key key = Keys.hmacShaKeyFor(Encoders.BASE64.encode(SIGN.getBytes()).getBytes());
	
	
	
	
	public static String createToken(Map<String, Object> map) throws Exception{
		
		String token = Jwts.builder()
				.setId(UUID.randomUUID().toString())
				.setIssuedAt(new Date())
				.setSubject(SUB)
				.setExpiration(new Date(System.currentTimeMillis() + TOKEN_EXPIRE))
				.addClaims(map).
				signWith(key, SignatureAlgorithm.HS256).
//				compressWith(CompressionCodecs.GZIP).    
				compact();
		
		return token;

	}
	
	
	public static Map<String, Object>  getMessage(String token,String... keys) throws Exception{
		
		List<String> list = new ArrayList<String>();
		
		for(String key : keys){
			list.add(key);
		}
		
		return getMessage(token, list);
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
		
		map.put("jti", claims.getId());
		map.put("iat", claims.getIssuedAt());
		map.put("sub", claims.getSubject());
		map.put("exp", claims.getExpiration());
		
		return map;
	}
	


}
