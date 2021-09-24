package com.lt.bean;

/**
 * 
 * @author Pratyush Singh
 * StudentsEnrolled Class
 *
 */
public class StudentsEnrolled {
	
	private String courseCode;
	private String courseName;
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
	 * @param studentId
	 */
	public StudentsEnrolled(String courseCode, String courseName, int studentId) {
		this.courseCode = courseCode;
		this.courseName = courseName;
		this.studentId = studentId;
	}
	public StudentsEnrolled() {
	}
	

}
