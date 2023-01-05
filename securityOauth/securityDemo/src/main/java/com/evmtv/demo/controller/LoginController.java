/**
 * LoginController.java
 * Copyright(JAVA) EnRich DTV Group co.,Ltd
 * 功能描述：
 *   
 * 创建者：zhangzy@evmtv.com 
 * 编辑者: zhangzy@evmtv.com
 * 2023年1月2日
 */

package com.evmtv.demo.controller;


import java.util.Collection;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import util.ResponseUtil;

@RestController
public class LoginController {
	
	@SuppressWarnings("unchecked")
	@PostMapping("/toMain")
	public ResponseUtil<Collection<GrantedAuthority>> toMain(HttpServletResponse resp,HttpSession session) throws Exception {
		
		//resp.sendRedirect("/main.html");
		Collection<GrantedAuthority> attribute = (Collection<GrantedAuthority>) session.getAttribute("auth");
		
		return new ResponseUtil<Collection<GrantedAuthority>>().result(ResponseUtil.SUCCESS, ResponseUtil.SUCCESS_MESSAGE + " 登录成功", attribute);
	}
	
	@PostMapping("/toError")
	public ResponseUtil<String> toError(HttpServletResponse resp) throws Exception {
		
//		resp.sendRedirect("/error.html");
		return new ResponseUtil<String>().result(ResponseUtil.FAILED, ResponseUtil.FAILED_MESSAGE + " 登录失败");
	}
	
	@GetMapping("/authFailed")
	public ResponseUtil<String> authFailed() throws Exception {
		
		return new ResponseUtil<String>().result(ResponseUtil.FAILED, ResponseUtil.FAILED_MESSAGE + " 认证失败");
	}
	
}
