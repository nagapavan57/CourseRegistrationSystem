package com.lt.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Global exception class to handle Exception based on the Custom Exceptions
 * 
 * @author Ariz
 */
@ControllerAdvice
@ResponseStatus(HttpStatus.NOT_FOUND)
public class GlobalExceptionHandler {

	/**
	 * Method to handle "UserNotFoundException" custom Exception
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@ExceptionHandler(value = UserNotFoundException.class)
	public ResponseEntity handleException(UserNotFoundException e) {
		return new ResponseEntity(e.getMessage(), HttpStatus.NOT_FOUND);
	}

	/**
	 * Method to handle "CourseNotFoundException" custom Exception
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@ExceptionHandler(value = CourseNotFoundException.class)
	public ResponseEntity handleException(CourseNotFoundException e) {
		return new ResponseEntity(e.getMessage(), HttpStatus.NOT_FOUND);
	}

	/**
	 * Method to handle "CourseFoundException" custom Exception
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@ExceptionHandler(value = CourseFoundException.class)
	public ResponseEntity handleException(CourseFoundException e) {
		return new ResponseEntity(e.getMessage(), HttpStatus.NOT_FOUND);
	}

	/**
	 * Method to handle "CourseNotAssignedToProfessorException" custom Exception
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@ExceptionHandler(value = CourseNotAssignedToProfessorException.class)
	public ResponseEntity handleException(CourseNotAssignedToProfessorException e) {
		return new ResponseEntity(e.getMessage(), HttpStatus.NOT_FOUND);
	}

	/**
	 * Method to handle "CourseLimitExceedException" custom Exception
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@ExceptionHandler(value = CourseLimitExceedException.class)
	public ResponseEntity handleException(CourseLimitExceedException e) {
		return new ResponseEntity(e.getMessage(), HttpStatus.NOT_FOUND);
	}

	/**
	 * Method to handle "GradeNotAddedException" custom Exception
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@ExceptionHandler(value = GradeNotAddedException.class)
	public ResponseEntity handleException(GradeNotAddedException e) {
		return new ResponseEntity(e.getMessage(), HttpStatus.NOT_FOUND);
	}

	/**
	 * Method to handle "ProfessorNotAddedException" custom Exception
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@ExceptionHandler(value = ProfessorNotAddedException.class)
	public ResponseEntity handleException(ProfessorNotAddedException e) {
		return new ResponseEntity(e.getMessage(), HttpStatus.NOT_FOUND);
	}

	/**
	 * Method to handle "ProfessorNotFoundException" custom Exception
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@ExceptionHandler(value = ProfessorNotFoundException.class)
	public ResponseEntity handleException(ProfessorNotFoundException e) {
		return new ResponseEntity(e.getMessage(), HttpStatus.NOT_FOUND);
	}

	/**
	 * Method to handle "SeatNotAvailableException" custom Exception
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@ExceptionHandler(value = SeatNotAvailableException.class)
	public ResponseEntity handleException(SeatNotAvailableException e) {
		return new ResponseEntity(e.getMessage(), HttpStatus.NOT_FOUND);
	}

	/**
	 * Method to handle "StudentNotFoundException" custom Exception
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@ExceptionHandler(value = StudentNotFoundException.class)
	public ResponseEntity handleException(StudentNotFoundException e) {
		return new ResponseEntity(e.getMessage(), HttpStatus.NOT_FOUND);
	}

	/**
	 * Method to handle "StudentNotRegisteredException" custom Exception
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@ExceptionHandler(value = StudentNotRegisteredException.class)
	public ResponseEntity handleException(StudentNotRegisteredException e) {
		return new ResponseEntity(e.getMessage(), HttpStatus.NOT_FOUND);
	}

	/**
	 * Method to handle "UserIdAlreadyExistException" custom Exception
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@ExceptionHandler(value = UserIdAlreadyExistException.class)
	public ResponseEntity handleException(UserIdAlreadyExistException e) {
		return new ResponseEntity(e.getMessage(), HttpStatus.NOT_FOUND);
	}

}
