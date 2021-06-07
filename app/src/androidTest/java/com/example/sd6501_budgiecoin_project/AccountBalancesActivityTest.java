package com.example.sd6501_budgiecoin_project;

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

public class AccountBalancesActivityTest {

    @Rule
    public ActivityTestRule<AccountBalancesActivity> activityTestRule = new ActivityTestRule<>(AccountBalancesActivity.class);
    AccountBalancesActivity activity;
    DBHandler db;

    @Before
    public void setUp() throws Exception {
        activity = activityTestRule.getActivity();
        db = new DBHandler(activity.getApplicationContext());
        db.createAccount(new Account("Account Balances Test"));
    }

    @After
    public void tearDown() throws Exception {
        activity = null;
    }

    // Check that all the view elements are present
    @Test
    public void checkAllViewElementsPresent(){
        onView(withId(R.id.accountBalanceActivity)).check(matches(isDisplayed()));
        onView(withId(R.id.accountsList)).check(matches(isDisplayed()));
    }
}