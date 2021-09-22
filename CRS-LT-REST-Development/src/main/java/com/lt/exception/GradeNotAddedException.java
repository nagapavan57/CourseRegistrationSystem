package com.lt.exception;

public class GradeNotAddedException extends Exception {

	public int studentId;

	public GradeNotAddedException(int studentId) {
		this.studentId = studentId;
	}

	public String getMessage(int studentId) {
		return "Grade Assigning Failed for Student with Id:" + studentId;
	}
}
