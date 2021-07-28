package com.comp.track.plasck;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class loginactivity extends AppCompatActivity {

    Button loginsignup;
    EditText phonenumber;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    FirebaseUser currentuser;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loginactivity);

        getSupportActionBar().hide(); //hide the title bar

        Intent intent = getIntent();
        String type = intent.getStringExtra("type");

        loginsignup = findViewById(R.id.loginsignup);
        phonenumber = findViewById(R.id.phonenumberfield);
        loginsignup.setVisibility(View.INVISIBLE);
        progressBar = findViewById(R.id.progressBarlogin);
        progressBar.setVisibility(View.INVISIBLE);
        DatabaseReference myref = database.getReference();

        phonenumber.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(phonenumber.getText().toString().length() ==10){
                    loginsignup.setVisibility(View.VISIBLE);
                }else{
                    loginsignup.setVisibility(View.INVISIBLE);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });
        loginsignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressBar.setVisibility(View.VISIBLE);
                Intent intent = new Intent(getApplicationContext(),otpactivity.class);
                intent.putExtra("phone",phonenumber.getText().toString());
                intent.putExtra("type",type);
                startActivity(intent);
            }
        });

    }
}