package com.lt.business;

import java.sql.SQLException;
import java.util.List;

import org.apache.log4j.Logger;

import com.lt.bean.Course;
import com.lt.bean.Grade;
import com.lt.dao.SemisterRegistrationDaoImpl;
import com.lt.exception.CourseLimitExceedException;
import com.lt.exception.CourseNotFoundException;
import com.lt.exception.SeatNotAvailableException;
import com.lt.validator.StudentValidator;

public class SemisterRegistrationImplService implements SemisterRegistrationInterface{

	SemisterRegistrationDaoImpl regiImpl = new SemisterRegistrationDaoImpl();
	private static Logger logger = Logger.getLogger(SemisterRegistrationImplService.class);
	
	public boolean addCourse(String courseCode,String courseName, int studentId)throws CourseNotFoundException,CourseLimitExceedException, SeatNotAvailableException, SQLException{
		
		if (regiImpl.numOfRegisteredCourses(studentId) >= 6)
		{	
			throw new CourseLimitExceedException(6);
		}
		else if (regiImpl.isRegistered(courseCode, studentId)) 
		{
			return false;
		}
		else if (!regiImpl.seatAvailable(courseCode)) 
		{
			throw new SeatNotAvailableException(courseCode);
		} 
		return regiImpl.addCourse(courseCode,courseName, studentId);
	}

	public boolean dropCourse(int studentId, String courseCode,
			List<Course> registeredCourseList) throws CourseNotFoundException, SQLException {
		
		 if(!StudentValidator.isRegistered(courseCode, studentId, registeredCourseList))
	        {
	        	throw new CourseNotFoundException(courseCode);
	        }
		return regiImpl.dropCourse(courseCode, studentId);
	}

	public List<Course> viewCourses(int studentId) throws SQLException {
		return regiImpl.viewCourses(studentId);
	}

	public List<Course> viewRegisteredCourses(int studentId) throws SQLException {
		return regiImpl.viewRegisteredCourses(studentId);
	}

	public List<Grade> viewGradeCard(int studentId)throws SQLException {
		return regiImpl.viewGradeCard(studentId);
	}

	public double calculateFee(int studentId) throws SQLException {
		return regiImpl.calculateFee(studentId);
	}

	@Override
	public boolean getRegistrationStatus(int studentId){
			return regiImpl.getRegistrationStatus(studentId);
	}

}
