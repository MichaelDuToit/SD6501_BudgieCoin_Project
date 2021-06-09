package com.example.sd6501_budgiecoin_project;

import androidx.test.rule.ActivityTestRule;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.junit.Assert.*;

public class SettingsActivityTest {

    @Rule
    public ActivityTestRule<SettingsActivity> activityTestRule = new ActivityTestRule<>(SettingsActivity.class);

    @Before
    public void setUp() throws Exception {
        activityTestRule.getActivity();
    }

    @After
    public void tearDown() throws Exception {
        activityTestRule = null;
    }

    // ES21 - Test that the Settings items are displayed
    @Test
    public void testAllViewElementsDisplayed(){
        onView(withText("Username")).check(matches(isDisplayed()));
        onView(withText("PIN")).check(matches(isDisplayed()));
    }

}