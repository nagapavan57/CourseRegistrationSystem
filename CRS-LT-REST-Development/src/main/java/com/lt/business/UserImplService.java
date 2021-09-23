package com.lt.business;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lt.dao.UserDaoImpl;
import com.lt.exception.UserNotFoundException;

@Service
public class UserImplService implements UserInterface {
	
	@Autowired
	UserDaoImpl usrDaoImpl;

	public boolean updatePassword(String userId, String newPassword) {

		return usrDaoImpl.updatePassword(userId, newPassword);

	}

	@Override
	public boolean verifyCredentials(String userID, String password) throws UserNotFoundException {
		try {
			return usrDaoImpl.verifyCredentials(userID, password);
		} finally {
		}

	}

	@Override
	public String getRoleById(String userId) {
		return usrDaoImpl.getRoleById(userId);
	}

}
