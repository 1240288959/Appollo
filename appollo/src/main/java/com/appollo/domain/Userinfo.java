package com.appollo.domain;

import java.util.Date;
/**
 * 用户表的实体类
 * @author 谭洋
 *
 */
public class Userinfo {
	private String id;
	private String user_name;
	private String password;
	private String real_name;
	private String certification_id;
	private String mobile;
	private String address;
	private int    timer;
	private String gender;
	private int age;
	private Date birth;
	
	
	
	public Userinfo() {
		super();
	}
	
	
	
	public Userinfo(String id, String user_name, String password, String real_name, String certification_id,
			String mobile, String address, int timer) {
		super();
		this.id = id;
		this.user_name = user_name;
		this.password = password;
		this.real_name = real_name;
		this.certification_id = certification_id;
		this.mobile = mobile;
		this.address = address;
		this.timer = timer;
	}



	public Userinfo(String id, String user_name, String password, String real_name, String certification_id, String mobile,
			String address, int timer, String gender, int age, Date birth) {
		super();
		this.id = id;
		this.user_name = user_name;
		this.password = password;
		this.real_name = real_name;
		this.certification_id = certification_id;
		this.mobile = mobile;
		this.address = address;
		this.timer = timer;
		this.gender = gender;
		this.age = age;
		this.birth = birth;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getReal_name() {
		return real_name;
	}
	public void setReal_name(String real_name) {
		this.real_name = real_name;
	}
	public String getCertification_id() {
		return certification_id;
	}
	public void setCertification_id(String certification_id) {
		this.certification_id = certification_id;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public int getTimer() {
		return timer;
	}
	public void setTimer(int timer) {
		this.timer = timer;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public Date getBirth() {
		return birth;
	}
	public void setBirth(Date birth) {
		this.birth = birth;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", user_name=" + user_name + ", password=" + password + ", real_name=" + real_name
				+ ", certification_id=" + certification_id + ", mobile=" + mobile + ", address=" + address + ", timer="
				+ timer + ", gender=" + gender + ", age=" + age + ", birth=" + birth + "]";
	}
	
	
}
