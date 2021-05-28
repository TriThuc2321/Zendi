package com.example.zendi_application;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

public class Introduction extends AppCompatActivity {
    Animation topAim,bottomAim;
    ImageView background,logo;
    TextView appName, slogan;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);
        //Hooks
        background = findViewById(R.id.bg);
        logo = findViewById(R.id.logo);
        appName = findViewById(R.id.app_name);
        slogan = findViewById(R.id.slogan);
        /// set animation 1
        topAim = AnimationUtils.loadAnimation(this,R.anim.top_animation);
        bottomAim = AnimationUtils.loadAnimation(this,R.anim.bottom_animation);
        logo.setAnimation(topAim);
        appName.setAnimation(bottomAim);
        slogan.setAnimation(bottomAim);
        // Set animation 2
        background.animate().translationY(-2500).setDuration(1000).setStartDelay(2000);
        logo.animate().translationY(2000).setDuration(1000).setStartDelay(2000);
        appName.animate().translationY(2000).setDuration(1000).setStartDelay(2000);
        slogan.animate().translationY(2000).setDuration(1000).setStartDelay(2000);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                DataManager.getShoeInBagFromFirestone("InBag",DataManager.list);
                Intent intent = new Intent(Introduction.this,HomeScreen.class);
                startActivity(intent);
                finish();
            }
        },3500);

    }
}