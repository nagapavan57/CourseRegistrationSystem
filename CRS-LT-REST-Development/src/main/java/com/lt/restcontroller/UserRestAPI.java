package com.lt.restcontroller;

import java.sql.SQLException;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.Response;
import javax.xml.bind.ValidationException;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.lt.bean.Student;
import com.lt.bean.User;
import com.lt.business.StudentImplService;
import com.lt.business.UserImplService;
import com.lt.constants.Role;
import com.lt.exception.StudentNotRegisteredException;
import com.lt.exception.UserNotFoundException;

@RestController
@RequestMapping("/User")
public class UserRestAPI {

	UserImplService userInterface = new UserImplService();
	StudentImplService studInterfce = new StudentImplService();

	@RequestMapping(produces = MediaType.APPLICATION_JSON, method = RequestMethod.POST, value = "/login")
	@ResponseBody
	public String verifyCredentials(@RequestBody User user){

		try {
			boolean loggedin = userInterface.verifyCredentials(user.getUserId(), user.getPassword());
			if (loggedin) {
				String role = userInterface.getRoleById(user.getUserId());
				Role userRole = Role.stringToName(role);
				/*
				 * switch (userRole) {
				 * 
				 * case STUDENT: int studentId = studInterfce.getStudentId(user.getUserId());
				 * boolean isApproved = studInterfce.isApproved(studentId); if (!isApproved) {
				 * return Response.status(200).entity("Login unsuccessful! Student " +
				 * user.getUserId() + " has not been approved by the admin!").build(); } break;
				 * }
				 */
				
				return "Login successful "+user.getUserId()+"!!";
			} else {
				
				return "Invalid credentials!";
			}
		} catch (UserNotFoundException e) {
			
			return e.getMessage(user.getUserId());
		}

	}
	
	@RequestMapping(method = RequestMethod.PUT, value = "/updatePassword")
	@ResponseBody
	public  String updatePassword(@RequestBody User user){
		
		if(userInterface.updatePassword(user.getUserId(), user.getPassword()))
		{
			
			return "Password updated successfully for User "+user.getUserId();
		}
		else
		{
			
			return "Something went wrong, please try again!";
		}

	}
	
	@ExceptionHandler(StudentNotRegisteredException.class)
	@RequestMapping(method = RequestMethod.PUT, value = "/studentRegistration")
	@ResponseBody
	public  String register(@RequestBody Student student){

		try
		{
			studInterfce.register(student.getUserId(),student.getPassword(),student.getName(),student.getEmailId(),
					student.getBranchName(),student.getAddress());
			
		}
		catch(StudentNotRegisteredException ex)
		{
			
			return ex.getMessage(student.getName()); 
		}
		
		
		return "Registration Successful for "+student.getUserId();
	}

}
