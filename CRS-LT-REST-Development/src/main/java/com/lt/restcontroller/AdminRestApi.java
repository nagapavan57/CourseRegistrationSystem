package com.lt.restcontroller;

import java.util.List;
import java.util.Map;

import javax.ws.rs.core.MediaType;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.lt.bean.Course;
import com.lt.bean.Professor;
import com.lt.bean.Student;
import com.lt.business.AdminInterface;
import com.lt.exception.CourseFoundException;
import com.lt.exception.CourseNotFoundException;
import com.lt.exception.ProfessorNotAddedException;
import com.lt.exception.StudentNotFoundException;
import com.lt.exception.UserNotFoundException;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/admin")
@CrossOrigin //This Annotation will enable all the request which is coming from various cross platform browser
public class AdminRestApi {

	@Autowired
	AdminInterface admin;
	
	private static Logger logger = Logger.getLogger(AdminRestApi.class);
	
	@ApiOperation(value = "Add Course into CourseCatalog ", response = Iterable.class, tags = "addCourse")
	@ApiResponses(value = { 
	            @ApiResponse(code = 200, message = "Success|OK"),
	            @ApiResponse(code = 401, message = "Not Authorized!"), 
	            @ApiResponse(code = 403, message = "Forbidden!!!"),
	            @ApiResponse(code = 404, message = "Not Found!!!") })
	@RequestMapping(produces = MediaType.APPLICATION_JSON, method = RequestMethod.POST, value = "/addCourse")
	@ResponseBody
	public ResponseEntity<Object> addCourse(@RequestBody Course course) throws CourseFoundException {

		// We need to call the service layer over here and set all the values
		List<Course> courseList = admin.viewCourses();

		Course course1 = new Course(course.getCourseCode(), course.getCourseName(), null, course.getSeats());
		
			if(admin.addCourse(courseList, course1, course.getFee()))
				return new ResponseEntity("Course " + course.getCourseCode() + " added successfully!!!",HttpStatus.OK);
			else
				throw new CourseFoundException(course.getCourseCode());
	}
	
	
	@ApiOperation(value = "Get the List of Courses from  CourseCatalog ", response = Iterable.class, tags = "viewCoursesInCatalogue")
	@ApiResponses(value = { 
	            @ApiResponse(code = 200, message = "Success|OK"),
	            @ApiResponse(code = 401, message = "Not Authorized!"), 
	            @ApiResponse(code = 403, message = "Forbidden!!!"),
	            @ApiResponse(code = 404, message = "Not Found!!!") })
	@RequestMapping(produces = MediaType.APPLICATION_JSON, method = RequestMethod.GET, value = "/viewCoursesInCatalogue")
	public @ResponseBody List<Course> viewCoursesInCatalogue() {

		List<Course> courseList = admin.viewCourses();
		if (courseList.size() == 0) {
			return courseList;
		}
		return courseList;
	}
	
	@ApiOperation(value = "Delete Course from CourseCatalog ", response = Iterable.class, tags = "delete"
			+ "Course")
	@ApiResponses(value = { 
	            @ApiResponse(code = 200, message = "Success|OK"),
	            @ApiResponse(code = 401, message = "Not Authorized!"), 
	            @ApiResponse(code = 403, message = "Forbidden!!!"),
	            @ApiResponse(code = 404, message = "Not Found!!!") })
	@RequestMapping(produces = MediaType.APPLICATION_JSON, method = RequestMethod.DELETE, value = "/deleteCourse")
	public ResponseEntity<Object> deleteCourse(@RequestBody String courseCode) throws CourseNotFoundException {
		List<Course> courseList = null;
		
			if(admin.deleteCourse(courseCode, courseList)) {
				return new ResponseEntity("Course "+courseCode+" deleted successfully!",HttpStatus.OK);
			}else {
				throw new CourseNotFoundException(courseCode);
			}
	}
	
	@ApiOperation(value = "Adding Professor into System", response = Iterable.class, tags = "addProfessor")
	@ApiResponses(value = { 
	            @ApiResponse(code = 200, message = "Success|OK"),
	            @ApiResponse(code = 401, message = "Not Authorized!"), 
	            @ApiResponse(code = 403, message = "Forbidden!!!"),
	            @ApiResponse(code = 404, message = "Not Found!!!") })
	@RequestMapping(produces = MediaType.APPLICATION_JSON, method = RequestMethod.POST, value = "/addProfessor")
	@ResponseBody
	public ResponseEntity<Object> addProfessor(@RequestBody Professor professor) throws ProfessorNotAddedException {

		if(admin.addProfessor(professor)) {
			return new ResponseEntity("Professor " + professor.getName() + " added successfully!",HttpStatus.OK);
		}else {
			throw new ProfessorNotAddedException(professor.getName());
		}
	}

	@ApiOperation(value = "It Will Assign Professor to Particular Course", response = Iterable.class, tags = "assignProfessor")
	@ApiResponses(value = { 
	            @ApiResponse(code = 200, message = "Success|OK"),
	            @ApiResponse(code = 401, message = "Not Authorized!"), 
	            @ApiResponse(code = 403, message = "Forbidden!!!"),
	            @ApiResponse(code = 404, message = "Not Found!!!") })
	@RequestMapping(produces = MediaType.APPLICATION_JSON, method = RequestMethod.PUT, value = "/assignProfessor")
	@ResponseBody
	public ResponseEntity<Object> assignProfessor(@RequestBody Map<String, String> json) throws CourseNotFoundException, UserNotFoundException, CourseFoundException {

		if(admin.assignCourse(json.get("courseCode"), json.get("professorId"))) {
			return new ResponseEntity("courseCode: " + json.get("courseCode") + " assigned to professor: " + json.get("professorId"),
					HttpStatus.OK);
		}else {
			throw new CourseFoundException(json.get("courseCode"));
		} 
		

	}
	
	
	@ApiOperation(value = "It Approves Student Registration", response = Iterable.class, tags = "approveStudent")
	@ApiResponses(value = { 
	            @ApiResponse(code = 200, message = "Success|OK"),
	            @ApiResponse(code = 401, message = "Not Authorized!"), 
	            @ApiResponse(code = 403, message = "Forbidden!!!"),
	            @ApiResponse(code = 404, message = "Not Found!!!") })
	@RequestMapping(produces = MediaType.APPLICATION_JSON, method = RequestMethod.PUT, value = "/approveStudent")
	@ResponseBody
	public ResponseEntity<Object> approveStudent(@RequestBody int studentId) throws StudentNotFoundException {
		List<Student> studentList = admin.viewPendingAdmissions();

		if(admin.approveStudent(studentId, studentList)) {
			return new ResponseEntity("Student " + studentId + " Approved Succesfully!!",HttpStatus.OK);
		}else{
			throw new StudentNotFoundException(studentId);
		}
	}
	
	
	@ApiOperation(value = "Get the List of students whose Admission is Pending for Approval", response = Iterable.class, tags = "viewPendingAddmission")
	@ApiResponses(value = { 
	            @ApiResponse(code = 200, message = "Success|OK"),
	            @ApiResponse(code = 401, message = "Not Authorized!"), 
	            @ApiResponse(code = 403, message = "Forbidden!!!"),
	            @ApiResponse(code = 404, message = "Not Found!!!") })
	@RequestMapping(produces = MediaType.APPLICATION_JSON, method = RequestMethod.GET, value = "/viewPendingAddmission")
	@ResponseBody
	public List<Student> viewPendingAddmission() {
		return admin.viewPendingAdmissions();
		
	}
}