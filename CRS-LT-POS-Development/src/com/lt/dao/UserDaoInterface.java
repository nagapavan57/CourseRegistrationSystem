package com.lt.dao;

import com.lt.exception.UserNotFoundException;

public interface UserDaoInterface {
	
	
	/**
	 * Method to update password of user in DataBase
	 * @param userID
	 * @return Update Password operation Status
	 */
	public boolean updatePassword(String userID,String newPassword);
	
	/**
	 * Method to get Role of User from DataBase
	 * @param userId
	 * @return Role
	 */
	
	public String getRoleById(String userId);
	/**
	 * Method to verify credentials of Users from DataBase
	 * @param userId
	 * @param password
	 * @return Verify credentials operation status
	 * @throws UserNotFoundException
	 */
	public boolean verifyCredentials(String userId,String password)throws UserNotFoundException;
	
	
}
