package com.lt.exception;

public class StudentNotFoundException extends Exception {
	
public int studentId;
	
	public StudentNotFoundException(int studentId)
	{	
		this.studentId = studentId;
	}
	
	public String getMessage(){
		return "Student With "+studentId+" Is Not Found!!!";
	}

}
