package com.example.sd6501_budgiecoin_project;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Locale;
import java.util.concurrent.ExecutionException;

public class TransactionsAdapter extends ArrayAdapter<Transaction> {
    // Fields
    private Activity activity;
    private ArrayList<Transaction> transactions;
    private static LayoutInflater inflater = null;



    // Default Constructor (Required)
    public TransactionsAdapter(Activity activity, int textViewResourceID, ArrayList<Transaction> transactions){
        super(activity, textViewResourceID, transactions);
        try {
            this.activity = activity;
            this.transactions = transactions;
            inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        } catch (Exception ex){
            Log.e("BudgieCoin: ", "Exception Occurred: " + ex);
        }
    }

    // Methods that must be implemented due to extending ArrayAdapter
    public int getCount(){
        return transactions.size();
    }

    public Transaction getItem(Transaction item){
        return item;
    }

    public long getItemId(int position){
        return position;
    }

    // Setup a ViewHolder class.
    public static class ViewHolder {
        public TextView transactionName;
        public TextView transactionValue;
        public TextView transactionAccount;
        public TextView transactionDate;
    }

    // Configure the adaptor to use a custom view, connect the View components to the list values.
    public View getView(int position, View convertView, ViewGroup parent){
        View view = convertView;
        final ViewHolder holder;
        try {
            if(convertView == null){
                view = inflater.inflate(R.layout.transactions_list_row, null);
                holder = new ViewHolder();
                holder.transactionName = (TextView)view.findViewById(R.id.transactionName);
                holder.transactionValue = (TextView) view.findViewById(R.id.transactionValue);
                holder.transactionAccount = (TextView) view.findViewById(R.id.transactionAccount);
                holder.transactionDate = (TextView) view.findViewById(R.id.transactionDate);
                view.setTag(holder);
            } else {
                holder = (ViewHolder)view.getTag();
            }

            DBHandler db = new DBHandler(getContext());
            holder.transactionName.setText(transactions.get(position).getName());
            holder.transactionValue.setText(
                    NumberFormat.getCurrencyInstance(new Locale("en", "NZ")).format(
                            transactions.get(position).getValue()
                    ));
            holder.transactionAccount.setText(
                    db.getAccount(transactions.get(position).getAccount()).getName()
            );
            holder.transactionDate.setText(transactions.get(position).getDate());
        } catch (Exception ex){
            Log.e("BudgieCoin: ", "Exception Occurred: " + ex);
        }

        return view;
    }

}
