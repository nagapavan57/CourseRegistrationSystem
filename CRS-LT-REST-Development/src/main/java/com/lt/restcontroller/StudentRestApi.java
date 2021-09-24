package com.lt.restcontroller;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import javax.ws.rs.Consumes;
import javax.ws.rs.core.MediaType;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.lt.bean.Course;
import com.lt.bean.Grade;
import com.lt.bean.StudentsEnrolled;
import com.lt.business.NotificationImplService;
import com.lt.business.NotificationInterface;
import com.lt.business.PaymentImplService;
import com.lt.business.PaymentInterface;
import com.lt.business.SemisterRegistrationImplService;
import com.lt.business.SemisterRegistrationInterface;
import com.lt.exception.CourseLimitExceedException;
import com.lt.exception.CourseNotFoundException;
import com.lt.exception.SeatNotAvailableException;


@RestController
@RequestMapping("/Student")
@CrossOrigin //This Annotation will enable all the request which is coming from various cross platform browser
public class StudentRestApi {

	private static Logger logger = Logger.getLogger(AdminRestApi.class);
	
	@Autowired
	SemisterRegistrationInterface semisterRegistrationInterface;
	@Autowired
	PaymentInterface payment;
	@Autowired
	NotificationInterface notify;
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Consumes(MediaType.APPLICATION_JSON)
	@RequestMapping(method = RequestMethod.POST, value = "/registerCourse")
	public ResponseEntity registerCourse(@RequestBody StudentsEnrolled studEnroll) throws CourseNotFoundException, SQLException, CourseLimitExceedException, SeatNotAvailableException {
		
		if(semisterRegistrationInterface.getRegistrationStatus(studEnroll.getStudentId()) == false) {
			 return  new ResponseEntity("Student course registration is pending",HttpStatus.BAD_REQUEST);
		}
		else if(semisterRegistrationInterface.addCourse(studEnroll.getCourseCode(),studEnroll.getCourseName(),studEnroll.getStudentId())) {
			return  new ResponseEntity("Student course registration is Sucessfull",HttpStatus.OK);
		}else {
			throw new CourseNotFoundException(studEnroll.getCourseCode());
		}
		
		}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(method = RequestMethod.DELETE, value = "/dropCourse")
	public ResponseEntity dropCources(@RequestBody Map<String, String> json) throws SQLException, CourseNotFoundException {
		List<Course> registeredCourseList = semisterRegistrationInterface.viewRegisteredCourses(Integer.parseInt(json.get("studentId")));
		
		if(semisterRegistrationInterface.getRegistrationStatus(Integer.parseInt(json.get("studentId"))) == false) {
			return new ResponseEntity("Student course registration is pending for ID " + json.get("studentId"), HttpStatus.NOT_FOUND);
		}
		else if(semisterRegistrationInterface.dropCourse(Integer.parseInt(json.get("studentId")), json.get("courseCode"), registeredCourseList)) {
			return new ResponseEntity("Course Drop is Successfull for "+json.get("studentId"), HttpStatus.OK);
		}else {
			throw new CourseNotFoundException(json.get("courseCode"));
		}
		
	}
	
	@RequestMapping(produces = MediaType.APPLICATION_JSON, method = RequestMethod.GET, value ="/viewAvailableCourses")
	public List<Course> viewCourses(@RequestBody int studentId) throws SQLException{
		return semisterRegistrationInterface.viewCourses(studentId);
	}
	
	@RequestMapping(produces = MediaType.APPLICATION_JSON, method = RequestMethod.GET, value ="/viewRegisteredCourses")
	public List<Course> viewRegisteredCourses(@RequestBody int studentId) throws SQLException{
		return semisterRegistrationInterface.viewRegisteredCourses(studentId);
	}
	
	@RequestMapping(produces = MediaType.APPLICATION_JSON, method = RequestMethod.GET, value ="/viewGradeCard")
	public List<Grade> viewGradeCourses(@RequestBody int studentId) throws SQLException{
		return semisterRegistrationInterface.viewGradeCard(studentId);
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(produces = MediaType.APPLICATION_JSON, method = RequestMethod.GET, value ="/calculateFee/{studentId}")
	public ResponseEntity calculateFee(@PathVariable int studentId)throws SQLException{
		if(!semisterRegistrationInterface.getRegistrationStatus(studentId)) {
			return new ResponseEntity("Something Went Wrong", HttpStatus.BAD_REQUEST);
		}
			double fee = semisterRegistrationInterface.calculateFee(studentId);
			return new ResponseEntity("Semister Fee for  "+studentId+" is "+fee, HttpStatus.OK);
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(produces = MediaType.APPLICATION_JSON, method = RequestMethod.POST, value ="/payFee")
	public ResponseEntity payFee(@RequestBody Map<String, String> json)throws SQLException{
			int studentId=Integer.parseInt(json.get("studentId"));
			if(!semisterRegistrationInterface.getRegistrationStatus(studentId)) {
				return new ResponseEntity("Please register First!!!!", HttpStatus.BAD_REQUEST);
			}
			double fee = semisterRegistrationInterface.calculateFee(studentId);
			String notificationMsg="";
			int transactionId = payment.makePayment(studentId, json.get("mode"), Double.toString(fee),json.get("cardNo"),
					json.get("expiry"),json.get("cvv"));
			 if(transactionId > 0){
				 notificationMsg="Payment Succesfull with Amount of"+fee+"with Transaction Id of"+transactionId; 
			 }
			 notify.sendNotification(studentId, transactionId, notificationMsg);
			return new ResponseEntity("Semister Fee for  "+studentId+" is "+fee+" Paid Sucessfully", HttpStatus.OK);
	}
	
	@RequestMapping(produces = MediaType.APPLICATION_JSON, method = RequestMethod.GET, value ="/getRegistrationStatus")
	public boolean getRegistrationStatus(@RequestBody int studentId) throws SQLException{
		return semisterRegistrationInterface.getRegistrationStatus(studentId);
	}
	
	
	
}
