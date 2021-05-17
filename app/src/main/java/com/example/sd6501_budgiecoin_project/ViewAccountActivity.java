package com.example.sd6501_budgiecoin_project;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.widget.Toolbar;

public class ViewAccountActivity extends AppCompatActivity {

    private Toolbar actionBar;
    Bundle intentBundle;
    int accountID;
    EditText etAccountName;
    Button updateBtn, deleteBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_account);

        updateBtn = findViewById(R.id.updateBtn);
        deleteBtn = findViewById(R.id.deleteBtn);

        // Get data passed with intent;
        intentBundle = getIntent().getExtras();
        accountID = intentBundle.getInt("AccountID");

        // Setup the custom ActionBar / Toolbar.
        actionBar = (Toolbar)findViewById(R.id.appBar);
        setSupportActionBar(actionBar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(R.string.addTransactionLbl);

        // DB connection and get the specified account
        DBHandler db = new DBHandler(this);
        Account account = db.getAccount(accountID);

        // Set the name field to the account's name.
        etAccountName = findViewById(R.id.accountName);
        etAccountName.setText(account.getName());
    }

    public void updateAccount(View v){
        DBHandler db = new DBHandler(this);
        String newName = etAccountName.getText().toString();
        db.updateAccount(accountID, newName);
        startActivity(new Intent(this, AccountBalancesActivity.class));
    }

    public void deleteAccount(View v){
        DBHandler db = new DBHandler(this);
        AlertDialog confirmationDialog = new AlertDialog.Builder(this)
                .setTitle("Delete Account")
                .setMessage("Are you sure you want to delete this account?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        db.deleteAccount(accountID);
                        startActivity(new Intent(getApplicationContext(), AccountBalancesActivity.class));
                    }
                })
                .setNegativeButton("No", null).show();
        confirmationDialog.show();
    }
}