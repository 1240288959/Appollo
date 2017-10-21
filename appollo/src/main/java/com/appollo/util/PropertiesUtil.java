package com.appollo.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * 访问属性文件工具类
 * @author 谭洋
 *
 */
public class PropertiesUtil {
	
	/**
	 * 获得属性
	 * @author 谭洋
	 * @param Key
	 * @return String 
	 */
	public static String get(String Key) {
		InputStream inStream=PropertiesUtil.class.getClassLoader().getResourceAsStream("config/db.properties");
		Properties properties =new Properties();
		try {
			properties.load(inStream);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return properties.getProperty(Key);
	}
}
