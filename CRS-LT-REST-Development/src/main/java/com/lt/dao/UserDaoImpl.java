package com.lt.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import com.lt.constants.SQLConstants;
import com.lt.exception.UserNotFoundException;
import com.lt.utils.DBUtils;

@Repository
public class UserDaoImpl implements UserDaoInterface {

	private static Logger logger = Logger.getLogger(UserDaoImpl.class);

	/*
	 * private static volatile UserDaoImpl instance = null;
	 * 
	 * private UserDaoImpl() {
	 * 
	 * }
	 * 
	 * public static UserDaoImpl getInstance() { if (instance == null) { // This is
	 * a synchronized block, when multiple threads will access this instance
	 * synchronized (UserDaoImpl.class) { instance = new UserDaoImpl(); } } return
	 * instance; }
	 */

	@Override
	public boolean verifyCredentials(String userId, String password) throws UserNotFoundException {
		Connection connection = DBUtils.getConnection();
		PreparedStatement stmnt = null;
		ResultSet rs = null;
		try {
			stmnt = connection.prepareStatement(SQLConstants.VERIFY_CRED);
			stmnt.setString(1, userId);
			// stmnt.setString(2, password);

			rs = stmnt.executeQuery();
			if (!rs.next()) {
				logger.error("User Not Found");
				throw new UserNotFoundException(userId);
			} else if (password.equals(rs.getString("password")) && userId.equals(rs.getString("userid"))) {
				return true;
			} else {
				return false;
			}
		} catch (SQLException e) {
			logger.error("Something went wrong, please try again! " + e.getMessage());
		} finally {

		}
		return false;
	}

	@Override
	public boolean updatePassword(String userID, String newPassword) {
		Connection con = DBUtils.getConnection();
		PreparedStatement stmnt = null;
		try {
			stmnt = con.prepareStatement(SQLConstants.UPDATE_PASSWORD);
			stmnt.setString(1, newPassword);
			stmnt.setString(2, userID);

			int row = stmnt.executeUpdate();
			if (row == 1) {
				return true;
			} else {
				return false;
			}
		} catch (SQLException e) {
			logger.error("Something went wrong, please try again! " + e.getMessage());
		}
		return false;
	}

	@Override
	public String getRoleById(String userId) {
		Connection connection = DBUtils.getConnection();
		PreparedStatement statement = null;
		ResultSet rs = null;
		try {
			statement = connection.prepareStatement(SQLConstants.GET_ROLE);
			statement.setString(1, userId);
			rs = statement.executeQuery();

			if (rs.next()) {
				return rs.getString("role");
			}

		} catch (SQLException e) {
			logger.error("Something went wrong, please try again! " + e.getMessage());
		}
		return null;
	}

}
