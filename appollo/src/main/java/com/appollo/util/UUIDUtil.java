package com.appollo.util;

import java.util.UUID;

/**
 * 获取uuid工具类
 * @author 谭洋
 *
 */
public class UUIDUtil {
	
	/**
	 * 获取uuid方法
	 * @author 谭洋
	 * @return
	 */
	public static String getUUID() {
		return UUID.randomUUID().toString();
	}
}
