/**
 * RedisConfig.java
 * Copyright(JAVA) EnRich DTV Group co.,Ltd
 * 功能描述：
 *   
 * 创建者：zhangzy@evmtv.com 
 * 编辑者: zhangzy@evmtv.com
 * 2023年1月28日
 */

package com.evmtv.demo.config;

import javax.annotation.Resource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore;

@SuppressWarnings("deprecation")
//@Configuration
public class RedisConfig {
	
	@Resource
	private RedisConnectionFactory redisConnectionFactory;
	
	
	@Bean
	TokenStore redisTokenStore() {
		
		return new RedisTokenStore(redisConnectionFactory);
	}
}
