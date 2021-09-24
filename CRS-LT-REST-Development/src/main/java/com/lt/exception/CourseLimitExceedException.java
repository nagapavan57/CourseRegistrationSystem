package com.lt.exception;

/**
 * Custom exception class to handle Exception when default course limit is
 * exceeded to register for a course
 * 
 * @author Ariz
 */
public class CourseLimitExceedException extends Exception {

	private int num;

	public CourseLimitExceedException(int num) {
		this.num = num;
	}

	/**
	 * Method to display custom message "You have already registered for {num}
	 * courses"
	 */
	public String getMessage() {
		return "You have already registered for " + num + " courses";
	}

}
