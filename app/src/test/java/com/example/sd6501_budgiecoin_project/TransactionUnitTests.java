package com.example.sd6501_budgiecoin_project;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Calendar;

import static org.junit.Assert.*;

public class TransactionUnitTests {
    Transaction testTransaction;
    int expectedId, expectedAccount;
    String expectedName, expectedNote;
    Calendar expectedDateTime;
    double expectedValue, DELTA;

    @Before
    public void setUp() throws Exception {
        expectedId = 1;
        expectedAccount = 1;
        expectedName = "Test Transaction UT";
        expectedNote = "Unit Testing";
        expectedValue = 10.00;
        expectedDateTime = Calendar.getInstance();
        DELTA = 0.01;
    }

    @After
    public void tearDown() throws Exception {
        testTransaction = null;
        expectedName = null;
        expectedNote = null;
        expectedDateTime = null;
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
        assertEquals(null, testTransaction.getDateTime());
    }
    // Unit Test the Transaction class' constructor that takes all params except an ID.
    @Test
    public void transaction_allParamsExceptIdConstructor(){
        testTransaction = new Transaction(expectedName, expectedValue, expectedDateTime, expectedAccount, expectedNote);
        assertEquals(0, testTransaction.getId());
        assertEquals(expectedName, testTransaction.getName());
        assertEquals(expectedValue, testTransaction.getValue(), DELTA);
        assertEquals(expectedAccount, testTransaction.getAccount());
        assertEquals(expectedNote, testTransaction.getNote());
        assertEquals(expectedDateTime, testTransaction.getDateTime());
    }
    // Unit Test the Transaction class' constructor that takes all params.
    @Test
    public void transaction_allParamsConstructor(){
        testTransaction = new Transaction(expectedId, expectedName, expectedValue, expectedDateTime, expectedAccount, expectedNote);
        assertEquals(expectedId, testTransaction.getId());
        assertEquals(expectedName, testTransaction.getName());
        assertEquals(expectedValue, testTransaction.getValue(), DELTA);
        assertEquals(expectedAccount, testTransaction.getAccount());
        assertEquals(expectedNote, testTransaction.getNote());
        assertEquals(expectedDateTime, testTransaction.getDateTime());
    }

    // Test all the Getter and Setter methods of the class
    @Test
    public void transaction_testAllGetterSetterMethods(){
        testTransaction = new Transaction();
        testTransaction.setId(expectedId);
        testTransaction.setName(expectedName);
        testTransaction.setValue(expectedValue);
        testTransaction.setDatetime(expectedDateTime);
        testTransaction.setAccount(expectedAccount);
        testTransaction.setNote(expectedNote);
        assertEquals(expectedId, testTransaction.getId());
        assertEquals(expectedName, testTransaction.getName());
        assertEquals(expectedValue, testTransaction.getValue(), DELTA);
        assertEquals(expectedDateTime, testTransaction.getDateTime());
        assertEquals(expectedAccount, testTransaction.getAccount());
        assertEquals(expectedNote, testTransaction.getNote());
    }
}