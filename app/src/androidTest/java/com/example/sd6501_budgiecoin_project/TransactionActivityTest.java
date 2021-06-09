package com.example.sd6501_budgiecoin_project;

import android.os.SystemClock;
import android.view.View;

import androidx.test.espresso.Espresso;
import androidx.test.espresso.ViewAction;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.rule.ActivityTestRule;
import androidx.viewpager.widget.ViewPager;

import org.hamcrest.Matcher;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static androidx.test.espresso.Espresso.onData;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isCompletelyDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.isDescendantOfA;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.anything;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class TransactionActivityTest {

    @Rule
    public ActivityTestRule<TransactionActivity> activityTestRule = new ActivityTestRule<>(TransactionActivity.class);
    public Matcher<View> matcher;
    String testTransactionName, testTransactionValue, testTransactionNote;

    @Before
    public void setUp() throws Exception {
        activityTestRule.getActivity().getSupportFragmentManager().beginTransaction();
        testTransactionName = "Espresso Test Transaction";
        testTransactionNote = "Espresso";
        testTransactionValue = "5.0";

    }

    @After
    public void tearDown() throws Exception {
        activityTestRule = null;
    }

    // ES22 - Check that all UI elements are present on the Income Tab
    @Test
    public void checkViewElementsIncomePresent() {
        Matcher<View> matcher = allOf(withText("Income"), isDescendantOfA(withId(R.id.transactionTabLayout)));
        onView(matcher).perform(click());
        SystemClock.sleep(200);
        onView(allOf(withId(R.id.inputTransactionName), isCompletelyDisplayed()));
        onView(allOf(withId(R.id.datePicker), isCompletelyDisplayed()));
        onView(allOf(withId(R.id.timePicker), isCompletelyDisplayed()));
        onView(allOf(withId(R.id.accountSelectionSpinner), isCompletelyDisplayed()));
        onView(allOf(withId(R.id.inputTransactionValue), isCompletelyDisplayed()));
        onView(allOf(withId(R.id.inputTransactionNote), isCompletelyDisplayed()));
        onView(allOf(withId(R.id.btnCancelTransaction), isCompletelyDisplayed()));
        onView(allOf(withId(R.id.btnSaveTransaction), isCompletelyDisplayed()));
    }

    // ES23 - Check that all the UI Elements are present on the Expense Tab
    @Test
    public void checkViewElementsExpensePresent() {
        Matcher<View> matcher = allOf(withText("Expense"), isDescendantOfA(withId(R.id.transactionTabLayout)));
        onView(matcher).perform(click());
        SystemClock.sleep(200);
        onView(allOf(withId(R.id.inputTransactionName), isCompletelyDisplayed()));
        onView(allOf(withId(R.id.datePicker), isCompletelyDisplayed()));
        onView(allOf(withId(R.id.timePicker), isCompletelyDisplayed()));
        onView(allOf(withId(R.id.accountSelectionSpinner), isCompletelyDisplayed()));
        onView(allOf(withId(R.id.inputTransactionValue), isCompletelyDisplayed()));
        onView(allOf(withId(R.id.inputTransactionNote), isCompletelyDisplayed()));
        onView(allOf(withId(R.id.btnCancelTransaction), isCompletelyDisplayed()));
        onView(allOf(withId(R.id.btnSaveTransaction), isCompletelyDisplayed()));
    }

    //ES24 - Create an Income Transaction
    @Test
    public void createIncomeTransaction() {
        Matcher<View> matcher = allOf(withText("Income"), isDescendantOfA(withId(R.id.transactionTabLayout)));
        onView(matcher).perform(click());
        SystemClock.sleep(200);
        onView(allOf(withId(R.id.inputTransactionName), isCompletelyDisplayed())).perform(typeText(testTransactionName));
        onView(allOf(withId(R.id.datePicker), isCompletelyDisplayed()));
        onView(allOf(withId(R.id.timePicker), isCompletelyDisplayed()));
        onView(allOf(withId(R.id.accountSelectionSpinner), isCompletelyDisplayed())).perform(click());
        onData(anything()).atPosition(0).perform(click());
        onView(allOf(withId(R.id.inputTransactionValue), isCompletelyDisplayed())).perform(typeText(testTransactionValue));
        Espresso.closeSoftKeyboard();
        onView(allOf(withId(R.id.inputTransactionNote), isCompletelyDisplayed())).perform(typeText(testTransactionNote));
        Espresso.closeSoftKeyboard();
        onView(allOf(withId(R.id.btnSaveTransaction), isCompletelyDisplayed())).perform(click());
        onView(withId(R.id.viewTransactionsActivity)).check(matches(isDisplayed()));
    }

    // ES25 - Create an Expense Transaction
    @Test
    public void createExpenseTransaction() {
        Matcher<View> matcher = allOf(withText("Expense"), isDescendantOfA(withId(R.id.transactionTabLayout)));
        onView(matcher).perform(click());
        SystemClock.sleep(200);
        onView(allOf(withId(R.id.inputTransactionName), isCompletelyDisplayed())).perform(typeText(testTransactionName));
        onView(allOf(withId(R.id.datePicker), isCompletelyDisplayed()));
        onView(allOf(withId(R.id.timePicker), isCompletelyDisplayed()));
        onView(allOf(withId(R.id.accountSelectionSpinner), isCompletelyDisplayed())).perform(click());
        onData(anything()).atPosition(0).perform(click());
        onView(allOf(withId(R.id.inputTransactionValue), isCompletelyDisplayed())).perform(typeText(testTransactionValue));
        Espresso.closeSoftKeyboard();
        onView(allOf(withId(R.id.inputTransactionNote), isCompletelyDisplayed())).perform(typeText(testTransactionNote));
        Espresso.closeSoftKeyboard();
        onView(allOf(withId(R.id.btnSaveTransaction), isCompletelyDisplayed())).perform(click());
        onView(withId(R.id.viewTransactionsActivity)).check(matches(isDisplayed()));
    }


}