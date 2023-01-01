/**
 * Test.java
 * Copyright(JAVA) EnRich DTV Group co.,Ltd
 * 功能描述：
 *   
 * 创建者：zhangzy@evmtv.com 
 * 编辑者: zhangzy@evmtv.com
 * 2023年1月2日
 */

package com.evmtv.demo;


import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootTest
public class Test {
	
	@org.junit.jupiter.api.Test
	public void encodePwTest() {
		
		PasswordEncoder pw = new BCryptPasswordEncoder();
		String rawPWD = "abc123";
		String encode = pw.encode(rawPWD);
		System.out.println(encode);
		
		System.out.println("=========================================");
		
		
		String inputPWD = "abc123";
		boolean match = pw.matches(inputPWD, encode);
		System.out.println(match);
		
	}
}
