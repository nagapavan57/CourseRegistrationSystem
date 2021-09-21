package com.lt.business;

import com.lt.exception.UserNotFoundException;

public interface UserInterface {
	
	public boolean updatePassword(String userId,String newPassword);
	
	public boolean verifyCredentials(String userID,String password)throws UserNotFoundException;
	
	public String getRoleById(String userId);

}
