package com.lt.exception;

public class CourseFoundException extends Exception {
	
	public String courseCode;
	
	public CourseFoundException(String courseCode)
	{	
		this.courseCode = courseCode;
	}
	
	public String getMessage(String courseCode){
		return "Course Code with "+courseCode+"Is Already Available in Catalog";
	}

}
