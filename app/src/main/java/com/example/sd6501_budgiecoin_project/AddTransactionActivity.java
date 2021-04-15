package com.example.sd6501_budgiecoin_project;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.DialogFragment;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class AddTransactionActivity extends AppCompatActivity {

    public Toolbar actionBar;
    public Button datePickerBtn, timePickerBtn;
    public EditText transactionName, transactionValue, transactionNote;
    public Date transactionDateObj;
    protected TimePickerDialog timePickerDialog;
    protected DatePickerDialog datePickerDialog;
    protected SimpleDateFormat formatDate, formatTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_transaction);

        actionBar = (Toolbar)findViewById(R.id.appBar);
        setSupportActionBar(actionBar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // Connect input attributes to layout fields
        datePickerBtn = (Button)findViewById(R.id.datePicker);
        timePickerBtn = (Button)findViewById(R.id.timePicker);
        transactionName = (EditText)findViewById(R.id.inputTransactionName);
        transactionValue = (EditText)findViewById(R.id.inputTransactionValue);
        transactionNote = (EditText)findViewById(R.id.inputTransactionNote);

        // Setup and format date and time picker buttons.
        // By default the transaction is given the current date and time.
        transactionDateObj = new Date();
        formatDate = new SimpleDateFormat("dd/MM/yyyy");
        formatTime = new SimpleDateFormat("HH:mm");
        datePickerBtn.setText(formatDate.format(transactionDateObj));
        timePickerBtn.setText(formatTime.format(transactionDateObj));
    }

    public void showDatePickerDialog(View v){

        DialogFragment dialogFragment = new DatePickerFragment();
        dialogFragment.show(getSupportFragmentManager(), "datePicker");

        // Assignment 2 - Update datePicker button to show the new date on it
    }

    public void showTimePickerDialog(View v){
        /*
            Time Picker DialogFragment Code
         */
        // Update timePicker button to show the new date on it
    }

    public void saveTransaction(View v){
        try {
            Toast.makeText(this, "Transaction Created", Toast.LENGTH_SHORT).show();
            // Assignment 2 - store transaction values to Transaction obj which is then added to DB.
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