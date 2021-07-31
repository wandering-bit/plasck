package com.comp.track.plasck;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class HomeScreen2nd extends AppCompatActivity {
 Button sell,call,watch ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_home_screen2nd);
       sell=findViewById(R.id.sell);
       call=findViewById(R.id.call);
       watch=findViewById(R.id.watch);



    }
}