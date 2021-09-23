package com.lt.restcontroller;

import java.util.List;
import java.util.Map;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.log4j.Logger;
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
import com.lt.business.AdminImplService;
import com.lt.dao.AdminDaoImpl;
import com.lt.exception.CourseFoundException;
import com.lt.exception.CourseNotFoundException;
import com.lt.exception.ProfessorNotAddedException;
import com.lt.exception.StudentNotFoundException;
import com.lt.exception.UserNotFoundException;

@RestController
@RequestMapping("/admin")
@CrossOrigin //This Annotation will enable all the request which is coming from various cross platform browser
public class AdminRestApi {

	AdminImplService admin = new AdminImplService();
	AdminDaoImpl adminimpl = AdminDaoImpl.getInstance();
	private static Logger logger = Logger.getLogger(AdminRestApi.class);

	@RequestMapping(produces = MediaType.APPLICATION_JSON, method = RequestMethod.POST, value = "/addCourse")
	@ResponseBody
	public ResponseEntity<Object> addCourse(@RequestBody Course course) throws CourseFoundException {

		// We need to call the service layer over here and set all the values
		List<Course> courseList = adminimpl.viewCourses();

		Course course1 = new Course(course.getCourseCode(), course.getCourseName(), null, course.getSeats());
		
			if(admin.addCourse(courseList, course1, course.getFee()))
				return new ResponseEntity("Course " + course.getCourseCode() + " added successfully!!!",HttpStatus.OK);
			else
				throw new CourseFoundException(course.getCourseCode());
	}

	@RequestMapping(produces = MediaType.APPLICATION_JSON, method = RequestMethod.GET, value = "/viewCoursesInCatalogue")
	public @ResponseBody List<Course> viewCoursesInCatalogue() {

		List<Course> courseList = admin.viewCourses();
		if (courseList.size() == 0) {
			return courseList;
		}
		return courseList;
	}

	@RequestMapping(produces = MediaType.APPLICATION_JSON, method = RequestMethod.DELETE, value = "/deleteCourse")
	public ResponseEntity<Object> deleteCourse(@RequestBody String courseCode) throws CourseNotFoundException {
		List<Course> courseList = null;
		
			if(admin.deleteCourse(courseCode, courseList)) {
				return new ResponseEntity("Course "+courseCode+" deleted successfully!",HttpStatus.OK);
			}else {
				throw new CourseNotFoundException(courseCode);
			}
	}

	@RequestMapping(produces = MediaType.APPLICATION_JSON, method = RequestMethod.POST, value = "/addProfessor")
	@ResponseBody
	public ResponseEntity<Object> addProfessor(@RequestBody Professor professor) throws ProfessorNotAddedException {

		if(admin.addProfessor(professor)) {
			return new ResponseEntity("Professor " + professor.getName() + " added successfully!",HttpStatus.OK);
		}else {
			throw new ProfessorNotAddedException(professor.getName());
		}
	}

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
	
	@RequestMapping(produces = MediaType.APPLICATION_JSON, method = RequestMethod.GET, value = "/viewPendingAddmission")
	@ResponseBody
	public List<Student> viewPendingAddmission() {
		return admin.viewPendingAdmissions();
		
	}
}