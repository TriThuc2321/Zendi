package com.example.zendi_application.ActivityAccount;

import androidx.appcompat.app.AppCompatActivity;
import com.example.zendi_application.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

import java.util.Calendar;

public class RegisterActivity extends AppCompatActivity {
    //view
    EditText etName;
    EditText etPhone;
    EditText etAddress;
    EditText etBirthday;
    EditText etEmail;
    EditText etPassword;
    EditText etRepeatPassword;

    TextView tvNameNote;
    TextView tvPhoneNote;
    TextView tvAddressNote;
    TextView tvBirthdayNote;
    TextView tvEmailNote;
    TextView tvPasswordNote;
    TextView tvRepeatPasswordNote;
    TextView tvGenderNote;
    TextView tvSignIn;

    RadioButton maleRad;
    RadioButton femaleRad;
    RadioButton otherRad;

    Button btnRegister;


    //firebase
    private User user;
    private DatabaseReference dataBase;
    private FirebaseAuth mAuth;
    private FirebaseUser currentUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        init();

        tvSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent newIntent = new Intent(RegisterActivity.this, Account_Activity.class);
                startActivity(newIntent);
            }
        });

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (validate()){

                }
            }
        });

    }
    void setData(){
        int mGender =0;
        if (maleRad.isChecked() == true) {
            mGender = 0;
        } else if (femaleRad.isChecked() == true) {
            mGender = 1;
        } else if (otherRad.isChecked() == true) {
            mGender = 2;
        }

        String id = "";
        String profilePic = "";
        String size = "";
        String total = "";
        int shopOwner = 0;

        user = new User(
                etAddress.getText().toString(),
                etBirthday.getText().toString(),
                etEmail.getText().toString(),
                mGender,
                id,
                etName.getText().toString(),
                etPhone.getText().toString(),
                profilePic,
                size,
                total,
                shopOwner);
        ///Log.d(user)
        dataBase.child("Users").child(etEmail.getText().toString()).setValue(user);
    }
    boolean validate(){
        boolean check = true;
        if (etName.getText().equals("") || etName.getText().equals(null) || etName.getText().equals(" "))
        {
            tvNameNote.setText("Please enter your name!");
            tvNameNote.setVisibility(View.VISIBLE);
            check = false;
        }

        //Log.d("name", etName.getText().toString());
        if (etPhone.getText().equals("") || etPhone.getText().equals(null)|| etPhone.getText().equals(" ")){
            tvPhoneNote.setText("Please enter your phone number");
            tvPhoneNote.setVisibility(View.VISIBLE);
            check = false;
        }

        if (etAddress.getText().equals("") || etAddress.getText().equals(null) || etAddress.getText().equals(" ")){
            tvAddressNote.setText("Please enter your address");
            tvAddressNote.setVisibility(View.VISIBLE);
            check = false;
        }

        if (etBirthday.getText().equals("") || etBirthday.getText().equals(null) || etAddress.getText().equals(" ")){
            tvBirthdayNote.setText("Please enter your birthday");
            tvBirthdayNote.setVisibility(View.VISIBLE);
            check = false;
        }

        if (etEmail.getText().equals("") || etEmail.getText().equals(null) || etEmail.getText().equals(" ")){
            tvEmailNote.setText("Please enter your email");
            tvEmailNote.setVisibility(View.VISIBLE);
            check = false;
        }
        else if (!isValidEmail(etEmail.getText().toString())){
            tvEmailNote.setText("Your email is invalid!");
            tvEmailNote.setVisibility(View.VISIBLE);
            check = false;
        }

        return true;
    }
    void init(){
         etName = findViewById(R.id.txtRegisterName);
         etPhone = findViewById(R.id.txtRegisterPhone);
         etAddress = findViewById(R.id.txtRegisterAddress);
         etBirthday = findViewById(R.id.txtRegisterBirthday);
         etEmail = findViewById(R.id.txtRegisterEmail);
         etPassword = findViewById(R.id.txtRegisterPass1);
         etRepeatPassword = findViewById(R.id.txtRegisterPass2);

         tvNameNote= findViewById(R.id.txtRegisterNameNote);
         tvPhoneNote= findViewById(R.id.txtRegisterPhoneNote);
         tvAddressNote= findViewById(R.id.txtRegisterAddressNote);
         tvBirthdayNote= findViewById(R.id.txtRegisterBirthdayNote);
         tvEmailNote= findViewById(R.id.txtRegisterEmailNote);
         tvPasswordNote= findViewById(R.id.txtRegisterPass1Note);
         tvRepeatPasswordNote= findViewById(R.id.txtRegisterPass2Note);
         tvGenderNote= findViewById(R.id.txtRegisterGenderNote);
         tvSignIn= findViewById(R.id.txtRegisterSignIn);

         tvNameNote.setVisibility(View.GONE);
         tvPhoneNote.setVisibility(View.GONE);
         tvAddressNote.setVisibility(View.GONE);
         tvBirthdayNote.setVisibility(View.GONE);
         tvEmailNote.setVisibility(View.GONE);
         tvPasswordNote.setVisibility(View.GONE);
         tvRepeatPasswordNote.setVisibility(View.GONE);
         tvGenderNote.setVisibility(View.GONE);

         maleRad= findViewById(R.id.rdRegisterMale);
         femaleRad= findViewById(R.id.rdRegisterFemale);
         otherRad= findViewById(R.id.rdRegisterOther);

         btnRegister= findViewById(R.id.btnRegisterSubmit);

        etBirthday.setOnClickListener(new View.OnClickListener() {
                                          @Override
                                          public void onClick(View v) {
                                              final Calendar c = Calendar.getInstance();
                                              int mYear = c.get(Calendar.YEAR);
                                              int mMonth = c.get(Calendar.MONTH);
                                              int mDay = c.get(Calendar.DAY_OF_MONTH);

                                              DatePickerDialog datePickerDialog = new DatePickerDialog(RegisterActivity.this, android.R.style.Theme_Holo_Dialog,
                                                      new DatePickerDialog.OnDateSetListener() {

                                                          @Override
                                                          public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                                                              etBirthday.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
                                                          }
                                                      }, mYear, mMonth, mDay);
                                              datePickerDialog.show();


                                          }
                                      });

        mAuth = FirebaseAuth.getInstance();
        dataBase = FirebaseDatabase.getInstance().getReference();
    }

    public boolean isValidEmail(String email)
    {
        return !TextUtils.isEmpty(email) && Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }
}