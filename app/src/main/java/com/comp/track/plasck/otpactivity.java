package com.comp.track.plasck;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthOptions;
import com.google.firebase.auth.PhoneAuthProvider;
import com.google.firebase.database.FirebaseDatabase;

import java.util.concurrent.TimeUnit;

public class otpactivity extends AppCompatActivity {

    Button verifyotp,resendotp;
    EditText otpfield;
    TextView otpcountdown,disphone;
    ProgressBar progressBarotp;
    String mVerificationId;
    private FirebaseAuth mAuth;
    String phonenumber;
    String intention;
    PhoneAuthProvider.ForceResendingToken mResendToken;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    FirebaseUser currentuser;
    FirebaseAuth getmAuth = FirebaseAuth.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otpactivity);

        mAuth = FirebaseAuth.getInstance();
        getSupportActionBar().hide();
        otpfield = findViewById(R.id.otpfield);
        otpcountdown = findViewById(R.id.otpcountdown);
        resendotp = findViewById(R.id.resendotp);
        resendotp.setVisibility(View.INVISIBLE);
        verifyotp = findViewById(R.id.verifyotp);
        progressBarotp = findViewById(R.id.progressBarotp);
        verifyotp.setVisibility(View.INVISIBLE);

        disphone = findViewById(R.id.phoneotp);
        progressBarotp.setVisibility(View.INVISIBLE);
        currentuser = FirebaseAuth.getInstance().getCurrentUser();

        Intent intent = getIntent();
        phonenumber = intent.getStringExtra("phone");

        otpverification();

        disphone.setText("+91"+phonenumber);

        verifyotp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressBarotp.setVisibility(View.VISIBLE);
                PhoneAuthCredential credential = PhoneAuthProvider.getCredential(mVerificationId, otpfield.getText().toString());
                signInWithPhoneAuthCredential(credential);
            }
        });

        otpfield.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(otpfield.getText().toString().length() == 6){
                    verifyotp.setVisibility(View.VISIBLE);
                }
                else{
                    verifyotp.setVisibility(View.INVISIBLE);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        new CountDownTimer(30000, 1000) {

            public void onTick(long millisUntilFinished) {
                otpcountdown.setText(String.valueOf(millisUntilFinished / 1000));
            }

            public void onFinish() {
                resendotp.setVisibility(View.VISIBLE);
                otpcountdown.setVisibility(View.INVISIBLE);
            }
        }.start();


        resendotp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resendotp.setVisibility(View.INVISIBLE);
                otpcountdown.setVisibility(View.VISIBLE);
                otpverification();
                new CountDownTimer(30000, 1000) {
                    public void onTick(long millisUntilFinished) {
                        otpcountdown.setText(String.valueOf(millisUntilFinished / 1000));
                    }

                    public void onFinish() {
                        resendotp.setVisibility(View.VISIBLE);
                    }
                }.start();
            }
        });
    }

    public void otpverification(){
        PhoneAuthOptions options =
                PhoneAuthOptions.newBuilder(mAuth)
                        .setPhoneNumber("+91"+phonenumber)       // Phone number to verify
                        .setTimeout(30L, TimeUnit.SECONDS) // Timeout and unit
                        .setActivity(this)                 // Activity (for callback binding)
                        .setCallbacks(new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
                            @Override
                            public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {
                                Toast.makeText(otpactivity.this, "verification complete", Toast.LENGTH_SHORT).show();
                                signInWithPhoneAuthCredential(phoneAuthCredential);
                            }

                            @Override
                            public void onVerificationFailed(@NonNull FirebaseException e) {
                                Toast.makeText(otpactivity.this, "Please Check Phone Number", Toast.LENGTH_LONG).show();
                                                          }

                            @Override
                            public void onCodeSent(@NonNull String verificationId,
                                                   @NonNull PhoneAuthProvider.ForceResendingToken token) {

                                mVerificationId = verificationId;
                                mResendToken = token;
                            }
                        })
                        .build();
        PhoneAuthProvider.verifyPhoneNumber(options);

    }

    private void signInWithPhoneAuthCredential(PhoneAuthCredential credential) {
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Intent intent = new Intent(otpactivity.this,MainActivity.class);
                            startActivity(intent);
                            progressBarotp.setVisibility(View.INVISIBLE);
                        } else {
                            Toast.makeText(otpactivity.this, "Please check the enterd OTP", Toast.LENGTH_LONG).show();
                        }
                    }
                });
    }
}