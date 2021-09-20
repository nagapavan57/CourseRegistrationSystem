package com.lt.exception;

public class CourseNotAssignedToProfessorException extends Exception {
	
public String courseCode,profId;
	
	public CourseNotAssignedToProfessorException(String courseCode,String profId)
	{	
		this.courseCode = courseCode;
		this.profId = profId;
	}
	
	public String getMessage(String courseCode){
	return "Course Code  "+courseCode+" or ProfessorId "+profId+" Not Found!!";
	}

}
