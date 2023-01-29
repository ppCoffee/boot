/**
 * UserController.java
 * Copyright(JAVA) EnRich DTV Group co.,Ltd
 * 功能描述：
 *   
 * 创建者：zhangzy@evmtv.com 
 * 编辑者: zhangzy@evmtv.com
 * 2023年1月28日
 */

package com.evmtv.demo.controller;

import java.nio.charset.StandardCharsets;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.evmtv.demo.config.AuthorizationServerConfig;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

@SuppressWarnings("deprecation")
@RestController
@RequestMapping("/user")
public class UserController {
	

	
	/**
	 * 授权模式demo
	 * @param authentication
	 * @return
	 */
	@GetMapping("/getUser")
	public Object getUser(Authentication authentication) {
		
		return authentication.getPrincipal();
	}
	
	@GetMapping("/getUserByJwt")
	public Object getUserByJwt(Authentication authentication,HttpServletRequest request) {
		
		String header = request.getHeader("Authorization");
		String token = header.substring(header.lastIndexOf("bearer") + 7); //发送时在header中Authorization的值需要 bearer+空格+jwt生成的token 
		Claims claims = Jwts.parser().setSigningKey(AuthorizationServerConfig.SIGN.getBytes(StandardCharsets.UTF_8)).parseClaimsJws(token).getBody();
		
		return claims;
	}
}
