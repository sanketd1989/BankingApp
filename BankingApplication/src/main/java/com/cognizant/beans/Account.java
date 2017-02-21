package com.cognizant.beans;

import java.math.BigDecimal;

/**
 * This is a bean defined to store the account related information such as
 * account id and balance it has.
 * @author SANKET
 * @since 02/18/2017
 */
public class Account {

    /**
     * Represents UNIQUE account id, e.g. GB123-456
     */
    private String accountId;

    /**
     * Represents current balance of the account
     */
    private BigDecimal balance;

    public Account(String accountId, BigDecimal balance) {
        this.accountId = accountId;
        this.balance = balance;
    }

	public String getAccountId() {
		return accountId;
	}

	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}

	public BigDecimal getBalance() {
		return balance;
	}

	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((accountId == null) ? 0 : accountId.hashCode());
		result = prime * result + ((balance == null) ? 0 : balance.hashCode());
		return result;
	}//End of hasCode() method

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Account other = (Account) obj;
		if (accountId == null) {
			if (other.accountId != null)
				return false;
		} else if (!accountId.equals(other.accountId))
			return false;
		if (balance == null) {
			if (other.balance != null)
				return false;
		} else if (!balance.equals(other.balance))
			return false;
		return true;
	}//End of equals() method
    
}//End of Account class
