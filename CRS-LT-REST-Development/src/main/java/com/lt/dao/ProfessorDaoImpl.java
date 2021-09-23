package com.lt.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import com.lt.bean.Course;
import com.lt.bean.StudentsEnrolled;
import com.lt.constants.SQLConstants;
import com.lt.exception.GradeNotAddedException;
import com.lt.utils.DBUtils;
@Repository
public class ProfessorDaoImpl implements ProfessorDaoInterface {

	private static Logger logger = Logger.getLogger(ProfessorDaoImpl.class);

	/*
	 * private static volatile ProfessorDaoImpl instance = null;
	 * 
	 * private ProfessorDaoImpl() {
	 * 
	 * }
	 * 
	 * public static ProfessorDaoImpl getInstance() { if (instance == null) { //
	 * This is a synchronized block, when multiple threads will access this instance
	 * synchronized (ProfessorDaoImpl.class) { instance = new ProfessorDaoImpl(); }
	 * } return instance; }
	 */

	@Override
	public List<Course> getCoursesByProfessor(String profId) {
		Connection con = DBUtils.getConnection();
		List<Course> courseList = new ArrayList<Course>();
		PreparedStatement stmt = null;
		try {
			stmt = con.prepareStatement(SQLConstants.GET_COURSES);
			stmt.setString(1, profId);

			ResultSet res = stmt.executeQuery();
			while (res.next()) {
				courseList.add(new Course(res.getString("coursecode"), res.getString("coursename"),
						res.getString("profid"), res.getString("seats")));
			}

		} catch (SQLException e) {
			logger.error(e.getMessage());
		}
		return courseList;
	}

	@Override
	public List<StudentsEnrolled> getEnrolledStudents(String profId) {
		Connection con = DBUtils.getConnection();
		List<StudentsEnrolled> studentList = new ArrayList<StudentsEnrolled>();
		PreparedStatement stmt = null;
		try {
			stmt = con.prepareStatement(SQLConstants.GET_STUDENTS_ENROLLED);
			stmt.setString(1, profId);

			ResultSet res = stmt.executeQuery();
			while (res.next()) {
				studentList.add(new StudentsEnrolled(res.getString("coursecode"), res.getString("coursename"),
						res.getInt("studentid")));
			}

		} catch (SQLException e) {
			logger.error(e.getMessage());
		}

		return studentList;
	}

	@Override
	public boolean addGrade(int studentId, String courseCode, String courseName, String grade)
			throws GradeNotAddedException {

		Connection con = DBUtils.getConnection();
		PreparedStatement stmt = null;
		try {
			stmt = con.prepareStatement(SQLConstants.ADD_GRADE);
			stmt.setString(1, courseCode);
			stmt.setString(2, courseName);
			stmt.setString(3, grade);
			stmt.setInt(4, studentId);

			int row = stmt.executeUpdate();
			if (row == 1) {
				logger.info("Grade Successfully Added for Student with Id: " + studentId + " for course " + courseName);
				return true;
			} else {
				logger.error("Grade Assiging Failed!!!");
				return false;
			}

		} catch (SQLException e) {
			logger.error(e.getMessage());
			throw new GradeNotAddedException(studentId);
		}

	}

	@Override
	public String getProfessorById(String profId) {
		String name = null;
		Connection con = DBUtils.getConnection();
		PreparedStatement stmt = null;
		try {
			stmt = con.prepareStatement(SQLConstants.GET_PROFFESSOR);
			stmt.setString(1, profId);

			ResultSet res = stmt.executeQuery();
			while (res.next()) {
				name = res.getString(1);
			}

		} catch (SQLException e) {
			logger.error(e.getMessage());
		}

		return name;
	}

}
