package com.cognizant.transaction;

import java.math.BigDecimal;
import com.cognizant.beans.Account;
import com.cognizant.exceptions.IncorrectAmountException;
import com.cognizant.exceptions.InsufficientBalanceException;


/**
 * This class provide the implementation of the account service method. 
 * @author SANKET
 * @since 02/18/2017
 */
public class AccountServiceImpl implements AccountService {

	
	/*This method transfer the certain amount from one account to another. This also make sure that
	 * the account from which transfer is going to made has sufficient balance and amount to be transfered is valid.
	 * (non-Javadoc)
	 * @see com.cognizant.transaction.AccountService#transfer(com.cognizant.beans.Account, com.cognizant.beans.Account, java.math.BigDecimal)
	 */
	public void transfer(Account fromAccount, Account toAccount, BigDecimal amountToTransfer) throws Exception{
		try{
			
			if(fromAccount!=null&& toAccount!=null&& amountToTransfer!=null){
				
				//check incorrect amount
				if(amountToTransfer.compareTo(new BigDecimal(0))<0){
					throw new IncorrectAmountException("This amount "+amountToTransfer+" cannot be transfered as it is negative or incorrect");
				}
				
				synchronized (this) {
				
					//check insufficient balance 
					if(amountToTransfer.compareTo(fromAccount.getBalance())>0){
						throw new InsufficientBalanceException("Account "+fromAccount.getAccountId()+" does not have sufficient balance");
					}
					
					BigDecimal newBalanceFromAccount=fromAccount.getBalance().subtract(amountToTransfer);
					BigDecimal newBalanceToAccount=toAccount.getBalance().add(amountToTransfer);
					
					fromAccount.setBalance(newBalanceFromAccount);//setting new balance
					toAccount.setBalance(newBalanceToAccount);//setting new balance
					//System.out.println(Thread.currentThread().getName()+" Leaving ");
				}
			}//end of outer if
		}catch(InsufficientBalanceException |IncorrectAmountException e){
			//error logger here. Not implementing as am assuming project has a logger setup.
			System.err.println("Exception"+e.getMessage());
			throw e;
		}
	}//End of transfer() method

}//End of AccountServiceImpl class
