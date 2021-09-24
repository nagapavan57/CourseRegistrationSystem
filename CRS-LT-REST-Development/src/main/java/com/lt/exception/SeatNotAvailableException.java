package com.lt.exception;

/**
 * Custom exception class to handle Exception when requested Seat {courseCode }
 * is not available
 *
 * @author saurabh
 * 
 */
public class SeatNotAvailableException extends Exception {

	private String courseCode;

	public SeatNotAvailableException(String courseCode) {
		this.courseCode = courseCode;
	}

	/**
	 * return error message Method to display custom message "course code
	 * {courseCode} Seat are Not available!!!
	 */

	@Override
	public String getMessage() {
		return "Seats are not available in : " + courseCode;
	}

}
