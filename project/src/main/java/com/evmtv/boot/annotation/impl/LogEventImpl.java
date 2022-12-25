/**
 * LogEventImpl.java
 * Copyright(JAVA) EnRich DTV Group co.,Ltd
 * 功能描述：
 *   
 * 创建者：zhangzy@evmtv.com 
 * 编辑者: zhangzy@evmtv.com
 * 2022年12月20日
 */



package com.evmtv.boot.annotation.impl;

import java.lang.reflect.Method;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.evmtv.boot.annotation.LogEvent;

@Component
@Aspect
public class LogEventImpl {
	
	
	@AfterReturning(pointcut = "@annotation(com.evmtv.boot.annotation.LogEvent)")
    public void doAfterReturning(JoinPoint joinPoint) {
		
		RequestAttributes ra = RequestContextHolder.getRequestAttributes();
        ServletRequestAttributes sra = (ServletRequestAttributes)ra;
        HttpServletRequest request = sra.getRequest();
        
		new Thread(new Runnable() {
			
			public void run() {
				
				MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
		        Method method = methodSignature.getMethod();
		        
		        LogEvent logEvent = method.getAnnotation(LogEvent.class);
		        
		        String param = request.getParameter("pageSize");
		        
				System.err.println("===================" + logEvent.value() + "====================" + param);
			}
		}).start();
	}
	
}
