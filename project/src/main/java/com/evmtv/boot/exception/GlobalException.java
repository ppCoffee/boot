/**
 * GlobalException.java
 * Copyright(JAVA) EnRich DTV Group co.,Ltd
 * 功能描述：
 *   
 * 创建者：zhangzy@evmtv.com 
 * 编辑者: zhangzy@evmtv.com
 * 2022年12月14日
 */



package com.evmtv.boot.exception;


import java.sql.SQLException;

import org.springframework.core.NestedRuntimeException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.evmtv.boot.annotation.LogEvent;
import com.evmtv.boot.util.ResultHelper;

import io.jsonwebtoken.ExpiredJwtException;
import lombok.extern.slf4j.Slf4j;


@Slf4j
@ControllerAdvice
@ResponseBody
@SuppressWarnings("rawtypes")
public class GlobalException {
	
	@ExceptionHandler({RuntimeException.class})
	public ResultHelper RuntimeException(Exception e) {
		
		
		String message = e.getMessage();
		String errorCode = ResultHelper.FAILED;
		
		if(message == null) {
			
			message = ResultHelper.FAILED_MESSAGE;
			
		} 
		
		log.error("error",e);
		
		return ResultHelper.result(errorCode, message);
	}
	
	@LogEvent(value = "err!!")
	@ExceptionHandler({UserSideException.class})
	public ResultHelper UserSideException(UserSideException e) {
		
		
		log.error("error",e);
		
		return ResultHelper.result(e.getCode(), e.getMessage());
	}
	
	
	@ExceptionHandler({SQLException.class,NestedRuntimeException.class})
	public ResultHelper sqlException(Exception e) {
		
		
		String message = e.getMessage();
		String errorCode = ResultHelper.SQL_FAILED;
		
		log.error("error",e);
		
		return ResultHelper.result(errorCode, message);
	}
	
	@ExceptionHandler({ExpiredJwtException.class})
	public ResultHelper jwtException(Exception e) {
		
		
		String message = JwtException.TOKEN_TIMEOUT_REASON;
		String errorCode = JwtException.TOKEN_TIMEOUT;
		
		log.error("error",e);
		
		return ResultHelper.result(errorCode, message);
	}
	
	
}
