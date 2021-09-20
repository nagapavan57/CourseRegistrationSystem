package com.lt.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.lt.bean.Course;
import com.lt.bean.Grade;
import com.lt.constants.SQLConstants;
import com.lt.utils.DBUtils;


public class SemisterRegistrationDaoImpl implements SemisterRegistrationDaoInterface{
    
	private static Logger logger = Logger.getLogger(SemisterRegistrationDaoImpl.class);
	
	private static volatile SemisterRegistrationDaoImpl instance=null;
	
	private PreparedStatement stmt = null;
	
	@Override
	public boolean addCourse(String courseCode,String courseName, int studentId) throws SQLException{
		
		Connection conn = DBUtils.getConnection();
		

		try 
		{
			stmt = conn.prepareStatement(SQLConstants.REGISTER_COURSE);
			stmt.setInt(1, studentId);
			stmt.setString(2, courseCode);
			stmt.setString(3,courseName);

			stmt.executeUpdate();
			
			stmt = conn.prepareStatement(SQLConstants.DECREMENT_COURSE_SEATS);
			stmt.setString(1, courseCode);
			stmt.executeUpdate();
			return true;
		}
		catch (SQLException e) 
		{
			logger.error(e.getMessage());
		}
		
		return false;
		
	}
	
	
	/**
	 * Number of registered courses for a student
	 * @param studentId
	 * @return Number of registered courses for a student
	 * @throws SQLException 
	 */
	@Override
	public int numOfRegisteredCourses(int studentId){
		
		Connection conn = DBUtils.getConnection();
		
		int count = 0;
		try {

			stmt = conn.prepareStatement(SQLConstants.NUMBER_OF_REGISTERED_COURSES);
			stmt.setInt(1, studentId);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				count++;
			}
			return count;

		}
		catch (SQLException se) 
		{
			logger.error(se.getMessage());
		}
		
		
		return count;
	}
	@Override
	public boolean seatAvailable(String courseCode) throws SQLException {

		Connection conn = DBUtils.getConnection();
		try 
		{
			stmt = conn.prepareStatement(SQLConstants.GET_SEATS);
			stmt.setString(1, courseCode);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				return (rs.getInt("seats") > 0);
			}

		}
		catch (SQLException e) {
			logger.error(e.getMessage());
		}
		
		return true;
		

	}
	
	@Override
	public boolean isRegistered(String courseCode, int studentId) throws SQLException{
		
		Connection conn = DBUtils.getConnection();
		
		boolean isRegistered = false;
		try
		{
			stmt = conn.prepareStatement(SQLConstants.IS_REGISTERED);
			stmt.setString(1, courseCode);
			stmt.setInt(2, studentId);
			ResultSet rs = stmt.executeQuery();
			
			while(rs.next())
			{
				isRegistered = true;
			}
		}
		catch(SQLException e)
		{
			logger.error(e.getMessage());
		}
		
		return isRegistered;
		
	}
	public boolean dropCourse(String courseCode, int studentId) throws SQLException {
	
		Connection conn = DBUtils.getConnection();
		
		
			try
			{
				stmt = conn.prepareStatement(SQLConstants.DROP_COURSE_QUERY);
				stmt.setString(1, courseCode);
				stmt.setInt(2, studentId);
				stmt.execute();
				
				stmt = conn.prepareStatement(SQLConstants.INCREMENT_SEAT_QUERY);
				stmt.setString(1, courseCode);
				stmt.execute();
				
				stmt.close();
				
				return true;
			}
			catch(SQLException e)
			{
				logger.error(e.getMessage());
			}
			
		
		return false;
		
	}
	
	/**
	 * Method to retrieve fee for the selected courses from the database and calculate total fee
	 * @param studentId
	 * @return Fee Student has to pay
	 * @throws SQLException 
	 */
	
	@Override
	public double calculateFee(int studentId) throws SQLException
	{
		Connection conn = DBUtils.getConnection();
		double fee = 0;
		try
		{
			stmt = conn.prepareStatement(SQLConstants.CALCULATE_FEES);
			stmt.setInt(1, studentId);
			ResultSet rs = stmt.executeQuery();
			rs.next();
			fee = rs.getDouble(1);
		}
		catch(SQLException e)
		{
			logger.error(e.getMessage());
		}
		
		return fee;
	}
	@Override
	public List<Grade> viewGradeCard(int studentId) throws SQLException {
		
		Connection conn = DBUtils.getConnection();
		List<Grade> grade_List = new ArrayList<>();
		try
		{
			stmt = conn.prepareStatement(SQLConstants.VIEW_GRADE);
			stmt.setInt(1, studentId);
			ResultSet rs = stmt.executeQuery();
			
			while(rs.next())
			{
				String courseCode = rs.getString("courseCode");
				String courseName = rs.getString("courseName");
				String grade = rs.getString("grade");
				Grade grd = new Grade(courseCode, courseName,grade,studentId);
				grade_List.add(grd);
			}
		}
		catch(SQLException e)
		{
			logger.error(e.getMessage());
		}

		
		return grade_List;
	}
	@Override
	public List<Course> viewCourses(int studentId) throws SQLException {
		
		List<Course> coursesAvailable = new ArrayList<>();
		Connection conn = DBUtils.getConnection();
		
		try 
		{
			stmt = conn.prepareStatement(SQLConstants.VIEW_AVAILABLE_COURSES);
			stmt.setInt(1, studentId);
			//stmt.setBoolean(2, true);
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				coursesAvailable.add(new Course(rs.getString("coursecode"), rs.getString("coursename"),
						rs.getString("profid"), rs.getString("seats")));

			}
			

		} 
		catch (SQLException e) 
		{
			logger.error(e.getMessage());
		}
		
		return coursesAvailable;
		
	}

	/**
	 * Method to get the list of courses registered by the student
	 * @param studentId
	 * @return list of courses registered by student
	 * @throws SQLException 
	 */
	@Override
	public List<Course> viewRegisteredCourses(int studentId) throws SQLException {

		Connection conn = DBUtils.getConnection();
		List<Course> registeredCourseList = new ArrayList<>();
		try 
		{
			stmt = conn.prepareStatement(SQLConstants.VIEW_REGISTERED_COURSES);
			stmt.setInt(1, studentId);

			ResultSet rs = stmt.executeQuery();
			
			while (rs.next()) {
				registeredCourseList.add(new Course(rs.getString("coursecode"), rs.getString("coursename"),
						rs.getString("profid"), rs.getString("seats")));

			}
		} 
		catch (SQLException e) 
		{
			logger.error(e.getMessage());
		}
		
		return registeredCourseList;
	}
	@Override
	public boolean getRegistrationStatus(int studentId)
	{
		Connection conn = DBUtils.getConnection();
		boolean status = false;
		try 
		{
			stmt = conn.prepareStatement(SQLConstants.GET_REGISTRATION_STATUS);
			stmt.setInt(1, studentId);
			ResultSet rs = stmt.executeQuery();
			rs.next();
			status = rs.getBoolean(1);

		} 
		catch (SQLException e) 
		{
			logger.error(e.getMessage());
		}

		return status;
	}
	
	/*@Override
	public void setRegistrationStatus(int studentId) throws SQLException
	{
		Connection conn = DBUtils.getConnection();
		try 
		{
			stmt = conn.prepareStatement(SQLConstants.SET_REGISTRATION_STATUS);
			stmt.setInt(1, studentId);
			stmt.executeUpdate();

		} 
		catch (SQLException e) 
		{
		} 
		finally
		{
			stmt.close();
			conn.close();
		}

	}*/


}
