package com.example.sd6501_budgiecoin_project;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class TransactionUnitTests {
    Transaction testTransaction;
    int expectedId, expectedAccount;
    String expectedName, expectedNote, expectedDate, expectedTime;
    double expectedValue, DELTA;

    @Before
    public void setUp() throws Exception {
        expectedId = 1;
        expectedAccount = 1;
        expectedName = "Test Transaction UT";
        expectedNote = "Unit Testing";
        expectedValue = 10.00;
        expectedDate = "01/01/2020";
        expectedTime = "10:00";
        DELTA = 0.01;
    }

    @After
    public void tearDown() throws Exception {
        testTransaction = null;
        expectedName = null;
        expectedNote = null;
        expectedDate = null;
        expectedTime = null;
    }

    // Unit Test the Transaction class' default constructor
    @Test
    public void transaction_defaultConstructor(){
        testTransaction = new Transaction();
        assertEquals(0, testTransaction.getId());
        assertEquals(null, testTransaction.getName());
        assertEquals(0.0, testTransaction.getValue(), DELTA);
        assertEquals(0, testTransaction.getAccount());
        assertEquals(null, testTransaction.getNote());
        assertEquals(null, testTransaction.getDate());
        assertEquals(null, testTransaction.getTime());
    }
    // Unit Test the Transaction class' constructor that takes all params except an ID.
    @Test
    public void transaction_allParamsExceptIdConstructor(){
        testTransaction = new Transaction(expectedName, expectedValue, expectedDate, expectedTime, expectedAccount, expectedNote);
        assertEquals(0, testTransaction.getId());
        assertEquals(expectedName, testTransaction.getName());
        assertEquals(expectedValue, testTransaction.getValue(), DELTA);
        assertEquals(expectedAccount, testTransaction.getAccount());
        assertEquals(expectedNote, testTransaction.getNote());
        assertEquals(expectedDate, testTransaction.getDate());
        assertEquals(expectedTime, testTransaction.getTime());
    }
    // Unit Test the Transaction class' constructor that takes all params.
    @Test
    public void transaction_allParamsConstructor(){
        testTransaction = new Transaction(expectedId, expectedName, expectedValue, expectedDate, expectedTime, expectedAccount, expectedNote);
        assertEquals(expectedId, testTransaction.getId());
        assertEquals(expectedName, testTransaction.getName());
        assertEquals(expectedValue, testTransaction.getValue(), DELTA);
        assertEquals(expectedAccount, testTransaction.getAccount());
        assertEquals(expectedNote, testTransaction.getNote());
        assertEquals(expectedDate, testTransaction.getDate());
        assertEquals(expectedTime, testTransaction.getTime());
    }

    // Test all the Getter and Setter methods of the class
    @Test
    public void transaction_testAllGetterSetterMethods(){
        testTransaction = new Transaction();
        testTransaction.setId(expectedId);
        testTransaction.setName(expectedName);
        testTransaction.setValue(expectedValue);
        testTransaction.setDate(expectedDate);
        testTransaction.setTime(expectedTime);
        testTransaction.setAccount(expectedAccount);
        testTransaction.setNote(expectedNote);
        assertEquals(expectedId, testTransaction.getId());
        assertEquals(expectedName, testTransaction.getName());
        assertEquals(expectedValue, testTransaction.getValue(), DELTA);
        assertEquals(expectedDate, testTransaction.getDate());
        assertEquals(expectedTime, testTransaction.getTime());
        assertEquals(expectedAccount, testTransaction.getAccount());
        assertEquals(expectedNote, testTransaction.getNote());
    }
}