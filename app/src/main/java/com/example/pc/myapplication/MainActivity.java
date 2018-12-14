package com.example.pc.myapplication;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, CompoundButton.OnCheckedChangeListener {

    private static final String TAG = "MainActivity";
    private static final int PASSWORD_LENGTH = 6;

    EditText emailET;
    EditText passwordET;

    Button loginBtn;
    Button registerBtn;

    Switch switchS;
    LinearLayout backLL;

    String PREFS_NAME;
    SharedPreferences settings;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_login);

        emailET = findViewById(R.id.email_et);
        passwordET = findViewById(R.id.password_et);

        loginBtn = findViewById(R.id.login_btn);
        registerBtn = findViewById(R.id.register_btn);

        switchS = findViewById(R.id.switch_s);
        backLL = findViewById(R.id.backgrd);

        registerBtn.setVisibility(View.VISIBLE);

        registerBtn.setOnClickListener(this);

        loginBtn.setOnClickListener(this);
        switchS.setOnCheckedChangeListener(this);

        Log.i(TAG,"activity created");

        settings = getPreferences(Context.MODE_PRIVATE);
        editor = settings.edit();

        switchS.setChecked(settings.getBoolean(PREFS_NAME, false));

    }

    private boolean isValidEmail(){
        String email = emailET.getText().toString();
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();

    }

    private boolean isValidPassword(){
        //TODO check the password's length

        return (passwordET.getText().toString().length() > PASSWORD_LENGTH);

    }

    private void showErrorMessage(String message){
        Toast.makeText(this,message,Toast.LENGTH_LONG).show();

        Log.e(TAG,message);
    }

    private void showSuccessMessage(String message){
        Toast.makeText(this,message,Toast.LENGTH_LONG).show();

        Log.e(TAG,message);
    }

    public void changeRegisterActivity(){
        Intent intent = new Intent(MainActivity.this, RegisterActivity.class);
        startActivity(intent);
    }

    public void changeAccessActivity(){
        Intent intent = new Intent(MainActivity.this, AccessActivity.class);
        intent.putExtra("message",emailET.getText().toString());
        startActivity(intent);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.login_btn){

            if (!isValidEmail()){
                showErrorMessage(getString(R.string.email_error));
            }

            if (!isValidPassword()){
                showErrorMessage(getString(R.string.password_error));
            }

            else{
                changeAccessActivity();
                showSuccessMessage(getString(R.string.login_success));
            }

        }

        if (v.getId() == R.id.register_btn){

            changeRegisterActivity();
        }
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        if (isChecked) {
            backLL.setBackgroundColor(getResources().getColor(R.color.colorDark));
            emailET.setTextColor(getResources().getColor(R.color.colorLight));
            passwordET.setTextColor(getResources().getColor(R.color.colorLight));

            editor.putBoolean(PREFS_NAME, isChecked);
            editor.commit();
        } else{
            backLL.setBackgroundColor(getResources().getColor(R.color.colorLight));
            emailET.setTextColor(getResources().getColor(R.color.colorDark));
            passwordET.setTextColor(getResources().getColor(R.color.colorDark));

            editor.putBoolean(PREFS_NAME, isChecked);
            editor.commit();
        }



    }
}
