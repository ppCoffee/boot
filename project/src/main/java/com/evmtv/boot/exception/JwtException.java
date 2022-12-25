/**
 * JwtException.java
 * Copyright(JAVA) EnRich DTV Group co.,Ltd
 * 功能描述：
 *   
 * 创建者：zhangzy@evmtv.com 
 * 编辑者: zhangzy@evmtv.com
 * 2022年12月19日
 */



package com.evmtv.boot.exception;

/**
 * @author Administrator
 *
 */
public class JwtException  extends RuntimeException{

	private static final long serialVersionUID = 1L;
	
	public static final String TOKEN_TIMEOUT = "6001";
    public static final String TOKEN_TIMEOUT_REASON = "token time out";
	
    public JwtException(String message) {
    	
    	super(message);
    }
}
