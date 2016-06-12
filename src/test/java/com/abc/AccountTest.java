package com.abc;

import static org.junit.Assert.*;

import org.junit.Test;

public class AccountTest {
	private static final double DOUBLE_DELTA = 1e-15;
	
	@Test
	public void testInterestEarned() {
        Account checkingAccount = new Account(Account.CHECKING);
        checkingAccount.deposit(100.0);
        checkingAccount.deposit(200.0);
        checkingAccount.deposit(50.0);
        checkingAccount.withdraw(75.0);
        double interest = checkingAccount.interestEarned();
        assertEquals(interest, 0.275, DOUBLE_DELTA);       
	}
	
	@Test
	public void testWithdrawalIn10Days() {
        Account checkingAccount = new Account(Account.CHECKING);
        checkingAccount.withdraw(50.0);
        boolean withdrew = checkingAccount.withdrawalsInPast10Days();
        assertTrue(withdrew);       
	}
	
	@Test
	public void testMaxiSavingsAccounts() {
        Account checkingAccount = new Account(Account.MAXI_SAVINGS);
        checkingAccount.deposit(100);
        double intEarned = checkingAccount.interestEarned();
        assertEquals(intEarned, 5.0, DOUBLE_DELTA); 
	}
	


}
