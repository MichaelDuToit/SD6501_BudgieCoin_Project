package com.example.sd6501_budgiecoin_project;

import android.app.Activity;

import androidx.test.rule.ActivityTestRule;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.doesNotExist;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.junit.Assert.*;

public class LoginActivityTest {

    @Rule
    public ActivityTestRule<LoginActivity> activityTestRule = new ActivityTestRule<>(LoginActivity.class);
    Activity loginActivity;
    User validUser;
    User invalidUser;
    // This is for creating a test user.
    DBHandler db;

    @Before
    public void setUp() throws Exception {
        loginActivity = activityTestRule.getActivity();
        db = new DBHandler(loginActivity.getApplicationContext());
        validUser = new User("admin", "4321");
        db.createUser(validUser); // Create a user in the DB so testLoginValidLogin doesn't fail.
        invalidUser = new User("invalid", "9999");
    }

    @After
    public void tearDown() throws Exception {
        loginActivity = null;
        validUser = null;
        invalidUser = null;
    }

    // ES05 - Check that all expected view elements are present
    @Test
    public void checkAllViewElementsPresent(){
        onView(withId(R.id.loginActivity)).check(matches(isDisplayed()));
        onView(withId(R.id.loginUsername)).check(matches(isDisplayed()));
        onView(withId(R.id.btnRegister)).check(matches(isDisplayed()));
        onView(withId(R.id.loginPin)).check(matches(isDisplayed()));
        onView(withId(R.id.pinLoginBtn)).check(matches(isDisplayed()));
        onView(withId(R.id.biometricLoginBtn)).check(matches(isDisplayed()));
    }

    // ES06 - Enter invalid login and then check that error message is shown.
    @Test
    public void testLoginInvalidLogin(){
        onView(withId(R.id.loginUsername)).perform(typeText(invalidUser.getUsername()));
        onView(withId(R.id.loginPin)).perform(typeText(invalidUser.getPinNumber()));
        onView(withId(R.id.pinLoginBtn)).perform(click());
        onView(withId(R.id.loginError)).check(matches(isDisplayed()));
    }
    // ES07 - Enter valid login and check that proceeds to the MainAcivity
    @Test
    public void testLoginValidLogin(){
        onView(withId(R.id.loginUsername)).perform(typeText(validUser.getUsername()));
        onView(withId(R.id.loginPin)).perform(typeText(validUser.getPinNumber()));
        onView(withId(R.id.pinLoginBtn)).perform(click());
        onView(withId(R.id.mainActivity)).check(matches(isDisplayed()));
    }

    // ES08 - Enter valid pin but no username and check that error message is shown.
    @Test
    public void testEmptyUsernameLogin(){
        onView(withId(R.id.loginPin)).perform(typeText(validUser.getPinNumber()));
        onView(withId(R.id.pinLoginBtn)).perform(click());
        onView(withId(R.id.loginError)).check(matches(isDisplayed()));
    }

    // ES09 - Check that activity navigates to Register Activity on Register Btn click
    @Test
    public void testRegisterButton(){
        onView(withId(R.id.btnRegister)).check(matches(isDisplayed()));
        onView(withId(R.id.btnRegister)).perform(click());
        onView(withId(R.id.registrationActivity)).check(matches(isDisplayed()));
    }

}