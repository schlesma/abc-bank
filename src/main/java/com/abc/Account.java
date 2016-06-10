package com.abc;

import java.util.ArrayList;
import java.util.List;
import java.util.Date;

public class Account {

    public static final int CHECKING = 0;
    public static final int SAVINGS = 1;
    public static final int MAXI_SAVINGS = 2;

    private final int accountType;
    public List<Transaction> transactions;

    public Account(int accountType) {
        this.accountType = accountType;
        this.transactions = new ArrayList<Transaction>();
    }

    public void deposit(double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("amount must be greater than zero");
        } else {
            transactions.add(new Transaction(amount));
        }
    }

    public void withdraw(double amount) {
    	if (amount <= 0) {
    		throw new IllegalArgumentException("amount must be greater than zero");
    	} else {
    		transactions.add(new Transaction(-amount));
    	}
    }
    
    public double calculate(double p, double r) {
    	return p * Math.pow(1 + (r / 365), 365);
    }


    public double interestEarned() {
        double amount = sumTransactions();
        switch(accountType){
            case SAVINGS:
                if (amount <= 1000) 
                    //return amount * 0.001;
                	return calculate(amount, 0.001);
                else
                    return calculate((1 + (amount-1000.0)), 0.002);
//            case SUPER_SAVINGS:
//                if (amount <= 4000)
//                    return 20;
                
            /*
             * Change Maxi-Savings accounts to have an interest rate of 5% assuming no withdrawals in the past 10 days otherwise 0.1%
             */
            case MAXI_SAVINGS:
            	if (!withdrawalsInPast10Days()){
            		return calculate(amount, 0.05);
            	} else {
            		return calculate(amount, 0.001);
            	}

            default:
                return calculate(amount, 0.001);
        }
    }

    public double sumTransactions() {
       return checkIfTransactionsExist(true);
    }

    private double checkIfTransactionsExist(boolean checkAll) {
        double amount = 0.0;
        for (Transaction t: transactions)
            amount += t.amount;
        return amount;
    }

    public int getAccountType() {
        return accountType;
    }

    public boolean withdrawalsInPast10Days() {
    	DateProvider provider = DateProvider.getInstance();
    	
    	if (transactions.size() == 0) {
    		return false;
    	}
    	
    	for (Transaction t: transactions) {
    		if (t.amount < 0) {
    			Date tDate = t.getTransactionDate();
    			Date now = provider.now();
    			if (provider.getDateDiff(tDate, now) < 10) {
    				return true;
    			}
    		}
    	}
    	
    	return false;
    }
}
