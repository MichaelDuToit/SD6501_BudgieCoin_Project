package com.example.sd6501_budgiecoin_project;

import android.content.Intent;
import android.os.Bundle;

import androidx.test.rule.ActivityTestRule;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static org.junit.Assert.*;

public class ViewAccountActivityTest {

    @Rule
    public ActivityTestRule<ViewAccountActivity> activityActivityTestRule = new ActivityTestRule<>(ViewAccountActivity.class, false, false);
    //ViewAccountActivity activity;


    @Before
    public void setUp() throws Exception {
        int accountIDStub = 7;
        Intent i = new Intent();
        Bundle bundle = new Bundle();
        bundle.putInt("AccountID", accountIDStub);
        i.putExtras(bundle);
        activityActivityTestRule.launchActivity(i);
    }

    @After
    public void tearDown() throws Exception {
        activityActivityTestRule.finishActivity();
        activityActivityTestRule = null;
    }

    // Check that all the view elements are present
    @Test
    public void testViewElementsPresent(){
        onView(withId(R.id.accountName)).check(matches(isDisplayed()));
        onView(withId(R.id.updateBtn)).check(matches(isDisplayed()));
        onView(withId(R.id.deleteBtn)).check(matches(isDisplayed()));
    }

}