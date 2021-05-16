package com.example.sd6501_budgiecoin_project;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class AddAccountActivity extends AppCompatActivity {

    public Toolbar actionBar;
    EditText editTxtAccountName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_account);

        // Setup the custom ActionBar / Toolbar.
        actionBar = (Toolbar)findViewById(R.id.appBar);
        setSupportActionBar(actionBar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(R.string.addAccountLbl);

        editTxtAccountName = findViewById(R.id.accountName);
    }

    public void createAccount(View v){
        try {
            DBHandler db = new DBHandler(getApplicationContext());
            db.createAccount(new Account(editTxtAccountName.getText().toString()));
            Toast.makeText(this, R.string.t_accountCreated, Toast.LENGTH_SHORT).show();
            startActivity(new Intent(this, AccountBalancesActivity.class));
        } catch (Exception e) {
            Toast.makeText(this, R.string.t_accountError, Toast.LENGTH_SHORT).show();
        }
    }

    // If the Cancel Btn is clicked, call this method to return to the MainActivity
    public void cancelBtn(View v){
        Toast.makeText(this, R.string.t_accountCanceled, Toast.LENGTH_SHORT).show();
        startActivity(new Intent(this, MainActivity.class));
    }
}