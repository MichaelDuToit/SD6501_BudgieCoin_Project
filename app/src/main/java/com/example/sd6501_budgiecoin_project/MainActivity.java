package com.example.sd6501_budgiecoin_project;


import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;


public class MainActivity extends AppCompatNavigationDrawerActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LayoutInflater inflater = (LayoutInflater)this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View activityContentView = inflater.inflate(R.layout.activity_main, null, false);
        drawer.addView(activityContentView, 0);
        navView.setCheckedItem(R.id.nav_home);
    }

    // For extending AppCompatNavigationDrawerActivity
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.appBar) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    // If the Nav Drawer is open on back btn press, close it. Otherwise close the application.
    @Override
    public void onBackPressed() {
        drawer = (DrawerLayout) findViewById(R.id.drawerLayoutMain);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                finishAffinity();
            } else {
                super.onBackPressed();
            }
        }
    }

    public void changeToTransaction(View view){
        Intent intent = new Intent(this, AddTransactionActivity.class);
        startActivity(intent);
    }

    public void changeToViewTransactions(View view){
        startActivity(new Intent(this, ViewTransactionsActivity.class));
    }
    public void changeToAccountsView(View view){
        startActivity(new Intent(this, AccountBalancesActivity.class));
    }
}

