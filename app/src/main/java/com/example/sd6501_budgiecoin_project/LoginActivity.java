package com.example.sd6501_budgiecoin_project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.biometric.BiometricManager;
import androidx.biometric.BiometricPrompt;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.util.concurrent.Executor;


public class LoginActivity extends AppCompatActivity {

    EditText userInput, pinInput;
    TextView errorMsg;
    User user = new User("admin", "4321");
    private Executor executor;
    private BiometricPrompt biometricPrompt;
    private BiometricPrompt.PromptInfo biometricPromptInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // Assign layout fields to variables
        userInput = findViewById(R.id.loginUsername);
        pinInput = findViewById(R.id.loginPin);
        errorMsg = findViewById(R.id.loginError);

        // Configure the Biometric Login Prompts and methods
        executor = ContextCompat.getMainExecutor(this);
        biometricPrompt = new BiometricPrompt(LoginActivity.this, executor, new BiometricPrompt.AuthenticationCallback() {
            // These three methods need to be overriden to implement the BiometricPrompt.
            // Override the onAuthenticationError method
            @Override
            public void onAuthenticationError(int errorCode, @NonNull CharSequence errString) {
                super.onAuthenticationError(errorCode, errString);
            }
            // Override onAuthenticationSucceeded method. If Authentication succeeds, invoke
            // goToMainActivity method to change to home screen.
            @Override
            public void onAuthenticationSucceeded(@NonNull BiometricPrompt.AuthenticationResult result) {
                super.onAuthenticationSucceeded(result);
                if(BiometricManager.BIOMETRIC_SUCCESS == 0){
                    goToMainActivity();
                }
            }
            // Override the on onAuthenticationFailed method
            @Override
            public void onAuthenticationFailed() {
                super.onAuthenticationFailed();
            }
        });

        // Use to BiometricPrompt builder to create the Android Biometric login prompt
        biometricPromptInfo = new BiometricPrompt.PromptInfo.Builder()
                .setTitle("Biometric Login")            // These strings had to be hardcoded as
                .setSubtitle("Login with Biometrics")   // running the app with R.string references
                .setNegativeButtonText("Cancel")        // kept failing.
                .build();
    }

    // If the user clicks the Login with Pin btn, call the pinLoginAuthentication method.
    public void pinLoginBtn(View v){
        pinLoginAuthentication();
    }

    // If the user clicks the Login with Biometrics, call the biometricPrompt to handle
    // the authentication.
    public void biometricLoginBtn(View v){
        biometricPrompt.authenticate(biometricPromptInfo);
    }

    // If the user attempts to login using username and password, check their details
    // and authenticate them.
    public void pinLoginAuthentication(){
        if (userInput.getText().toString().equals(user.getUsername())) {
            if (pinInput.getText().toString().equals(user.getPinNumber())) {
                // If both the username and password are correct, call the goToMainActivity method.
                goToMainActivity();
            } else {
                errorMsg.setText(R.string.loginPinIncorrect);
            }
        } else {
            errorMsg.setText(R.string.loginUsernameIncorrect);
        }
    }

    // General purpose method to hold the intent call to go to the MainActivity class.
    public void goToMainActivity(){
        startActivity(new Intent(this, MainActivity.class));
    }

    // If the user clicks the Enter tick key on the keyboard, attempt to
    // login using the Pin Login authentication.
    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        if(keyCode == KeyEvent.KEYCODE_ENTER){
            pinLoginAuthentication();
        }
        return super.onKeyUp(keyCode, event);
    }

    // Override the onBackPressed method to close the application if the back btn is pressed.
    @Override
    public void onBackPressed() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            finishAffinity();
        } else {
            super.onBackPressed();
        }
    }
}