/**
 * DemoHealth.java
 * Copyright(JAVA) EnRich DTV Group co.,Ltd
 * 功能描述：
 *   
 * 创建者：zhangzy@evmtv.com 
 * 编辑者: zhangzy@evmtv.com
 * 2022年12月17日
 */



package com.evmtv.boot.actuator.health;

import java.util.HashMap;
import java.util.Map;

import org.springframework.boot.actuate.health.AbstractHealthIndicator;
import org.springframework.boot.actuate.health.Health.Builder;
import org.springframework.boot.actuate.health.Status;
import org.springframework.stereotype.Component;

@Component
public class DemoHealth extends AbstractHealthIndicator{

	@Override
	protected void doHealthCheck(Builder builder) throws Exception {
		
		
		Map<String,Object> map = new HashMap<String,Object>();
		
		map.put("msg", "abc");
		map.put("code", "404");
		builder.status(Status.UP);
		builder.withDetails(map);
		
	}

}
