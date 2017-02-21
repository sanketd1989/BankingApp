package com.cognizant.exceptions;

/**
 * This class is a custom checked exception defined to denote the incorrect
 * balance i.e. amount which cannot be transfered.
 * 
 * @author SANKET
 * @since 02/18/2017
 */
public class IncorrectAmountException extends Exception {

	public IncorrectAmountException(String pMessage) {
		super(pMessage);
	}
}//End of IncorrectAmountException class
