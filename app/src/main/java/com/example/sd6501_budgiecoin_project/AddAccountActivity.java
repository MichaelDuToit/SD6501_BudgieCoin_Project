package com.example.sd6501_budgiecoin_project;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class AddAccountActivity extends AppCompatActivity {

    public Toolbar actionBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_account);

        // Setup the custom ActionBar / Toolbar.
        actionBar = (Toolbar)findViewById(R.id.appBar);
        setSupportActionBar(actionBar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(R.string.addAccountLbl);
    }

    // If the Save Btn is clicked, call this method to (eventually) store the Account
    // then go to the Account Balances Activity.
    public void createAccount(View v){
        Toast.makeText(this,"Account Created", Toast.LENGTH_SHORT).show();
        startActivity(new Intent(this, AccountBalancesActivity.class));
    }

    // If the Cancel Btn is clicked, call this method to return to the MainActivity
    public void cancelBtn(View v){
        Toast.makeText(this, "Account Creation Canceled", Toast.LENGTH_SHORT).show();
        startActivity(new Intent(this, MainActivity.class));
    }
}