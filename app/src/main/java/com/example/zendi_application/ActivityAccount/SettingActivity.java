package com.example.zendi_application.ActivityAccount;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
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
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.zendi_application.ActivityAccount.ConfirmEmail.ConfirmPasswordDialog;
import com.example.zendi_application.ActivityAccount.ConfirmEmail.GmailSender;
import com.example.zendi_application.DataManager;
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
import com.google.gson.annotations.Until;

import java.util.Calendar;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SettingActivity extends AppCompatActivity {

    private User user;
    private DatabaseReference dataBase;
    private FirebaseAuth mAuth;
    private FirebaseUser currentUser;

    private RelativeLayout settingLayout;
    private LinearLayout location;

    private EditText locationEdt;
    private TextView locationTxt;

    private EditText emailEdt;
    private TextView emailTxt;

    private TextView birthdayTxt;

    private TextView nameTxt;
    private EditText nameEdt;

    private RadioButton maleRad;
    private RadioButton femaleRad;
    private RadioButton otherRad;

    private TextView sizeTxt;
    private SeekBar sizeSb;

    private TextView totalTxt;

    private TextView phoneNumberTxt;
    private EditText phoneNumberEdt;

    private Button saveBtn;

    private String txtForcus;

    private int isShopOwner;
    private List<User> listUsers = DataManager.listUsers;

    public static boolean isConfirm;
    int randomCode;

    public static Button openConfirmDialogBtn;
    public static ImageView lockEmailIcon;


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

                DataManager.host = null;
                setResult(RESULT_OK, null);
                setResult(99);
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
                startActivity(new Intent(SettingActivity.this, HomeScreen.class));
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
                setEdtToTxt();
                txtForcus="location";
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
                    txtForcus = "";
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

        //---------------------EMAIL-----------------------//
        emailTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!isConfirm){
                    emailTxt.setVisibility(View.GONE);
                    emailEdt.setText(emailTxt.getText());
                    emailEdt.setVisibility(View.VISIBLE);
                    saveBtn.setVisibility(View.VISIBLE);
                    setEdtToTxt();
                    txtForcus="email";
                }

            }
        });

        emailEdt.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                boolean handled = false;
                if(actionId == EditorInfo.IME_ACTION_DONE){
                    if (isValidEmail(emailEdt.getText().toString())) {
                        emailTxt.setText(emailEdt.getText().toString());
                    }
                        else {
                        Toast.makeText(getApplicationContext(), "Invalid email address", Toast.LENGTH_SHORT).show();
                    }

                    emailTxt.setVisibility(View.VISIBLE);
                    emailEdt.setVisibility(View.GONE);


                    txtForcus = "";
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

        //-----------------------------EMAIL---------------------------//


        //---------------------BIRTHDAY---------------------//
        birthdayTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveBtn.setVisibility(View.VISIBLE);
                setEdtToTxt();

                final Calendar c = Calendar.getInstance();
                int mYear = c.get(Calendar.YEAR);
                int mMonth = c.get(Calendar.MONTH);
                int mDay = c.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog datePickerDialog = new DatePickerDialog(SettingActivity.this, android.R.style.Theme_Holo_Dialog,
                        new DatePickerDialog.OnDateSetListener() {

                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {

                                birthdayTxt.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);

                            }
                        }, mYear, mMonth, mDay);
                datePickerDialog.show();


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
                setEdtToTxt();
                txtForcus="name";
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
                    txtForcus="";
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
                if(sizeTxt.getText() == "" || sizeTxt.getText()=="Size") {
                    sizeSb.setProgress(0);
                }
                else{
                    sizeSb.setProgress(Integer.parseInt(sizeTxt.getText().toString()) - 3);
                }
                setEdtToTxt();
                sizeSb.setVisibility(View.VISIBLE);
                saveBtn.setVisibility(View.VISIBLE);
                txtForcus="size";
            }
        });
        sizeSb.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                sizeTxt.setText(progress+ 3+ "");
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        //----------------------SIZE----------------------//
        //----------------------------PHONE NUMBER-----------------------//

        phoneNumberTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                phoneNumberTxt.setVisibility(View.GONE);
                phoneNumberEdt.setText(phoneNumberTxt.getText());
                phoneNumberEdt.setVisibility(View.VISIBLE);
                saveBtn.setVisibility(View.VISIBLE);
                setEdtToTxt();
                txtForcus="phone";
            }
        });
        phoneNumberEdt.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                boolean handled = false;
                if(actionId == EditorInfo.IME_ACTION_DONE){
                    phoneNumberEdt.setVisibility(View.GONE);
                    phoneNumberTxt.setVisibility(View.VISIBLE);

                    String temp = phoneNumberEdt.getText().toString();


                    if((temp.length()==10 || temp.length()==11) && android.text.TextUtils.isDigitsOnly(temp)){
                        phoneNumberTxt.setText(phoneNumberEdt.getText());
                    }
                    else {
                        Toast.makeText(getApplicationContext(), "Incorrect phone number!", Toast.LENGTH_LONG).show();
                    }

                    handled = true;
                    txtForcus="";
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
                setEdtToTxt();
                saveBtn.setVisibility(View.VISIBLE);
            }
        });
        femaleRad.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                setEdtToTxt();
                saveBtn.setVisibility(View.VISIBLE);
            }
        });
        otherRad.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                setEdtToTxt();
                saveBtn.setVisibility(View.VISIBLE);
            }
        });


        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isConfirm) {
                    saveBtn.setVisibility(View.INVISIBLE);
                    int mGender = 2;

                    if (maleRad.isChecked() == true) {
                        mGender = 0;
                    } else if (femaleRad.isChecked() == true) {
                        mGender = 1;
                    } else if (otherRad.isChecked() == true) {
                        mGender = 2;
                    }
                    setData(locationTxt.getText().toString(), birthdayTxt.getText().toString(), emailTxt.getText().toString(), mGender, currentUser.getUid(), nameTxt.getText().toString(), phoneNumberTxt.getText().toString(), "ImageUri", sizeTxt.getText().toString(), totalTxt.getText().toString(), isShopOwner);
                }
                else {
                    Toast.makeText(getApplicationContext(),"Email is not confirm",Toast.LENGTH_LONG).show();
                }
                setEdtToTxt();
            }
        });

        openConfirmDialogBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(emailTxt.getText() == null || emailTxt.getText().toString() == ""){
                    Toast.makeText(getApplicationContext(), "Enter email to continue", Toast.LENGTH_LONG).show();
                }
                else if(existEmail(emailTxt.getText().toString())){
                    Toast.makeText(getApplicationContext(), "Email already used", Toast.LENGTH_LONG).show();
                }
                else{
                    sendEmail();
                    ConfirmPasswordDialog confirmPasswordDialog = new ConfirmPasswordDialog(SettingActivity.this, emailTxt.getText().toString(), randomCode);
                    confirmPasswordDialog.show();
                }
            }
        });
    }

    private void Init() {
        mAuth = FirebaseAuth.getInstance();
        dataBase = FirebaseDatabase.getInstance().getReference();
        currentUser = mAuth.getCurrentUser();

        settingLayout = findViewById(R.id.settingLayout);

        nameTxt = findViewById(R.id.nameTxt);
        nameEdt = findViewById(R.id.nameEdt);

        location = findViewById(R.id.location);
        locationEdt = findViewById(R.id.locationEdt);
        locationTxt = findViewById(R.id.locationTxt);

        emailEdt = findViewById(R.id.emailEdt);
        emailTxt = findViewById(R.id.emailTxt);

        birthdayTxt = findViewById(R.id.birthdayTxt);

        maleRad = findViewById(R.id.radioButton_male);
        femaleRad = findViewById(R.id.radioButton_female);
        otherRad = findViewById(R.id.radioButton_other);

        sizeTxt = findViewById(R.id.sizeTxt);
        sizeSb = findViewById(R.id.sizeSb);

        totalTxt = findViewById(R.id.totalTxt);

        phoneNumberEdt = findViewById(R.id.phoneNumberEdt);
        phoneNumberTxt = findViewById(R.id.phoneNumberTxt);

        saveBtn = findViewById(R.id.saveBtn);
        txtForcus = "";

        openConfirmDialogBtn = findViewById(R.id.open_confirm_dialog_btn);
        lockEmailIcon = findViewById(R.id.lockImg);


    }
    void getData(){
        dataBase.child("Users").child(currentUser.getUid()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                user = snapshot.getValue(User.class);

                String a = user.getEmail();
                if (user.getEmail() == null || a.compareTo("")==0) {
                    isConfirm = false;
                    lockEmailIcon.setVisibility(View.GONE);
                    openConfirmDialogBtn.setVisibility(View.VISIBLE);
                } else{
                    isConfirm = true;
                    lockEmailIcon.setVisibility(View.VISIBLE);
                    openConfirmDialogBtn.setVisibility(View.GONE);
                }

                DataManager.host = user;
                DataManager.getShoeInBagFromFirestone("InBag/" + DataManager.host.getId() + "/ShoeList",DataManager.list);
                DataManager.getShoeInWishFromFirestone("InWish/" + DataManager.host.getId() + "/ShoeList",DataManager.shoeInWish);

                nameTxt.setText(user.getName()+"");
                emailTxt.setText(user.getEmail());
                locationTxt.setText(user.getAddress()+"");
                birthdayTxt.setText(user.getDOB()+"");
                sizeTxt.setText(user.getSize()+"");
                phoneNumberTxt.setText(user.getPhoneNumber()+"");
                totalTxt.setText(user.getTotal()+"");
                isShopOwner = user.getShopOwner();

                //maleRad.setChecked(true);

                if(user.getGender() == 0){
                    maleRad.setChecked(true);
                }
                if( user.getGender()== 1){
                    femaleRad.setChecked(true);
                }
                if(user.getGender() == 2){
                    otherRad.setChecked(true);
                }
                saveBtn.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    public boolean existEmail(String email){
        for(int i =0; i< listUsers.size(); i++){
            String a = listUsers.get(i).getEmail();
            if(a == null) break;
            if(a.compareTo(email) == 0){
                return true;
            }
        }
        return false;
    }
    public void setData(String address, String DOB, String email, int gender, String id, String name, String phoneNumber, String profilePic, String size, String total, int isShoOwner){
        user =  new User(address, DOB, email, gender, id, name,phoneNumber,profilePic,size,total, isShoOwner);
        dataBase.child("Users").child(currentUser.getUid()).setValue(user);
    }
    public void hideKeyboard(View view) {
        InputMethodManager inputMethodManager =(InputMethodManager)getSystemService(Activity.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }
    public void setEdtToTxt(){
        if(txtForcus=="name"){
            nameEdt.setVisibility(View.GONE);
            nameTxt.setVisibility(View.VISIBLE);
        }
        else if(txtForcus=="phone"){
            phoneNumberEdt.setVisibility(View.GONE);
            phoneNumberTxt.setVisibility(View.VISIBLE);
        }
        else if(txtForcus=="location"){
            locationEdt.setVisibility(View.GONE);
            locationTxt.setVisibility(View.VISIBLE);
        }
        else if(txtForcus=="size"){
            sizeSb.setVisibility(View.GONE);
        }
        else if(txtForcus=="email"){
            emailTxt.setVisibility(View.VISIBLE);
            emailEdt.setVisibility(View.GONE);
        }
        txtForcus="";
    }

    protected void onStop() {
        setResult(99);
        super.onStop();
    }
    @Override
    protected void onDestroy() {
        setResult(99);
        super.onDestroy();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
        startActivity(new Intent(SettingActivity.this, HomeScreen.class));
    }

    public boolean isValidEmail(String email)
    {
        return !TextUtils.isEmpty(email) && Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    void sendEmail(){
        final ProgressDialog dialog = new ProgressDialog(SettingActivity.this);
        dialog.setTitle("Sending Email");
        dialog.setMessage("Please wait");
        dialog.show();
        Thread sender = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    randomCode = new Random().nextInt(900000) + 100000;

                    GmailSender sender = new GmailSender("zendiapplication@gmail.com", "ThucThienThangHuynh123");
                    sender.sendMail("Verify code",
                            "Thank for using Zendi Application, this is you verify code: " + randomCode,
                            "planzyapplycation@gmail.com",
                            emailTxt.getText().toString());
                    dialog.dismiss();

                } catch (Exception e) {
                    Log.e("mylog", "Error: " + e.getMessage());
                }
            }
        });
        sender.start();
    }
}