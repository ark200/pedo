package com.example.pedo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Login_Main extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_login_main);

        EditText username   =   findViewById(R.id.username_login);
        EditText password   =   findViewById(R.id.password_login);
        Button login_main   =   findViewById(R.id.login_button_main);
        Button signup_login =   findViewById(R.id.signup_button_loginpage);

        signup_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Login_Main.this, Signup_Main.class);
                startActivity(intent);
                finish();
            }
        });

        login_main.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(username.getText().toString().isEmpty())
                    username.setError("Username cannot be Empty");
                else if (password.getText().toString().isEmpty())
                    password.setError("Password cannot be empty");
                else{
                    if(username.getText().toString().equals(password.getText().toString()))
                    {
                        Intent intent = new Intent(Login_Main.this,Homepage.class);
                        startActivity(intent);
                        finish();
                    }
                    else
                        Toast.makeText(Login_Main.this,"Invalid Credentials",Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}