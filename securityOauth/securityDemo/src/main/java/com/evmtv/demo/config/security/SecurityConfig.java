/**
 * SecurityConfig.java
 * Copyright(JAVA) EnRich DTV Group co.,Ltd
 * 功能描述：
 *   
 * 创建者：zhangzy@evmtv.com 
 * 编辑者: zhangzy@evmtv.com
 * 2023年1月2日
 */

package com.evmtv.demo.config.security;





import javax.annotation.Resource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;


@SuppressWarnings("deprecation")
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	
	@Resource
	UserAuth userAuth;
	
	@Bean
	PasswordEncoder getPasswordEncoder() {
		
		return new BCryptPasswordEncoder();
	}
	
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		//表单提交
		http.formLogin()
				//自定义入参
				.usernameParameter("username")
				.passwordParameter("password")
				//指定登录页
				.loginPage("/login.html")
				//html表单提交的接口地址
				.loginProcessingUrl("/login")
				//登录成功后跳转地址
//				.successForwardUrl("/toMain")
				//自定义成功跳转
				.successHandler(userAuth)
				//失败跳转地址
//				.failureForwardUrl("/toError");
				.failureHandler(userAuth);
		
		
		//认证
		http.authorizeRequests()
				//放行页面
				.antMatchers("/error.html").permitAll()
				.antMatchers("/login.html").permitAll()
				//放行静态路径
				.antMatchers("/css/**","/js/**","/images/**").permitAll()
//				.mvcMatchers("/xxx").servletPath("/xxx").permitAll() 基于mvc放行某段path下的地址路径
//				.antMatchers("/**/*.png")
//				.antMatchers("/main2.html").hasAnyAuthority("admin","admiN") //基于权限，严格区分大小写
//				.antMatchers("/main2.html").hasAnyRole("gm") //基于角色，严格区分大小写
//				.antMatchers("/main2.html").hasIpAddress("127.0.0.1") //基于访问者ip
				//指定所有页面都需要认证才能访问
				.anyRequest().authenticated();
		
		//TODO remember-me
		
		http.logout().logoutSuccessUrl("/login.html");
		
		//认证失败调用
		http.exceptionHandling().accessDeniedHandler(userAuth);
		
		
		
		//类似防火墙 开启情况下需要页面上表单传入_csrf的value
	    http.csrf().disable();
	}

}
