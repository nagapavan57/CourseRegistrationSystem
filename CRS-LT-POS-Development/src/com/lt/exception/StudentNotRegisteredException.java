package com.lt.exception;

public class StudentNotRegisteredException extends Exception {
	
	private String  studentName;

	
	public String getStudentName() {
		return studentName;
	}

	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}

	public StudentNotRegisteredException(String studentName)
	{	
		this.studentName = studentName;
	}
	
	public String getMessage(String studentName){
		return "Student With Name "+studentName+" Is Not Registered!!!";
	}
	

}
