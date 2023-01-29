/**
 * JwtTokenEnhancer.java
 * Copyright(JAVA) EnRich DTV Group co.,Ltd
 * 功能描述：
 *   
 * 创建者：zhangzy@evmtv.com 
 * 编辑者: zhangzy@evmtv.com
 * 2023年1月29日
 */

package com.evmtv.demo.config;

import java.util.HashMap;
import java.util.Map;

import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;

@SuppressWarnings("deprecation")
public class JwtTokenEnhancer implements TokenEnhancer{

	@Override
	public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {
		
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("myEnhance", "my info");
		((DefaultOAuth2AccessToken)accessToken).setAdditionalInformation(map);
//		((DefaultOAuth2AccessToken)accessToken).setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 30));
		
		return accessToken;
	}

}
