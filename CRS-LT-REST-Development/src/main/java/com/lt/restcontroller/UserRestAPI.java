package com.lt.restcontroller;

import java.time.LocalDateTime;
import java.util.Map;

import javax.ws.rs.core.MediaType;

import org.springframework.web.bind.annotation.ExceptionHandler;
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
		LocalDateTime localDateTime = LocalDateTime.now();
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
				
				return "Login successful "+user.getUserId()+"!!@"+localDateTime;
			} else {
				
				return "Invalid credentials!";
			}
		} catch (UserNotFoundException e) {
			
			return e.getMessage(user.getUserId());
		}

	}
	
	@RequestMapping(method = RequestMethod.PUT, value = "/updatePassword")
	@ResponseBody
	public  String updatePassword(@RequestBody Map<String,String> json){
		LocalDateTime localDateTime = LocalDateTime.now();
		if(userInterface.updatePassword(json.get("userId"),json.get("newPassword")))
		{
			
			return "Password updated successfully for User "+json.get("userId")+"@"+localDateTime;
		}
		else
		{
			
			return "Something went wrong, please try again!";
		}

	}
	
	@ExceptionHandler(StudentNotRegisteredException.class)
	@RequestMapping(method = RequestMethod.POST, value = "/studentRegistration")
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
