package com.oasis.model;

public class HistoryCode {
	
	private History history;
	
	private String bookcode;
	
	public HistoryCode() {
		
	}
	
	public HistoryCode(History history, String bookcode) {
		this.history = history;
		this.bookcode = bookcode;
	}
	
	public void setHistory(History history) {
		this.history = history;
	}
	
	public void setBookcode(String bookcode) {
		this.bookcode = bookcode;
	}
	
	public History getHistory() {
		return history;
	}
	
	public String getBookcode() {
		return bookcode;
	}
}
