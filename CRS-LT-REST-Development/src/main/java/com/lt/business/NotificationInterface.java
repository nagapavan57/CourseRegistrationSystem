package com.lt.business;

import java.sql.SQLException;

import org.springframework.stereotype.Service;

@Service
public interface NotificationInterface {
	
	/**
	 * Method to send notification
	 * @param transaction id: payment transaction id
	 * @param studentId: student to be notified
	 * @return notificationMsg message to be sent in notification
	 */
	public boolean sendNotification(int studentId,int transactionId,String notificationMsg);

}
