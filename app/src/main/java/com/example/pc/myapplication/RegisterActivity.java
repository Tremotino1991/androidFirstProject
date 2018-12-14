package com.example.pc.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "MainActivity";
    private static final int PASSWORD_LENGTH = 6;

    EditText emailET;
    EditText passwordET;
    EditText phoneET;
    Button registerBtn;
    boolean emailValidate, passwordValidate, phoneValidate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_register);

        emailET = findViewById(R.id.email_et);
        passwordET = findViewById(R.id.password_et);
        phoneET = findViewById(R.id.phone_et);
        registerBtn = findViewById(R.id.register_btn);

        registerBtn.setOnClickListener(this);

        emailET.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                emailValidate = isValidEmail(s.toString());
                enableButt();
            }
        });

        passwordET.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                passwordValidate = isValidPassword(s.toString());
                enableButt();
            }
        });

        phoneET.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                phoneValidate = isValidPhone(s.toString());
                enableButt();
            }
        });

        Log.i(TAG,"activity created");
    }

    private boolean isValidEmail(String email){
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();

    }

    private boolean isValidPassword(String password){

        return (passwordET.getText().toString().length() > PASSWORD_LENGTH);

    }

    private boolean isValidPhone(String number){
        return android.util.Patterns.PHONE.matcher(number).matches();

    }

    private void showErrorMessage(String message){
        Toast.makeText(this,message,Toast.LENGTH_LONG).show();

        Log.e(TAG,message);
    }

    private void showSuccessMessage(String message){
        Toast.makeText(this,message,Toast.LENGTH_LONG).show();

        Log.e(TAG,message);
    }

    public void changeActivity(){
        Intent intent = new Intent(RegisterActivity.this, MainActivity.class);
        startActivity(intent);
    }

    public void enableButt() {
            registerBtn.setEnabled(emailValidate && passwordValidate && phoneValidate);
    }


    @Override
    protected void onStart() {
        super.onStart();
        Log.i(TAG,"activity started");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i(TAG,"activity resumed");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i(TAG,"activity paused");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i(TAG,"activity stopped");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i(TAG,"activity destroyed");
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.register_btn) {
            showSuccessMessage(getString(R.string.register_success));
        }
    }
}
