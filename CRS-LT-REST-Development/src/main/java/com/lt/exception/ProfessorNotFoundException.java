package com.lt.exception;

/**
 * Custom exception class to handle Exception when requested professorName is
 * not available
 * 
 * @author saurabh
 */
public class ProfessorNotFoundException extends Exception {

	private String professorName;

	public ProfessorNotFoundException(String professorName) {
		this.professorName = professorName;
	}

	/**
	 * return error message Method to display custom message "professor Name
	 * {professorName} Not Available
	 */
	public String getMessage() {
		return "Professor with name " + professorName + " Is Not Available";
	}

}
