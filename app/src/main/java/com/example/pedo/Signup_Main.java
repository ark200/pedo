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

public class Signup_Main extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_signup_main);

        EditText first_name         =   findViewById(R.id.firstname_signup_main);
        EditText last_name          =   findViewById(R.id.lastname_signup_main);
        EditText username           =   findViewById(R.id.username_signup_main);
        EditText password           =   findViewById(R.id.password_signup_main);
        EditText confirm_password   =   findViewById(R.id.confirm_password_singup_main);

        Button signup               =   findViewById(R.id.signup_button_signup_main);
        Button login                =   findViewById(R.id.login_button_signup_main);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Signup_Main.this,Login_Main.class);
                startActivity(intent);
                finish();
            }
        });

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(first_name.getText().toString().isEmpty())
                    first_name.setError("Firstname cannot be empty");
                else if(last_name.getText().toString().isEmpty())
                    last_name.setError("Lastname cannot be empty");
                else if(username.getText().toString().isEmpty())
                    username.setError("Username cannot be empty");
                else if (password.getText().toString().isEmpty())
                    password.setError("Password cannot be empty");
                else if (confirm_password.getText().toString().isEmpty())
                    confirm_password.setError("Please confirm your password");
                else if(password.getText().toString().length()<6)
                    Toast.makeText(Signup_Main.this,"Password should be atleast 6 characters",Toast.LENGTH_SHORT).show();
                else if (!password.getText().toString().equals(confirm_password.getText().toString()))
                    Toast.makeText(Signup_Main.this,"Passwords don't match.",Toast.LENGTH_SHORT).show();
                else{
                    Toast.makeText(Signup_Main.this,"SIGNUP SUCCESSFUL",Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(Signup_Main.this,Login_Main.class);
                    startActivity(intent);
                    finish();
                }
            }
        });
    }
    @Override
    public void onBackPressed(){
        super.onBackPressed();
        Intent intent = new Intent(Signup_Main.this, loginsignup.class);
        startActivity(intent);
        finish();
    }
}