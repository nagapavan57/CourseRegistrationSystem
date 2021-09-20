package com.lt.exception;

public class CourseNotFoundException extends Exception{
	
	private String courseCode;
	
	public CourseNotFoundException(String courseCode)
	{	
		this.courseCode = courseCode;
	}
	
	public String getMessage(String courseCode){
		return "Course Code with "+courseCode+" Is Not Available";
	}

}
