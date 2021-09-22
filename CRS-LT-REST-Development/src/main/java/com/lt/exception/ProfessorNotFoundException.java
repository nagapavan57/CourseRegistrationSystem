package com.lt.exception;

public class ProfessorNotFoundException extends Exception {

private String professorName;
	
	public ProfessorNotFoundException(String professorName)
	{	
		this.professorName = professorName;
	}
	
	public String getMessage(String professorName){
		return "Professor with name "+professorName+" Is Not Available";
	}

}
