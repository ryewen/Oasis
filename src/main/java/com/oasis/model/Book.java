package com.oasis.model;

public class Book {

	private String bookname;
	
	private String bookcode;
	
	public Book() {
		
	}
	
	public Book(String bookname, String bookcode) {
		this.bookname = bookname;
		this.bookcode = bookcode;
	}
	
	public void setBookname(String bookname) {
		this.bookname = bookname;
	}
	
	public void setBookcode(String bookcode) {
		this.bookcode = bookcode;
	}
	
	public String getBookname() {
		return bookname;
	}
	
	public String getBookcode() {
		return bookcode;
	}
}
