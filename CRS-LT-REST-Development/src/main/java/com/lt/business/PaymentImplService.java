package com.lt.business;

import java.sql.SQLException;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lt.dao.AdminDaoImpl;
import com.lt.dao.NotificationDaoImpl;
import com.lt.dao.PaymentDaoImpl;

@Service
public class PaymentImplService implements PaymentInterface{
	 private static Logger logger = Logger.getLogger(AdminDaoImpl.class);

	@Autowired
	PaymentDaoImpl payment ;

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
