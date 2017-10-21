package com.appollo.book.imp;

import java.util.List;
import java.util.Objects;
import java.util.Scanner;

import com.appollo.book.IBookService;
import com.appollo.domain.Book;
import com.appollo.util.UUIDUtil;

/**
 * 书籍模块业务逻辑实现类
 * @author 谭洋
 *
 */
public class BookService implements IBookService {
	
	private BookDao bookDao=new BookDao();
	
	/**
	 * 根据书籍名称和作者查找书籍 实现函数
	 * @author 谭洋
	 * @param name
	 * @param author
	 * @return void
	 */
	@Override
	public void findByNameAndAuthor(String name, String author) {
		List<Book> list= bookDao.findByNameAndAuthor(name, author);
		for(Book book:list) {
			System.out.println("ID:\t"+book.getId());
			System.out.println("Name:\t"+book.getName());
			System.out.println("Author:\t"+book.getAuthor());
			System.out.println("Price:\t"+book.getPrice());
			System.out.println("Total:\t"+book.getTotal());
			System.out.println("Big_Book_Type_id:\t"+book.getBig_book_type_id());
			System.out.println("Small_Book_Type_id:\t"+book.getSmall_book_type_id());
			System.out.println("Publisher_id:\t"+book.getPublisher_id());
			System.out.println("Sumarry:\t"+book.getSummary());
		}
	}
	
	/**
	 * 根据id删除书籍  实现函数
	 * @author 谭洋
	 * @param id
	 * @return boolean
	 */
	@Override
	public boolean deleteByID(String id) {
		Scanner scanner=new Scanner(System.in);
		System.out.println("确认是否删除  y/n?");
		String confirm=scanner.next();
		scanner.close();
		if(Objects.equals(confirm, "n"))
		{
			System.out.println("手动确认了不删除");
			return false;
		}
		boolean flag= bookDao.deleteByID(id);
		if(flag==true) {
			System.out.println("删除书籍成功");
		}else {
			System.out.println("删除书籍失败");
		}
		return flag;
	}
	
	/**
	 * 插入书籍  实现函数
	 * @author 谭洋
	 * @return boolean
	 */
	@Override
	public boolean insert() {
		System.out.println("请输入要添加的书籍信息");
		Scanner scanner=new Scanner(System.in);
		String id=UUIDUtil.getUUID();
		System.out.println("请输入要添加的书名");
		String name=scanner.next();
		System.out.println("请输入要添加的作者名");
		String author=scanner.next();
		System.out.println("请输入要添加的价格");
		double price=scanner.nextDouble();
		System.out.println("请输入要添加的库存");
		int total=scanner.nextInt();
//		System.out.println("请输入要添加的大类型ID");
		String big_book_type_id=null;
//		System.out.println("请输入要添加的小类型ID");
		String small_book_type_id=null;
//		System.out.println("请输入要添加的出版社ID");
		String publisher_id=null;
		System.out.println("请输入要添加的总结");
		String sumarry=scanner.next();
		scanner.close();
		Book book=new Book(id, name, author, price, total, big_book_type_id, small_book_type_id, publisher_id, sumarry);
		boolean flag=bookDao.insert(book);
		if(flag==true) {
			System.out.println("添加成功");
		}else {
			System.out.println("添加失败");
		}
		return flag;
	}
	
	/**
	 * 根据输入的id更改价格和库存  实现函数
	 * @author 谭洋
	 * @return boolean
	 */
	@Override
	public boolean updatePriceAndTotalByID() {
		Scanner scanner=new Scanner(System.in);
		System.out.println("请输入要更改的书籍ID");
		String id=scanner.next();
		System.out.println("请输入要更改的书价格");
		double price=scanner.nextDouble();
		System.out.println("请输入要更改的书库存");
		int total=scanner.nextInt();
		boolean flag=bookDao.updatePriceAndTotalByID(id, price, total);
		if(flag==true) {
			System.out.println("更新成功");
		}else {
			System.out.println("更新失败");
		}
		scanner.close();
		return flag;
	}
	
}
