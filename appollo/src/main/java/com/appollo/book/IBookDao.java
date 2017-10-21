package com.appollo.book;

import java.util.List;

import com.appollo.domain.Book;


/**
 * 书籍模块数据实现层接口
 * @author 谭洋
 *
 */
public interface IBookDao {
	
	/**
	 * 根据书名和作者返回书籍  抽象方法
	 * @author 谭洋
	 * @param name
	 * @param author
	 * @return
	 */
	List<Book> findByNameAndAuthor(String name,String author);
	
	/**
	 * 根据id删除书籍   抽象方法
	 * @author 谭洋
	 * @param id
	 * @return boolean
	 */
	boolean deleteByID(String id);
	
	/**
	 * 根据id更新价格和库存  抽象方法
	 * @author 谭洋
	 * @param id
	 * @param price
	 * @param total
	 * @return boolean
	 */
	boolean updatePriceAndTotalByID(String id,Double price,Integer total);
	
	/**
	 * 插入书籍   抽象方法
	 * @author 谭洋
	 * @param book
	 * @return boolean
	 */
	boolean insert(Book book);
}
