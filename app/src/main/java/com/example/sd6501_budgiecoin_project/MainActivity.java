package com.example.sd6501_budgiecoin_project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Build;
import android.os.Bundle;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

//public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
public class MainActivity extends AppCompatNavigationDrawerActivity {
    /*
    public DrawerLayout drawer;
    public ListView drawerListView;
    public ActionBarDrawerToggle actionBarDrawerToggle;
    public Toolbar actionBar;
    public NavigationView navigationView;
    */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        /*
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        actionBar = (Toolbar)findViewById(R.id.appBar);
        setSupportActionBar(actionBar);

        drawer = (DrawerLayout) findViewById(R.id.drawerLayoutMain);
        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawer, actionBar, R.string.lbl_open, R.string.lbl_close);

        drawer.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        navigationView = (NavigationView) findViewById(R.id.navView);
        navigationView.setNavigationItemSelectedListener(this);
*/
        // This is for attempting to extend the class rom AppCompatNavigationDrawerActivity

        super.onCreate(savedInstanceState);
        LayoutInflater inflater = (LayoutInflater)this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View activityContentView = inflater.inflate(R.layout.activity_main, null, false);
        drawer.addView(activityContentView, 0);
        navView.setCheckedItem(R.id.nav_home);
    }

    // For extending AppCompatNavigationDrawerActivity

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.appBar) {
            return true;
        }
        return super.onOptionsItemSelected(item);
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
    /*

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        // Sync the toggle state
        actionBarDrawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
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
                intent = new Intent(this, AddTransactionActivity.class);
                break;
            case R.id.nav_settings:
                intent = new Intent(this, SettingsActivity.class);
                break;
            case R.id.nav_logout:
                Toast.makeText(this, "Logout", Toast.LENGTH_SHORT).show();
                break;
            default:
        }
        startActivity(intent);
        drawer.closeDrawers();
        return true;
    }

*/
    public void changeToTransaction(View view){
        Intent intent = new Intent(this, AddTransactionActivity.class);
        startActivity(intent);
    }

    public void changeToViewTransactions(View view){
        startActivity(new Intent(this, ViewTransactionsActivity.class));
    }
    public void changeToAccountsView(View view){
        startActivity(new Intent(this, ViewTransactionsActivity.class));
    }
}

