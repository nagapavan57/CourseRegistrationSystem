package com.lt.business;

import java.util.List;

import org.springframework.stereotype.Service;

import com.lt.bean.Course;
import com.lt.bean.Professor;
import com.lt.bean.Student;
import com.lt.exception.CourseFoundException;
import com.lt.exception.CourseNotFoundException;
import com.lt.exception.ProfessorNotAddedException;
import com.lt.exception.StudentNotFoundException;
import com.lt.exception.UserNotFoundException;

@Service
public interface AdminInterface {
	
	
	/**
	 * Method to add Course to Course Catalog
	 * @param course : Course object storing details of a course
	 * @param courseList : Courses available in the catalog
	 * @param fee : fee of that particular course
	 */
	public boolean addCourse(List<Course> courseList,Course course,String fee) throws CourseFoundException;
	
	/**
	 * Method to Delete Course from Course Catalog
	 * @param courseCode
	 * @param courseList : Courses available in the catalog
	 * @throws CourseNotFoundException 
	 */
	public boolean deleteCourse(String courseCode,List<Course> courceList) throws CourseNotFoundException;
	
	/**
	 * Method to approve a Student 
	 * @param studentId
	 * @param studentList
	 * @throws StudentNotFoundException
	 */
	public boolean approveStudent(int StudentId,List<Student>studentlist)throws StudentNotFoundException;
	
	/**
	 * Method to add Professor to database
	 * @param professor : Professor Object storing details of a professor
	 */
	public boolean addProfessor(Professor professor)throws ProfessorNotAddedException;
	
	/**
	 * Method to assign Course to a Professor
	 * @param courseCode
	 * @param professorId
	 * @throws CourseNotFoundException
	 */
	public boolean assignCourse(String courseCode, String professorId) throws CourseNotFoundException,UserNotFoundException;
	
	/**
	 * Method to get list of courses in catalog
	 * @param catalogId
	 * @return List of courses in catalog
	 */
	public List<Course> viewCourses();
	
	/**
	 * Method to view Students yet to be approved by Admin
	 * @return List of Students with pending admissions
	 */
	public List<Student> viewPendingAdmissions();
	
	/**
	 * View professor in the institute
	 * @return List of the professors in the institute  
	 */

	public List<Professor> viewProfessors();

}
