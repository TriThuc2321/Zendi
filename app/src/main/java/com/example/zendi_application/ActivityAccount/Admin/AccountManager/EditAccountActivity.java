package com.example.zendi_application.ActivityAccount.Admin.AccountManager;

import static com.example.zendi_application.DataManager.listUsers;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.zendi_application.ActivityAccount.User;
import com.example.zendi_application.DataManager;
import com.example.zendi_application.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Calendar;

public class EditAccountActivity extends AppCompatActivity {
    private User user;

    private EditText nameEdt;
    private EditText emailEdt;
    private EditText locationEdt;
    private EditText phoneNumberEdt;
    private TextView birthdayTxt;

    private RadioButton maleRad;
    private RadioButton femaleRad;
    private RadioButton otherRad;

    private RadioButton adminRad;
    private RadioButton staffRad;

    private Button saveBtn;
    private Button deleteBtn;


    private DatabaseReference dataBase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_account);

        init();
        setUser();
    }

    void init(){
        dataBase = FirebaseDatabase.getInstance().getReference();

        nameEdt = findViewById(R.id.nameEdt);
        emailEdt = findViewById(R.id.emailEdt);
        locationEdt = findViewById(R.id.locationEdt);
        phoneNumberEdt = findViewById(R.id.phoneNumberEdt);
        birthdayTxt = findViewById(R.id.birthdayTxt);

        maleRad = findViewById(R.id.radioButton_male);
        maleRad.setChecked(true);
        femaleRad = findViewById(R.id.radioButton_female);

        adminRad = findViewById(R.id.radioButton_admin);
        adminRad.setChecked(true);
        staffRad = findViewById(R.id.radioButton_staff);
        saveBtn = findViewById(R.id.saveBtn);

        birthdayTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final Calendar c = Calendar.getInstance();
                int mYear = c.get(Calendar.YEAR);
                int mMonth = c.get(Calendar.MONTH);
                int mDay = c.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog datePickerDialog = new DatePickerDialog(EditAccountActivity.this, android.R.style.Theme_Holo_Dialog,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                                if(year > 2006) {
                                    Toast.makeText(getApplicationContext(), "Age must be greater than 16", Toast.LENGTH_LONG).show();
                                }
                                else{
                                    birthdayTxt.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
                                }
                            }
                        }, mYear, mMonth, mDay);
                datePickerDialog.show();

            }
        });

        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkData();
            }
        });

        findViewById(R.id.turnBack).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    void setUser(){
        String id = getIntent().getStringExtra("id");

        dataBase.child("Users").child(id).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                user = snapshot.getValue(User.class);

                nameEdt.setText(user.getName()+"");
                emailEdt.setText(user.getEmail());
                locationEdt.setText(user.getAddress()+"");
                birthdayTxt.setText(user.getDOB()+"");
                phoneNumberEdt.setText(user.getPhoneNumber()+"");

                if(user.getGender() == 0){
                    maleRad.setChecked(true);
                }
                if( user.getGender()== 1){
                    femaleRad.setChecked(true);
                }

                if(user.getShopOwner() == 1){
                    staffRad.setChecked(true);
                }
                if(user.getShopOwner() == 2){
                    adminRad.setChecked(true);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    boolean checkData (){
        String nameVal = nameEdt.getText().toString();
        String emailVal = emailEdt.getText().toString().trim();
        String locationVal = locationEdt.getText().toString();
        String phoneVal = phoneNumberEdt.getText().toString();
        String  birthdayVal = birthdayTxt.getText().toString();

        if(nameVal.isEmpty() || emailVal.isEmpty() || locationVal.isEmpty() || phoneVal.isEmpty() || birthdayVal.equals("DD / MM / YY") ){
            Toast.makeText(getApplicationContext(), "Please fill out information!", Toast.LENGTH_LONG).show();
            return false;
        }

        if(TextUtils.isEmpty(emailVal) || !Patterns.EMAIL_ADDRESS.matcher(emailVal).matches()){
            Toast.makeText(getApplicationContext(), "Email invalid!", Toast.LENGTH_LONG).show();
            return false;
        }

        if(phoneVal.length()!=10 && phoneVal.length()!=11 && !android.text.TextUtils.isDigitsOnly(phoneVal)){
            Toast.makeText(getApplicationContext(), "Incorrect phone number!", Toast.LENGTH_LONG).show();
            return false;
        }

        if(existEmail(emailVal)){
            Toast.makeText(getApplicationContext(), "Email already in use", Toast.LENGTH_LONG).show();
            return false;
        }
        return true;
    }

    public boolean existEmail(String email){
        for(int i =0; i< listUsers.size(); i++){
            String a = listUsers.get(i).getEmail();
            if(a == null) continue;
            if(a.compareTo(email) == 0){
                return true;
            }
        }
        return false;
    }
}