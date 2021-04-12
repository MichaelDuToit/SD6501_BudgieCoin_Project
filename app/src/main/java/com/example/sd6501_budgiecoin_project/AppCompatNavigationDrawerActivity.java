package com.example.sd6501_budgiecoin_project;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

public class AppCompatNavigationDrawerActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    public DrawerLayout drawer;
    public ActionBarDrawerToggle actionBarDrawerToggle;
    public Toolbar toolbar;
    public NavigationView navView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app_compat_navigation_drawer);

        // Set the Toolbar/ActionBar to the custom one.
        toolbar = (Toolbar)findViewById(R.id.appBar);
        setSupportActionBar(toolbar);

        // Setup the Navigation Drawer
        drawer = (DrawerLayout)findViewById(R.id.drawerLayoutMain);
        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.lbl_open, R.string.lbl_close );
        drawer.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        navView = (NavigationView)findViewById(R.id.navView);
        navView.setNavigationItemSelectedListener(this);

    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        //Sync the navigation drawer toggle state
        actionBarDrawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(@NonNull Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        actionBarDrawerToggle.onConfigurationChanged(newConfig);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        if(actionBarDrawerToggle.onOptionsItemSelected(item)){
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        Log.i("Drawer", "Entered onNavigationItem");
        Intent intent = null;
        int id = item.getItemId();
        switch (item.getItemId()){
            case R.id.nav_home:
                Log.i("Drawer", "Home Case");
                intent = new Intent(this, MainActivity.class);
                break;
            case R.id.nav_balance:
                Log.i("Drawer", "Account Balances Case");
                intent = new Intent(this, AccountBalancesActivity.class);
                break;
            case R.id.nav_transactions:
                Log.i("Drawer", "View Transactions Case");
                intent = new Intent(this, ViewTransactionsActivity.class);
                break;
            case R.id.nav_add_transaction:
                Log.i("Drawer", "Add Transaction Case");
                intent = new Intent(this, AddTransactionActivity.class);
                break;
            case R.id.nav_settings:
                Log.i("Drawer", "Settings Case");
                intent = new Intent(this, SettingsActivity.class);
                break;
            case R.id.nav_logout:
                Log.i("Drawer", "Logout Case");
                break;
            default:
        }
        startActivity(intent);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

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
}