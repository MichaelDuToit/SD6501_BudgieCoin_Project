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
import android.widget.TimePicker;
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
    public Calendar transactionDate;
    public int transactionYear, transactionMonth, transactionDay, transactionHour, transactionMinute;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_transaction);

        // Setup the custom ActionBar / Toolbar.
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
        formatDate = new SimpleDateFormat("dd/MM/yyyy");
        formatTime = new SimpleDateFormat("HH:mm");
        transactionDate = Calendar.getInstance();
        transactionYear = transactionDate.get(Calendar.YEAR);
        transactionMonth = transactionDate.get(Calendar.MONTH);
        transactionDay = transactionDate.get(Calendar.DAY_OF_MONTH);
        transactionHour = transactionDate.get(Calendar.HOUR_OF_DAY);
        transactionMinute = transactionDate.get(Calendar.MINUTE);
        datePickerBtn.setText(formatDate.format(transactionDate.getTime()));
        timePickerBtn.setText(formatTime.format(transactionDate.getTime()));

    }

    // Method to call the DatePickerDialog
    public void showDatePickerDialog(View v){
        DatePickerDialog datePicker = new DatePickerDialog(this, dateSetCallback, transactionYear, transactionMonth, transactionDay);
        datePicker.show();
    }

    // Management of the DatePicker Dialog when a Date has been selected.
    DatePickerDialog.OnDateSetListener dateSetCallback = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
            transactionDate.set(year, month, dayOfMonth);
            datePickerBtn.setText(formatDate.format(transactionDate.getTime()));
        }
    };

    // Method to call the TimePickerDialog
    public void showTimePickerDialog(View v){
        TimePickerDialog timePicker = new TimePickerDialog(this, R.style.CustomDialogTheme, timeSetCallback, transactionHour, transactionMinute, true);
        timePicker.show();
    }

    // Management of the TimePicker Dialog when the Time has been selected.
    TimePickerDialog.OnTimeSetListener timeSetCallback = new TimePickerDialog.OnTimeSetListener() {
        @Override
        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
            transactionDate.set(transactionYear, transactionMonth, transactionDay, hourOfDay, minute);
            timePickerBtn.setText(formatTime.format(transactionDate.getTime()));
        }
    };

    // Method to manage when the Save Btn is clicked to save a Transaction.
    public void saveTransaction(View v){
        try {
            Toast.makeText(this, "Transaction Created", Toast.LENGTH_SHORT).show();
            // Assignment 2 - store transaction values to Transaction obj which is then added to DB.
        }
        catch(Exception e) {
            Toast.makeText(this, "Error Occurred. Please try again", Toast.LENGTH_LONG).show();
        }
    }

    // Method to manage when the Cancel Btn is clicked to cancel a Transaction.
    public void returnHome(View v){
        Toast.makeText(this, "Transaction entry has been cancelled", Toast.LENGTH_LONG).show();
        Intent home = new Intent(this, MainActivity.class);
        startActivity(home);
    }
}