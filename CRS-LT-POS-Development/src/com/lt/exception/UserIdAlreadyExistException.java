package com.lt.exception;

public class UserIdAlreadyExistException extends Exception{

private String profId;
	
	public UserIdAlreadyExistException(String profId)
	{	
		this.profId = profId;
	}
	
	public String getMessage(String courprofIdseCode){
		return "User with Userid "+profId+" Already Existed!!!";
	}
}
