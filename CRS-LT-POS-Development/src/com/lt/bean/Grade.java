package com.lt.bean;

/**
 * 
 * @author Pratyush Singh
 * Grade Class
 *
 */
public class Grade {
	
	private String courseCode;
	private String courseName;
	private String grade;
	private int studentId;
	
	
	public String getCourseCode() {
		return courseCode;
	}
	public void setCourseCode(String courseCode) {
		this.courseCode = courseCode;
	}
	public String getCourseName() {
		return courseName;
	}
	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}
	
	public String getGrade() {
		return grade;
	}
	public void setGrade(String grade) {
		this.grade = grade;
	}
	public int getStudentId() {
		return studentId;
	}
	public void setStudentId(int studentId) {
		this.studentId = studentId;
	}
	/**
	 * 
	 * @param courseCode
	 * @param courseName
	 * @param grade
	 * @param studentId
	 */
	public Grade(String courseCode, String courseName, String grade,
			int studentId) {
		super();
		this.courseCode = courseCode;
		this.courseName = courseName;
		this.grade = grade;
		this.studentId = studentId;
	}
	public Grade() {
	}
	/**
	 * 
	 * @param courseCode
	 * @param courseName
	 * @param grade
	 */
	public Grade(String courseCode, String courseName, String grade) {
		this.courseCode = courseCode;
		this.courseName = courseName;
		this.grade = grade;
	}
	
	
	
	

}
