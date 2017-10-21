package com.appollo.util.db;

import java.sql.ResultSet;

/**
 * 遍历行     接口
 * @author 谭洋
 *
 */
public interface IRowMapper {
	
	/**
	 * 遍历方法    抽象方法
	 * @author 谭洋
	 * @param rs
	 * @return void
	 */
	void rowgenerate(ResultSet rs);
}
