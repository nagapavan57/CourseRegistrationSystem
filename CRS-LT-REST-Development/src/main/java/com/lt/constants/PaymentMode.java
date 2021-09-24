package com.lt.constants;

/*
 * @author Komal
 * Payment mode Enum
 * 
 */
public enum PaymentMode {
	CREDIT_CARD,NET_BANKING,DEBIT_CARD,CASH;
	
	public static PaymentMode getModeofPayment(int value)
	{
		switch(value)
		{
			case 1:
				return PaymentMode.CREDIT_CARD;
			case 2:
				return PaymentMode.NET_BANKING;
			case 3:
				return PaymentMode.DEBIT_CARD;
			case 4:
				return PaymentMode.CASH;
			default:
				return null;
				
		}
			
	}

}
