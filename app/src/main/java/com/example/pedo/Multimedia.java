package com.example.pedo;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.WebView;

public class Multimedia extends AppCompatActivity {

    WebView mWebView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_multimedia);
//        String videoStr = "<html><body>Promo video<br><iframe width=\"420\" height=\"315\" src=\"https://www.youtube.com/watch?v=o20sLxinfwc" frameborder=\"0\" allowfullscreen></iframe></body></html>";
    }
}