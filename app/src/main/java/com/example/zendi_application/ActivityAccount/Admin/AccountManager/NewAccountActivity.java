package com.example.zendi_application.ActivityAccount.Admin.AccountManager;

import static com.example.zendi_application.DataManager.listUsers;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.zendi_application.ActivityAccount.SettingActivity;
import com.example.zendi_application.R;

import java.util.Calendar;

public class NewAccountActivity extends AppCompatActivity {

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_new_account);

        init();
    }

    void init(){
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

                DatePickerDialog datePickerDialog = new DatePickerDialog(NewAccountActivity.this, android.R.style.Theme_Holo_Dialog,
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