package com.lt.business;

import org.springframework.stereotype.Service;

@Service
public interface PaymentInterface {
	
	/**
	 * Method to send notification
	 * @param amount: total amount to pay for semister
	 * @param studentId: student to be notified
	 * @param mode: payment mode used
	 * @return true if payment successful
	 */
	public int makePayment(int studentId,String mode,String amount,String cardNo,String Expiry,String CVV);
}
