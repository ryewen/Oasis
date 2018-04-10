package com.oasis.model;

import java.sql.Timestamp;

public class History {
	
	private String username;
	
	private String bookname;
	
	private int lastIndex;
	
	private Timestamp lastTime;
	
	public History() {
		
	}
	
	public History(String username, String bookname, int lastIndex, Timestamp lastTime) {
		this.username = username;
		this.bookname = bookname;
		this.lastIndex = lastIndex;
		this.lastTime = lastTime;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	public void setBookname(String bookname) {
		this.bookname = bookname;
	}
	
	public void setLastIndex(int lastIndex) {
		this.lastIndex = lastIndex;
	}
	
	public void setLastTime(Timestamp lastTime) {
		this.lastTime = lastTime;
	}
	
	public String getUsername() {
		return username;
	}
	
	public String getBookname() {
		return bookname;
	}
	
	public int getLastIndex() {
		return lastIndex;
	}
	
	public Timestamp getLastTime() {
		return lastTime;
	}
}
