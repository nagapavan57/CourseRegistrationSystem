package com.lt.exception;

/**
 * Custom exception class to handle Exception when course already exists
 * 
 * @author Ariz
 */
public class CourseFoundException extends Exception {

	public String courseCode;

	public CourseFoundException(String courseCode) {
		this.courseCode = courseCode;
	}

	/**
	 * Method to display custom message "Course with code {courseCode} is already
	 * available in catalogue"
	 */
	public String getMessage() {
		return "Course with code " + courseCode + " is already available in catalogue";
	}

}
