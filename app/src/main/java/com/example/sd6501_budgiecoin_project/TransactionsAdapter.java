package com.example.sd6501_budgiecoin_project;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.text.NumberFormat;
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
            Log.e(String.valueOf(R.string.app_name), ex.toString());
        }
    }


    public int getCount(){
        return transactions.size();
    }

    public Transaction getItem(Transaction item){
        return item;
    }

    public long getItemId(int position){
        return position;
    }

    public static class ViewHolder {
        public TextView transactionName;
        public TextView transactionValue;
        public TextView transactionAccount;
    }

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
                view.setTag(holder);
            } else {
                holder = (ViewHolder)view.getTag();
            }
            DBHandler db = new DBHandler(getContext());
            holder.transactionName.setText(transactions.get(position).getName());
            holder.transactionValue.setText(
                    NumberFormat.getCurrencyInstance(new Locale("en", "NZ")).format(
                            db.getAccountBalance(transactions.get(position).getId()))
            );
            holder.transactionAccount.setText(
                    db.getAccount(transactions.get(position).getId()).getName()
            );

        } catch (Exception ex){

        }
        return view;
    }

}
