/**
 * UserMapper.java
 * Copyright(JAVA) EnRich DTV Group co.,Ltd
 * 功能描述：
 *   
 * 创建者：zhangzy@evmtv.com 
 * 编辑者: zhangzy@evmtv.com
 * 2022年12月12日
 */



package com.evmtv.boot.mapper;


import org.apache.ibatis.annotations.Mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.evmtv.boot.entity.User;

/**
 * @author Administrator
 *
 */
@Mapper
public interface UserMapper extends BaseMapper<User>{
	
}
