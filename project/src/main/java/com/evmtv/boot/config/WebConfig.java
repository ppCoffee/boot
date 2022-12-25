/**
 * WebConfig.java
 * Copyright(JAVA) EnRich DTV Group co.,Ltd
 * 功能描述：
 *   
 * 创建者：zhangzy@evmtv.com 
 * 编辑者: zhangzy@evmtv.com
 * 2022年12月14日
 */



package com.evmtv.boot.config;

import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.evmtv.boot.interceptor.DemoInterceptor;
import com.evmtv.boot.listener.DemoListener;

@Configuration
public class WebConfig implements WebMvcConfigurer{
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		
		WebMvcConfigurer.super.addInterceptors(registry);
		
		registry.addInterceptor(new DemoInterceptor()).addPathPatterns("/**").excludePathPatterns("/","/login","/index.html");
	}
	
	
	@Bean
	ServletListenerRegistrationBean<DemoListener> listenr(){
		
		System.out.println("listenr started");
		
		DemoListener dl = new DemoListener();
		
		return new ServletListenerRegistrationBean<DemoListener>(dl);
	}
}
