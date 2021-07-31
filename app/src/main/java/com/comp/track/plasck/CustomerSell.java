package com.comp.track.plasck;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.basgeekball.awesomevalidation.ValidationStyle;
import com.basgeekball.awesomevalidation.utility.RegexTemplate;

public class CustomerSell extends AppCompatActivity {
 Button request;
 AwesomeValidation awesomeValidation;

 EditText PersonName,phonenumber,adress,pincode;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_sell);
        request= findViewById(R.id.req);
        PersonName=findViewById(R.id.PersonName);
        phonenumber=findViewById(R.id.phonenumber);
        adress=findViewById(R.id.adress);
        pincode=findViewById(R.id.pincode);
        awesomeValidation= new AwesomeValidation(ValidationStyle.BASIC);
        awesomeValidation.addValidation(this, R.id.PersonName, RegexTemplate.NOT_EMPTY, R.string.invalid_name);
        awesomeValidation.addValidation(this, R.id.phonenumber, "^[+]?[0-9]{10,13}$", R.string.invalid_phone);
        awesomeValidation.addValidation(this, R.id.adress,  RegexTemplate.NOT_EMPTY, R.string.invalid_adress);
        awesomeValidation.addValidation(this, R.id.pincode, "^[+]?[0-9]{10,13}$", R.string.invalid_pin);

        request.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });



    }
}