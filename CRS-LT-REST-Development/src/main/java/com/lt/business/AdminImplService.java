
package com.lt.business;

import java.sql.SQLException;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

@Service
public class AdminImplService implements AdminInterface{
	@Autowired
	AdminDaoImpl adminimpl;
	
	private static Logger logger = Logger.getLogger(AdminImplService.class);
	public boolean addCourse(List<Course> courseList,Course course,String fee)
			throws CourseFoundException {
	
		if(!AdminValidator.isValidNewCourse(course, courseList)) {
			logger.error("courseCode: " + course.getCourseCode() + " already present in catalog!!");
			throw new CourseFoundException(course.getCourseCode());
		}
		
		try {
			return adminimpl.addCourse(course,fee);
		} catch (CourseFoundException ex) {
			logger.error(ex.getMessage());
			
		}
		return false;
		
	}

	public boolean deleteCourse(String courseCode, List<Course> courseList)
			throws CourseNotFoundException{
		
		/*
		 * if(!AdminValidator.isValidDropCourse(courseCode, courseList)) {
		 * logger.error("courseCode: " + courseCode+ " already present in catalog!!");
		 * System.out.println("inside if block of validation"); throw new
		 * CourseNotFoundException(courseCode); }else{
		 */
			return adminimpl.deleteCourse(courseCode);
		//}
		
	}

	public boolean approveStudent(int studentId, List<Student> studentlist)
			throws StudentNotFoundException {
		if(!AdminValidator.isValidUnapprovedStudent(studentId, studentlist)) {
			//logger.error("studentId: " + studentId + " is already approvet/not-present!");
			throw new StudentNotFoundException(studentId);
		}
		if(adminimpl.approveStudent(studentId)) {
			return true;
		}
		return false;

		
	}

	

	public boolean assignCourse(String courseCode, String professorId)
			throws CourseNotFoundException,UserNotFoundException {
		/*for(Course c:courceList){
			if(c.getCourseCode().equalsIgnoreCase(courseCode))
				c.setprofessorId(professorId);
		}
		System.out.println("Course Assignment is Completed!!");*/
		try {
			return adminimpl.assignCourse(courseCode, professorId);
		} catch(CourseNotFoundException e) {
			logger.error(e.getMessage());
		}
		catch (UserNotFoundException   e) {
			//logger.error(e.getMessage(professorId));
		}
		return false;
	}

	public boolean addProfessor(Professor professor)
			throws ProfessorNotAddedException {
		try {
			return adminimpl.addProfessor(professor);
		} catch (UserIdAlreadyExistException e) {
			logger.error(e.getMessage(professor.getUserId()));
		}
		return false;
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
