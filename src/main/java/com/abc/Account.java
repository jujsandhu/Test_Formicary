package com.abc;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ListIterator;

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

    public double interestEarned() {
        double amount = sumTransactions();
        switch(accountType){
            case SAVINGS:
                if (amount <= 1000)
                    return dailyInterest(amount,0.001);
                else
                    return 1 + dailyInterest((amount-1000),0.002);
            case MAXI_SAVINGS:
                if (checkPreviousWithdrawals(10)){
                	return dailyInterest(amount,0.05);
                }
                else
                	return dailyInterest(amount,0.02);

            default:
                return dailyInterest(amount, 0.001);
        }
    }

    //calculates accrued interest rate for a period of 1 year.
    public double dailyInterest(double original_amount, double rate){
       double amount = original_amount;
       double accrued = (rate/365.0);
       for(int i = 1;i<=365;i++){
    	  amount += amount * accrued;
       }
       return Math.round((amount-original_amount)*1000.0)/1000.0;
    }
    
    public double sumTransactions() {
       return checkIfTransactionsExist(true);
    }
    
    //iterate list in reverse to get latest orders, return false if withdrawal
    public boolean checkPreviousWithdrawals(int days){
    	    ListIterator<Transaction> iter = transactions.listIterator(transactions.size());
    	   
    	    while (iter.hasPrevious()) 
    	    {	
    	    	
    	    	Transaction t = iter.previous();
    	    	Date subtractedDate = DateProvider.subtractDays(days);
    	    	boolean inRange = DateProvider.compareDates(subtractedDate, t.getTransactionDate());   	    
    	    	
    	    	if(inRange)
    	        {
    	        	if(t.amount < 0)
    	        		return false;
    	        }
    	        else break;
    	}   
    	return true;
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

}
