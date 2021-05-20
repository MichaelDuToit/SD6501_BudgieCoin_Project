package com.example.sd6501_budgiecoin_project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;

public class AccountBalancesActivity extends AppCompatNavigationDrawerActivity {

    ListView accountsList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Because this activity extends the NavigationDrawerActivity, we dynamically inflate the content into the holder.
        LayoutInflater inflater = (LayoutInflater)this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View activityContentView = inflater.inflate(R.layout.activity_account_balances, null, false);
        drawer.addView(activityContentView, 0);
        navView.setCheckedItem(R.id.nav_balance);
        getSupportActionBar().setTitle(R.string.accounts);

        // Setup the Adapter, DBHandler and get a list of all the accounts
        accountsList = findViewById(R.id.accountsList);
        DBHandler db = new DBHandler(this);
        ArrayList<Account> accounts = db.getAllAccounts();
        AccountsBalancesAdapter balancesAdapter = new AccountsBalancesAdapter(this, 0, accounts);
        accountsList.setAdapter(balancesAdapter);

        // Set onClick listener on the list items. OnClick go to the View Account activity and
        // pass the selected account's ID to the activity.
        accountsList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getApplicationContext(), ViewAccountActivity.class);
                int selectedAccountID = balancesAdapter.getItem(position).getId();
                Bundle bundle = new Bundle();
                bundle.putInt("AccountID", selectedAccountID);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.appBar) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}