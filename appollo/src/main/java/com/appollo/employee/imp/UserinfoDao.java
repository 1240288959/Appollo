package com.appollo.employee.imp;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.Date;

import com.appollo.domain.Userinfo;
import com.appollo.employee.IUserinfoDao;
import com.appollo.util.db.DBLinkUtil;
import com.appollo.util.db.IRowMapper;
import com.appollo.util.db.ISelectMapper;
/**
 * 用户模块数据操作实现类
 * @author 谭洋
 *
 */
public class UserinfoDao implements IUserinfoDao {
	
	/**
	 * 根据用户名获取错误次数  实现方法
	 * @author 谭洋
	 * @param username
	 * @return int
	 */
	@Override
	public int getTimerByUserName(String username) {
		class RowMapper implements IRowMapper{
			
			private int timer=0;
			
			@Override
			public void rowgenerate(ResultSet rs) {
				// TODO Auto-generated method stub
				try {
					if(rs.next()) {
						timer=rs.getInt("timer");
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
		}
		
		RowMapper rowMapper=new RowMapper();
		DBLinkUtil.select("select timer from userinfo where user_name = ?", rowMapper, username);
		return rowMapper.timer;
	}
	
	/**
	 * 根据用户名获取密码  实现方法
	 * @author 谭洋
	 * @param username
	 * @return String
	 */
	@Override
	public String getPasswordByUserName(String username) {
		class RowMapper implements IRowMapper{
			private String password;
			@Override
			public void rowgenerate(ResultSet rs) {
				// TODO Auto-generated method stub
				try {
					if(rs.next()) {
						password=rs.getString("password");
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
		}
		RowMapper rowMapper=new RowMapper();
		DBLinkUtil.select("select password from userinfo where user_name = ?", rowMapper,username);
		return rowMapper.password;
	}

	/**
	 * 根据用户名调用procedure使错误次数归0  实现方法
	 * @author 谭洋
	 * @param username
	 * @return void
	 */
	@Override
	public void resetTimerByUserName(String username) {
		// TODO Auto-generated method stub
		DBLinkUtil.procedure("return_timer_zero_ge(?)", new Object[] {username}, new int[] {}, new ISelectMapper() {
			
			@Override
			public void selectMapper(CallableStatement callableStatement) {
				// TODO Auto-generated method stub
				
			}
		});
	}

	/**
	 * 根据用户名调用procedure使错误次数加一  实现方法
	 * @author 谭洋
	 * @param username
	 * @return void
	 */
	@Override
	public void addTimerByUserName(String username) {
		// TODO Auto-generated method stub
		DBLinkUtil.procedure("timmer_add(?)", new Object[]{username}, new int[] {}, new ISelectMapper() {
			
			@Override
			public void selectMapper(CallableStatement callableStatement) {
				// TODO Auto-generated method stub
				
			}
		});
	}

	/**
	 * 根据用户名称更新密码     实现方法
	 * @author 谭洋
	 * @param username
	 * @param password
	 * @return boolean
	 */
	@Override
	public boolean updatePasswordByUserName(String username,String password) {
		String sql="update userinfo set password = ? where user_name = ?";
		return DBLinkUtil.update(sql, password,username);
	}
	
	/**
	 * 根据用户名获取手机号 实现方法
	 * @author 谭洋
	 * @param username
	 * @return String
	 */
	@Override
	public String getMobileByUserName(String username) {
		class RowMapper implements IRowMapper{

			private String mobile;
			
			@Override
			public void rowgenerate(ResultSet rs) {
				try {
					if(rs.next()) {
						mobile=rs.getString("mobile");
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
		}
		RowMapper rowMapper=new RowMapper();
		String sql="select mobile from userinfo where user_name = ?";
		DBLinkUtil.select(sql, rowMapper, username);
		return rowMapper.mobile;
	}

	/**
	 * 根据用户名获取用户信息对象  实现方法
	 * @author 谭洋
	 * @param username
	 * @return userinfo
	 */
	@Override
	public Userinfo getByUserName(String username) {
		class RowMapper implements IRowMapper{
			
			private String id;
			private String user_name;
			private String password;
			private String real_name;
			private String certification_id;
			private String mobile;
			private String address;
			private int timer;
			private String gender;
			private int age;
			private Date birth;
			private Userinfo userinfo;
			@Override
			public void rowgenerate(ResultSet rs) {
				// TODO Auto-generated method stub
				try {
					if(rs.next()) {
						id=rs.getString("id");
						user_name=rs.getString("user_name");
						password=rs.getString("password");
						real_name=rs.getString("real_name");
						certification_id=rs.getString("certification_id");
						mobile=rs.getString("mobile");
						address=rs.getString("address");
						timer=rs.getInt("timer");
						age=rs.getInt("age");
						gender=rs.getString("gender");
						birth=rs.getDate("birth");
						
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				userinfo=new Userinfo(id,user_name,password,real_name,certification_id,mobile,address,timer, gender,age,birth);
			}
			
		}
		String sql="select id,user_name,password,real_name,certification_id,mobile,address,timer,age(certification_id) age,checkgender(certification_id) gender,re_birth(certification_id) birth from userinfo where user_name = ?";
		RowMapper rowMapper=new RowMapper();
		DBLinkUtil.select(sql, rowMapper, username);
		return rowMapper.userinfo;
	}
	
	/**
	 * 插入用户信息对象	实现方法
	 * @author 谭洋
	 * @param  userinfo
	 * @return boolean
	 */
	@Override
	public boolean insert(Userinfo userinfo) {
		String sql="insert into userinfo(id,user_name,password,real_name,certification_id,mobile,address,timer) values(?,?,?,?,?,?,?,?)";
		String id=userinfo.getId();
		String user_name  =userinfo.getUser_name();
		String password=userinfo.getPassword();
		String real_name=userinfo.getReal_name();
		String certification_id=userinfo.getCertification_id();
		String mobile=userinfo.getMobile();
		String address=userinfo.getAddress();
		int timer=userinfo.getTimer();		
		return DBLinkUtil.update(sql,id,user_name,password,real_name,certification_id,mobile,address,timer);
	}

	
}
