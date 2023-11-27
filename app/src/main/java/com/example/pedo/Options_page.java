package com.example.pedo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Options_page extends AppCompatActivity {
// in this page, the user decides the functionality to be executed next. the user has 3 options- history, distance calculation and multimedia
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_options_page);

        Button distance_home        =   findViewById(R.id.distance_home);
        Button history              =   findViewById(R.id.view_records);
        Button multimedia           =   findViewById(R.id.multimedia);

        distance_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Options_page.this, New_Homepage.class);
                startActivity(intent);
                finish();
            }
        });

        history.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Options_page.this, Records.class);
                startActivity(intent);
                finish();
            }
        });

        multimedia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Options_page.this, Multimedia.class);
                startActivity(intent);
                finish();
            }
        });
    }
}