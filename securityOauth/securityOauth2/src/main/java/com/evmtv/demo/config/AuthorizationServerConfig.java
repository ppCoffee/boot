/**
 * AuthorizationServerConfig.java
 * Copyright(JAVA) EnRich DTV Group co.,Ltd
 * 功能描述：
 *   
 * 创建者：zhangzy@evmtv.com 
 * 编辑者: zhangzy@evmtv.com
 * 2023年1月28日
 */

package com.evmtv.demo.config;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;

import com.evmtv.demo.service.UserService;

/**
 * 授权服务器上的配置 
 */
@SuppressWarnings("deprecation")
@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter{
	
	@Resource
	PasswordEncoder passwordEncoder;
	
	@Resource
	AuthenticationManager authenticationManager;
	
	@Resource
//	@Qualifier("redisTokenStore")
	@Qualifier("jwtTokenStore")
	private TokenStore tokenStore;
	
	@Resource
	private JwtAccessTokenConverter jwtAccessTokenConverter;
	
	@Resource
	UserService userService;
	
	
	/**
	 * 密码模式
	 * 固定访问地址 http://127.0.0.1:8080/oauth/token 
	 * post请求  Authorization username=客户端id password=密钥 
	 * grant_type=password username=用户用户名 password=用户密码 scope=all
	 * 返回值
	 * 		{
	 *	    "access_token": "13u2btTIX73O1SZ37VkUJraqDvE",
	 *	    "token_type": "bearer",
	 *	    "expires_in": 43199,
	 *	    "scope": "all"
	 *	}
	 */
	@Override
	public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
		
		endpoints.authenticationManager(authenticationManager)
					.userDetailsService(userService)
					.tokenStore(tokenStore)
					.accessTokenConverter(jwtAccessTokenConverter);
	}
	
	 


	/**
	 * 授权模式
	 * 固定访问地址 http://127.0.0.1:8080/oauth/authorize?response_type=code&client_id=clientId&redirect_uri=http://www.baidu.com&scope=all   获取code
	 * 获取令牌 http://127.0.0.1:8080/oauth/token  
	 * 			post请求  Authorization username=客户端id password=密钥  
	 * 			body中表单提交 grant_type=authorization_code client_id=客户端id redirect_uri=重定向地址 code=获取到的code scope=all
	 * 			返回值：
	 * 				{
	 *				    "access_token": "tmKWGzk9hvMdRgftx1iqQsRSqgs",
	 *				    "token_type": "bearer",
	 *				    "expires_in": 43199,
	 *				    "scope": "all"
	 *				}
	 * 请求资源服务器 http://127.0.0.1:8080/user/getUser  post请求  Authorization Bearer Token = tmKWGzk9hvMdRgftx1iqQsRSqgs
	 */
	@Override
	public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
		
		//demo原因，仅操作内存，实际根据业务情况定处理情况
		
		clients.inMemory()
			.withClient("clientId") //客户端id
			.secret(passwordEncoder.encode("112233")) //密钥
			.redirectUris("http://www.baidu.com") //重定向地址
			.scopes("all") //授权范围
			.authorizedGrantTypes("authorization_code","password"); //授权类型 authorization_code(授权模式) password(密码模式)
		
	}
	
	
}
