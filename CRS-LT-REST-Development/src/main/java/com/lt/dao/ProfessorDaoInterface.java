package com.lt.dao;

import java.util.List;

import com.lt.bean.Course;
import com.lt.bean.StudentsEnrolled;
import com.lt.exception.GradeNotAddedException;

public interface ProfessorDaoInterface {

	/**
	 * Method to get Courses by Professor id
	 * @param userId, Professor id of the Professor
	 * @return get the courses offered by the professor
	 */
	public List<Course> getCoursesByProfessor(String userId);
	
	/**
	 * Method to view list of enrolled Students
	 * @param: profId: professor id 
	 * @return: return the enrolled students for the corresponding professor
	 */
	public List<StudentsEnrolled> getEnrolledStudents(String profId);
	
	
	/**
	 * Method to Grade a student for particular course
	 * @param: profId: professor id 
	 * @param: courseCode: course code for the corresponding grade to be assigned
	 * @param: grade: which grade needs to give for particular student
	 * @return: returns the status after adding the grade
	 */
	public boolean addGrade(int studentId,String courceCode,String courseName,String grade)throws GradeNotAddedException;
	
	
	/**
	 * Method to Get professor name by id
	 * @param profId
	 * @return Professor Id in string
	 */
	public String getProfessorById(String profId);
	
}
