package com.example.sd6501_budgiecoin_project;

import androidx.test.rule.ActivityTestRule;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static androidx.test.espresso.Espresso.onData;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class ViewTransactionsActivityTest {

    @Rule
    public ActivityTestRule<ViewTransactionsActivity> activityActivityTestRule = new ActivityTestRule<>(ViewTransactionsActivity.class);
    ViewTransactionsActivity activity;

    @Before
    public void setUp() throws Exception {
        activity = activityActivityTestRule.getActivity();
    }

    @After
    public void tearDown() throws Exception {
        activity = null;
    }

    // Check that all the view elements are present
    @Test
    public void checkAllViewElementsPresent(){
        onView(withId(R.id.viewTransactionsActivity)).check(matches(isDisplayed()));
    }
}