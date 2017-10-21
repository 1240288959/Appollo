package com.appollo.book.imp;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.appollo.domain.Book;
import com.appollo.util.db.DBLinkUtil;
import com.appollo.util.db.IRowMapper;
/**
 * 数据操作实现类
 * @author 谭洋
 *
 */
public class BookDao implements com.appollo.book.IBookDao {
	
	/**
	 * 根据姓名和作者查找书籍  实现函数
	 * @author 谭洋
	 * @param name
	 * @param author
	 * @return List<Book>
	 */
	@Override
	public List<Book> findByNameAndAuthor(String name, String author) {
		class RowMapper implements IRowMapper{
			
			private String id;
			private String name;
			private String author;
			private double price;
			private int total;
			private String big_book_type_id;
			private String small_book_type_id;
			private String publisher_id;
			private String sumarry;
			private Book book;
			private List<Book> list=new ArrayList<>();
			@Override
			public void rowgenerate(ResultSet rs) {
				try {
					while(rs.next()) {
						id=rs.getString("id");
						name=rs.getString("name");
						author=rs.getString("author");
						price=rs.getDouble("price");
						total=rs.getInt("total");
						big_book_type_id=rs.getString("big_book_type_id");
						small_book_type_id=rs.getString("small_book_type_id");
						publisher_id=rs.getString("publisher_id");
						sumarry=rs.getString("sumarry");
						book=new Book(id, name, author, price, total, big_book_type_id, small_book_type_id, publisher_id, sumarry);
						list.add(book);
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
		}
		RowMapper rowMapper=new RowMapper();
		String sql="select id,name,author,price,total,big_book_type_id,small_book_type_id,publisher_id,sumarry from book where name like ? and author like ?";
		DBLinkUtil.select(sql, rowMapper, name,author);
		return rowMapper.list;
	}

	/**
	 * 根据id删除书籍  实现函数
	 * @author 谭洋
	 * @param id
	 * @return boolean
	 */
	@Override
	public boolean deleteByID(String id) {
		String sql="delete from book where id = ?";
		return DBLinkUtil.update(sql, id);
	}
	
	/**
	 * 插入书籍  实现函数
	 * @author 谭洋
	 * @param book
	 * @return boolean
	 */
	@Override
	public boolean insert(Book book) {
		String sql="insert into book(id,name,author,price,total,big_book_type_id,small_book_type_id,publisher_id,sumarry) values(?,?,?,?,?,?,?,?,?) ";
		return DBLinkUtil.update(sql, book.getId(),book.getName(),book.getAuthor(),book.getPrice(),book.getTotal(),book.getBig_book_type_id(),book.getSmall_book_type_id(),book.getPublisher_id(),book.getSummary());
	}

	/**
	 * 根据书籍id更新价格和库存  实现函数
	 * @author 实现函数
	 * @param id
	 * @param price
	 * @param total
	 * @return boolean
	 */
	@Override
	public boolean updatePriceAndTotalByID(String id, Double price, Integer total) {
		String sql="update book set price = ?,total= ?  where id = ?";
		return DBLinkUtil.update(sql, price,total,id);
	}

	

	
}
