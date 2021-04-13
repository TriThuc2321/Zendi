package com.example.zendi_application;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.appbar.MaterialToolbar;

public class Account_Activity extends AppCompatActivity {
    MaterialToolbar mAppBarTop;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_);

        mAppBarTop = findViewById(R.id.topAppBar);


        mAppBarTop.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                if(item.getItemId() == R.id.itemBack){
                    OnBackPressed();
                }
                if(item.getItemId() == R.id.itemSetting){
                    startSettingActivity();
                }
                return true;
            }
        });

        findViewById(R.id.btnLogin).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Account_Activity.this, LoginActivity.class);
                startActivity(intent);
                //overridePendingTransition(R.anim.slide_from_right_account,R.anim.slide_to_left_account);
            }
        });


    }
    private void OnBackPressed()
    {
        super.onBackPressed();
        overridePendingTransition(R.anim.slide_from_left_account,R.anim.slide_to_right_account);
    }
    private void startSettingActivity(){
        Intent intent = new Intent(Account_Activity.this, SettingActivity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_from_right_account,R.anim.slide_to_left_account);
    }
}