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
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static org.junit.Assert.*;

public class RegistrationActivityTest {

    @Rule
    public ActivityTestRule<RegistrationActivity> activityTestRule = new ActivityTestRule<>(RegistrationActivity.class);
    Activity registrationActivity;
    User testUser = new User("test-1", "9999");
    User testUserCancel = new User("Cancel Test", "9999");


    @Before
    public void setUp() throws Exception {
        registrationActivity = activityTestRule.getActivity();
    }

    @After
    public void tearDown() throws Exception {
        registrationActivity = null;
    }

    // ES14 - Check that all expected view elements are present
    @Test
    public void checkAllViewElementsPresent(){
        onView(withId(R.id.registrationActivity)).check(matches(isDisplayed()));
        onView(withId(R.id.et_username)).check(matches(isDisplayed()));
        onView(withId(R.id.et_pin)).check(matches(isDisplayed()));
        onView(withId(R.id.btnRegister)).check(matches(isDisplayed()));
        onView(withId(R.id.btnCancel)).check(matches(isDisplayed()));
    }

    // ES15 - Check that cancel btn navigates to correct activity
    @Test
    public void testCancelButton(){
        onView(withId(R.id.et_username)).perform(typeText(testUserCancel.getUsername()));
        onView(withId(R.id.et_pin)).perform(typeText(testUserCancel.getPinNumber()));
        onView(withId(R.id.btnCancel)).perform(click());
        onView(withId(R.id.loginActivity)).check(matches(isDisplayed()));
    }

    // ES16 - Check that the registration button submits and navigates to Main Activity.
    @Test
    public void testRegisterButton(){
        onView(withId(R.id.et_username)).perform(typeText(testUser.getUsername()));
        onView(withId(R.id.et_pin)).perform(typeText(testUser.getPinNumber()));
        onView(withId(R.id.btnRegister)).perform(click());
        onView(withId(R.id.mainActivity)).check(matches(isDisplayed()));
    }
}