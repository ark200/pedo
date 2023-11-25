package com.example.pedo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

public class loginsignup extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_loginsignup);

        ImageView loginsignup_image     =   findViewById(R.id.login_signup_image);
        Button login_button             =   findViewById(R.id.login_button);
        Button signup_button            =   findViewById(R.id.signup_button);

        Glide.with(this).load(R.drawable.pedo_splash).into(loginsignup_image);

        login_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(loginsignup.this, Login_Main.class);
                startActivity(intent);
                finish();
            }
        });

        signup_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(loginsignup.this, Signup_Main.class);
                startActivity(intent);
                finish();
            }
        });
    }
}