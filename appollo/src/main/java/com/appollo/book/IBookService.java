package com.appollo.book;



/**
 * 书籍模块业务逻辑 接口
 * @author 谭洋
 *
 */
public interface IBookService {
	
	/**
	 * 根据名字和作者查找书籍  抽象方法
	 * @author 谭洋
	 * @param name
	 * @param author
	 * @return void
	 */
	void findByNameAndAuthor(String name,String author);
	
	/**
	 * 根据id删除 book 抽象方法
	 * @author 谭洋
	 * @param id
	 * @return boolean
	 */
	boolean deleteByID(String id);
	
	/**
	 * 根据id更新价格和库存 抽象方法
	 * @author 谭洋
	 * @return boolean
	 */
	boolean updatePriceAndTotalByID();
	
	/**
	 * 插入书籍  抽象方法
	 * @author 谭洋
	 * @return boolean
	 */
	boolean insert();
}
