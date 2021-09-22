package com.lt.restcontroller;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import javax.ws.rs.core.MediaType;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.lt.bean.Course;
import com.lt.bean.Grade;
import com.lt.bean.StudentsEnrolled;
import com.lt.business.ProfessorImplService;
import com.lt.exception.GradeNotAddedException;

@RestController
@RequestMapping("/Professor")
public class ProfessorRestApi {
	
	ProfessorImplService professorImplService = new ProfessorImplService();
	
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(produces = MediaType.APPLICATION_JSON, method = RequestMethod.POST, value ="/addGrade")
	public ResponseEntity addGrade(@RequestBody Grade grade)
			throws GradeNotAddedException{
		if(professorImplService.addGrade(grade.getStudentId(), grade.getCourseCode(), grade.getCourseName(), grade.getGrade())) {
			return new ResponseEntity("Grade added successfully for student "+grade.getStudentId(),HttpStatus.OK);
		}
		return new ResponseEntity("Something went wrong..Please try again... ",HttpStatus.BAD_REQUEST);
		
	}
	@RequestMapping(consumes = MediaType.APPLICATION_JSON, method = RequestMethod.GET, value = "/viewEnrolledStudents")
	public List<StudentsEnrolled> viewEnrolledStudents(@RequestBody Map<String,String> json) throws SQLException{
		return professorImplService.viewEnrolledStudents(json.get("profId"));
	}
  
   @RequestMapping(consumes = MediaType.APPLICATION_JSON, method = RequestMethod.GET, value = "/viewRegisteredCourses")
   public List<Course> viewRegisteredCourses(@RequestBody Map<String,String> json) throws SQLException{
	   return professorImplService.viewRegisteredCourses(json.get("profId"));
   }
   @PostMapping
   public String getProfessorById(@PathVariable String profId) {
	   return professorImplService.getProfessorById(profId);
   }
   
}
