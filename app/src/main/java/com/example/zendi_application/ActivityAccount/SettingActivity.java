package com.example.zendi_application.ActivityAccount;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
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
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class SettingActivity extends AppCompatActivity {

    private User user;
    private DatabaseReference dataBase;

    private TextView nameTxt;
    private FirebaseAuth mAuth;
    private FirebaseUser currentUser;

    private RelativeLayout settingLayout;
    private LinearLayout location;

    private EditText locationEdt;
    private TextView locationTxt;

    private TextView birthdayTxt;
    private EditText birthdayEdt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        Init();

        //SetButtonClick();
        setData("Soc Trang","23_02", "10522321@gm.uit.edu.vn","Male", "123","Trần Trí Thức","123456","0787960456","MyUri",40,10000);
        getData();
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
        dataBase = FirebaseDatabase.getInstance().getReference();

        currentUser = mAuth.getCurrentUser();

        settingLayout = findViewById(R.id.settingLayout);

        nameTxt = findViewById(R.id.nameTxt);

        location = findViewById(R.id.location);
        locationEdt = findViewById(R.id.locationEdt);
        locationTxt = findViewById(R.id.locationTxt);

        birthdayEdt = findViewById(R.id.birthdayEdt);
        birthdayTxt = findViewById(R.id.birthdayTxt);

        nameTxt.setText(currentUser.getDisplayName());

    }
    private void setAfterCompleteEdt(){
        locationEdt.setVisibility(View.VISIBLE);

        locationTxt.setVisibility(View.GONE);

        locationEdt.setText(locationTxt.getText());
    }

    void getData(){
        dataBase.child("Users").child(currentUser.getUid()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                user = snapshot.getValue(User.class);
                locationTxt.setText(user.getAddress());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    public void setData(String address, String DOB, String email, String gender, String id, String name, String password, String phoneNumber, String profilePic, int size, int total){
        User mUser =  new User(address, DOB, email, gender, currentUser.getUid(), name, password,phoneNumber,profilePic,size,total);
        dataBase.child("Users").child(currentUser.getUid()).setValue(mUser);
    }
}