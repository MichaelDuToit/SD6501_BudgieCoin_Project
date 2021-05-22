package com.example.sd6501_budgiecoin_project;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import org.w3c.dom.Text;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;

// https://stackoverflow.com/questions/15297840/populate-listview-from-arraylist-of-objects

public class AccountsBalancesAdapter extends ArrayAdapter<Account> {

    private Activity activity;
    private ArrayList<Account> account;
    private static LayoutInflater inflater = null;

    // Setup the default constructor.
    public AccountsBalancesAdapter(Activity activity, int textViewResourceId, ArrayList<Account> acc){
        super(activity, textViewResourceId, acc);
        try {
            this.activity = activity;
            this.account = acc;
            inflater = (LayoutInflater)activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        } catch (Exception ex){
            Log.e(String.valueOf(R.string.app_name), ex.toString());
        }
    }

    // Implement the required methods for extending ArrayAdapter.
    public int getCount(){
        return account.size();
    }

    public Account getItem(Account position){
        return position;
    }

    public long getItemId(int position){
        return position;
    }

    public static class ViewHolder {
        public TextView accountName;
        public TextView accountBalance;
    }

    // Configure the adaptor to use a custom view, connect the View components to the list values.
    public View getView(int position, View convertView, ViewGroup parent){
        View view = convertView;
        final ViewHolder holder;
        try {
            if(convertView == null){
                view = inflater.inflate(R.layout.account_balances_list_row, null);
                holder = new ViewHolder();
                holder.accountName = (TextView)view.findViewById(R.id.accountName);
                holder.accountBalance = (TextView) view.findViewById(R.id.accountBalance);
                view.setTag(holder);
            } else {
                holder = (ViewHolder)view.getTag();
            }
            DBHandler db = new DBHandler(getContext());
            holder.accountName.setText(account.get(position).getName());
            holder.accountBalance.setText(
                    NumberFormat.getCurrencyInstance(new Locale("en", "NZ")).format(
                    db.getAccountBalance(account.get(position).getId()))
            );

        } catch (Exception ex){
            Log.e("BudgieCoin: ", "Exception Occurred: " + ex);
        }
        return view;
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return getView(position, convertView, parent);
    }
}
