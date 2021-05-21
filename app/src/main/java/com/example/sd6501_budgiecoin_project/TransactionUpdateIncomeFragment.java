package com.example.sd6501_budgiecoin_project;

import android.util.Log;

public class TransactionUpdateIncomeFragment extends TransactionUpdateBaseFragment {

    @Override
    protected void UpdateTransaction(Transaction transaction) {

        double value = Double.parseDouble(transactionValue.getText().toString());

        if(value < 0){
            value = Math.abs(value);
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
            Log.i("BudgieCoin: ", "In try after DB instance");
            db.updateTransaction(tempTransaction);
            Log.i("BudgieCoin: ", tempTransaction.getName());
            db.close();

        } catch (Exception e){
            Log.e("BudgieCoin: ", "Exception Occurred: " + e);
        }
    }
}
