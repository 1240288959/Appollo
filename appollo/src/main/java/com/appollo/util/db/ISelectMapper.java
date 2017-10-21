package com.appollo.util.db;

import java.sql.CallableStatement;

/**
 * 选择映射   接口
 * @author 谭洋
 *
 */
public interface ISelectMapper {
	
	/**
	 * 选择映射方法    抽象方法
	 * @author 谭洋
	 * @param callableStatement
	 * @return void
	 */
	void selectMapper(CallableStatement callableStatement);
}
