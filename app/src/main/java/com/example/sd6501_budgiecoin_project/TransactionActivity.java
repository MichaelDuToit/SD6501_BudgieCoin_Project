package com.example.sd6501_budgiecoin_project;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.google.android.material.tabs.TabLayout;

public class TransactionActivity extends AppCompatActivity {

    public Toolbar actionBar;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private TransactionViewAdapter transactionViewAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transaction);

        // Setup the custom ActionBar / Toolbar.
        actionBar = (Toolbar)findViewById(R.id.appBar);
        setSupportActionBar(actionBar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(R.string.addTransactionLbl);

        tabLayout = findViewById(R.id.transactionTabLayout);
        viewPager = findViewById(R.id.viewPager);

        transactionViewAdapter = new TransactionViewAdapter(getSupportFragmentManager(), FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        transactionViewAdapter.addFragment(new TransactionIncomeFragment(),"Income");
        transactionViewAdapter.addFragment(new TransactionExpenseFragment(), "Expense");

        viewPager.setAdapter(transactionViewAdapter);
        tabLayout.setupWithViewPager(viewPager);
    }
}