package com.abc;


import java.util.Date;

public class Transaction {
    public final double amount;

    private Date transactionDate;

    public Transaction(double amount) {
        this.amount = amount;
        this.transactionDate = DateProvider.getInstance().now();
    }
    
    
    public void transferBetweenAccounts(Account a, Account b){
    	try{
          a.withdraw(amount);
          b.deposit(amount);
    	}
    	catch(IllegalArgumentException e){
    	  System.out.println("Transfer amount must be greater than 0");
    	}
    }
    
    public Date getTransactionDate(){
    	return transactionDate;
    }

}
