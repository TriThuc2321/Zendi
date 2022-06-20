package com.example.zendi_application.ActivityAccount;

import static com.example.zendi_application.ActivityAccount.SettingActivity.isConfirm;
import static com.example.zendi_application.DataManager.listUsers;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.zendi_application.ActivityAccount.ConfirmEmail.GmailSender;
import com.example.zendi_application.R;
import com.google.android.gms.tasks.OnSuccessListener;

import java.util.Random;

public class ForgotPassword_1 extends AppCompatActivity {

    Button btnSend, btnConfirm;
    EditText number1, number2, number3, number4, number5, number6;
    EditText etEmail;
    TextView tvCodeNote, tvEmailNote;

    int randomCode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password1);
        initView();
        setVisibleGone();

        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setVisibleGone();
                if (etEmail.getText().toString().equals("")){
                    tvEmailNote.setVisibility(View.VISIBLE);
                    tvEmailNote.setText("Please enter your email");
                }
                else if (isValidEmail(etEmail.getText().toString())){
                    boolean existed = false;
                    for (int i = 0; i < listUsers.size(); i++) {
                        if (listUsers.get(i).getEmail().equals(etEmail.getText().toString())) {
                            existed = true;
                            break;
                        }
                    }

                    if (!existed){
                        tvEmailNote.setVisibility(View.VISIBLE);
                        tvEmailNote.setText("This email is not existed!");
                    }
                    else{
                        sendEmail();
                        btnConfirm.setVisibility(View.VISIBLE);
                        btnSend.setText("Resend");
                    }
                }
                else
                {
                    tvEmailNote.setVisibility(View.VISIBLE);
                    tvEmailNote.setText("Invalid email!");
                }
            }
        });

        btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setVisibleGone();
                String b = number1.getText().toString()
                        + number2.getText().toString() +
                        number3.getText().toString() +
                        number4.getText().toString() +
                        number5.getText().toString() +
                        number6.getText().toString();
                Log.d("ConfirmRegister userentercode", b);
                int c = Integer.parseInt(b);
                String a = randomCode + "";

                if (c == randomCode) {
                    isConfirm = true;
                    Log.d("ConfirmRegister", "correct code");
                    Intent newIntent = new Intent(ForgotPassword_1.this, ForgotPassword_2.class);
                    newIntent.putExtra("email", etEmail.getText().toString());
                    startActivity(newIntent);

                } else {
                    Log.d("ConfirmRegister", "incorrect code");
                    //Toast.makeText(mContext,"Incorrect verify code",Toast.LENGTH_LONG).show();
                    tvCodeNote.setVisibility(View.VISIBLE);
                    tvCodeNote.setText("Incorrect verify code!");
                }
            }
        });
    }

    void initView(){
        btnSend = findViewById(R.id.btnForgotPassSend);
        btnConfirm = findViewById(R.id.btnForgotPassConfirm);
        number1 = findViewById(R.id.et_num_1);
        number2 = findViewById(R.id.et_num_2);
        number3 = findViewById(R.id.et_num_3);
        number4 = findViewById(R.id.et_num_4);
        number5 = findViewById(R.id.et_num_5);
        number6 = findViewById(R.id.et_num_6);
        btnConfirm.setVisibility(View.GONE);
        tvCodeNote = findViewById(R.id.txtForgotPassNoteCode);
        tvEmailNote = findViewById(R.id.txtForgotPassEmailNote);
        etEmail = findViewById(R.id.txtForgotPassEmail);
        setTextChangedListener();
    }

    void setVisibleGone(){
        tvCodeNote.setVisibility(View.GONE);
        tvEmailNote.setVisibility(View.GONE);
    }

    private void setTextChangedListener() {
        number1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                String text = editable.toString();
                if (text.length() == 1)
                    number2.requestFocus();
            }
        });

        number2.addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String text = s.toString();
                if (text.length() == 1)
                    number3.requestFocus();
                else
                    number1.requestFocus();
            }
        });

        number3.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String text = s.toString();
                if (text.length() == 1)
                    number4.requestFocus();
                else
                    number2.requestFocus();
            }
        });

        number4.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String text = s.toString();
                if (text.length() == 1)
                    number5.requestFocus();
                else
                    number3.requestFocus();
            }
        });

        number5.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String text = s.toString();
                if (text.length() == 1)
                    number6.requestFocus();
                else
                    number4.requestFocus();
            }
        });

        number6.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String text = s.toString();
                if (text.length() == 1)
                    ;
                else
                    number5.requestFocus();
            }
        });
    }

    public boolean isValidEmail(String email) {
        return !TextUtils.isEmpty(email) && Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    void sendEmail() {
        final ProgressDialog dialog = new ProgressDialog(ForgotPassword_1.this);
        dialog.setTitle("Sending Email");
        dialog.setMessage("Please wait");
        dialog.show();
        randomCode = new Random().nextInt(900000) + 100000;
        Thread sender = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    GmailSender sender = new GmailSender("zendiapplication@gmail.com", "yovmsjtkpwwfgbbv");
                    sender.sendMail("Verify code",
                            "Thank you for using Zendi Application, this is you verify code: " + randomCode,
                            "planzyapplycation@gmail.com",
                            etEmail.getText().toString());
                    dialog.dismiss();


                } catch (Exception e) {
                    Log.e("mylog", "Error: " + e.getMessage());
                }
            }
        });
        sender.start();
    }
}