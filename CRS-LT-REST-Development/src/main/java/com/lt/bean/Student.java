package com.lt.bean;

import com.lt.constants.Role;
/**
 * 
 * @author Pratyush Singh
 * Student Class
 *
 */
public class Student extends User {
	
	private int studentId;
	private String name;
	private String emailId;
	private String branchName;
	private String Address;
	private boolean isApproved;

	public String getBranchName() {
		return branchName;
	}

	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}

	public int getStudentId() {
		return studentId;
	}

	public void setStudentId(int studentId) {
		this.studentId = studentId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public String getAddress() {
		return Address;
	}

	public void setAddress(String address) {
		Address = address;
	}

	public boolean isApproved() {
		return isApproved;
	}

	public void setApproved(boolean isApproved) {
		this.isApproved = isApproved;
	}

	

	/**
	 * 
	 * @param userId
	 * @param password
	 * @param role
	 * @param name
	 * @param emailId
	 * @param branchName
	 * @param address
	 * @param isApproved
	 */
	public Student(String userId, String password, Role role,
			String name, String emailId, String branchName, String address,
			boolean isApproved) {
		super(userId, password, role);
		this.name = name;
		this.emailId = emailId;
		this.branchName = branchName;
		Address = address;
		this.isApproved = isApproved;
	}

	public Student() {
		super();

	}

}
