package com.appollo.domain;
/**
 * 书籍表的实体类
 * @author 谭洋
 *
 */
public class Book {
	private String id;
	private String name;
	private String author;
	private double price;
	private int total;
	private String big_book_type_id;
	private String small_book_type_id;
	private String publisher_id;
	private String sumarry;
	
	
	
	public Book() {
		super();
	}



	public Book(String id, String name, String author, double price, int total, String big_book_type_id,
			String small_book_type_id, String publisher_id, String sumarry) {
		super();
		this.id = id;
		this.name = name;
		this.author = author;
		this.price = price;
		this.total = total;
		this.big_book_type_id = big_book_type_id;
		this.small_book_type_id = small_book_type_id;
		this.publisher_id = publisher_id;
		this.sumarry = sumarry;
	}



	public String getId() {
		return id;
	}



	public void setId(String id) {
		this.id = id;
	}



	public String getName() {
		return name;
	}



	public void setName(String name) {
		this.name = name;
	}



	public String getAuthor() {
		return author;
	}



	public void setAuthor(String author) {
		this.author = author;
	}



	public double getPrice() {
		return price;
	}



	public void setPrice(double price) {
		this.price = price;
	}



	public int getTotal() {
		return total;
	}



	public void setTotal(int total) {
		this.total = total;
	}



	public String getBig_book_type_id() {
		return big_book_type_id;
	}



	public void setBig_book_type_id(String big_book_type_id) {
		this.big_book_type_id = big_book_type_id;
	}



	public String getSmall_book_type_id() {
		return small_book_type_id;
	}



	public void setSmall_book_type_id(String small_book_type_id) {
		this.small_book_type_id = small_book_type_id;
	}



	public String getPublisher_id() {
		return publisher_id;
	}



	public void setPublisher_id(String publisher_id) {
		this.publisher_id = publisher_id;
	}



	public String getSummary() {
		return sumarry;
	}



	public void setSummary(String summary) {
		this.sumarry = summary;
	}



	@Override
	public String toString() {
		return "Book [id=" + id + ", name=" + name + ", author=" + author + ", price=" + price + ", total=" + total
				+ ", big_book_type_id=" + big_book_type_id + ", small_book_type_id=" + small_book_type_id
				+ ", publisher_id=" + publisher_id + ", summary=" + sumarry + "]";
	}
	
	
	
}
