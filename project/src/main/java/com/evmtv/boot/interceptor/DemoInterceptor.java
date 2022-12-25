/**
 * DemoInterceptor.java
 * Copyright(JAVA) EnRich DTV Group co.,Ltd
 * 功能描述：
 *   
 * 创建者：zhangzy@evmtv.com 
 * 编辑者: zhangzy@evmtv.com
 * 2022年12月14日
 */



package com.evmtv.boot.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.evmtv.boot.annotation.LogEvent;
import com.evmtv.boot.util.ResultHelper;

import lombok.extern.slf4j.Slf4j;







@Slf4j
public class DemoInterceptor implements HandlerInterceptor{
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		
        
        return true;
		
//		HttpSession session = request.getSession();
//		
//		log.info("do preHandle " + session);
//		
//		Object sessionId = session.getAttribute("sessionId");
//		if(sessionId != null)
//			return HandlerInterceptor.super.preHandle(request, response, handler);
//		else {
//			request.getRequestDispatcher("/").forward(request, response);
//			return false;
//		}
		
	}
	
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		
		
		log.info("do postHandle");
		
		HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
	}
	
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		
		log.info("do afterCompletion");
		
		HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
	}
}
