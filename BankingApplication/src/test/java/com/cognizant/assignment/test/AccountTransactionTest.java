package com.cognizant.assignment.test;

import java.math.BigDecimal;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import junit.framework.Assert;
import junit.framework.TestCase;

import com.cognizant.beans.Account;
import com.cognizant.exceptions.IncorrectAmountException;
import com.cognizant.exceptions.InsufficientBalanceException;
import com.cognizant.transaction.AccountServiceImpl;

public class AccountTransactionTest{
	private static  BalanceTransferThread runnable=null;
	private static Account fromAccount=null;
	private static Account toAccount=null;
	private static AccountServiceImpl  accountServiceImpl=null;
	
	
	@BeforeClass
	public static void setupObject(){
		
		toAccount= new Account("ABC-123", new BigDecimal(1));
		fromAccount= new Account("MNO-456", new BigDecimal(1));
		accountServiceImpl= new AccountServiceImpl();
		
		runnable= new BalanceTransferThread();
		runnable.initialiseState(fromAccount, toAccount, accountServiceImpl);
		
	}
	
	/**
	 * To test the multiple thread performing the balance transfer
	 * @throws InterruptedException 
	 * @throws Exception //
	 */
	@Test
	public void testMultipleThread() throws InterruptedException{
		
		fromAccount.setBalance(new BigDecimal(500));
		toAccount.setBalance(new BigDecimal(200));
		runnable.setAmtToTransfer(new BigDecimal(30));
		
		Thread thread0 = new Thread(runnable);
		thread0.start();
		
		Thread  thread1 = new Thread(runnable);
		thread1.start();
	
		Thread  thread2 = new Thread(runnable);
		thread2.start();
		
		thread0.join();
		thread1.join();
		thread2.join();
		
		Assert.assertEquals(new BigDecimal(410), fromAccount.getBalance());
		Assert.assertEquals(new BigDecimal(290), toAccount.getBalance());
	}//end of testMultipleThread() method 
		
	
	/**
	 * Test method is to test the InsufficientBalanceException exception
	 * @throws Exception
	 */
	@Test(expected=InsufficientBalanceException.class)	
	public void testInsufficientBalance() throws Exception{
		accountServiceImpl.transfer(fromAccount, toAccount, new BigDecimal(500));
	}//end of testInsufficientBalance() method
	
	
	/**
	 * Test method is used to test the IncorrectAmountException exception
	 * @throws Exception
	 */
	@Test(expected=IncorrectAmountException.class)	
	public void testIncorrectBalance() throws Exception{
		accountServiceImpl.transfer(fromAccount, toAccount, new BigDecimal(-50));
	}//end of testIncorrectBalance(0
	
	
}//End of AccountTransctionTest Junit test class


/**
 * Class created to create a thread which does the balance transfer
 * @author SANKET
 *
 */

class BalanceTransferThread implements Runnable{
	private Account accountFrom = null;
	private Account accountTo = null;
	private AccountServiceImpl accountServiceImpl=null;
	private BigDecimal amtToTransfer=null;
	
	public void initialiseState(Account acctFrm, Account acctTo, AccountServiceImpl acctServiceImpl){
		this.accountFrom=acctFrm;
		this.accountTo=acctTo;
		this.accountServiceImpl=acctServiceImpl;
		this.amtToTransfer=amtToTransfer;
	}
	
		
	public BigDecimal getAmtToTransfer() {
		return amtToTransfer;
	}

	public void setAmtToTransfer(BigDecimal amtToTransfer) {
		this.amtToTransfer = amtToTransfer;
	}

	@Override
	public void run() {
		try {
			accountServiceImpl.transfer(accountFrom, accountTo, amtToTransfer);
			System.out.println(Thread.currentThread().getName()+" balance frm :"+accountFrom.getBalance()+"balance frm : "+accountTo.getBalance());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
