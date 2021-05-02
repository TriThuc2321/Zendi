package com.example.zendi_application.ActivityAccount;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
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

    private RelativeLayout settingLayout;
    private LinearLayout location;

    private EditText locationEdt;
    private TextView locationTxt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        Init();
        SetButtonClick();


    }

    private void SetButtonClick() {
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



        settingLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                locationTxt.setText(locationEdt.getText());
                locationEdt.setVisibility(View.GONE);
                locationTxt.setVisibility(View.VISIBLE);
            }
        });


    }

    private void Init(){
        mAuth = FirebaseAuth.getInstance();
        nameTxt = findViewById(R.id.nameTxt);
        currentUser = mAuth.getCurrentUser();

        settingLayout = findViewById(R.id.settingLayout);

        location = findViewById(R.id.location);
        locationEdt = findViewById(R.id.locationEdt);
        locationTxt = findViewById(R.id.locationTxt);

        nameTxt.setText(currentUser.getDisplayName());

    }
    private void setAfterCompleteEdt(){
        locationEdt.setVisibility(View.VISIBLE);

        locationTxt.setVisibility(View.GONE);

        locationEdt.setText(locationTxt.getText());
    }
}