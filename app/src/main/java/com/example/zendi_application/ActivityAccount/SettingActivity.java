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
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.zendi_application.HomeScreen;
import com.example.zendi_application.R;
import com.facebook.login.LoginManager;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import com.google.android.gms.common.api.internal.GoogleApiManager;
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

    private FirebaseAuth mAuth;
    private FirebaseUser currentUser;

    private RelativeLayout settingLayout;
    private LinearLayout location;

    private EditText locationEdt;
    private TextView locationTxt;

    private TextView birthdayTxt;
    private EditText birthdayEdt;

    private TextView nameTxt;
    private EditText nameEdt;

    private RadioButton maleRad;
    private RadioButton femaleRad;
    private RadioButton otherRad;

    private TextView sizeTxt;
    private EditText sizeEdt;

    private Button saveBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        Init();
        SetButtonClick();
        setData("Soc Trang","23_02", "10522321@gm.uit.edu.vn",0, "123","Trần Trí Thức","123456","0787960456","MyUri",40,10000);
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

        findViewById(R.id.turnBack).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        locationTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                locationTxt.setVisibility(View.GONE);
                locationEdt.setText(locationTxt.getText());
                locationEdt.setVisibility(View.VISIBLE);
                setEnableSaveButton(true);
            }
        });

        locationEdt.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                boolean handled = false;
                if(actionId == EditorInfo.IME_ACTION_DONE){
                    locationEdt.setVisibility(View.GONE);
                    locationTxt.setVisibility(View.VISIBLE);
                    locationTxt.setText(locationEdt.getText());
                    handled = true;
                }

                return handled;
            }
        });

        birthdayTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                birthdayTxt.setVisibility(View.GONE);
                birthdayEdt.setText(birthdayTxt.getText());
                birthdayEdt.setVisibility(View.VISIBLE);

            }
        });

        birthdayEdt.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                boolean handled = false;
                if(actionId == EditorInfo.IME_ACTION_DONE){
                    birthdayEdt.setVisibility(View.GONE);
                    birthdayTxt.setVisibility(View.VISIBLE);
                    birthdayTxt.setText(birthdayEdt.getText());
                    handled = true;
                }

                return handled;
            }
        });

        nameTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nameTxt.setVisibility(View.GONE);
                nameEdt.setText(nameTxt.getText());
                nameEdt.setVisibility(View.VISIBLE);

            }
        });

        nameEdt.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                boolean handled = false;
                if(actionId == EditorInfo.IME_ACTION_DONE){
                    nameEdt.setVisibility(View.GONE);
                    nameTxt.setVisibility(View.VISIBLE);
                    nameTxt.setText(nameEdt.getText());
                    handled = true;
                }
                return handled;
            }
        });

        sizeTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sizeTxt.setVisibility(View.GONE);
                sizeEdt.setText(sizeTxt.getText());
                sizeEdt.setVisibility(View.VISIBLE);

            }
        });
        sizeEdt.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                boolean handled = false;
                if(actionId == EditorInfo.IME_ACTION_DONE){
                    sizeEdt.setVisibility(View.GONE);
                    sizeTxt.setVisibility(View.VISIBLE);
                    sizeTxt.setText(sizeEdt.getText());
                    handled = true;
                }
                return handled;
            }
        });

        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    private void Init(){
        mAuth = FirebaseAuth.getInstance();
        dataBase = FirebaseDatabase.getInstance().getReference();
        currentUser = mAuth.getCurrentUser();
        settingLayout = findViewById(R.id.settingLayout);

        nameTxt = findViewById(R.id.nameTxt);
        nameEdt = findViewById(R.id.nameEdt);

        location = findViewById(R.id.location);
        locationEdt = findViewById(R.id.locationEdt);
        locationTxt = findViewById(R.id.locationTxt);

        birthdayEdt = findViewById(R.id.birthdayEdt);
        birthdayTxt = findViewById(R.id.birthdayTxt);

        maleRad = findViewById(R.id.radioButton_male);
        femaleRad = findViewById(R.id.radioButton_female);
        otherRad = findViewById(R.id.radioButton_other);

        sizeEdt = findViewById(R.id.sizeEdt);
        sizeTxt = findViewById(R.id.sizeTxt);

        saveBtn = findViewById(R.id.saveBtn);

    }
    void getData(){
        dataBase.child("Users").child(currentUser.getUid()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                user = snapshot.getValue(User.class);

                locationTxt.setText(user.getAddress());
                birthdayTxt.setText(user.getDOB());
                sizeTxt.setText(user.getSize()+"");

                if(user.getGender() == 0){
                    maleRad.setChecked(true);
                }
                else if(user.getGender() == 1){
                    femaleRad.setChecked(true);
                }
                else if(user.getGender() == -1){
                    otherRad.setChecked(true);
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    public void setData(String address, String DOB, String email, int gender, String id, String name, String password, String phoneNumber, String profilePic, int size, int total){
        User mUser =  new User(address, DOB, email, gender, currentUser.getUid(), name, password,phoneNumber,profilePic,size,total);
        dataBase.child("Users").child(currentUser.getUid()).setValue(mUser);
    }

    public void setEnableSaveButton(boolean isEnable){
        if(isEnable == true){
            saveBtn.setVisibility(View.VISIBLE);
            saveBtn.setBackgroundColor(Color.parseColor("#000000"));

        }
        else if(isEnable == false){
            saveBtn.setVisibility(View.INVISIBLE);
            saveBtn.setBackgroundColor(Color.parseColor("#777676"));
        }
    }
}