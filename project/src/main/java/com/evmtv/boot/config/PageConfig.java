/**
 * PageConfig.java
 * Copyright(JAVA) EnRich DTV Group co.,Ltd
 * 功能描述：
 *   
 * 创建者：zhangzy@evmtv.com 
 * 编辑者: zhangzy@evmtv.com
 * 2022年12月16日
 */



package com.evmtv.boot.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;

@Configuration
@MapperScan("com.evmtv.boot.mapper")
public class PageConfig { 


    @Bean
    MybatisPlusInterceptor myBatisPlusInterceptor() {
		
        MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();
        
		PaginationInnerInterceptor inner = new PaginationInnerInterceptor();
		inner.setDbType(DbType.MYSQL);
		inner.setMaxLimit(30L);
		
		interceptor.addInnerInterceptor(inner);
		return interceptor;
	}
	
}
