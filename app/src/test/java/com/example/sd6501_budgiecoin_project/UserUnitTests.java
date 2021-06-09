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

    // UT09 - Test the Default Constructor for User
    @Test
    public void user_testDefaultConstructor(){
        testUser = new User();
        assertEquals(0, testUser.getId());
        assertEquals(null, testUser.getUsername());
        assertEquals(null, testUser.getPinNumber());
    }

    // UT10 - Test the Parameterized Constructor excluding ID for User
    @Test
    public void user_testExcludingIdConstructor(){
        testUser = new User(expectedName, expectedPin);
        assertEquals(0, testUser.getId());
        assertEquals(expectedName, testUser.getUsername());
        assertEquals(expectedPin, testUser.getPinNumber());
    }
    // UT11 - Test the all parameters Constructor for User
    @Test
    public void user_testAllParamsConstructor(){
        testUser = new User(expectedId, expectedName, expectedPin);
        assertEquals(expectedId, testUser.getId());
        assertEquals(expectedName, testUser.getUsername());
        assertEquals(expectedPin, testUser.getPinNumber());
    }
    // UT12 - Test all the getter and setter methods
    @Test
    public void user_testAllGetterSetterMethods(){
        testUser = new User();
        testUser.setId(2);
        testUser.setUsername(expectedName);
        testUser.setPinNumber(expectedPin);
        assertEquals(2, testUser.getId());
        assertEquals(expectedName, testUser.getUsername());
        assertEquals(expectedPin, testUser.getPinNumber());
    }

}