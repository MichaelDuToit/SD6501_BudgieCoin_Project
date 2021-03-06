package com.example.sd6501_budgiecoin_project;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.preference.PreferenceManager;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

public abstract class AppCompatNavigationDrawerActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
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

    // Using a switch statement that checks which menu item has been clicked and sets the intent attribute
    // to that item's Activity. Then uses the intent attribute to navigate to that activity and closes the
    // navigation drawer.
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        Intent intent = null;
        switch (item.getItemId()){
            case R.id.nav_home:
                intent = new Intent(this, MainActivity.class);
                break;
            case R.id.nav_balance:
                intent = new Intent(this, AccountBalancesActivity.class);
                break;
            case R.id.nav_transactions:
                intent = new Intent(this, ViewTransactionsActivity.class);
                break;
            case R.id.nav_add_transaction:
                intent = new Intent(this, TransactionActivity.class);
                break;
            case R.id.nav_settings:
                intent = new Intent(this, SettingsActivity.class);
                break;
            case R.id.nav_logout:
                /*SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                SharedPreferences.Editor editor = sharedPref.edit();
                editor.remove("user-id");
                editor.remove("user-name");
                editor.remove("user-pin");
                editor.apply();*/
                intent = new Intent(this, LoginActivity.class);
                break;
            case R.id.nav_add_account:
                intent = new Intent(this, AddAccountActivity.class);
                break;
            default:
        }
        startActivity(intent);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    // If the Navigation Drawer is open on back button press then the close navigation drawer.
    @Override
    public void onBackPressed() {
        drawer = (DrawerLayout) findViewById(R.id.drawerLayoutMain);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }
}