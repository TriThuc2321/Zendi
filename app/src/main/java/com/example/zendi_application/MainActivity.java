package com.example.zendi_application;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    ImageView background,logo;
    TextView appName, slogan;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Hooks
        background = findViewById(R.id.bg);
        logo = findViewById(R.id.logo);
        appName = findViewById(R.id.app_name);
        slogan = findViewById(R.id.slogan);

        // Set animation
        background.animate().translationY(-1800).setDuration(1000).setStartDelay(1500);
        logo.animate().translationY(1400).setDuration(1000).setStartDelay(1500);
        appName.animate().translationY(1400).setDuration(1000).setStartDelay(1500);
        slogan.animate().translationY(1400).setDuration(1000).setStartDelay(1500);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(MainActivity.this,HomeScreen.class);
                startActivity(intent);
                finish();
            }
        },3000);

    }
}