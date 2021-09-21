package com.lt.exception;

public class UserNotFoundException extends Exception{
	
public String profId;
	
	public UserNotFoundException(String profId)
	{	
		this.profId = profId;
	}
	
	public String getMessage(String profId){
		return "User With Id "+profId+" Not Found!!!";
	}

}
