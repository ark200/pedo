package com.example.pedo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

public class splash_screen extends AppCompatActivity {

    private boolean doubleBackToExitPressedOnce = false;
    private static final int DOUBLE_BACK_PRESS_INTERVAL = 2000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash_screen);

        ImageView logo      =   findViewById(R.id.splash_screen);
        Glide.with(this).load(R.drawable.last_logo).into(logo);


        new Handler().postDelayed(() ->                                                             //function to move to the next activity after a wait of 3 sec
        {
            Intent intent= new Intent(splash_screen.this,loginsignup.class);
            startActivity(intent);
            finish();
        },3000);
    }

    @Override
    public void onBackPressed(){                                                                    //function to execute double back to exit
        if(doubleBackToExitPressedOnce){
            super.onBackPressed();
            return;
        }
        this.doubleBackToExitPressedOnce =true;
        Toast.makeText(this,"Press back again to Exit",Toast.LENGTH_SHORT).show();

        new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
            @Override
            public void run() {
                doubleBackToExitPressedOnce = false;
            }
        },DOUBLE_BACK_PRESS_INTERVAL);
    }
}