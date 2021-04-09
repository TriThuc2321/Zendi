package com.example.zendi_application;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;

public class Account_Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_);
        findViewById(R.id.gobackbtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OnBackPressed();
            }
        });
    }
    private void OnBackPressed()
    {
        super.onBackPressed();
        overridePendingTransition(R.anim.slide_from_left_account,R.anim.slide_to_right_account);
    }
}