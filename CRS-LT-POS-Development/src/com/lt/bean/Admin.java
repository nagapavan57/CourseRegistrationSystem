package com.lt.bean;

import java.util.Date;

import com.lt.constants.Role;

/**
 * 
 * @author Pratyush Singh
 * Admin Class
 */
public class Admin extends User{
	
	private String name;
	private Date doj;
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Date getDoj() {
		return doj;
	}
	public void setDoj(Date doj) {
		this.doj = doj;
	}
	/**
	 * 
	 * @param userId
	 * @param password
	 * @param role
	 * @param name
	 * @param doj
	 */
	public Admin(String userId, String password, Role role, String name,
			Date doj) {
		super(userId, password, role);
		this.name = name;
		this.doj = doj;
	}
	
	
	
	

}
