package com.lt.business;

import java.sql.SQLException;

import org.apache.log4j.Logger;

import com.lt.dao.AdminDaoImpl;
import com.lt.dao.NotificationDaoImpl;
import com.lt.dao.PaymentDaoImpl;

public class PaymentImplService implements PaymentInterface{
	 private static Logger logger = Logger.getLogger(AdminDaoImpl.class);

	//PaymentDaoImpl payment = new PaymentDaoImpl();
	PaymentDaoImpl payment = PaymentDaoImpl.getInstance();

	@Override
	public int makePayment(int studentId, String mode, String amount,String cardNo,String Expiry,String CVV) {
		try{
		return payment.makePayment(studentId, mode, amount,cardNo,Expiry,CVV);
		}catch(SQLException e){
			logger.error(e.getMessage());
		}
		return 0;
	}

}
