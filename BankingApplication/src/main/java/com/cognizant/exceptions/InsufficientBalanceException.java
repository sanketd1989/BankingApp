package com.cognizant.exceptions;

/**
 * This class is a custom checked exception defined to denote the insufficient balance.
 * @author SANKET
 * @since 02/18/2017
 */
public class InsufficientBalanceException extends Exception{
	
	public InsufficientBalanceException(String pMessage) {
		super(pMessage);
	}
}//End of InsufficientBalanceException class
