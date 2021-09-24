package com.lt.exception;

/**
 * Custom exception class to handle Exception when requested professor could not
 * be added
 * 
 * @author Ariz
 */
public class ProfessorNotAddedException extends Exception {

	private String professorName;

	public ProfessorNotAddedException(String professorName) {
		this.professorName = professorName;
	}

	/**
	 * Method to display custom message "Failed to add professor {professorName}"
	 */
	public String getMessage() {
		return "Failed to add professor " + professorName;
	}

}
