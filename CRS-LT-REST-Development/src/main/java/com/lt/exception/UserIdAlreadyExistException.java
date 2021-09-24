package com.lt.exception;

/**
 * Custom exception class to handle Exception when requested r professor id is
 * available while assigning to course
 *
 * @author saurabh
 *
 */
public class UserIdAlreadyExistException extends Exception {

	private String profId;

	public UserIdAlreadyExistException(String profId) {
		this.profId = profId;
	}

	/**
	 * 
	 * @param courprofIdseCode
	 * @return error message: Method to display custom message "Professor id
	 *         {profId} Already Exist!!!
	 */
	public String getMessage(String courprofIdseCode) {
		return "User with Userid " + profId + " Already Existed!!!";
	}
}
