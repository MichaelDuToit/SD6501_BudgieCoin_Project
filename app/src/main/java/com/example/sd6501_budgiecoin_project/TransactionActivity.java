package com.example.sd6501_budgiecoin_project;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.util.Log;

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
        getSupportActionBar().setTitle(R.string.lbl_createTransaction);

        // Setup the TabLayout and ViewPager view components.
        tabLayout = findViewById(R.id.transactionTabLayout);
        viewPager = findViewById(R.id.viewPager);

        // Create the TransactionViewAdapter and add the specified fragments to it.
        transactionViewAdapter = new TransactionViewAdapter(getSupportFragmentManager(), FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        transactionViewAdapter.addFragment(new TransactionIncomeFragment(),"Income");
        transactionViewAdapter.addFragment(new TransactionExpenseFragment(), "Expense");

        // Set the viewPager's adapter to the transactionViewAdaptor and then set tabLayout with the viewPager.
        viewPager.setAdapter(transactionViewAdapter);
        tabLayout.setupWithViewPager(viewPager);



    }
}