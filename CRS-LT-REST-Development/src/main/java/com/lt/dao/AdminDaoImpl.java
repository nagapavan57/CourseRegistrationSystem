package com.lt.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.lt.bean.Course;
import com.lt.bean.Professor;
import com.lt.bean.Student;
import com.lt.constants.SQLConstants;
import com.lt.exception.CourseFoundException;
import com.lt.exception.CourseNotFoundException;
import com.lt.exception.StudentNotFoundException;
import com.lt.exception.UserIdAlreadyExistException;
import com.lt.exception.UserNotFoundException;
import com.lt.utils.DBUtils;

public class AdminDaoImpl implements AdminDaoInterface{

 private static Logger logger = Logger.getLogger(AdminDaoImpl.class);
 private Connection con=DBUtils.getConnection();
 private PreparedStatement stmt=null;
 
	private static volatile AdminDaoImpl instance = null;

	private AdminDaoImpl() {

	}

	public static AdminDaoImpl getInstance() {
		if (instance == null) {
			// This is a synchronized block, when multiple threads will access this instance
			synchronized (AdminDaoImpl.class) {
				instance = new AdminDaoImpl();
			}
		}
		return instance;
	}

	@Override
	public boolean addCourse(Course course,String fee) throws CourseFoundException{
		try {
		
			
			stmt = con.prepareStatement(SQLConstants.ADD_COURSES_TO_CATLOG,Statement.RETURN_GENERATED_KEYS);
			stmt.setString(1,course.getCourseCode());
			stmt.setInt(2,1);
			int row=stmt.executeUpdate();
			if(row!=0){
				stmt = con.prepareStatement(SQLConstants.ADD_COURSES);
				stmt.setString(1,course.getCourseCode());
				stmt.setString(2,course.getCourseName());
				stmt.setString(3,course.getProfid());
				stmt.setString(4, course.getSeats());
				stmt.setString(5, fee);
				stmt.setInt(6, 1);
				int update_status=stmt.executeUpdate();
				//logger.info(update_status+" Course Added");
				if(update_status==0){
					logger.error("Course with coursecode "+course.getCourseCode()+" Not Added!!!");
					return false;
					
				}else{
					logger.info("Course with coursecode "+course.getCourseCode()+" Added Successfully!!!");
					return true;
				}
			}
		}catch (SQLException e) {
				logger.error(e.getMessage());
				throw new CourseFoundException(course.getCourseCode());
		}
		return false;
	}

	@Override
	public boolean deleteCourse(String courseCode) throws CourseNotFoundException{
		
		try {	
			stmt=con.prepareStatement(SQLConstants.DEL_COURSES_IN_CATALOG);
			stmt.setString(1, courseCode);
			int catalog_del_Status=stmt.executeUpdate();
			if(catalog_del_Status>0){
				stmt=con.prepareStatement(SQLConstants.DEL_COURSES);
				stmt.setString(1, courseCode);
				int del_Status=stmt.executeUpdate();
				if(del_Status==0){
					logger.error("Course with coursecode "+courseCode+"Not Deleted!!!!");
					return true;
				}else{
					logger.info("Course with coursecode "+courseCode+" Deleted Succesfully");
					return false;
				}
			}else{
				throw new CourseNotFoundException(courseCode);
			}
		}
		catch (SQLException e) {
			logger.error(e.getMessage());
			throw new CourseNotFoundException(courseCode);
		}
	}

	@Override
	public boolean approveStudent(int studentId) throws StudentNotFoundException {
		try {
			stmt=con.prepareStatement(SQLConstants.APPROVE_STUDENT);
			stmt.setInt(1, 1);
			stmt.setInt(2, studentId);
			int apprv_status=stmt.executeUpdate();
			if(apprv_status==0){
				logger.error("Approval unsuccessfull for Student id "+studentId);
			}else {
				logger.info("Student With Id "+studentId+" Approved Successfully");
				return true;
			}
		}
		catch(SQLException ex){
			logger.error(ex.getMessage());
			throw new StudentNotFoundException(studentId);
		}
		return false;
	}

