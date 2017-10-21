package com.appollo.employee;

/**
 * 用户信息业务逻辑操作接口
 * @author 谭洋
 *
 */
public interface IUserinfoService {
	
	/**
	 *根据用户名获取错误次数 抽象方法
	 * @author 谭洋 
	 * @param username
	 * @return int
	 */
	int getTimerByUserName(String username);
	
	/**
	 * 根据用户名获取密码  抽象方法
	 * @author 谭洋
	 * @param username
	 * @return String
	 */
	String getPasswordByUserName(String username);
	
	/**
	 * 根据用户名使错误次数归0   抽象方法
	 * @author 谭洋
	 * @param username
	 * @return void
	 */
	void resetTimerByUserName(String username);
	
	/**
	 * 根据用户名使错误次数+1   抽象方法
	 * @author 谭洋
	 * @param username
	 * @return void
	 */
	void addTimerByUserName(String username);
	
	/**
	 * 根据用户名更新密码    抽象方法
	 * @author 谭洋
	 * @param username
	 * @param oldpassword
	 * @param newpassword
	 * @param repeatpassword
	 * @return boolean
	 */
	boolean updatePasswordByUserName(String username,String oldpassword,String newpassword,String repeatpassword);
	
	/**
	 * 根据用户名获取用户信息对象   抽象方法
	 * @author 谭洋
	 * @param username
	 * @return void
	 */
	void getByUserName(String username);
	
	/**
	 * 插入用户信息对象   抽象方法
	 * @author 谭洋
	 * @return boolean
	 */
	boolean insert();
	
}
