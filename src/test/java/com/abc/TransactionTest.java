package com.abc;

import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class TransactionTest {
    @Test
    public void transaction() {
        Transaction t = new Transaction(5);
        assertTrue(t instanceof Transaction);
    }
    
    @Test
    public void transfer(){
    	Account a = new Account(Account.CHECKING);
    	Account b = new Account(Account.CHECKING);
    	
    	a.deposit(100);
    	b.deposit(100);
    	
    	double amount = 50;
    	
    	Transaction t = new Transaction(amount);
    	t.transferBetweenAccounts(a, b);
    	
    	assertTrue(a.sumTransactions() == 100 - amount);
    	assertTrue(b.sumTransactions() == 100 + amount);
    }
    
    
    
}
