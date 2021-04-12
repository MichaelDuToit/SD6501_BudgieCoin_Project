package com.example.sd6501_budgiecoin_project;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class AddTransactionActivity extends AppCompatActivity {

    public Toolbar actionBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_transaction);

        actionBar = (Toolbar)findViewById(R.id.appBar);
        setSupportActionBar(actionBar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    public void saveTransaction(View v){
        try {
            Toast.makeText(this, "Transaction Created", Toast.LENGTH_SHORT).show();
        }
        catch(Exception e) {
            Toast.makeText(this, "Error Occurred. Please try again", Toast.LENGTH_LONG).show();
        }
    }
    public void returnHome(View v){
        Toast.makeText(this, "Transaction entry has been cancelled", Toast.LENGTH_LONG).show();
        Intent home = new Intent(this, MainActivity.class);
        startActivity(home);
    }
}
