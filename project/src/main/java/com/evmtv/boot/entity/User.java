/**
 * User.java
 * Copyright(JAVA) EnRich DTV Group co.,Ltd
 * 功能描述：
 *   
 * 创建者：zhangzy@evmtv.com 
 * 编辑者: zhangzy@evmtv.com
 * 2022年12月12日
 */



package com.evmtv.boot.entity;







import com.baomidou.mybatisplus.annotation.TableField;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class User {
	
	
	
	public User(String userId, String saveDate) {
		super();
		this.userId = userId;
		this.saveDate = saveDate;
	}
	
	
	@TableField(exist = false)
	private Integer age;
	
	private Long id;
	private String userId;
	private String saveDate;
	
}
