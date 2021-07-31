package com.comp.track.plasck;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;

public class homescreen extends AppCompatActivity {

    private ArrayList<String> usage = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homescreen);
        getSupportActionBar().hide();

        usage.clear();

        usage.add("50");
        usage.add("10");
        usage.add("100");
        usage.add("150");
        usage.add("20");
        usage.add("60");


        RecyclerView recyclerView = findViewById(R.id.recycleusage);
        graphadapter adapter = new graphadapter(usage,homescreen.this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(homescreen.this, LinearLayoutManager.HORIZONTAL,false));
    }
}