package com.lt.dao;

import java.sql.SQLException;
import java.util.List;

import com.lt.bean.Student;
import com.lt.bean.StudentsEnrolled;
import com.lt.exception.StudentNotRegisteredException;

public interface StudentDaoInterface {
	
	/**
	 * Method to add student to database
	 * @param student: student object containing all the fields
	 * @return some integer if student is added
	 * @throws StudentNotRegisteredException
	 */
	public int registerStudent(Student student) throws StudentNotRegisteredException;
	
	/**
	 * Method to check if Student is approved
	 * @param studentId
	 * @return boolean indicating if student is approved
	 */
	public boolean isApproved(int studentId);
	
	/**
	 * Method to retrieve Student Id from User Id
	 * @param userId
	 * @return Student Id
	 */
	public int getStudentId(String userId)throws SQLException;
	
}
