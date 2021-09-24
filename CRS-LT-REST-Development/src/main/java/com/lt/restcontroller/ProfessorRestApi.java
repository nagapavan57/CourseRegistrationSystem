package com.lt.restcontroller;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import javax.ws.rs.core.MediaType;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.lt.bean.Course;
import com.lt.bean.Grade;
import com.lt.bean.StudentsEnrolled;
import com.lt.business.ProfessorInterface;
import com.lt.exception.GradeNotAddedException;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/Professor")
@CrossOrigin //This Annotation will enable all the request which is coming from various cross platform browser
public class ProfessorRestApi {
	
	@Autowired
	ProfessorInterface professorImplService;
	private static Logger logger = Logger.getLogger(AdminRestApi.class);
	
	
	@ApiOperation(value = "Add Grade to Student for Particular Course", response = Iterable.class, tags = "addGrade")
	@ApiResponses(value = { 
	            @ApiResponse(code = 200, message = "Success|OK"),
	            @ApiResponse(code = 401, message = "Not Authorized!"), 
	            @ApiResponse(code = 403, message = "Forbidden!!!"),
	            @ApiResponse(code = 404, message = "Not Found!!!") })
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(produces = MediaType.APPLICATION_JSON, method = RequestMethod.POST, value ="/addGrade")
	public ResponseEntity addGrade(@RequestBody Grade grade)
			throws GradeNotAddedException{
		if(professorImplService.addGrade(grade.getStudentId(), grade.getCourseCode(), grade.getCourseName(), grade.getGrade())) {
			return new ResponseEntity("Grade added successfully for student "+grade.getStudentId(),HttpStatus.OK);
		}else {
			throw new GradeNotAddedException(grade.getStudentId());
		}
		
	}
	
	
	@ApiOperation(value = "Get list of Students Enrolled for the Particular Professor Teaching Course", response = Iterable.class, tags = "getStudents")
	@ApiResponses(value = { 
	            @ApiResponse(code = 200, message = "Success|OK"),
	            @ApiResponse(code = 401, message = "not authorized!"), 
	            @ApiResponse(code = 403, message = "forbidden!!!"),
	            @ApiResponse(code = 404, message = "not found!!!") })
	@RequestMapping(consumes = MediaType.APPLICATION_JSON, method = RequestMethod.GET, value = "/viewEnrolledStudents")
	public List<StudentsEnrolled> viewEnrolledStudents(@RequestBody Map<String,String> json) throws SQLException{
		return professorImplService.viewEnrolledStudents(json.get("profId"));
	}
  
	
	@ApiOperation(value = "Get List Of Courses which he registered to teach", response = Iterable.class, tags = "addCourse")
	@ApiResponses(value = { 
	            @ApiResponse(code = 200, message = "Success|OK"),
	            @ApiResponse(code = 401, message = "Not Authorized!"), 
	            @ApiResponse(code = 403, message = "Forbidden!!!"),
	            @ApiResponse(code = 404, message = "Not Found!!!") })
    @RequestMapping(consumes = MediaType.APPLICATION_JSON, method = RequestMethod.GET, value = "/viewRegisteredCourses")
    public List<Course> viewRegisteredCourses(@RequestBody Map<String,String> json) throws SQLException{
	   return professorImplService.viewRegisteredCourses(json.get("profId"));
    }
    @PostMapping
    public String getProfessorById(@PathVariable String profId) {
	   return professorImplService.getProfessorById(profId);
    }
   
}
