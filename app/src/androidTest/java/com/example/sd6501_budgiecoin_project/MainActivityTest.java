package com.example.sd6501_budgiecoin_project;

import androidx.test.espresso.Espresso;
import androidx.test.rule.ActivityTestRule;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.pressBack;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static org.junit.Assert.*;

public class MainActivityTest {
    @Rule
    public ActivityTestRule<MainActivity> activityActivityTestRule = new ActivityTestRule<>(MainActivity.class);
    MainActivity mainActivity;

    @Before
    public void setUp() throws Exception {
        mainActivity = activityActivityTestRule.getActivity();
    }

    @After
    public void tearDown() throws Exception {
        mainActivity = null;
    }

    // Check that the Add Transaction Button performs and goes to Transaction Activity
    @Test
    public void testAddTransactionButton(){
        onView(withId(R.id.btnAddTransaction)).check(matches(isDisplayed()));
        onView(withId(R.id.btnAddTransaction)).perform(click());
        onView(withId(R.id.transactionTabLayout)).check(matches(isDisplayed()));
    }
    // Check that the View Transaction Button performs and goes to the Transactions listing activity.
    @Test
    public void testViewTransactionsButton(){
        onView(withId(R.id.btnViewTransactions)).check(matches(isDisplayed()));
        onView(withId(R.id.btnViewTransactions)).perform(click());
        onView(withId(R.id.listAllTransactions)).check(matches(isDisplayed()));
    }

    // Check that the View Account Balances Button performs and goes to the Accounts listing activity.
    @Test
    public void testViewAccountBalances(){
        onView(withId(R.id.btnViewAccounts)).check(matches(isDisplayed()));
        onView(withId(R.id.btnViewAccounts)).perform(click());
        onView(withId(R.id.accountsList)).check(matches(isDisplayed()));
    }
}