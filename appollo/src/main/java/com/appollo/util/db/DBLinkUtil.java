package com.appollo.util.db;

import java.io.IOException;
import java.io.InputStream;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

/**
 * 数据库连接工具类
 * @author 谭洋
 *
 */
public class DBLinkUtil {
	
	private static Properties properties =new Properties();
	private static String DBusername;
	private static String DBpassword;
	private static String DBurl;	
	private static InputStream inStream=DBLinkUtil.class.getClassLoader().getResourceAsStream("config/db.properties");
	
	static {
		try {
			properties.load(inStream);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		DBusername=properties.getProperty("db.username");
		DBpassword=properties.getProperty("db.password");
		DBurl=properties.getProperty("db.url");
//		System.out.println(DBusername+" "+DBpassword+" "+DBurl);
		try {
			//1、加载JDBC驱动程序
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	} 
	
	/**
	 * 获取链接 
	 * @author 谭洋
	 * @return Connection
	 * @throws SQLException
	 */
	public static Connection getConnection() throws SQLException {
		
		//2、获取数据库连接
		Connection connection = DriverManager.getConnection(DBurl,DBusername,DBpassword);
		return connection;
	}
	
	/**
	 * 更新数据
	 * @author 谭洋
	 * @param sql
	 * @param obj
	 * @return boolean
	 */
	public static boolean update(String sql,Object ...obj) {
		
		Connection connection = null;
		PreparedStatement preparedStatement=null;
		try {
			connection = getConnection();
			//3、创建Statement实例
			preparedStatement = connection.prepareStatement(sql);
			//为?赋值
			for(int i=0;i<obj.length;i++) {
				preparedStatement.setObject(i+1, obj[i]);
			}		
			//4、执行SQL语句
			int result = preparedStatement.executeUpdate();
			//5、处理结果
			return result>0;
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			//6、关闭JDBC对象，释放资源
			close(preparedStatement,connection);
		}
		return false;
	}
	
	
	/**
	 * 更新数据
	 * @author 谭洋
	 * @param sql
	 * @return boolean
	 */
	public boolean update(String sql) {
		
		Connection connection = null;
		Statement statement = null;
		try {
			
			connection =getConnection();
			//3、创建Statement实例
			statement = connection.createStatement();
			//4、执行SQL语句
			int result = statement.executeUpdate(sql);
			//5、处理结果
			return result >0;
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			//6、关闭JDBC对象，释放资源
			close(statement,connection);
		}
		return false;
	}
	
	/**
	 * 事务处理
	 * @author  谭洋
	 * @param   sql
	 * @return	boolean
	 */
	public static boolean transaction(String ...sql) {
		
		Connection connection = null;
		Statement statement = null;
		try {
			//2、获取数据库连接
			
			connection = getConnection();
			connection.setAutoCommit(false);//关闭默认自动事务提交
			//3、创建Statement实例
			statement = connection.createStatement();
			//4、执行SQL语句
			for(int i=0;i<sql.length;i++) {
				statement.executeQuery(sql[i]);
			}
			connection.commit();//提交事务
			return true;
		} catch (SQLException e) {
			try {
				connection.rollback();//撤销DML操作
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		}finally {
			//6、关闭JDBC对象，释放资源
			close(statement, connection);
		}
		return false;
	}
	
	/**
	 * 事务处理
	 * @author 谭洋
	 * @param sql
	 * @param obj
	 * @return boolean
	 */
	public static boolean transaction(String[] sql,Object[]...obj) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		try {
			//2、获取数据库连接
			connection = getConnection();
			connection.setAutoCommit(false);//关闭默认自动事务提交
			//3、创建Statement实例
			//4、执行SQL语句
			for(int i=0;i<obj.length;i++) {
				try {
					preparedStatement=connection.prepareStatement(sql[i]);
					for(int j=0;j<obj[i].length;j++) {
						preparedStatement.setObject(j+1, obj[i][j]);
					}
				} finally {
					if(preparedStatement!=null)
						preparedStatement.close();
				}
			}
			connection.commit();//提交事务
			return true;
		} catch (SQLException e) {
			try {
				connection.rollback();//撤销DML操作
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		}finally {
			//6、关闭JDBC对象，释放资源
			close(preparedStatement, connection);
		}
		return false;
	}
	
	/**
	 * 选择查询
	 * @author 谭洋
	 * @param sql
	 * @param rowMapper
	 * @return void
	 */
	public static void select(String sql,IRowMapper rowMapper) {
		
		Connection connection = null;
		Statement statement = null;
		ResultSet resultSet = null;
		try {
			//2、获取数据库连接
			connection = getConnection();
			//3、创建Statement实例
			statement = connection.createStatement();
			//4、执行SQL语句
			resultSet = statement.executeQuery(sql);
			//5、处理结果
			//next用于移动指针并判断当前指针所指位置是否有数据
			rowMapper.rowgenerate(resultSet);
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			//6、关闭JDBC对象，释放资源
			close(resultSet,statement,connection);
		}
	}
	
	/**
	 * 选择查询
	 * @author 谭洋
	 * @param sql
	 * @param rowMapper
	 * @param obj
	 * @return void
	 */
	public static void select(String sql,IRowMapper rowMapper,Object ...obj) {
		
		Connection connection = null;
		PreparedStatement preparedStatement=null;
		ResultSet resultSet = null;
		try {
			
			connection = getConnection();
			//3、创建Statement实例
			preparedStatement = connection.prepareStatement(sql);
			//为?赋值
			for(int i=0;i<obj.length;i++) {
				preparedStatement.setObject(i+1, obj[i]);
			}
			//4、执行SQL语句
			resultSet = preparedStatement.executeQuery();
			//5、处理结果
			rowMapper.rowgenerate(resultSet);
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			//6、关闭JDBC对象，释放资源
			close(resultSet, preparedStatement, connection);
		}
	}
	
	
	/**
	 * 调用存储过程
	 * @author 谭洋
	 * @param procedure
	 * @param paramArray
	 * @param jdbcTypeArray
	 * @param selectMapper
	 */
	public static void procedure(String procedure,Object [] paramArray,int [] jdbcTypeArray, ISelectMapper selectMapper) {
		
		
		Connection connection = null;
		CallableStatement callableStatement = null;
		try {
			connection =getConnection();
			callableStatement = connection.prepareCall("{call "+procedure+"}");
			//为问号占位符赋值——传入的值赋值
			int i = 0;
			for (; i < paramArray.length; i++) {
				callableStatement.setObject(i+1, paramArray[i]);
			}
			
			for (int j = 0; j < jdbcTypeArray.length; j++) {
				callableStatement.registerOutParameter(i+1+j, jdbcTypeArray[j]);//指定输出数据类型
			}
			callableStatement.execute();
			selectMapper.selectMapper(callableStatement);
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(callableStatement, connection);
		}
	}

	/**
	 * 关闭连接
	 * @author 谭洋
	 * @param conn
	 * @return void 
	 */
	public static void close(Connection conn) {
		try {
			if(conn!=null) {
				conn.close();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * 关闭连接
	 * @author 谭洋
	 * @param statement
	 * @param connection
	 * @return void
	 */
	public static void close(Statement statement,Connection connection) {
		try {
			if(statement!=null) {
				statement.close();//释放Statement类型对象
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			if(connection!=null) {
				connection.close();//释放Connection类型对象
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	/**
	 * 关闭连接
	 * @author 谭洋
	 * @param resultset
	 * @param statement
	 * @param connection
	 * @return void
	 */
	public static void close(ResultSet resultset,Statement statement,Connection connection) {
		try {
			if(resultset!=null)
				resultset.close();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		close(statement, connection);
	} 
	
	
	
}
