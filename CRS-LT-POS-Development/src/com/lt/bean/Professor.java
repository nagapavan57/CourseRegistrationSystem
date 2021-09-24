package com.lt.bean;

import com.lt.constants.Role;
/**
 * 
 * @author Pratyush Singh
 * Professor Class
 *
 */
public class Professor extends User {

	private String department;
	private String name;
	private String subject;

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	/**
	 * 
	 * @param userId
	 * @param password
	 * @param role
	 * @param department
	 * @param name
	 * @param subject
	 */
	public Professor(String userId, String password, Role role, String department, String name, String subject) {
		super(userId, password, role);
		this.department = department;
		this.name = name;
		this.subject = subject;
	}
	
	public Professor() {
		super();
	}

}
