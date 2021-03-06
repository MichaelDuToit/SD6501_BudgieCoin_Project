package com.example.sd6501_budgiecoin_project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ViewTransactionsActivity extends AppCompatNavigationDrawerActivity {

    ListView lvAllTransactions;
    FloatingActionButton floatingActionButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Because this activity extends the NavigationDrawerActivity, we dynamically inflate the content into the holder.
        LayoutInflater inflater = (LayoutInflater)this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View activityContentView = inflater.inflate(R.layout.activity_view_transactions, null, false);
        drawer.addView(activityContentView, 0);
        navView.setCheckedItem(R.id.nav_transactions);
        getSupportActionBar().setTitle(R.string.transactions);

        // Setup the Floating Action Button
        floatingActionButton = findViewById(R.id.floatingActionBtn);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), TransactionActivity.class));
            }
        });

        // Setup the List View to show all Transactions
        lvAllTransactions = findViewById(R.id.listAllTransactions);
        DBHandler db = new DBHandler(this);
        ArrayList<Transaction> transactions = db.getAllTransactions();
        TransactionsAdapter transactionsAdapter = new TransactionsAdapter(this, 0, transactions);
        lvAllTransactions.setAdapter(transactionsAdapter);

        lvAllTransactions.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getApplicationContext(), TransactionUpdateActivity.class);
                int selectedTransaction = transactionsAdapter.getItem(position).getId();
                double selectedTransactionValue = transactionsAdapter.getItem(position).getValue();
                Bundle bundle = new Bundle();
                bundle.putInt("TransactionID", selectedTransaction);
                bundle.putDouble("TransactionValue", selectedTransactionValue);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.appBar) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


}