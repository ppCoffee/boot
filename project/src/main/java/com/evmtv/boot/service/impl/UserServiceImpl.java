/**
 * UserServiceImpl.java
 * Copyright(JAVA) EnRich DTV Group co.,Ltd
 * 功能描述：
 *   
 * 创建者：zhangzy@evmtv.com 
 * 编辑者: zhangzy@evmtv.com
 * 2022年12月12日
 */



package com.evmtv.boot.service.impl;




import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.evmtv.boot.entity.User;
import com.evmtv.boot.mapper.UserMapper;
import com.evmtv.boot.service.UserService;



@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService{
	

}