	@Override
	public boolean assignCourse(String courseCode, String professorId)
			throws CourseNotFoundException,UserNotFoundException{
		
		try {
			stmt=con.prepareStatement(SQLConstants.ASSIGN_COURSES);
			stmt.setString(1,professorId);
			stmt.setString(2,courseCode);
			int row = stmt.executeUpdate();
			if(row == 0) {
				logger.error("Course code "+courseCode+" Not Found!!");
				return false;
			}else{
				logger.info("Course with Code "+courseCode+" Assigned to Professor with id "+professorId+" Succesfully!!!");
				return true;
			}
		}
		catch (SQLException e) {
			logger.error(e.getMessage());
			throw new UserNotFoundException(professorId);
		}
	}

	@Override
	public boolean addProfessor(Professor professor) throws UserIdAlreadyExistException {
		try {
			int status=0;
			stmt = con.prepareStatement(SQLConstants.GET_ROLENAME);
			stmt.setInt(1, 2);
			ResultSet rs = stmt.executeQuery();
			String role = "";
			if(rs.next()){
				role=rs.getString(1);
			}
				
			stmt = con.prepareStatement(SQLConstants.ADD_USER_QUERY);
			stmt.setString(1, professor.getUserId());
			stmt.setString(2, professor.getPassword());
			stmt.setString(3,role);
			int row = stmt.executeUpdate();
			if(row!=0){
				stmt=con.prepareStatement(SQLConstants.ADD_PROFESSOR);
				stmt.setString(1, professor.getDepartment());
				stmt.setString(2, professor.getName());
				stmt.setString(3, professor.getSubject());
				stmt.setInt(4, 2);
				 status=stmt.executeUpdate();
			}
			if(status==0){
				logger.error("Adding Professor Failed for Professor "+professor.getName());
				return false;
			}else{
				logger.info("Professor "+professor.getName()+" Added Successfully");
				return true;
			}
		}
		catch (SQLException e) {
			logger.error(e.getMessage());
			throw new UserIdAlreadyExistException(professor.getUserId());
		}
	}

	@Override
	public List<Course> viewCourses(){
		List<Course> courses=new ArrayList<Course>();
		try {
		stmt=con.prepareStatement(SQLConstants.VIEW_COURSES);
		/*stmt.setInt(1, catalogId);*/
		ResultSet res_set=stmt.executeQuery();
		while(res_set.next()) {
			Course course= new Course();
			course.setCourseCode(res_set.getString(1));
			course.setCourseName(res_set.getString(2));
			course.setProfid(res_set.getString(3));
			course.setSeats(res_set.getString(4));
			courses.add(course);
			
		}
		logger.info(courses.size() + " courses in catalogId: 1.");
		}
		catch (SQLException e) {
			logger.error(e.getMessage());
		}
		
		
		return courses;
	}

	@Override
	public List<Student> viewPendingAdmissions() {
		PreparedStatement stmnt = null;
		List<Student> userList = new ArrayList<Student>();
		try {
			
			stmnt = con.prepareStatement(SQLConstants.VIEW_PENDING_ADMISSION_QUERY);
			ResultSet resultSet = stmnt.executeQuery();

			while(resultSet.next()) {
				
				Student user = new Student();
				user.setStudentId(resultSet.getInt(1));
				user.setName(resultSet.getString(2));
				user.setBranchName(resultSet.getString(3));
				user.setEmailId(resultSet.getString(4));
				//user.setGender(resultSet.getString(5));
				user.setAddress(resultSet.getString(5));
				userList.add(user);
				
			}
			if(userList.size()>0)
				logger.info("Student's are Pending for Approval - "+userList.size());
			else
				logger.info("No Students are Pending for Approval!!!");
			
		}catch(SQLException se) {
			logger.error(se.getMessage());
		}
		
		return userList;
	}
	
	
	@Override
	public List<Professor> viewProfessors() {
		
		PreparedStatement statement = null;
		List<Professor> professorList = new ArrayList<>();
		try {
			
			statement = con.prepareStatement(SQLConstants.VIEW_PROFESSOR_QUERY);
			ResultSet resultSet = statement.executeQuery();
			
			while(resultSet.next()) {
				
				Professor professor = new Professor();
				professor.setUserId(resultSet.getString(1));
				professor.setName(resultSet.getString(2));
				professor.setSubject(resultSet.getString(3));
				professorList.add(professor);
				
			}
			
			logger.info(professorList.size() + " professors in the institute.");
			
		}catch(SQLException se) {
			
			logger.error(se.getMessage());
			
		}
		return professorList;
	}
}

	
