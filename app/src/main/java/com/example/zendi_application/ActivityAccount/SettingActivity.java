package com.example.zendi_application.ActivityAccount;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.zendi_application.HomeScreen;
import com.example.zendi_application.R;
import com.facebook.login.LoginManager;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SettingActivity extends AppCompatActivity {
    TextView nameTxt;
    private FirebaseAuth mAuth;
    private FirebaseUser currentUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        mAuth = FirebaseAuth.getInstance();
        nameTxt = findViewById(R.id.nameTxt);
        currentUser = mAuth.getCurrentUser();

        nameTxt.setText(currentUser.getDisplayName());

        findViewById(R.id.logOutBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                LoginManager.getInstance().logOut();

                setResult(RESULT_OK, null);
                finish();
                startActivity(new Intent(SettingActivity.this, HomeScreen.class));
            }
        });
    }
}