package com.lt.restcontroller;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lt.bean.Course;
import com.lt.bean.Grade;
import com.lt.business.SemisterRegistrationImplService;
import com.lt.business.SemisterRegistrationInterface;
import com.lt.dao.SemisterRegistrationDaoImpl;
import com.lt.exception.CourseLimitExceedException;
import com.lt.exception.CourseNotFoundException;
import com.lt.exception.SeatNotAvailableException;


@RestController
@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
public class StudentRestApi {


	SemisterRegistrationImplService semisterRegistrationInterface = new SemisterRegistrationImplService();
	
	@PostMapping("/addCourse")
	public void addCourseDetails(@RequestBody String courseCode,String courseName, int studentId) throws CourseNotFoundException, SQLException, CourseLimitExceedException, SeatNotAvailableException {
		semisterRegistrationInterface.addCourse(courseCode,courseName,studentId);
		}
	
	@GetMapping("/dropCourses")
	public boolean dropCources(@RequestBody int studentId,String courseCode,List<Course> registeredCourseList) throws SQLException, CourseNotFoundException {
		return semisterRegistrationInterface.dropCourse(studentId, courseCode, registeredCourseList);
	}
	
	@RequestMapping("/viewCourses/{id}")
	public List<Course> viewCourses(@PathVariable int studentId) throws SQLException{
		return semisterRegistrationInterface.viewCourses(studentId);
	}
	
	@RequestMapping("/viewRegisteredCourses/{id}")
	public List<Course> viewRegisteredCourses(@PathVariable int studentId) throws SQLException{
		return semisterRegistrationInterface.viewRegisteredCourses(studentId);
	}
	
	@RequestMapping("/viewGradeCard/{id}")
	public List<Grade> viewGradeCourses(@PathVariable int studentId) throws SQLException{
		return semisterRegistrationInterface.viewGradeCard(studentId);
	}
	
	@RequestMapping("/calculateFee/{id}")
	public double calculateFee(@PathVariable int studentId)throws SQLException{
		return semisterRegistrationInterface.calculateFee(studentId);
	}
	
	@RequestMapping("/getRegistrationStatus/{id}")
	public boolean getRegistrationStatus(@PathVariable int studentId) throws SQLException{
		return semisterRegistrationInterface.getRegistrationStatus(studentId);
	}
	
	
	
}
