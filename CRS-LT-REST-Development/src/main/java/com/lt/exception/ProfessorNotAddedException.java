package com.lt.exception;

public class ProfessorNotAddedException extends Exception {

	private String professorName;

	public ProfessorNotAddedException(String professorName) {
		this.professorName = professorName;
	}

	public String getMessage() {
		return "Failed to add professor " + professorName;
	}

}
