package com.example.sd6501_budgiecoin_project;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class TransactionUpdateExpenseFragment extends TransactionUpdateBaseFragment {

    @Override
    protected void UpdateTransaction(Transaction transaction) {

        double value = Double.parseDouble(transactionValue.getText().toString());

        if(value > 0){
            value = -value;
        }

        try {
            Transaction tempTransaction = new Transaction(
                    transactionName.getText().toString(),
                    value,
                    formatStoreDate.format(transactionDate.getTime()),
                    timePickerBtn.getText().toString(),
                    selectedAccount,
                    transactionNote.getText().toString()
            );

            DBHandler db = new DBHandler(getActivity());
            db.updateTransaction(tempTransaction);

        } catch (Exception e){
            Log.e("BudgieCoin: ", "Exception Occurred: " + e);
        }
    }

}
