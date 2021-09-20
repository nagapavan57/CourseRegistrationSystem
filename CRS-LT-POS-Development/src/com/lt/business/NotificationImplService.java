package com.lt.business;

import java.sql.SQLException;

import org.apache.log4j.Logger;

import com.lt.constants.PaymentMode;
import com.lt.dao.NotificationDaoImpl;

public class NotificationImplService implements NotificationInterface{
	private static Logger logger = Logger.getLogger(NotificationImplService.class);
	NotificationDaoImpl notify = new NotificationDaoImpl();
	public boolean sendNotification(int studentId, int transactionId,String notificationMsg){
		try{
			return notify.sendNotification(transactionId, notificationMsg, studentId);
		}catch(SQLException ex){
			logger.error(ex.getMessage());
		}
		return false;
	}

}
