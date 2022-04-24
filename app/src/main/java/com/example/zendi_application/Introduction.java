package com.example.zendi_application;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.provider.ContactsContract;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Calendar;

public class Introduction extends AppCompatActivity {
    Animation topAim,bottomAim;
    ImageView background,logo;
    TextView appName, slogan;
    DownloadAsynctask LoadDataTask;
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
        LoadDataTask= new DownloadAsynctask(Introduction.this);
        //Gọi hàm execute để kích hoạt tiến trình
        LoadDataTask.execute();
        topAim = AnimationUtils.loadAnimation(this,R.anim.top_animation);
        bottomAim = AnimationUtils.loadAnimation(this,R.anim.bottom_animation);
        logo.setAnimation(topAim);
        appName.setAnimation(bottomAim);
        slogan.setAnimation(bottomAim);
        // Set animation 2
        background.animate().translationY(-3500).setDuration(1000).setStartDelay(2000);
        logo.animate().translationY(2000).setDuration(1000).setStartDelay(2000);
        appName.animate().translationY(2000).setDuration(1000).setStartDelay(2000);
        slogan.animate().translationY(2000).setDuration(1000).setStartDelay(2000);


        //Load data
//        DataManager.LoadDropInformation("Collection/",DataManager.listDrop);  // load products
//        DataManager.LoadProductInformation("Product",DataManager.listProduct); // load categors
//        DataManager.loadUser();
//        DataManager.GetUser();
//        DataManager.getListOrderedFromFirestone();
//
//        new Handler().postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                Intent intent = new Intent(Introduction.this,HomeScreen.class);
//                startActivity(intent);
//                finish();
//            }
//        },5000);

    }


}
