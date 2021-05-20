package com.example.sd6501_budgiecoin_project;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class TransactionIncomeFragment extends Fragment {

    public Button datePickerBtn, timePickerBtn, saveTransactionBtn, cancelBtn;
    public EditText transactionName, transactionValue, transactionNote;
    protected SimpleDateFormat formatDate, formatTime;
    public Calendar transactionDate;
    public int transactionYear, transactionMonth, transactionDay, transactionHour, transactionMinute, selectedAccount;
    public Spinner accountSelectionSpinner;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstance){
        View view = inflater.inflate(R.layout.transaction_income, container, false);

        // Connect layout input attributes to layout fields
        saveTransactionBtn = (Button)view.findViewById(R.id.btnSaveTransaction);
        cancelBtn = (Button)view.findViewById(R.id.btnCancelTransaction);
        datePickerBtn = (Button)view.findViewById(R.id.datePicker);
        timePickerBtn = (Button)view.findViewById(R.id.timePicker);
        transactionName = (EditText)view.findViewById(R.id.inputTransactionName);
        transactionValue = (EditText)view.findViewById(R.id.inputTransactionValue);
        transactionNote = (EditText)view.findViewById(R.id.inputTransactionNote);
        accountSelectionSpinner = view.findViewById(R.id.accountSelectionSpinner);

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

        DBHandler db = new DBHandler(getActivity());
        ArrayList<Account> allAccounts;
        allAccounts = db.getAllAccounts();
        // then create adapter, set adapter with spinner and allAccounts as source.
        AccountsBalancesAdapter adapter = new AccountsBalancesAdapter(getActivity(), 0, allAccounts);
        accountSelectionSpinner.setAdapter(adapter);
        accountSelectionSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            // Override the Spinner's onItemSelected method. When an item is picked, get the id of the account.
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Account account = (Account) parent.getItemAtPosition(position);
                selectedAccount = account.getId();
            }
            // Override the Spinner's onNothingSelected.
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        timePickerBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                TimePickerDialog timePicker = new TimePickerDialog(getActivity(), timeSetCallback, transactionHour, transactionMinute, true);
                timePicker.show();
            }
        });

        datePickerBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                DatePickerDialog datePicker = new DatePickerDialog(getActivity(), dateSetCallback, transactionYear, transactionMonth, transactionDay);
                datePicker.show();
            }
        });

        saveTransactionBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                try {
                    // Assignment 2 - store transaction values to Transaction obj which is then added to DB
                    Transaction tempTransaction = new Transaction(
                            transactionName.getText().toString(),
                            Double.parseDouble(transactionValue.getText().toString()),
                            datePickerBtn.getText().toString(),
                            timePickerBtn.getText().toString(),
                            selectedAccount,
                            transactionNote.getText().toString()
                    );
                    DBHandler db = new DBHandler(getActivity());
                    db.createTransaction(tempTransaction);

                    Toast.makeText(getActivity(), R.string.t_transactionCreated, Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(getContext(), ViewTransactionsActivity.class));
                }
                catch(Exception e) {
                    Toast.makeText(getActivity(), R.string.t_transactionError, Toast.LENGTH_LONG).show();
                }
            }
        });

        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), R.string.t_accountCanceled, Toast.LENGTH_LONG).show();
                Intent home = new Intent(getActivity(), MainActivity.class);
                startActivity(home);
            }
        });

        return view;
    }

    // Management of the DatePicker Dialog when a Date has been selected.
    DatePickerDialog.OnDateSetListener dateSetCallback = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
            transactionDate.set(year, month, dayOfMonth);
            datePickerBtn.setText(formatDate.format(transactionDate.getTime()));
        }
    };

    // Management of the TimePicker Dialog when the Time has been selected.
    TimePickerDialog.OnTimeSetListener timeSetCallback = new TimePickerDialog.OnTimeSetListener() {
        @Override
        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
            transactionDate.set(transactionYear, transactionMonth, transactionDay, hourOfDay, minute);
            timePickerBtn.setText(formatTime.format(transactionDate.getTime()));
        }
    };
}
