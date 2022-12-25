/**
 * UserSideException.java
 * Copyright(JAVA) EnRich DTV Group co.,Ltd
 * 功能描述：
 *   用户端异常处理
 * 创建者：shenhui@evmtv.com
 * 编辑者: zhangzy@evmtv.com
 */

package com.evmtv.boot.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@ToString
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class UserSideException extends RuntimeException {
	
    
    private static final long serialVersionUID = 1L;
    
	public static final String UMS_NO_SUCH_USERS = "2002";
    public static final String UMS_NO_SUCH_USERS_REASON = "用户无法匹配";
    
    private String code;
    private String message;
    
    
}
