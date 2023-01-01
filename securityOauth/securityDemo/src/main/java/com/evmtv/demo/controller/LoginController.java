/**
 * LoginController.java
 * Copyright(JAVA) EnRich DTV Group co.,Ltd
 * 功能描述：
 *   
 * 创建者：zhangzy@evmtv.com 
 * 编辑者: zhangzy@evmtv.com
 * 2023年1月2日
 */

package com.evmtv.demo.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class LoginController {
	
	
	@PostMapping("/login")
	public void login(HttpServletResponse resp,String username,String password) {
		
		System.err.println(username + " - " + password);
		
		try {
			resp.sendRedirect("/main.html");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
