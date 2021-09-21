package com.lt.dao;

import java.sql.SQLException;

public interface NotificationDaoInterface {
	
	/**
	 * Send Notification 
	 * @param transactionId: transactionId for payment
	 * @param studentId: student to be notified
	 * @param notification_msg: message to send in notification
	 * @return notification sent to user or not
	 */
	public boolean sendNotification(int transactionId, String notification_msg, int studentId)throws SQLException;
}
