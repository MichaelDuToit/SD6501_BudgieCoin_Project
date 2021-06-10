package com.example.sd6501_budgiecoin_project;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.preference.PreferenceManager;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class RegistrationActivity extends AppCompatActivity {

    public Toolbar actionBar;
    public Button btnRegister, btnCancel;
    public EditText et_username, et_pin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        // Setup the custom ActionBar / Toolbar.
        actionBar = (Toolbar)findViewById(R.id.appBar);
        setSupportActionBar(actionBar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(R.string.lbl_registration);

        // Connect view buttons to objects.
        btnRegister = findViewById(R.id.btnRegister);
        btnCancel = findViewById(R.id.btnCancel);
        et_username = findViewById(R.id.et_username);
        et_pin = findViewById(R.id.et_pin);

        btnRegister.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                try {
                    DBHandler db = new DBHandler(getApplicationContext());
                    User newUser = new User(
                            et_username.getText().toString(),
                            et_pin.getText().toString()
                    );
                    db.createUser(newUser);
                    ArrayList<User> allUsers = new ArrayList<>();
                    allUsers = db.getAllUsers();
                    for(int i = 0; i < allUsers.size(); i++){
                        if( newUser.getUsername().equals(allUsers.get(i).getUsername()) && newUser.getPinNumber().equals(allUsers.get(i).getPinNumber()))
                        {
                            SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                            SharedPreferences.Editor editor = sharedPref.edit();
                            editor.putInt("user-id", allUsers.get(i).getId());
                            editor.putString("user-name", allUsers.get(i).getUsername());
                            editor.putString("user-pin", allUsers.get(i).getPinNumber());
                            editor.apply();
                        }
                    }
                    Toast.makeText(getApplicationContext(), R.string.t_loginCreated, Toast.LENGTH_LONG).show();
                    startActivity(new Intent(getApplicationContext(), MainActivity.class));
                } catch (Exception ex){
                    Log.e("BudgieCoin: ", ex.toString());
                }

            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), LoginActivity.class));
            }
        });
    }
}