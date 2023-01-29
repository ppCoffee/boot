/**
 * JwtTokenStoreConfig.java
 * Copyright(JAVA) EnRich DTV Group co.,Ltd
 * 功能描述：
 *   
 * 创建者：zhangzy@evmtv.com 
 * 编辑者: zhangzy@evmtv.com
 * 2023年1月29日
 */

package com.evmtv.demo.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;


@SuppressWarnings("deprecation")
@Configuration
public class JwtTokenStoreConfig {
	
	
	@Bean
	TokenStore jwtTokenStore() {
		return new JwtTokenStore(jwtAccessTokenConverter());
	}

	@Bean
	JwtAccessTokenConverter jwtAccessTokenConverter() {
		JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
		// JWT签名
		converter.setSigningKey(AuthorizationServerConfig.SIGN);
		return converter;

	}
	
	@Bean
	JwtTokenEnhancer jwtTokenEnhancer() {
		
		return new JwtTokenEnhancer();
	}
}
