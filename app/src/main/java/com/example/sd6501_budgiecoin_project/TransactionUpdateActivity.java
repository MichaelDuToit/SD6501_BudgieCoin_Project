package com.example.sd6501_budgiecoin_project;

import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;

public class TransactionUpdateActivity extends AppCompatActivity {
    public Toolbar actionBar;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private TransactionViewAdapter transactionViewAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transaction);

        // Setup the custom ActionBar / Toolbar.
        actionBar = (Toolbar) findViewById(R.id.appBar);
        setSupportActionBar(actionBar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(R.string.lbl_updateTransaction);

        tabLayout = findViewById(R.id.transactionTabLayout);
        viewPager = findViewById(R.id.viewPager);

        // Create the TransactionViewAdapter and add the specified fragments to it.
        transactionViewAdapter = new TransactionViewAdapter(getSupportFragmentManager(), FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);

        if (getIntent().getExtras() != null) {
            double value = getIntent().getExtras().getDouble("TransactionValue");
            Log.i("BudgieCoin: ", "Extras Transaction Value: " + value);
            int pagerIndex;
            if (value < 0.0) {
                pagerIndex = 1;
            } else {
                pagerIndex = 0;
            }
            transactionViewAdapter.addFragment(new TransactionUpdateIncomeFragment(), "Income");
            transactionViewAdapter.addFragment(new TransactionUpdateExpenseFragment(), "Expense");
            viewPager.setCurrentItem(pagerIndex);
        }
        // Set the viewPager's adapter to the transactionViewAdaptor and then set tabLayout with the viewPager.
        viewPager.setAdapter(transactionViewAdapter);
        tabLayout.setupWithViewPager(viewPager);
    }
}
