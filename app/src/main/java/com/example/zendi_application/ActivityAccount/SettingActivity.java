package com.example.zendi_application.ActivityAccount;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.zendi_application.R;
import com.facebook.login.LoginManager;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.squareup.picasso.Picasso;

public class SettingActivity extends AppCompatActivity {

    FirebaseAuth auth = FirebaseAuth.getInstance();
    FirebaseUser user = auth.getCurrentUser();
    TextView email, name;
    ImageView profilePic;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        email = findViewById(R.id.email);
        name = findViewById(R.id.name);
        profilePic = findViewById(R.id.profilePic);

        if(user!= null){
            name.setText(user.getDisplayName());
            email.setText(user.getEmail()+ "");
            Picasso.get().load("https://graph.facebook.com/"+ user.getUid()+"/picture?type=normal").into(profilePic);
        }
        findViewById(R.id.btnLogout).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                auth.signOut();
                LoginManager.getInstance().logOut();
            }
        });

        findViewById(R.id.btnBack).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OnBackPressed();
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        if(user == null){
            openLogin();
        }
    }

    private void openLogin() {
        startActivity(new Intent(this,SettingActivity.class));

    }

    private void OnBackPressed()
    {
        super.onBackPressed();

    }
}