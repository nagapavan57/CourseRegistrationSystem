package com.lt.exception;

/**
 * Custom exception class to handle Exception when requested Student id is not
 * Registered while login
 * 
 * @author saurabh
 * 
 *
 */
public class StudentNotFoundException extends Exception {

	public int studentId;

	public StudentNotFoundException(int studentId) {
		this.studentId = studentId;
	}

	/**
	 * return error message Method to display custom message "Student id {studentId}
	 * Not Found!!!
	 */
	public String getMessage() {
		return "Student With " + studentId + " Is Not Found!!!";
	}

}
