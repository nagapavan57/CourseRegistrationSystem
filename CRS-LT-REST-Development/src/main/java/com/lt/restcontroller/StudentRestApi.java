package com.lt.restcontroller;

import java.sql.SQLException;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lt.bean.Course;
import com.lt.bean.Grade;
import com.lt.bean.StudentsEnrolled;
import com.lt.business.SemisterRegistrationImplService;
import com.lt.exception.CourseLimitExceedException;
import com.lt.exception.CourseNotFoundException;
import com.lt.exception.SeatNotAvailableException;


@RestController
@RequestMapping("/Student")
public class StudentRestApi {


	SemisterRegistrationImplService semisterRegistrationInterface = new SemisterRegistrationImplService();
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@PostMapping("/addCourse")
	@Consumes(MediaType.APPLICATION_JSON)
	public ResponseEntity addCourseDetails(@RequestBody StudentsEnrolled studEnroll) throws CourseNotFoundException, SQLException, CourseLimitExceedException, SeatNotAvailableException {
		
		if(semisterRegistrationInterface.getRegistrationStatus(studEnroll.getStudentId()) == false) {
			 return  new ResponseEntity("Student course registration is pending",HttpStatus.BAD_REQUEST);
		}
		else if(semisterRegistrationInterface.addCourse(studEnroll.getCourseCode(),studEnroll.getCourseName(),studEnroll.getStudentId())) {
			return  new ResponseEntity("Student course registration is Sucessfull",HttpStatus.OK);
		}
		return   new ResponseEntity("Something Wrong!!Please Try Again Later",HttpStatus.INTERNAL_SERVER_ERROR);
		}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@GetMapping("/dropCourses")
	public ResponseEntity dropCources(@RequestBody int studentId,String courseCode) throws SQLException, CourseNotFoundException {
		List<Course> registeredCourseList = semisterRegistrationInterface.viewRegisteredCourses(studentId);
		
		if(semisterRegistrationInterface.getRegistrationStatus(studentId) == false) {
			return new ResponseEntity("Student course registration is pending for ID " + studentId, HttpStatus.NOT_FOUND);
		}
		else if(semisterRegistrationInterface.dropCourse(studentId, courseCode, registeredCourseList)) {
			return new ResponseEntity("Course Drop is Successfull for "+studentId, HttpStatus.OK);
		}
		return new ResponseEntity("Something Wrong!!Please Try Again Later",HttpStatus.BAD_REQUEST);
		
	}
	
	@RequestMapping("/viewAvailableCourses/{studentId}")
	public List<Course> viewCourses(@PathVariable int studentId) throws SQLException{
		return semisterRegistrationInterface.viewCourses(studentId);
	}
	
	@RequestMapping("/viewRegisteredCourses/{studentId}")
	public List<Course> viewRegisteredCourses(@PathVariable int studentId) throws SQLException{
		return semisterRegistrationInterface.viewRegisteredCourses(studentId);
	}
	
	@RequestMapping("/viewGradeCard/{studentId}")
	public List<Grade> viewGradeCourses(@PathVariable int studentId) throws SQLException{
		return semisterRegistrationInterface.viewGradeCard(studentId);
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping("/calculateFee/{studentId}")
	public ResponseEntity calculateFee(@PathVariable int studentId)throws SQLException{
		if(!semisterRegistrationInterface.getRegistrationStatus(studentId)) {
			return new ResponseEntity("Something Went Wrong", HttpStatus.BAD_REQUEST);
		}
			double fee = semisterRegistrationInterface.calculateFee(studentId);
			return new ResponseEntity("Semister Fee for  "+studentId+" is "+fee, HttpStatus.OK);
	}
	
	@RequestMapping("/getRegistrationStatus/{studentId}")
	public boolean getRegistrationStatus(@PathVariable int studentId) throws SQLException{
		return semisterRegistrationInterface.getRegistrationStatus(studentId);
	}
	
	
	
}
