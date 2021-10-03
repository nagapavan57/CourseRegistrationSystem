package com.lt.restcontroller;

import java.time.LocalDateTime;
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

import com.lt.bean.Student;
import com.lt.bean.User;
import com.lt.business.StudentInterface;
import com.lt.business.UserInterface;
import com.lt.constants.Role;
import com.lt.exception.StudentNotRegisteredException;
import com.lt.exception.UserNotFoundException;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

/**
 * 
 * @author Nagapavan
 * API for Common User Operations
 *
 */
@RestController
@RequestMapping("/User")
@CrossOrigin /*This Annotation will enable all the request which is coming from various cross platform browser*/
public class UserRestAPI {

	private static Logger logger = Logger.getLogger(AdminRestApi.class);
	
	@Autowired
	UserInterface userInterface;
	@Autowired
	StudentInterface studInterfce;
	
	/**
	 * 
	 * @param user Object which contain login info
	 * @return Response Entity
	 * @throws UserNotFoundException
	 */
	@ApiOperation(value = "It will Verify Given userId and Password to login", response = Iterable.class, tags = "login")
	@ApiResponses(value = { 
	            @ApiResponse(code = 200, message = "Success|OK"),
	            @ApiResponse(code = 401, message = "Not Authorized!"), 
	            @ApiResponse(code = 403, message = "Forbidden!!!"),
	            @ApiResponse(code = 404, message = "Not Found!!!") })
	@RequestMapping(produces = MediaType.APPLICATION_JSON, method = RequestMethod.POST, value = "/login")
	@ResponseBody
	public ResponseEntity<?> verifyCredentials(@RequestBody User user) throws UserNotFoundException{
		LocalDateTime localDateTime = LocalDateTime.now();
			boolean loggedin = userInterface.verifyCredentials(user.getUserId(), user.getPassword());
			if (loggedin) {
				String role = userInterface.getRoleById(user.getUserId());
				Role userRole = Role.stringToName(role);
				
				logger.info("Login successful "+user.getUserId()+"!!@"+localDateTime);
				return new ResponseEntity(userRole.toString(), HttpStatus.OK);
			} else {
				
				logger.info("Something Went Wrong!!!");
				throw new UserNotFoundException(user.getUserId());
			}

	}
	
	/**
	 * 
	 * @param json object which contain userId and newPassword to change
	 * @return
	 */
	@ApiOperation(value = "Update the Password for User", response = Iterable.class, tags = "updatePassword")
	@ApiResponses(value = { 
	            @ApiResponse(code = 200, message = "Success|OK"),
	            @ApiResponse(code = 401, message = "Not Authorized!"), 
	            @ApiResponse(code = 403, message = "Forbidden!!!"),
	            @ApiResponse(code = 404, message = "Not Found!!!") })
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
	
	/**
	 * 
	 * @param student Student Object Which Contain Signup Info of Student
	 * @return ResponseEntity
	 * @throws StudentNotRegisteredException
	 */
	@ApiOperation(value = "Signup For Student/Register Student", response = Iterable.class, tags = "studentRegistration")
	@ApiResponses(value = { 
	            @ApiResponse(code = 200, message = "Success|OK"),
	            @ApiResponse(code = 401, message = "Not Authorized!"), 
	            @ApiResponse(code = 403, message = "Forbidden!!!"),
	            @ApiResponse(code = 404, message = "Not Found!!!") })
	@RequestMapping(method = RequestMethod.POST, value = "/studentRegistration")
	@ResponseBody
	public  ResponseEntity<?> register(@RequestBody Student student) throws StudentNotRegisteredException{

		if(studInterfce.register(student.getUserId(),student.getPassword(),student.getName(),student.getEmailId(),
					student.getBranchName(),student.getAddress())!=0) {
			
			logger.info("Registration Successful for "+student.getUserId());
			return new ResponseEntity("Registration Successful for "+student.getUserId(),HttpStatus.OK) ;
		}else {
			
			throw new StudentNotRegisteredException(student.getName());
		}
		
	}

}
