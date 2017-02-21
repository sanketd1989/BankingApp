package com.cognizant.transaction;

import java.math.BigDecimal;
import com.cognizant.beans.Account;

/**
 * This is a service interface created to define the contract for account's
 * transaction related activities.
 * 
 * @author SANKET
 * @since 02/18/2017
 */
public interface AccountService {
    public void transfer(Account from, Account to, BigDecimal amountToTransfer) throws Exception;
}//End of interface AccountService
