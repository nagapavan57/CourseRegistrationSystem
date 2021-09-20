package com.lt.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.log4j.Logger;

import com.lt.constants.SQLConstants;
import com.lt.utils.DBUtils;

public class NotificationDaoImpl implements NotificationDaoInterface {
	private static Logger logger = Logger.getLogger(NotificationDaoImpl.class);

	@Override
	public boolean sendNotification(int transactionId, String notification_msg, int studentId)throws SQLException {
		// TODO Auto-generated method stub
		Connection connection = DBUtils.getConnection();
		PreparedStatement statement = null;
		ResultSet rs = null;
		try {
			notification_msg = "";
			statement = connection.prepareStatement(SQLConstants.SEND_NOTIFICATION,Statement.RETURN_GENERATED_KEYS);
			statement.setInt(1, studentId);
			statement.setInt(2, transactionId);
			statement.setString(3, notification_msg);

			int row = statement.executeUpdate();
			if (row == 1) {
				logger.info("Notification sent to Student with id "+studentId+"Succesfully");
				return true;
			} else {
				logger.error("Notification sent to Student with id "+studentId+"Failed!!!");
				return false;
			}
			
		} catch (SQLException e) {
			logger.error(e.getMessage());
		}
		return false;

	}

}
