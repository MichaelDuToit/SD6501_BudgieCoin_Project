package com.example.sd6501_budgiecoin_project;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class UserUnitTests {
    User testUser;
    int expectedId;
    String expectedName, expectedPin;

    @Before
    public void setUp() throws Exception {
        expectedId = 1;
        expectedName = "Test User";
        expectedPin = "1234";
    }

    @After
    public void tearDown() throws Exception {
        expectedId = 0;
        expectedName = null;
        expectedPin = null;
    }

    // Test the Default Constructor for User
    @Test
    public void user_testDefaultConstructor(){
        testUser = new User();
        assertEquals(0, testUser.getId());
        assertEquals(null, testUser.getUsername());
        assertEquals(null, testUser.getPinNumber());
    }

    // Test the Parameterized Constructor excluding ID for User
    @Test
    public void user_testExcludingIdConstructor(){
        testUser = new User(expectedName, expectedPin);
        assertEquals(0, testUser.getId());
        assertEquals(expectedName, testUser.getUsername());
        assertEquals(expectedPin, testUser.getPinNumber());
    }
    // Test the all parameters Constructor for User
    @Test
    public void user_testAllParamsConstructor(){
        testUser = new User(expectedId, expectedName, expectedPin);
        assertEquals(expectedId, testUser.getId());
        assertEquals(expectedName, testUser.getUsername());
        assertEquals(expectedPin, testUser.getPinNumber());
    }
}