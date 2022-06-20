package com.example.zendi_application.notificationPackage;

import androidx.appcompat.app.AppCompatActivity;

import com.example.zendi_application.DataManager;
import com.example.zendi_application.R;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class notificationPlace extends AppCompatActivity {
    TextView testTxt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification_place);
        testTxt = findViewById(R.id.notification_txt);
        testTxt.setText(DataManager.host.getEmail());
    }
}