package com.lt.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.log4j.Logger;

import com.lt.bean.Student;
import com.lt.constants.SQLConstants;
import com.lt.exception.StudentNotRegisteredException;
import com.lt.utils.DBUtils;

public class StudentDaoImpl implements StudentDaoInterface {

	private static Logger logger = Logger.getLogger(StudentDaoImpl.class);

	private static volatile StudentDaoImpl instance = null;

	private StudentDaoImpl() {

	}

	public static StudentDaoImpl getInstance() {
		if (instance == null) {
			// This is a synchronized block, when multiple threads will access this instance
			synchronized (StudentDaoImpl.class) {
				instance = new StudentDaoImpl();
			}
		}
		return instance;
	}

	@Override
	public int registerStudent(Student student) throws StudentNotRegisteredException {
		Connection conn = DBUtils.getConnection();
		PreparedStatement stmnt = null;
		int studentId = 0;
		try {
			stmnt = conn.prepareStatement(SQLConstants.GET_ROLENAME);
			stmnt.setInt(1, 3);
			ResultSet rs = stmnt.executeQuery();
			String role = "";
			if (rs.next()) {
				role = rs.getString(1);
			}

			stmnt = conn.prepareStatement(SQLConstants.ADD_USER_QUERY);
			stmnt.setString(1, student.getUserId());
			stmnt.setString(2, student.getPassword());
			stmnt.setString(3, role);

			int rows = stmnt.executeUpdate();
			if (rows == 1) {

				PreparedStatement stmntStud = conn.prepareStatement(SQLConstants.ADD_STUDENT,
						Statement.RETURN_GENERATED_KEYS);

				stmntStud.setString(1, student.getName());
				stmntStud.setString(2, student.getEmailId());
				stmntStud.setString(3, student.getBranchName());
				stmntStud.setString(4, student.getAddress());
				stmntStud.setBoolean(5, false);
				stmntStud.setInt(6, 3);
				stmntStud.executeUpdate();
				ResultSet results = stmntStud.getGeneratedKeys();
				if (results.next())
					studentId = results.getInt(1);
			}

		} catch (SQLException ex) {
			logger.error(ex.getMessage());
			throw new StudentNotRegisteredException(student.getName());
		}
		return studentId;
	}

	@Override
	public boolean isApproved(int studentId) {
		Connection conn = DBUtils.getConnection();
		try {
			PreparedStatement statement = conn.prepareStatement(SQLConstants.IS_APPROVED);
			statement.setInt(1, studentId);
			ResultSet rs = statement.executeQuery();

			if (rs.next()) {
				return rs.getBoolean("isapproved");
			}

		} catch (SQLException e) {
			logger.error(e.getMessage());
		}

		return false;
	}

	@Override
	public int getStudentId(String studentId) {
		Connection conn = DBUtils.getConnection();
		try {
			PreparedStatement statement = conn.prepareStatement(SQLConstants.GET_STUDENT_ID);
			statement.setString(1, studentId);
			ResultSet rs = statement.executeQuery();

			if (rs.next()) {
				return rs.getInt("studentid");
			}

		} catch (SQLException e) {
			logger.error(e.getMessage());
		}

		return 0;
	}

}
