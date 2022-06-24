package com.example.zendi_application.ActivityAccount.Admin.AccountManager;

import static com.example.zendi_application.DataManager.listUsers;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.os.Build;
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

import com.example.zendi_application.ActivityAccount.ConfirmEmail.ConfirmPasswordDialog;
import com.example.zendi_application.ActivityAccount.SettingActivity;
import com.example.zendi_application.ActivityAccount.User;
import com.example.zendi_application.DataManager;
import com.example.zendi_application.R;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.Base64;
import java.util.Calendar;

public class EditAccountActivity extends AppCompatActivity {
    private User user;

    private EditText nameEdt;
    private TextView emailTxt;
    private EditText passwordEdt;
    private EditText locationEdt;
    private EditText phoneNumberEdt;
    private TextView birthdayTxt;

    private RadioButton maleRad;
    private RadioButton femaleRad;
    private RadioButton otherRad;

    private RadioButton adminRad;
    private RadioButton staffRad;

    private Button saveBtn;


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
        emailTxt = findViewById(R.id.emailTxt);
        passwordEdt = findViewById(R.id.passwordEdt);
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
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View view) {
                if(checkData()){
                    pushData();
                }
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
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                user = snapshot.getValue(User.class);

                nameEdt.setText(user.getName()+"");
                byte[] decodedBytes = Base64.getDecoder().decode(user.getPassword());
                passwordEdt.setText( new String(decodedBytes));
                emailTxt.setText(user.getEmail());
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

    @RequiresApi(api = Build.VERSION_CODES.O)
    void pushData(){
        String nameVal = nameEdt.getText().toString();
        String emailVal = emailTxt.getText().toString().trim();
        String passwordVal = passwordEdt.getText().toString();
        String locationVal = locationEdt.getText().toString();
        String phoneVal = phoneNumberEdt.getText().toString();
        String  birthdayVal = birthdayTxt.getText().toString();
        String id = emailVal.split("@")[0];

        int mGender = 0;
        if (femaleRad.isChecked() ) {
            mGender = 1;
        }

        int shopOwner = 1;
        if (adminRad.isChecked() ) {
            shopOwner = 2;
        }

        String encodePassword = Base64.getEncoder().encodeToString(passwordVal.getBytes());
        User user = new User(
                locationVal,
                birthdayVal,
                emailVal,
                encodePassword,
                mGender,
                id,
                nameVal,
                phoneVal,
                "",
                "0",
                "0",
                shopOwner);

        String idRemove = getIntent().getStringExtra("id");
        listUsers.removeIf(e -> e.getId().equals(idRemove));

        dataBase.child("Users").child(id).setValue(user)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(getApplicationContext(),"Edit account successfully!",Toast.LENGTH_LONG).show();
                        finish();
                    }
                });
    }

    boolean checkData (){
        String nameVal = nameEdt.getText().toString();
        String passwordVal = passwordEdt.getText().toString();
        String locationVal = locationEdt.getText().toString();
        String phoneVal = phoneNumberEdt.getText().toString();
        String  birthdayVal = birthdayTxt.getText().toString();

        if(nameVal.isEmpty() || locationVal.isEmpty() || phoneVal.isEmpty() || birthdayVal.equals("DD / MM / YY") ){
            Toast.makeText(getApplicationContext(), "Please fill out information!", Toast.LENGTH_LONG).show();
            return false;
        }


        if(phoneVal.length()!=10 && phoneVal.length()!=11 && !android.text.TextUtils.isDigitsOnly(phoneVal)){
            Toast.makeText(getApplicationContext(), "Incorrect phone number!", Toast.LENGTH_LONG).show();
            return false;
        }

        if(!checkPassword(passwordVal)){
            Toast.makeText(getApplicationContext(),  "Password length must be equal or more than 6 characters", Toast.LENGTH_LONG).show();
            return false;
        }

        return true;
    }

    boolean checkPassword(String password) {
        return password.length() >= 6;
    }

    void deleteHandle (){
        String id = getIntent().getStringExtra("id");
        ConfirmDeleteDialog confirmDialog = new ConfirmDeleteDialog(EditAccountActivity.this, id);
        confirmDialog.show();
    }

}