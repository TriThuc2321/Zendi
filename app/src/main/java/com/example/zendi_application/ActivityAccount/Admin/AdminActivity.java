package com.example.zendi_application.ActivityAccount.Admin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;

import com.example.zendi_application.R;

public class AdminActivity extends AppCompatActivity {

    View turnBack;
    LinearLayout staffManagerBtn;
    LinearLayout statisticBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_admin);

        init();

        staffManagerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AdminActivity.this, StaffManager.class));
                overridePendingTransition(R.anim.slide_from_right_account,R.anim.slide_to_left_account);
            }
        });

        statisticBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AdminActivity.this, StatisticActivity.class));
                overridePendingTransition(R.anim.slide_from_right_account,R.anim.slide_to_left_account);
            }
        });

        turnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void init() {
        turnBack = findViewById(R.id.turnBack_admin);
        staffManagerBtn = findViewById(R.id.staff_manager_btn);
        statisticBtn = findViewById(R.id.statistics_btn);
    }

}