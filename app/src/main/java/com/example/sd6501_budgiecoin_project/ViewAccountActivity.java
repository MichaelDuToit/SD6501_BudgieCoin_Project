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
    Account selectedAccount;

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
        getSupportActionBar().setTitle(R.string.account);

        // DB connection and get the specified account
        DBHandler db = new DBHandler(this);
        selectedAccount = db.getAccount(accountID);

        // Set the name field to the account's name.
        etAccountName = findViewById(R.id.accountName);
        etAccountName.setText(selectedAccount.getName());
    }

    // Method for when the Update Button in the view is clicked. Update the selected account with the new details.
    public void updateAccount(View v){
        DBHandler db = new DBHandler(this);
        String newName = etAccountName.getText().toString();
        selectedAccount.setName(etAccountName.getText().toString());
        db.updateAccount(selectedAccount);
        startActivity(new Intent(this, AccountBalancesActivity.class));
    }

    // Method for when the Delete Btn in the view is clicked. Prompt user to confirm deletion,
    // if confirmed delete the selected account.
    public void deleteAccount(View v){
        DBHandler db = new DBHandler(this);
        AlertDialog confirmationDialog = new AlertDialog.Builder(this)
                .setTitle(R.string.lbl_deleteAccount)
                .setMessage(R.string.msg_deleteAccount)
                .setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        db.deleteAccount(accountID);
                        startActivity(new Intent(getApplicationContext(), AccountBalancesActivity.class));
                    }
                })
                .setNegativeButton(R.string.no, null).show();
        confirmationDialog.show();
    }
}