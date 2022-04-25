package com.example.zendi_application.ActivityAccount;

import androidx.appcompat.app.AppCompatActivity;
import com.example.zendi_application.R;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

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
         tvPhoneNote= findViewById(R.id.txtRegisterNameNote);
         tvAddressNote= findViewById(R.id.txtRegisterNameNote);
         tvBirthdayNote= findViewById(R.id.txtRegisterNameNote);
         tvEmailNote= findViewById(R.id.txtRegisterNameNote);
         tvPasswordNote= findViewById(R.id.txtRegisterNameNote);
         tvRepeatPasswordNote= findViewById(R.id.txtRegisterNameNote);
         tvGenderNote= findViewById(R.id.txtRegisterNameNote);
         tvSignIn= findViewById(R.id.txtRegisterSignIn);

         maleRad= findViewById(R.id.rdRegisterMale);
         femaleRad= findViewById(R.id.rdRegisterFemale);
         otherRad= findViewById(R.id.rdRegisterOther);

         btnRegister= findViewById(R.id.btnRegisterSubmit);
    }
}