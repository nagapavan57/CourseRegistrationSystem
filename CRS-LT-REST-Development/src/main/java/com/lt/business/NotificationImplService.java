package com.lt.business;

import java.sql.SQLException;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lt.constants.PaymentMode;
import com.lt.dao.AdminDaoImpl;
import com.lt.dao.NotificationDaoImpl;

@Service
public class NotificationImplService implements NotificationInterface {
	private static Logger logger = Logger.getLogger(NotificationImplService.class);
	
	@Autowired
	NotificationDaoImpl notify;

	public boolean sendNotification(int studentId, int transactionId, String notificationMsg) {
		try {
			return notify.sendNotification(transactionId, notificationMsg, studentId);
		} catch (SQLException ex) {
			logger.error(ex.getMessage());
		}
		return false;
	}

}
