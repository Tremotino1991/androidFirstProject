package com.example.pc.myapplication;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

public class AccessActivity extends AppCompatActivity implements View.OnClickListener {

    TextView emailWL;
    String email;
    String openedEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_access);

        emailWL = (TextView)findViewById(R.id.email_wl);

        emailWL.setText(getIntent().getStringExtra("message"));

        emailWL.setOnClickListener(this);

        if (getIntent().getStringExtra("message") != null){
            email = getIntent().getStringExtra("message");
            emailWL.setText(email);
        }
        else{
            openedEmail = getIntent().getData().toString().substring(7);
            openedEmail = Uri.decode(openedEmail);
            emailWL.setText(openedEmail);
        }

    }

    public void onClick(View view){
        if (view.getId() == R.id.email_wl) {
            Intent i = new Intent(Intent.ACTION_SENDTO, Uri.fromParts(
                    "mailto", getIntent().getStringExtra("message"), null));
            startActivity(Intent.createChooser(i, "Choose an Email client :"));
        }
    }
}
