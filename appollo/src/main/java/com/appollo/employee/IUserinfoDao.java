package com.appollo.employee;


import com.appollo.domain.Userinfo;
/**
 * 用户模块数据操作接口
 * @author 谭洋
 *
 */
public interface IUserinfoDao {
	
	/**
	 * 根据用户名获取错误次数   抽象方法
	 * @author 谭洋
	 * @param username
	 * @return int
	 */
	int getTimerByUserName(String username);
	
	/**
	 * 根据用户名获取相应的密码  抽象方法
	 * @author 谭洋
	 * @param username
	 * @return String
	 */
	String getPasswordByUserName(String username);
	
	/**
	 * 根据用户名调用procedure使错误次数归0  抽象方法
	 * @author 谭洋
	 * @param username
	 * @return void
	 */
	void  resetTimerByUserName(String username);
	
	/**
	 * 根据用户名调用procedure使错误次数加一  抽象方法
	 * @author 谭洋
	 * @param username
	 * @return void
	 */
	void addTimerByUserName(String username);
	
	/**
	 * 根据用户名更新密码  抽象方法
	 * @author 谭洋
	 * @param username
	 * @param password
	 * @return boolean
	 */
	boolean updatePasswordByUserName(String username,String password);
	
	/**
	 * 根据用户名获取手机号  抽象方法
	 * @author 谭洋
	 * @param username
	 * @return String
	 */
	String getMobileByUserName(String username);
	
	/**
	 * 根据用户名获取用户信息 抽象方法
	 * @author 谭洋
	 * @param username
	 * @return Userinfo
	 */
	Userinfo getByUserName(String username);
	
	/**
	 * 插入用户信息   抽象方法
	 * @author 谭洋
	 * @param Userinfo
	 * @return boolean
	 */
	boolean insert(Userinfo Userinfo);
}
