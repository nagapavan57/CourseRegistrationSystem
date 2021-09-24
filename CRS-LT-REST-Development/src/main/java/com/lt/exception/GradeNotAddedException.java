package com.lt.exception;

/**
 * Custom exception class to handle Exception when requested Grade could not be
 * added
 * 
 * @author Ariz
 */
public class GradeNotAddedException extends Exception {

	public int studentId;

	public GradeNotAddedException(int studentId) {
		this.studentId = studentId;
	}

	/**
	 * Method to display custom message "Grade Assigning Failed for Student with Id:
	 * {studentId}"
	 */
	public String getMessage() {
		return "Grade Assigning Failed for Student with Id: " + studentId;
	}
}
