/**
 * DemoEndPoint.java
 * Copyright(JAVA) EnRich DTV Group co.,Ltd
 * 功能描述：
 *   
 * 创建者：zhangzy@evmtv.com 
 * 编辑者: zhangzy@evmtv.com
 * 2022年12月17日
 */



package com.evmtv.boot.actuator.endpoint;

import java.util.Collections;
import java.util.Map;

import org.springframework.boot.actuate.endpoint.annotation.Endpoint;
import org.springframework.boot.actuate.endpoint.annotation.ReadOperation;
import org.springframework.boot.actuate.endpoint.annotation.WriteOperation;
import org.springframework.stereotype.Component;

@Component
@Endpoint(id = "demoEndPoint")
public class DemoEndPoint {
	
	@ReadOperation
	public Map<String,Object> getDemoEndPointMessage() {
		
		return Collections.singletonMap("msg", "demo_value");
		
	}
	
	@WriteOperation
	public void writeDemoEndPointMessage() {
		System.out.println("do something");
	}
}
