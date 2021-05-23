package com.example.sd6501_budgiecoin_project;

import androidx.test.rule.ActivityTestRule;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static androidx.test.espresso.Espresso.closeSoftKeyboard;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.junit.Assert.*;

public class AddAccountActivityTest {

    @Rule
    public ActivityTestRule<AddAccountActivity> activityActivityTestRule= new ActivityTestRule<>(AddAccountActivity.class);
    AddAccountActivity activity;
    String testAccountInput;

    @Before
    public void setUp() throws Exception {
        activity = activityActivityTestRule.getActivity();
        testAccountInput = "Espresso Test Account";
    }

    @After
    public void tearDown() throws Exception {
        activity = null;
    }

    // Check that all the view elements are present
    @Test
    public void checkAllViewElementsPresent(){
        onView(withId(R.id.addAccountActivity)).check(matches(isDisplayed()));
        onView(withId(R.id.accountName)).check(matches(isDisplayed()));
        onView(withId(R.id.saveAccountBtn)).check(matches(isDisplayed()));
        onView(withId(R.id.cancelAccountBtn)).check(matches(isDisplayed()));
    }

    @Test
    public void testAccountCreation(){
        onView(withId(R.id.accountName)).perform(typeText(testAccountInput));
        closeSoftKeyboard();
        onView(withId(R.id.saveAccountBtn)).perform(click());
        onView(withId(R.id.accountsList)).check(matches(isDisplayed()));
        onView(withText(testAccountInput));
    }

    @Test
    public void testAccountCancellation(){
        onView(withId(R.id.cancelAccountBtn)).check(matches(isDisplayed()));
        onView(withId(R.id.cancelAccountBtn)).perform(click());
        onView(withId(R.id.mainActivity)).check(matches(isDisplayed()));
    }

}