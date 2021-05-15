package com.example.zendi_application.ActivityAccount;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.CompoundButton;
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

    private TextView totalTxt;

    private TextView phoneNumberTxt;
    private EditText phoneNumberEdt;

    private Button saveBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);

        Init();
        SetButtonClick();
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
                setEdtToTxt();
            }
        });

        findViewById(R.id.turnBack).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        findViewById(R.id.backgroundLayout).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setEdtToTxt();
            }
        });
        //---------------------LOCATION---------------------//
        locationTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                locationTxt.setVisibility(View.GONE);
                locationEdt.setText(locationTxt.getText());
                locationEdt.setVisibility(View.VISIBLE);
                saveBtn.setVisibility(View.VISIBLE);

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
        locationEdt.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    hideKeyboard(v);
                }
            }
        });
        //---------------------LOCATION--------------------//

        //---------------------BIRTHDAY---------------------//
        birthdayTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                birthdayTxt.setVisibility(View.GONE);
                birthdayEdt.setText(birthdayTxt.getText());
                birthdayEdt.setVisibility(View.VISIBLE);
                saveBtn.setVisibility(View.VISIBLE);
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

        birthdayEdt.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    hideKeyboard(v);
                }
            }
        });
        //--------------------BIRTHDAY--------------//

        //--------------------NAME------------------//
        nameTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nameTxt.setVisibility(View.GONE);
                nameEdt.setText(nameTxt.getText());
                nameEdt.setVisibility(View.VISIBLE);
                saveBtn.setVisibility(View.VISIBLE);
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
        nameEdt.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    hideKeyboard(v);
                }
            }
        });
        //----------------------NAME-------------------//

        //----------------------SIZE-------------------//
        sizeTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sizeTxt.setVisibility(View.GONE);
                sizeEdt.setText(sizeTxt.getText());
                sizeEdt.setVisibility(View.VISIBLE);
                saveBtn.setVisibility(View.VISIBLE);
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

        sizeEdt.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    hideKeyboard(v);
                }
            }
        });
        //----------------------SIZE----------------------//
        //----------------------------PHONE NUMBER-----------------------//

        phoneNumberTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                phoneNumberTxt.setVisibility(View.GONE);
                phoneNumberEdt.setText(sizeTxt.getText());
                phoneNumberEdt.setVisibility(View.VISIBLE);
                saveBtn.setVisibility(View.VISIBLE);
            }
        });
        phoneNumberEdt.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                boolean handled = false;
                if(actionId == EditorInfo.IME_ACTION_DONE){
                    phoneNumberEdt.setVisibility(View.GONE);
                    phoneNumberTxt.setVisibility(View.VISIBLE);
                    phoneNumberTxt.setText(phoneNumberEdt.getText());
                    handled = true;

                }
                return handled;
            }
        });

        phoneNumberEdt.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    hideKeyboard(v);
                }
            }
        });
        //----------------------------PHONE NUMBER-----------------------//
        findViewById(R.id.profileInfo).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setEdtToTxt();
            }
        });

                //---------------------RADIO BUTTON--------------------//
        maleRad.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                saveBtn.setVisibility(View.VISIBLE);
            }
        });
        femaleRad.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                saveBtn.setVisibility(View.VISIBLE);
            }
        });
        otherRad.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                saveBtn.setVisibility(View.VISIBLE);
            }
        });


        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setEdtToTxt();
                saveBtn.setVisibility(View.INVISIBLE);
                String mGender = "";

                if(maleRad.isChecked() == true){
                    mGender = "0";
                }
                else if(femaleRad.isChecked() == true){
                    mGender = "1";
                }
                else if(otherRad.isChecked() == true){
                    mGender = "";
                }
                setData(locationTxt.getText().toString(),birthdayTxt.getText().toString(),currentUser.getEmail(),mGender,currentUser.getUid(), nameTxt.getText().toString(),phoneNumberTxt.getText().toString(),"ImageUri",sizeTxt.getText().toString(),totalTxt.getText().toString());
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

        totalTxt = findViewById(R.id.totalTxt);

        phoneNumberEdt = findViewById(R.id.phoneNumberEdt);
        phoneNumberTxt = findViewById(R.id.phoneNumberTxt);

        saveBtn = findViewById(R.id.saveBtn);

    }
    void getData(){
        dataBase.child("Users").child(currentUser.getUid()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                user = snapshot.getValue(User.class);

                nameTxt.setText(user.getName());
                locationTxt.setText(user.getAddress());
                birthdayTxt.setText(user.getDOB());
                sizeTxt.setText(user.getSize()+"");
                phoneNumberTxt.setText(user.getPhoneNumber());
                totalTxt.setText(user.getTotal());

                //maleRad.setChecked(true);

                if(user.getGender() == "0"){
                    maleRad.setChecked(true);
                }
                if( user.getGender()== "1"){
                    femaleRad.setChecked(true);
                }
                if(user.getGender() == ""){
                    otherRad.setChecked(true);
                }
                saveBtn.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    public void setData(String address, String DOB, String email, String gender, String id, String name, String phoneNumber, String profilePic, String size, String total){
        user =  new User(address, DOB, email, gender, id, name,phoneNumber,profilePic,size,total);
        dataBase.child("Users").child(currentUser.getUid()).setValue(user);
    }
    public void hideKeyboard(View view) {
        InputMethodManager inputMethodManager =(InputMethodManager)getSystemService(Activity.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }
    public void setEdtToTxt(){
        nameEdt.setVisibility(View.GONE);
        locationEdt.setVisibility(View.GONE);
        birthdayEdt.setVisibility(View.GONE);
        sizeEdt.setVisibility(View.GONE);
        phoneNumberEdt.setVisibility(View.GONE);

        nameTxt.setVisibility(View.VISIBLE);
        locationTxt.setVisibility(View.VISIBLE);
        birthdayTxt.setVisibility(View.VISIBLE);
        sizeTxt.setVisibility(View.VISIBLE);
        phoneNumberTxt.setVisibility(View.VISIBLE);
    }

}