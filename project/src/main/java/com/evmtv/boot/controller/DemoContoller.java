package com.evmtv.boot.controller;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.druid.stat.DruidStatManagerFacade;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.evmtv.boot.annotation.LogEvent;
import com.evmtv.boot.config.ConfigEntity;
import com.evmtv.boot.entity.User;
import com.evmtv.boot.exception.UserSideException;
import com.evmtv.boot.service.UserService;
import com.evmtv.boot.util.JwtHelper;
import com.evmtv.boot.util.ResultHelper;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@SuppressWarnings("rawtypes")
public class DemoContoller {
	
	@Resource
	ConfigEntity config;
	
	@Resource
	UserService userService;
	
	
    @GetMapping("/druid/stat")
    public Object druidStat(){
        // DruidStatManagerFacade#getDataSourceStatDataList 该方法可以获取所有数据源的监控数据，除此之外 DruidStatManagerFacade 还提供了一些其他方法，你可以按需选择使用。
        return DruidStatManagerFacade.getInstance().getDataSourceStatDataList();
    }
	
	@GetMapping("/test")
	public String test() {
		
		//return "redirect:index.html";
		//return "forward:index.html";
		//return "index.html";
		return "test.html";
	}
	
	@GetMapping("/login")
	public String login(HttpSession session) {
		
		session.setAttribute("sessionId", session.getId());
		
		//return "redirect:index.html";
		//return "forward:index.html";
		//return "index.html";
		return "index.html";
	}
	
	@LogEvent(value = "test annotation")
	@ResponseBody
	@GetMapping("/demo")
	public ResultHelper demo(Map<String,Object> map) {
		
		throw new UserSideException(UserSideException.UMS_NO_SUCH_USERS, UserSideException.UMS_NO_SUCH_USERS_REASON);
		
		//return ResultHelper.result(ResultHelper.SUCCESS, null, ResultHelper.SUCCESS_MESSAGE);
		
	}
	
	@ResponseBody
	@GetMapping("/demo2")
	public ResultHelper demo2() {
		
		String name = config.getName();
		log.info("demo2 => " + name);
		
		return ResultHelper.result(ResultHelper.SUCCESS, name, ResultHelper.SUCCESS_MESSAGE);
	}
	
		
	@ResponseBody
	@GetMapping("/getToken")
	public String getToken() throws Exception {
		
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("userId", "123456");
		map.put("role", "up");
		String token = JwtHelper.createToken(map);
		
		return token;
	}
	
	@ResponseBody
	@GetMapping("/getMessageByToken")
	public Map<String, Object> getMessageByToken(String token) throws Exception {
		
		Map<String, Object> map = JwtHelper.getMessage(token, "userId","role");
		
		return map;
	}
	
	
	@ResponseBody
	@GetMapping("/user/{pageIndex}")
	public ResultHelper user(@PathVariable int pageIndex,Integer pageSize) throws Exception {
		
		Page<User> page = new Page<User>(pageIndex,pageSize);
//		QueryWrapper q = new QueryWrapper();
		Page<User> result = userService.page(page);
		
		return ResultHelper.result(ResultHelper.SUCCESS, "success", result);
		
	}
	
}
