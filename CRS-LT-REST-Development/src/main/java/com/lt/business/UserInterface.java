package com.lt.business;

import org.springframework.stereotype.Service;

import com.lt.exception.UserNotFoundException;

@Service
public interface UserInterface {
	
	public boolean updatePassword(String userId,String newPassword);
	
	public boolean verifyCredentials(String userID,String password)throws UserNotFoundException;
	
	public String getRoleById(String userId);

}
