package com.example.zendi_application.ActivityAccount;

import androidx.appcompat.app.AppCompatActivity;
import com.example.zendi_application.R;
import android.os.Bundle;

public class ForgotPassword_2 extends AppCompatActivity {
    String email = getIntent().getStringExtra("email");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password2);
    }
}