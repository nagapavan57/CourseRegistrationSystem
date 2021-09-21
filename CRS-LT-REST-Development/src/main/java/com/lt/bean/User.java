package com.lt.bean;

import com.lt.constants.Role;

public  class User {
	
	private String userId;
	private String password;
	private Role role;
	
	
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Role getRole() {
		return role;
	}
	public void setRole(Role role) {
		this.role = role;
	}
	public User(String userId, String password, Role role) {
		this.userId = userId;
		this.password = password;
		this.role = role;
	}
	public User() {
		
	}
	
	
	

}
