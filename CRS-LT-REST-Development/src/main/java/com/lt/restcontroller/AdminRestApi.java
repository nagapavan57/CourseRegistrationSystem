package com.lt.restcontroller;

import java.util.List;
import java.util.Map;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

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
public class AdminRestApi {

	AdminImplService admin = new AdminImplService();
	AdminDaoImpl adminimpl = AdminDaoImpl.getInstance();
	//private static Logger logger = Logger.getLogger(AdminRestApi.class);

	@RequestMapping(produces = MediaType.APPLICATION_JSON, method = RequestMethod.POST, value = "/addCourse")
	@ResponseBody
	public Response addCourse(@RequestBody Course course) {

		// We need to call the service layer over here and set all the values
		List<Course> courseList = adminimpl.viewCourses();

		Course course1 = new Course(course.getCourseCode(), course.getCourseName(), null, course.getSeats());
		try {
			admin.addCourse(courseList, course1, course.getFee());
			return Response.status(200).entity("Course " + course.getCourseCode() + " added successfully!!!").build();
		} catch (CourseFoundException e) {
			return Response.status(409).entity(e.getMessage(course.getCourseCode())).build();
		}
	}

	@RequestMapping(produces = MediaType.APPLICATION_JSON, method = RequestMethod.GET, value = "/viewCoursesInCatalogue")
	public @ResponseBody List<Course> viewCoursesInCatalogue() {

		List<Course> courseList = admin.viewCourses();
		if (courseList.size() == 0) {
			return courseList;
		}

		/*
		 * courseList.forEach(course ->
		 * System.out.println(String.format("%10s | %10s | %10s",
		 * course.getCourseCode(), course.getCourseName(), course.getProfid())));
		 */
		return courseList;
	}

	@RequestMapping(produces = MediaType.APPLICATION_JSON, method = RequestMethod.DELETE, value = "/deleteCourse")
	public Response deleteCourse(@RequestBody String courseCode) {
		List<Course> courseList = null;
		try {
			admin.deleteCourse(courseCode, courseList);
			return Response.status(200).entity("Course "+courseCode+" deleted successfully!").build();

		} catch (CourseNotFoundException e) {
			return Response.status(409).entity(e.getMessage(courseCode)).build();
		}

	}

	@RequestMapping(produces = MediaType.APPLICATION_JSON, method = RequestMethod.POST, value = "/addProfessor")
	@ResponseBody
	public Response addProfessor(@RequestBody Professor professor) {

		try {
			admin.addProfessor(professor);
			return Response.status(200).entity("Professor " + professor.getName() + " added successfully!").build();
		} catch (ProfessorNotAddedException e) {
			return Response.status(409).entity(e.getMessage(professor.getName())).build();

		}
	}

	@RequestMapping(produces = MediaType.APPLICATION_JSON, method = RequestMethod.PUT, value = "/assignProfessor")
	@ResponseBody
	public Response assignProfessor(@RequestBody Map<String, String> json) {

		try {
			admin.assignCourse(json.get("courseCode"), json.get("professorId"));
			return Response.status(201).entity(
					"courseCode: " + json.get("courseCode") + " assigned to professor: " + json.get("professorId"))
					.build();
		} catch (CourseNotFoundException e) {
			return Response.status(409).entity(e.getMessage(json.get("courseCode"))).build();
		} catch (UserNotFoundException e) {
			return Response.status(409).entity(e.getMessage(json.get("professorId"))).build();
		}

	}

	@RequestMapping(produces = MediaType.APPLICATION_JSON, method = RequestMethod.PUT, value = "/approveStudent")
	@ResponseBody
	public Response approveStudent(@RequestBody int studentId) {
		List<Student> studentList = admin.viewPendingAdmissions();

		try {
			admin.approveStudent(studentId, studentList);
			return Response.status(200).entity("Student " + studentId + " Approved Succesfully!!").build();
		} catch (StudentNotFoundException e) {
			return Response.status(409).entity(e.getMessage(studentId)).build();

		}
	}
	
	@RequestMapping(produces = MediaType.APPLICATION_JSON, method = RequestMethod.GET, value = "/viewPendingAddmission")
	@ResponseBody
	public List<Student> viewPendingAddmission() {
		return admin.viewPendingAdmissions();
		
	}
}