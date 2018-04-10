package com.oasis.model;

public class User {

	public static final String AUTH_ADMIN = "ADMIN";
	
	public static final String AUTH_USER = "USER";
	
	public static final String NO_EXIST = "NO_EXIST";

	private String username;
	
	private String password;
	
	private String authority;
	
	public User() {
		
	}
	
	public User(String username, String password, String authority) {
		this.username = username;
		this.password = password;
		this.authority = authority;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public void setAuthority(String authority) {
		this.authority = authority;
	}
	
	public String getUsername() {
		return username;
	}
	
	public String getPassword() {
		return password;
	}
	
	public String getAuthority() {
		return authority;
	}
}
