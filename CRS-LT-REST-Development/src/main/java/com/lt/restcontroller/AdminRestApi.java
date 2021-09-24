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

/**
 * 
 * @author Nagapavan
 * API Class For AdminOperations
 *
 */
@RestController
@RequestMapping("/admin")
@CrossOrigin //This Annotation will enable all the request which is coming from various cross platform browser
public class AdminRestApi {

	@Autowired
	AdminInterface admin;
	
	private static Logger logger = Logger.getLogger(AdminRestApi.class);
	
	/**
	 * 
	 * @param course Course object which contains  the Course Info
	 * @return ResponseEntity
	 * @throws CourseFoundException
	 */
	@ApiOperation(value = "Add Course into CourseCatalog ", response = Iterable.class, tags = "addCourse")
	@ApiResponses(value = { 
	            @ApiResponse(code = 200, message = "Success|OK"),
	            @ApiResponse(code = 401, message = "Not Authorized!"), 
	            @ApiResponse(code = 403, message = "Forbidden!!!"),
	            @ApiResponse(code = 404, message = "Not Found!!!") })
	@RequestMapping(produces = MediaType.APPLICATION_JSON, method = RequestMethod.POST, value = "/addCourse")
	@ResponseBody
	public ResponseEntity<Object> addCourse(@RequestBody Course course) throws CourseFoundException {

		List<Course> courseList = admin.viewCourses();

		Course course1 = new Course(course.getCourseCode(), course.getCourseName(), null, course.getSeats());
		
			if(admin.addCourse(courseList, course1, course.getFee())) {
				logger.info("Course " + course.getCourseCode() + " added successfully!!!");
				return new ResponseEntity("Course " + course.getCourseCode() + " added successfully!!!",HttpStatus.OK);
				}
			else {
				throw new CourseFoundException(course.getCourseCode());
				}
	}
	
	/**
	 * 
	 * @return List Of Courses from Catalog
	 */
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
	
	
	/**
	 * 
	 * @param courseCode Coursecode which needs to be deleted
	 * @return ResponseEntity
	 * @throws CourseNotFoundException
	 */
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
				logger.info("Course "+courseCode+" deleted successfully!");
				return new ResponseEntity("Course "+courseCode+" deleted successfully!",HttpStatus.OK);
			}else {
				throw new CourseNotFoundException(courseCode);
			}
	}
	
	
	/**
	 * 
	 * @param professor Professor Object which contain professor Info
	 * @return ResponseEntity
	 * @throws ProfessorNotAddedException
	 */
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
			logger.info("Professor " + professor.getName() + " added successfully!");
			return new ResponseEntity("Professor " + professor.getName() + " added successfully!",HttpStatus.OK);
		}else {
			throw new ProfessorNotAddedException(professor.getName());
		}
	}
	
	
	/**
	 * 
	 * @param json Input from Client with course and professor Info.
	 * @return ResponseEntity
	 * @throws CourseNotFoundException
	 * @throws UserNotFoundException
	 * @throws CourseFoundException
	 */
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
	
	/**
	 * 
	 * @param studentId Id of Student which Approval is Pending
	 * @return ResponseEntity
	 * @throws StudentNotFoundException
	 */
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
	
	
	/**
	 * 
	 * @return List of Students whose Registration is Pending for Approval
	 */
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