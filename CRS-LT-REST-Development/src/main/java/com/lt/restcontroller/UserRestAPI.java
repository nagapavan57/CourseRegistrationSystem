package com.lt.restcontroller;

import java.time.LocalDateTime;
import java.util.Map;

import javax.ws.rs.core.MediaType;

import org.apache.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
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
@CrossOrigin /*This Annotation will enable all the request which is coming from various cross platform browser*/
public class UserRestAPI {

	private static Logger logger = Logger.getLogger(AdminRestApi.class);
	UserImplService userInterface = new UserImplService();
	StudentImplService studInterfce = new StudentImplService();
	
	@RequestMapping(produces = MediaType.APPLICATION_JSON, method = RequestMethod.POST, value = "/login")
	@ResponseBody
	public ResponseEntity<?> verifyCredentials(@RequestBody User user) throws UserNotFoundException{
		LocalDateTime localDateTime = LocalDateTime.now();
			boolean loggedin = userInterface.verifyCredentials(user.getUserId(), user.getPassword());
			if (loggedin) {
				String role = userInterface.getRoleById(user.getUserId());
				Role userRole = Role.stringToName(role);
				
				return new ResponseEntity("Login successful "+user.getUserId()+"!!@"+localDateTime, HttpStatus.NOT_FOUND);
			} else {
				
				throw new UserNotFoundException(user.getUserId());
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
	
	@RequestMapping(method = RequestMethod.POST, value = "/studentRegistration")
	@ResponseBody
	public  String register(@RequestBody Student student) throws StudentNotRegisteredException{

		if(studInterfce.register(student.getUserId(),student.getPassword(),student.getName(),student.getEmailId(),
					student.getBranchName(),student.getAddress())!=0) {
			return "Registration Successful for "+student.getUserId();
		}else {
			
			throw new StudentNotRegisteredException(student.getName());
		}
		
	}

}
