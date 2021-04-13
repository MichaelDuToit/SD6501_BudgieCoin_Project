package com.example.sd6501_budgiecoin_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
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

    public void loginBtn(View v){
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
}