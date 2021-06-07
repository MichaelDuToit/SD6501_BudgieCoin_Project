package com.example.sd6501_budgiecoin_project;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class AccountUnitTests {
    String expectedAccountName;
    int expectedAccountId;
    double expectedAccountBalance;
    double DELTA = 0.01;
    Account testAccount;

    @Before
    public void setUp() throws Exception {
        expectedAccountName = "Test Account";
        expectedAccountId = 0;
        expectedAccountBalance = 0.0;
    }

    @After
    public void tearDown() throws Exception {
        expectedAccountName = null;
        expectedAccountId = 0;
        expectedAccountBalance = 0.0;
        testAccount = null;
    }

    // Test the Default Constructor
    @Test
    public void account_defaultConstructor(){
        testAccount = new Account();
        assertEquals(expectedAccountId, testAccount.getId());
        assertEquals(null, testAccount.getName());
        assertEquals(expectedAccountBalance, testAccount.getBalance(), DELTA);
    }
    // Test the Name parameterized Constructor
    @Test
    public void account_nameConstructor(){
        testAccount = new Account("Test Account");
        assertEquals(expectedAccountName, testAccount.getName());
        assertEquals(expectedAccountId, testAccount.getId());
        assertEquals(expectedAccountBalance, testAccount.getBalance(), DELTA);
    }
    // Test the all parameters Constructor
    @Test
    public void account_allParamsConstructor(){
        testAccount = new Account(1, "Test Account", 10.00);
        assertEquals(1, testAccount.getId());
        assertEquals(expectedAccountName, testAccount.getName());
        assertEquals(10.00, testAccount.getBalance(), DELTA);
    }
    // Test all the getter and setter methods
    @Test
    public void account_testAllGetterSetterMethods(){
        testAccount = new Account();
        testAccount.setId(expectedAccountId);
        testAccount.setName(expectedAccountName);
        testAccount.setBalance(expectedAccountBalance);
        assertEquals(expectedAccountId, testAccount.getId());
        assertEquals(expectedAccountName, testAccount.getName());
        assertEquals(expectedAccountBalance, testAccount.getBalance(), DELTA);
    }



}