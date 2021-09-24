package com.lt.exception;

/**
 * Custom exception class to handle Exception when requested professor id is not
 * available
 *
 * @author saurabh
 *
 */
public class UserNotFoundException extends Exception {

	public String profId;

	public UserNotFoundException(String profId) {
		this.profId = profId;
	}

	/**
	 * Method to display custom message "Professor id {profId} Not Found!!!
	 */
	public String getMessage() {
		return "User With Id " + profId + " Not Found!!!";
	}

}
