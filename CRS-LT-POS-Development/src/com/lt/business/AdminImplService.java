
package com.lt.business;

import java.sql.SQLException;
import java.util.List;

import org.apache.log4j.Logger;

import com.lt.bean.Course;
import com.lt.bean.Professor;
import com.lt.bean.Student;
import com.lt.dao.AdminDaoImpl;
import com.lt.exception.CourseFoundException;
import com.lt.exception.CourseNotFoundException;
import com.lt.exception.ProfessorNotAddedException;
import com.lt.exception.StudentNotFoundException;
import com.lt.exception.UserIdAlreadyExistException;
import com.lt.exception.UserNotFoundException;
import com.lt.validator.AdminValidator;

public class AdminImplService implements AdminInterface{
	AdminDaoImpl adminimpl=new AdminDaoImpl();
	private static Logger logger = Logger.getLogger(AdminImplService.class);
	public void addCourse(List<Course> courseList,Course course,String fee)
			throws CourseFoundException {
	
		/*if(!AdminValidator.isValidNewCourse(course, courseList)) {
			logger.error("courseCode: " + course.getCourseCode() + " already present in catalog!");
			throw new CourseFoundException(course.getCourseCode());
		}*/
		
		try {
			adminimpl.addCourse(course,fee);
		} catch (CourseFoundException ex) {
			logger.error(ex.getMessage(course.getCourseCode()));
			
		}
		
		
	}

	public void deleteCourse(String courseCode, List<Course> courseList)
			throws CourseNotFoundException{
		/*for(Course c:courseList){
			if(c.getCourseCode().equals(courseCode)){
				courseList.remove(c);
				System.out.println("Course deleted Successfully\n");
		
			}*/
		try {
			adminimpl.deleteCourse(courseCode);
		} catch (CourseNotFoundException ex) {
			logger.error(ex.getMessage(courseCode));
		}
		
		
	}

	public void approveStudent(int studentId, List<Student> studentlist)
			throws StudentNotFoundException {
		try {
			adminimpl.approveStudent(studentId);
		} catch (StudentNotFoundException ex) {
			logger.error(ex.getMessage(studentId));
		}
		
	}

	

	public void assignCourse(String courseCode, String professorId)
			throws CourseNotFoundException,UserNotFoundException {
		/*for(Course c:courceList){
			if(c.getCourseCode().equalsIgnoreCase(courseCode))
				c.setprofessorId(professorId);
		}
		System.out.println("Course Assignment is Completed!!");*/
		try {
			adminimpl.assignCourse(courseCode, professorId);
		} catch (CourseNotFoundException | UserNotFoundException   e) {
			logger.error(e.getMessage());
			throw new UserNotFoundException(professorId);
		}
	}

	public void addProfessor(Professor professor)
			throws ProfessorNotAddedException {
		try {
			adminimpl.addProfessor(professor);
		} catch (UserIdAlreadyExistException e) {
			logger.error(e.getMessage(professor.getUserId()));
		}
		
	}
	
	@Override
	public List<Course> viewCourses() {
		try {
			return adminimpl.viewCourses();
		}
		catch (Exception e) {
			logger.error(e.getMessage());
		}
		return null;
	}

	@Override
	public List<Student> viewPendingAdmissions() {
		return adminimpl.viewPendingAdmissions();
	}

	@Override
	public List<Professor> viewProfessors() {
		
		return adminimpl.viewProfessors();
	}

}
