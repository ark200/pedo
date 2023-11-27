package com.example.pedo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

public class Records extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_records);

        TextView records        =   findViewById(R.id.title_records);
        records.setTypeface(null, Typeface.BOLD_ITALIC);


    }
    @Override
    public void onBackPressed(){
        super.onBackPressed();
        Intent intent = new Intent(Records.this, Options_page.class);
        startActivity(intent);
        finish();
    }
}