package com.example.sd6501_budgiecoin_project;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputType;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.preference.EditTextPreference;
import androidx.preference.Preference;
import androidx.preference.PreferenceDataStore;
import androidx.preference.PreferenceFragmentCompat;
import androidx.preference.PreferenceManager;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Locale;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

public class SettingsActivity extends AppCompatActivity {

    protected Toolbar actionBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings_activity);
        if (savedInstanceState == null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.settings, new SettingsFragment())
                    .commit();
        }

        actionBar = (Toolbar)findViewById(R.id.appBar);
        setSupportActionBar(actionBar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(R.string.Settings);

        // Testing. Considering implementing a location selection which sets the currency values.
        /*
        String[] locales = Locale.getISOCountries();
        ArrayList<String> countries = new ArrayList<String>();
        for(String l : locales){
            Locale obj = new Locale("", l);
            countries.add(obj.getDisplayCountry());
        }
        for(String c : countries) {
            Log.i("BudgieCoin", c);
        }
        */
        // Store locales to array.
        // Populate spinner with displayCountry
        // when user picks an option set the locale to user preferences.
    }

    public static class SettingsFragment extends PreferenceFragmentCompat {
        SharedPreferences.OnSharedPreferenceChangeListener preferenceChangeListener;

        @Override
        public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
            setPreferencesFromResource(R.xml.root_preferences, rootKey);
            EditTextPreference usernamePreference = findPreference("user-name");
            EditTextPreference pinPreference = findPreference("user-pin");

            // Set the Summary field of the EditTextPreferences to the current logged in user's values.
            if(usernamePreference != null){
                usernamePreference.setSummaryProvider(new Preference.SummaryProvider<EditTextPreference>(){
                    @Override
                    public CharSequence provideSummary(EditTextPreference preference) {
                        String text = preference.getText();
                        if(TextUtils.isEmpty(text)){
                            return "Not Set";
                        }
                        return text;
                    }
                });
            }
            if(pinPreference != null){
                pinPreference.setSummaryProvider(new Preference.SummaryProvider<EditTextPreference>(){
                    @Override
                    public CharSequence provideSummary(EditTextPreference preference) {
                        String txt = preference.getText();
                        if(TextUtils.isEmpty(txt)){
                            return "Not Set";
                        }
                        return txt;
                    }
                });
            }

            usernamePreference.setOnBindEditTextListener(new EditTextPreference.OnBindEditTextListener() {
                @Override
                public void onBindEditText(@NonNull EditText editText) {
                    editText.setInputType(InputType.TYPE_TEXT_FLAG_NO_SUGGESTIONS);
                    editText.addTextChangedListener(new TextWatcher() {
                        @Override
                        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                        }
                        @Override
                        public void onTextChanged(CharSequence s, int start, int before, int count) {

                        }
                        @Override
                        public void afterTextChanged(Editable s) {
                            String validationError;
                            try {
                                if (s.length() < 3) {
                                    throw new Exception("Username must be at least 3 characters!");
                                }
                                validationError = null;
                            } catch (Exception ex){
                                validationError = ex.getMessage();
                            }
                            editText.setError(validationError);
                            editText.getRootView().findViewById(android.R.id.button1).setEnabled(validationError == null);
                        }
                    });
                }
            });

            pinPreference.setOnBindEditTextListener(new EditTextPreference.OnBindEditTextListener() {
                @Override
                public void onBindEditText(@NonNull EditText editText) {
                    editText.setInputType(InputType.TYPE_CLASS_NUMBER);
                    editText.addTextChangedListener(new TextWatcher() {
                        @Override
                        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                        }
                        @Override
                        public void onTextChanged(CharSequence s, int start, int before, int count) {

                        }
                        @Override
                        public void afterTextChanged(Editable s) {
                            String validationError;
                            try {
                                if (s.length() < 4) {
                                    throw new Exception("Pin must be at least 4 numbers!");
                                }
                                validationError = null;
                            } catch (Exception ex){
                                validationError = ex.getMessage();
                            }
                            editText.setError(validationError);
                            editText.getRootView().findViewById(android.R.id.button1).setEnabled(validationError == null);
                        }
                    });
                }
            });


            // Set the Change listeners on the Preference fields so that the DB is updated when the values are changed.
            preferenceChangeListener = new SharedPreferences.OnSharedPreferenceChangeListener() {
                @Override
                public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    if(key.equals("user-name")){
                        editor.putString(key, sharedPreferences.getString(key, ""));
                        editor.apply();
                        updateDatabase(sharedPreferences);
                    }
                    else if (key.equals("user-pin")){
                        editor.putString(key, sharedPreferences.getString(key, ""));
                        editor.apply();
                        updateDatabase(sharedPreferences);
                    }
                }
            };
        }

        // Simple method just to keep the codebase DRY.
        protected void updateDatabase(SharedPreferences sharedPreferences){
            DBHandler db = new DBHandler(getContext());
            db.updateUser(new User(
                    sharedPreferences.getInt("user-id", -1),
                    sharedPreferences.getString("user-name", ""),
                    sharedPreferences.getString("user-pin", "")
            ));

        }

        @Override
        public void onResume() {
            super.onResume();
            getPreferenceManager().getSharedPreferences().registerOnSharedPreferenceChangeListener(preferenceChangeListener);
        }

        @Override
        public void onPause() {
            super.onPause();
            getPreferenceManager().getSharedPreferences().unregisterOnSharedPreferenceChangeListener(preferenceChangeListener);
        }
    }
}
