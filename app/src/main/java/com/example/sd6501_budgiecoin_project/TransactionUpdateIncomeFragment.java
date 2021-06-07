package com.example.sd6501_budgiecoin_project;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
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

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class TransactionUpdateIncomeFragment extends TransactionUpdateBaseFragment {

    @Override
    protected void UpdateTransaction() {
        double value = Double.parseDouble(transactionValue.getText().toString());

        if(value < 0) {
            value = Math.abs(value);
        }

        try {
            Transaction tempTransaction = new Transaction(
                    transactionID,
                    transactionName.getText().toString(),
                    value,
                    transactionDate,
                    selectedAccount,
                    transactionNote.getText().toString()
            );

            DBHandler db = new DBHandler(getActivity());
            Log.i("BudgieCoin: ", "In try block after DBHandler");
            db.updateTransaction(tempTransaction);
            Log.i("BudgieCoin: ", String.valueOf(db.getTransaction(tempTransaction.getId())));

        } catch (Exception e) {
            Log.e("BudgieCoin: ", "Exception Occurred: " + e);
        }
        startActivity(new Intent(getContext(), ViewTransactionsActivity.class));
    }
}