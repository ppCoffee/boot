package com.evmtv.boot;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;

import com.evmtv.boot.entity.User;
import com.evmtv.boot.service.UserService;
import com.evmtv.boot.util.JwtHelper;



@SpringBootTest
class ProjectApplicationTests {
	
	@Resource
	JdbcTemplate jdbcTemplate;
	
	@Resource
	UserService userService;
	
	@Disabled
	@Test
	void contextLoads() {
		
		DataSource ds = jdbcTemplate.getDataSource();
		System.out.println("==>" + ds.getClass());
	}
	
	@Disabled
	//@Transactional
	@Test
	void insert() {
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		
		User user = new User("333",sdf.format(new Date()));
		
		boolean result = userService.save(user);
		
		System.out.println(result);
	}
	
	@Test
	void jwtTest() throws Exception {
		
		Map<String, Object> map = new HashMap<String,Object>();
		map.put("username", "张三");
		String token = JwtHelper.createToken(map);
		System.out.println(token);
		System.out.println("=================================================");
		Map<String, Object> msg = JwtHelper.getMessage(token, "username");
		System.out.println(msg.get("username"));
		System.out.println(msg.get("jti"));
		System.out.println(msg.get("iat"));
		System.out.println(msg.get("sub"));
		System.out.println(msg.get("exp"));
		String[] arr = token.split("\\.");
		Base64.Decoder decoder = Base64.getDecoder();
		byte[] b1 = decoder.decode(arr[0]);
		byte[] b2 = decoder.decode(arr[1].replace("\r\n", "").replaceAll("_", ""));
//		byte[] b3 = decoder.decode(arr[2].replace("\r\n", "").replaceAll("_", "").replaceAll("-", ""));
		System.out.println(new String(b1, "UTF-8"));
		System.out.println(new String(b2, "UTF-8"));
//		System.out.println(new String(b3, "UTF-8"));
		
	}
	
	@Disabled
	@Test
	void SortTest() {
		
		List<User> list = new ArrayList<User>();
		for(int i = 0; i < 2; i++) {
			User user = new User();
			user.setAge(i);
			user.setSaveDate(new Date().toString());
			user.setUserId(UUID.randomUUID().toString());
			System.out.println(user.toString());
			list.add(user);
		}
		
		list.sort(Comparator.comparing(User::getUserId));
		
		list.forEach(item -> {
			System.out.println(item.toString());
		});
	}
}
