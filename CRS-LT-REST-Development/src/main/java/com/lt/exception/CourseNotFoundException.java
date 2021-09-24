package com.lt.exception;

/**
 * Custom exception class to handle Exception when requested course is not
 * available
 * 
 * @author Ariz
 */
public class CourseNotFoundException extends Exception {

	private String courseCode;

	public CourseNotFoundException(String courseCode) {
		this.courseCode = courseCode;
	}

	/**
	 * Method to display custom message "Course with code {courseCode} is not
	 * available"
	 */
	public String getMessage() {
		return "Course with code " + courseCode + " is Not Available";
	}

}
