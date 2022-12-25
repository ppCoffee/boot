/**
 * DemoInfo.java
 * Copyright(JAVA) EnRich DTV Group co.,Ltd
 * 功能描述：
 *   
 * 创建者：zhangzy@evmtv.com 
 * 编辑者: zhangzy@evmtv.com
 * 2022年12月17日
 */



package com.evmtv.boot.actuator.info;

import org.springframework.boot.actuate.info.Info.Builder;

import java.util.HashMap;
import java.util.Map;

import org.springframework.boot.actuate.info.InfoContributor;
import org.springframework.stereotype.Component;

@Component
public class DemoInfo implements InfoContributor{

	@Override
	public void contribute(Builder builder) {
		
		Map<String,Object> map = new HashMap<String,Object>();
		
		map.put("project_name", "demo");
		map.put("version", "2.0.1");
		
		builder.withDetails(map);
	}

}
