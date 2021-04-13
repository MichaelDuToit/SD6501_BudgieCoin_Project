package com.example.sd6501_budgiecoin_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class LoginActivity extends AppCompatActivity {

    EditText userInput, pinInput;
    TextView errorMsg;
    String user = "admin", pin = "4321";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        userInput = findViewById(R.id.loginUsername);
        pinInput = findViewById(R.id.loginPin);
        errorMsg = findViewById(R.id.loginError);
    }

    public void authenticateUser(){
        if(userInput.getText().toString().equals(user)){
            if(pinInput.getText().toString().equals(pin)){
                startActivity(new Intent(this, MainActivity.class));
            }
            else {
                errorMsg.setText(R.string.loginPinIncorrect);
            }
        } else {
            errorMsg.setText(R.string.loginUsernameIncorrect);
        }
    }

    public void loginBtn(View v){
        authenticateUser();
    }

    @Override
    public void onBackPressed() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            finishAffinity();
        } else {
            super.onBackPressed();
        }
    }

    // If the user clicks the enter key on the keyboard, attempt to login

    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        if(keyCode == KeyEvent.KEYCODE_ENTER){
            authenticateUser();
        }
        return super.onKeyUp(keyCode, event);
    }
}