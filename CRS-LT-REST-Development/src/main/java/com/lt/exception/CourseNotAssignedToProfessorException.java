package com.lt.exception;

/**
 * Custom exception class to handle Exception when requested course code or
 * professor id is not available while assigning to course
 * 
 * @author Ariz
 */
public class CourseNotAssignedToProfessorException extends Exception {

	public String courseCode, profId;

	public CourseNotAssignedToProfessorException(String courseCode, String profId) {
		this.courseCode = courseCode;
		this.profId = profId;
	}

	/**
	 * Method to display custom message "Course code {courseCode} or professorId
	 * {profId} Not Found!!!"
	 */
	public String getMessage() {
		return "Course code " + courseCode + " or professorId " + profId + " Not Found!!!";
	}

}
