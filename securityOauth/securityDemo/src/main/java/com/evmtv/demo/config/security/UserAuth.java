/**
 * loginSuccessHandler.java
 * Copyright(JAVA) EnRich DTV Group co.,Ltd
 * 功能描述：
 *   
 * 创建者：zhangzy@evmtv.com 
 * 编辑者: zhangzy@evmtv.com
 * 2023年1月2日
 */

package com.evmtv.demo.config.security;

import java.io.IOException;
import java.util.Collection;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

@Component
public class UserAuth implements AuthenticationSuccessHandler,AuthenticationFailureHandler,AccessDeniedHandler{
	
	/**
	 * 登录成功
	 */
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
		
		User user = (User) authentication.getPrincipal();
		Collection<GrantedAuthority> authorities = user.getAuthorities();
		
//		System.out.println(authorities);
//		authorities.forEach(item -> {System.out.println(item);});
		
		request.getSession().setAttribute("auth", authorities);
		
		request.getRequestDispatcher("/toMain").forward(request, response);
		
	}
	
	/**
	 * 登录失败
	 */
	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException exception) throws IOException, ServletException {
		
		request.getRequestDispatcher("/toError").forward(request, response);
		
	}
	
	/**
	 * 认证失败
	 */
	@Override
	public void handle(HttpServletRequest request, HttpServletResponse response,
			AccessDeniedException accessDeniedException) throws IOException, ServletException {
		
		request.getRequestDispatcher("/authFailed").forward(request, response);
	}
	
}
