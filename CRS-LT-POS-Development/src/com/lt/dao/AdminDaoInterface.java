package com.lt.dao;

import java.sql.SQLException;
import java.util.List;

import com.lt.bean.Course;
import com.lt.bean.Professor;
import com.lt.bean.Student;
import com.lt.exception.CourseFoundException;
import com.lt.exception.CourseNotFoundException;
import com.lt.exception.ProfessorNotAddedException;
import com.lt.exception.StudentNotFoundException;
import com.lt.exception.UserIdAlreadyExistException;
import com.lt.exception.UserNotFoundException;

public interface AdminDaoInterface {
	
	/**
	 * Add Course into catalog
	 * @param course
	 * @param fee
	 * @throws CourseFoundException
	 */
	public void addCourse(Course course,String fee)throws CourseFoundException;
	
	/**
	 * Delete Course from catalog
	 * @param courseCode
	 * @throws CourseNotFoundException 
	 */
	public void deleteCourse(String courseCode)throws CourseNotFoundException;
	
	/**
	 * Approving Student 
	 * @param studentId
	 * @throws StudentNotFoundException
	 */
	public void approveStudent(int StudentId)throws StudentNotFoundException;
	
	/**
	 * Assign courses to Professor
	 * @param courseCode
	 * @param professorId
	 * @throws CourseNotFoundException
	 */
	public void assignCourse(String courseCode, String professorId) throws CourseNotFoundException,UserNotFoundException;
	
	/**
	 * Adding  professor 
	 * @param professor
	 * @throws ProfessorNotAddedException
	 */
	public void addProfessor(Professor professor)throws UserIdAlreadyExistException;
	
	/**
	 * View courses in the catalog
	 * @return List of courses in the catalog
	 */
	public List<Course> viewCourses()throws SQLException;
	
	/**
	 * Fetch Students yet to approved
	 * @return List of Students yet to approved
	 */
	public List<Student> viewPendingAdmissions();
	
	/**
	 * View professor in the institute
	 * @return List of the professors in the institute  
	 */

	public List<Professor> viewProfessors();
}
