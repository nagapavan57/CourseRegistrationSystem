package com.lt.business;

import java.sql.SQLException;
import java.util.List;

import org.springframework.stereotype.Service;

import com.lt.bean.Course;
import com.lt.bean.Grade;
import com.lt.bean.StudentsEnrolled;
import com.lt.exception.StudentNotRegisteredException;

@Service
public interface StudentInterface {
	
	public int register(String userId, String password,String name,String emailId,
			String branchName, String Address)throws StudentNotRegisteredException;
	public int getStudentId(String userId)throws SQLException;
	public boolean isApproved(int studentId)throws SQLException;
	/*public void registerCourse(int studentId, String courseCode, String courseName,List<StudentsEnrolled> studentEnrolList);
	
	public void addCourse(String courseCode, String courseName, String instructor, String duration,
			List<Course> courseList);

	public void dropCourse(int studentId,String courseCode, List<StudentsEnrolled> registeredCourseList);

	public void viewRegisteredCourse(int studentId, List<StudentsEnrolled> registerCourseList);

	public void viewGrades(int studentId, List<Grade> gradeList);
	
	public void makePayment(int studentId);*/
	

}
