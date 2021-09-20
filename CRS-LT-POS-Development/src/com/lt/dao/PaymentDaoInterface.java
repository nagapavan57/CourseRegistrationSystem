package com.lt.dao;

import java.sql.SQLException;

public interface PaymentDaoInterface {
	
	/**
	 * Making Payment
	 * @param studentId: student to be notified
	 * @param mode: mode of payment used
	 * @param amount
	 * @return transaction id for the record added in the database
	 */
	public int makePayment(int studentId,String mode,String amount,String cardNo,String Expiry,String CVV)throws SQLException;

}
