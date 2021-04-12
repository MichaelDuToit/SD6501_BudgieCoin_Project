package com.example.sd6501_budgiecoin_project;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.DialogFragment;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CalendarView;
import android.widget.Toast;

import java.util.Calendar;

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

    public void showDatePickerDialog(View v){
        DialogFragment dialogFragment = new DialogFragment();
        dialogFragment.show(getSupportFragmentManager(), "datePicker");
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