package com.lt.bean;
/**
 * 
 * @author Pratyush Singh
 * Course Class
 *
 */
public class Course {

	private String courseCode;
	private String courseName;
	private String profid;
	private String seats;
	private String fee;

	public String getCourseCode() {
		return courseCode;
	}

	public void setCourseCode(String courdseCode) {
		this.courseCode = courdseCode;
	}

	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	public String getProfid() {
		return profid;
	}

	public void setProfid(String profid) {
		this.profid = profid;
	}

	public String getFee() {
		return fee;
	}

	public void setFee(String fee) {
		this.fee = fee;
	}

	public String getSeats() {
		return seats;
	}

	public void setSeats(String seats) {
		this.seats = seats;
	}

	public Course() {
	}

	/**
	 *
	 * @param courseCode
	 * @param courseName
	 * @param profid
	 * @param seats
	 */
	public Course(String courseCode, String courseName, String profid, String seats) {
		super();
		this.courseCode = courseCode;
		this.courseName = courseName;
		this.profid = profid;
		this.seats = seats;
	}

	/**
	 * 
	 * @param courseCode
	 * @param courseName
	 * @param profid
	 * @param seats
	 * @param fee
	 */
	public Course(String courseCode, String courseName, String profid, String seats, String fee) {
		this.courseCode = courseCode;
		this.courseName = courseName;
		this.profid = profid;
		this.seats = seats;
		this.fee = fee;
	}

	

}
