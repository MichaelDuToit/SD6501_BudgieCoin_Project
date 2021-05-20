package com.example.sd6501_budgiecoin_project;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
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

import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class TransactionExpenseFragment extends TransactionBaseFragment {
    @Override
    public void TransactionProcess() {
        try {
            Double negativeValue = Double.parseDouble(transactionValue.getText().toString());
            negativeValue = -negativeValue;
            // Assignment 2 - store transaction values to Transaction obj which is then added to DB
            Transaction tempTransaction = new Transaction(
                    transactionName.getText().toString(),
                    negativeValue,
                    formatStoreDate.format(transactionDate.getTime()),
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
            Log.e("BudgieCoin: ", "Exception Occurred: " + e);
        }
    }
}
