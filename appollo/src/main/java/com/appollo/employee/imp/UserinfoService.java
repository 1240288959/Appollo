package com.appollo.employee.imp;

import java.io.IOException;

import java.util.Objects;
import java.util.Scanner;
import java.util.UUID;

import com.appollo.domain.Userinfo;
import com.appollo.employee.IUserinfoDao;
import com.appollo.employee.IUserinfoService;
import com.appollo.util.PinYinTool;
import com.appollo.util.ghj.SMSUtils;
/**
 * 用户模块的业务逻辑层实现类
 * @author 谭洋
 *
 */
public class UserinfoService implements IUserinfoService {
	
	private IUserinfoDao Userinfodao=new UserinfoDao();
	
	/**
	 * 根据用户名获取错误次数  实现方法
	 * @author 谭洋
	 * @param username
	 * @return int
	 */
	@Override
	public int getTimerByUserName(String username) {
		// TODO Auto-generated method stub
		return Userinfodao.getTimerByUserName(username);
	}

	/**
	 * 根据用户名获取密码 实现方法
	 * @author 谭洋
	 * @param username
	 * @return String
	 */
	@Override
	public String getPasswordByUserName(String username) {
		return Userinfodao.getPasswordByUserName(username);
	}
	
	/**
	 * 根据用户名调用procedure使错误次数归0 实现方法
	 * @author 谭洋
	 * @param username
	 * @return void
	 */
	@Override
	public void resetTimerByUserName(String username) {
		Userinfodao.resetTimerByUserName(username);
	}
	
	/**
	 * 根据用户名使错误次数+1  实现方法  
	 * @author 谭洋 
	 * @param username
	 * @return void
	 */
	@Override
	public void addTimerByUserName(String username) {
		Userinfodao.addTimerByUserName(username);
	}
	
	/**
	 * 根据用户名更新密码 实现方法
	 * @author 谭洋
	 * @param  username
	 * @param  oldpassword
	 * @param  newpassword
	 * @param  repeatpassword 
	 * @return boolean
	 */
	@SuppressWarnings("resource")
	@Override
	public boolean updatePasswordByUserName(String username, String oldpassword, String newpassword,
			String repeatpassword) {
		// TODO Auto-generated method stub
		String password=Userinfodao.getPasswordByUserName(username);
		String mobilephone=Userinfodao.getMobileByUserName(username);
		Scanner scanner= new Scanner(System.in);
		if(!SMSUtils.isMobile(mobilephone)) {
			System.out.println("没有用的验证手机号");
			return false;
		}else {
			try {
				SMSUtils.sendCode(mobilephone,"3096030" );
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		System.out.println("请输入验证码");
		String code=scanner.next();
		scanner.close();
		try {
			if(SMSUtils.verifyCode(mobilephone, code)==false) {
				System.out.println("验证码错误");
				return false;
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(Objects.equals(password, oldpassword)) {
			if(Objects.equals(newpassword, repeatpassword)) {
				boolean flag=Userinfodao.updatePasswordByUserName(username, newpassword);
				if(flag==true) System.out.println("更改成功"); else System.out.println("更改失败，请重试....");
				return flag;
			}
			System.out.println("两次输入密码不同");
			return false;
		}else {
			System.out.println("密码输入错误");
			return false;
		}
		
	}

	/**
	 * 根据用户名获取用户信息对象 实现方法
	 * @author 谭洋
	 * @param username
	 * @return void
	 */
	@Override
	public void getByUserName(String username) {
		Userinfo Userinfo=Userinfodao.getByUserName(username);
		System.out.println("ID\t\t\t\t\t用户名\t密码\t\t真实姓名\t信用卡\t\t\t手机\t\t地址\t错误次数\t年龄\t性别\t生日");
		System.out.println(Userinfo.getId()+"\t"+Userinfo.getUser_name()+"\t"+Userinfo.getPassword()+"\t"
							+Userinfo.getReal_name()+"\t"+Userinfo.getCertification_id()+"\t"
							+Userinfo.getMobile()+"\t"+Userinfo.getAddress()+"\t"+Userinfo.getTimer()
							+"\t"+Userinfo.getAge()+"\t"+Userinfo.getGender()+"\t"+Userinfo.getBirth());
	}
	
	/**
	 * 插入用户信息对象   实现方法
	 * @author 谭洋
	 * @return boolean
	 */
	@Override
	public boolean insert() {
		Scanner scanner =new Scanner(System.in);
		String id=UUID.randomUUID().toString();
		/*System.out.println("请输入用户名");
		String user_name=scanner.next();*/
		System.out.println("请输入真实姓名");
		String real_name=scanner.next();
		System.out.println("请输入身份证号");
		String certification_id=scanner.next();
		System.out.println("请输入手机号");
		String mobile=scanner.next();
		System.out.println("请输入地址");
		String address=scanner.next();
		
		String password="123456";
		String user_name=PinYinTool.getDefalutUserName(real_name);
		int timer=0;
		Userinfo Userinfo = new Userinfo(id, user_name, password, real_name, certification_id, mobile, address, timer);
		boolean flag= Userinfodao.insert(Userinfo);
		if(flag==true) {
			System.out.println("添加成功");
		}else {
			System.out.println("添加失败");
		}
		scanner.close();
		return flag;
	}
	



	
}
