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

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public abstract class TransactionUpdateBaseFragment extends Fragment {

    public Button datePickerBtn, timePickerBtn, updateTransactionBtn, cancelBtn, deleteTransactionBtn;
    public EditText transactionName, transactionValue, transactionNote;
    protected SimpleDateFormat formatViewDate, formatStoreDate, formatTime;
    public Calendar transactionDate;
    public int transactionYear, transactionMonth, transactionDay, transactionHour, transactionMinute, selectedAccount;
    public Spinner accountSelectionSpinner;
    Transaction selectedTransaction;
    int transactionID;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstance) {
        super.onCreateView(inflater, container, savedInstance);
        View view = inflater.inflate(R.layout.transaction_update_base, container, false);

        transactionID = getActivity().getIntent().getExtras().getInt("TransactionID");

        // Connect layout input attributes to layout fields
        updateTransactionBtn = (Button)view.findViewById(R.id.btnUpdateTransaction);
        deleteTransactionBtn = (Button)view.findViewById(R.id.btnDeleteTransaction);
        cancelBtn = (Button)view.findViewById(R.id.btnCancelTransaction);
        datePickerBtn = (Button)view.findViewById(R.id.datePicker);
        timePickerBtn = (Button)view.findViewById(R.id.timePicker);
        transactionName = (EditText)view.findViewById(R.id.inputTransactionName);
        transactionValue = (EditText)view.findViewById(R.id.inputTransactionValue);
        transactionNote = (EditText)view.findViewById(R.id.inputTransactionNote);
        accountSelectionSpinner = view.findViewById(R.id.accountSelectionSpinner);

        // Setup and format date and time picker buttons.
        // By default the transaction is given the current date and time.
        formatViewDate = new SimpleDateFormat("dd/MM/yyyy");
        formatStoreDate = new SimpleDateFormat("yyyy/MM/dd");
        formatTime = new SimpleDateFormat("HH:mm");
        transactionDate = Calendar.getInstance();
        transactionYear = transactionDate.get(Calendar.YEAR);
        transactionMonth = transactionDate.get(Calendar.MONTH);
        transactionDay = transactionDate.get(Calendar.DAY_OF_MONTH);
        transactionHour = transactionDate.get(Calendar.HOUR_OF_DAY);
        transactionMinute = transactionDate.get(Calendar.MINUTE);
        datePickerBtn.setText(formatViewDate.format(transactionDate.getTime()));
        timePickerBtn.setText(formatTime.format(transactionDate.getTime()));


        DBHandler db = new DBHandler(getContext());
        Transaction tempTransaction = db.getTransaction(transactionID);
        transactionName.setText(tempTransaction.getName());
        transactionValue.setText(String.valueOf(tempTransaction.getValue()));
        transactionNote.setText(tempTransaction.getNote());

        // Create a DB instance and get an ArrayList of all the Accounts from it.
        ArrayList<Account> allAccounts;
        allAccounts = db.getAllAccounts();
        // Then create adapter, set adapter with spinner and allAccounts as source.
        AccountsBalancesAdapter adapter = new AccountsBalancesAdapter(getActivity(), 0, allAccounts);
        accountSelectionSpinner.setAdapter(adapter);

        // Loop through the adapter and set the selected item to the transaction's account.
        for(int i = 0; i < adapter.getCount(); i++){
            if(tempTransaction.getAccount() == adapter.getItem(i).getId()){
                accountSelectionSpinner.setSelection(i);
            }
        }

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

        // Set the onClick listener for the Time Picker Button.
        timePickerBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                TimePickerDialog timePicker = new TimePickerDialog(getActivity(), timeSetCallback, transactionHour, transactionMinute, true);
                timePicker.show();
            }
        });

        // Set the onClick listener for the Date Picker Button
        datePickerBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                DatePickerDialog datePicker = new DatePickerDialog(getActivity(), dateSetCallback, transactionYear, transactionMonth, transactionDay);
                datePicker.show();
            }
        });
        // When the Save Transaction btn is clicked, call the TransactionProcess, which should be implemented by the children.
        updateTransactionBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Log.i("BudgieCoin: ", "Update Btn Clicked");
                UpdateTransaction(tempTransaction);
                startActivity(new Intent(getContext(), ViewTransactionsActivity.class));
            }
        });

        deleteTransactionBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                AlertDialog confirmationDialog = new AlertDialog.Builder(getContext())
                        .setTitle(R.string.lbl_deleteTransaction)
                        .setMessage(R.string.msg_deleteTransaction)
                        .setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                db.deleteTransaction(transactionID);
                                Toast.makeText(getActivity(), R.string.t_transactionDeleted, Toast.LENGTH_LONG).show();
                                startActivity(new Intent(getContext(), ViewTransactionsActivity.class));
                            }
                        })
                        .setNegativeButton(R.string.no, null).show();
                confirmationDialog.show();

            }
        });

        // Set the onClick listener for the Cancel Btn, if clicked return to the MainActivity.
        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), R.string.t_transactionUpdateCancelled, Toast.LENGTH_LONG).show();
                Intent home = new Intent(getActivity(), ViewTransactionsActivity.class);
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
            datePickerBtn.setText(formatViewDate.format(transactionDate.getTime()));
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

    protected abstract void UpdateTransaction(Transaction transaction);
}
