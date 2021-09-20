package com.lt.bean;

import com.lt.constants.Role;

public abstract class User {
	
	private String userId;
	private String password;
	private Role role;
	private String name;
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
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
		super();
		this.userId = userId;
		this.password = password;
		this.role = role;
	}
	public User() {
		super();
	}
	
	
	

}
