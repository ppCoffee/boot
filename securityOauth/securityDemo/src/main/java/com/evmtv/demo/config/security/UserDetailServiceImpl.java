/**
 * UserDetailServiceImpl.java
 * Copyright(JAVA) EnRich DTV Group co.,Ltd
 * 功能描述：
 *   
 * 创建者：zhangzy@evmtv.com 
 * 编辑者: zhangzy@evmtv.com
 * 2023年1月2日
 */

package com.evmtv.demo.config.security;


import javax.annotation.Resource;

import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import lombok.experimental.var;

@Service
public class UserDetailServiceImpl implements UserDetailsService{
	
	@Resource
	PasswordEncoder passwordEncoder;
	
	/*
	 * 根据用户id获取此id信息然后与前端默认login输入内容做匹配
	 */
	@Override
	public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {
		
		//此处省略数据库获取步骤 or 配置文件配置
		String myUserId = "admin";
		String myPwd = passwordEncoder.encode("123");
		
		if(!myUserId.equals(userId))
			throw new UsernameNotFoundException("user id not found");
		
		var list = AuthorityUtils.commaSeparatedStringToAuthorityList("admin,normal,ROLE_gm"); //省略数据库权限获取步骤,角色强制ROLE_写法
		
		return new User(userId, myPwd, list);
	}

}
