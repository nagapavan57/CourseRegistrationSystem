package com.lt.exception;


/**
 *  Custom exception class to handle Exception when requested 
* Student name is not Registered while login
 * @author saurabh
 *
 */
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
	
	
	/**
	 * return error message  Method to display custom message "Student Name  
*          is not registered !!!
	 */
	public String getMessage(){
		return "Student With Name "+studentName+" Is Not Registered!!!";
	}
	

}
