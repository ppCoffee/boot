package com.evmtv.boot;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.annotation.Transactional;

import com.evmtv.boot.entity.User;
import com.evmtv.boot.service.UserService;


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
	
	//@Transactional
	@Test
	void insert() {
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		
		User user = new User("333",sdf.format(new Date()));
		
		boolean result = userService.save(user);
		
		System.out.println(result);
	}
	
}
