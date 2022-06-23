package com.example.zendi_application.ActivityAccount;

import static com.example.zendi_application.ActivityAccount.SettingActivity.isConfirm;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.example.zendi_application.ActivityAccount.ConfirmEmail.GmailSender;
import com.example.zendi_application.DataManager;
import com.example.zendi_application.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;
import static com.example.zendi_application.DataManager.listUsers;
import java.util.Base64;

public class ChangePassword extends AppCompatActivity {
    View turnBack;
    TextView tvNotePass1, tvNotePass2, tvNotePass0;
    Button btnSend, btnConfirm;
    EditText number1, number2, number3, number4, number5, number6;
    EditText etPass1, etPass2, etPass0;
    TextView tvCodeNote;
    TextView tvEmail;
    LinearLayout confirmLayout;

    int randomCode;
    User currentUser;
    String email;
    private DatabaseReference dataBase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);

        dataBase = FirebaseDatabase.getInstance().getReference();

        email = getIntent().getExtras().getString("email");

        getUser();

       initView();

        turnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setVisibleGone();
                if (validatePassword()) {
                    sendEmail();
                    confirmLayout.setVisibility(View.VISIBLE);
                    btnSend.setText("Resend");
                }

            }
        });

        btnConfirm.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View v) {
                setVisibleGone();
                String b = number1.getText().toString()
                        + number2.getText().toString() +
                        number3.getText().toString() +
                        number4.getText().toString() +
                        number5.getText().toString() +
                        number6.getText().toString();
                if (b.equals("")){
                    tvCodeNote.setVisibility(View.VISIBLE);
                    tvCodeNote.setText("Please enter the code");
                    return;
                }
                Log.d("ConfirmRegister userentercode", b);
                int c = Integer.parseInt(b);
                String a = randomCode + "";

                if (c == randomCode) {
                    isConfirm = true;

                    String newEncodePassword = Base64.getEncoder().encodeToString(etPass1.getText().toString().getBytes());
                    currentUser.setPassword(newEncodePassword);
                    Log.d("ConfirmRegister", "correct code");
                    dataBase.child("Users").child(currentUser.getId()).setValue(currentUser)
                            .addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {
                                    Toast.makeText(ChangePassword.this, "Change password successfully!", Toast.LENGTH_LONG).show();

                                    for (int i = 0; i< listUsers.size(); i++){
                                        if (listUsers.get(i).getEmail().equals(email)){
                                            listUsers.get(i).setPassword(newEncodePassword);
                                            break;
                                        }
                                    }
                                    onBackPressed();
                                }
                            });

                } else {
                    Log.d("ConfirmRegister", "incorrect code");
                    //Toast.makeText(mContext,"Incorrect verify code",Toast.LENGTH_LONG).show();
                    tvCodeNote.setVisibility(View.VISIBLE);
                    tvCodeNote.setText("Incorrect verify code!");
                }
            }
        });
    }

    void initView() {
        turnBack = findViewById(R.id.changePasswordTurnBack);

        etPass0 = findViewById(R.id.txtChangePassPass0);
        etPass1 = findViewById(R.id.txtChangePassPass1);
        etPass2 = findViewById(R.id.txtChangePassPass2);

        tvNotePass0 = findViewById(R.id.txtChangePassPass0Note);
        tvNotePass1 = findViewById(R.id.txtChangePassPass1Note);
        tvNotePass2 = findViewById(R.id.txtChangePassPass2Note);

        tvCodeNote = findViewById(R.id.txtChangePassNoteCode);
        tvEmail = findViewById(R.id.changePassEmailTxt);
        tvEmail.setText(email);

        confirmLayout = findViewById(R.id.changePasswordLayoutConfirm);
        confirmLayout.setVisibility(View.GONE);

        number1 = findViewById(R.id.et_number_1);
        number2 = findViewById(R.id.et_number_2);
        number3 = findViewById(R.id.et_number_3);
        number4 = findViewById(R.id.et_number_4);
        number5 = findViewById(R.id.et_number_5);
        number6 = findViewById(R.id.et_number_6);

        btnSend = findViewById(R.id.btnChangePassSend);
        btnConfirm = findViewById(R.id.btnChangePassConfirm);

        setTextChangedListener();

        setVisibleGone();

    }

    void setVisibleGone() {
        tvNotePass0.setVisibility(View.GONE);
        tvNotePass1.setVisibility(View.GONE);
        tvNotePass2.setVisibility(View.GONE);

        tvCodeNote.setVisibility(View.GONE);
    }

    void getUser() {
        String[] parts = email.split("@");
        dataBase.child("Users").child(parts[0]).get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                if (task.isSuccessful()) {
                    if (task.getResult().getValue() != null) {
                        currentUser = task.getResult().getValue(User.class);
                    } else {
                        Toast.makeText(ChangePassword.this, "Sorry, there are some error. Please try again!", Toast.LENGTH_SHORT).show();
                    }

                }
            }
        });
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    boolean validatePassword() {
        boolean checkEmptyPass = false;
        boolean checkEmptyConfirmPass = false;
        boolean check = true;

        String encodeOldPassword = Base64.getEncoder().encodeToString(etPass0.getText().toString().getBytes());

        if (etPass0.getText().toString().equals("") || etPass0.getText().toString().equals(null) || etPass0.getText().toString().equals(" ")) {
            tvNotePass0.setText("Please enter your password!");
            tvNotePass0.setVisibility(View.VISIBLE);
            check = false;
        } else if (!encodeOldPassword.equals(currentUser.getPassword())) {
            tvNotePass0.setText("Incorrect password!");
            tvNotePass0.setVisibility(View.VISIBLE);
            check = false;
        }

        if (etPass1.getText().toString().equals("") || etPass1.getText().toString().equals(null) || etPass1.getText().toString().equals(" ")) {
            tvNotePass1.setText("Please enter your password!");
            tvNotePass1.setVisibility(View.VISIBLE);
            check = false;
            checkEmptyPass = true;
        } else if (etPass1.getText().toString().equals(currentUser.getPassword())) {
            tvNotePass1.setText("New password must be not similar to old password");
            tvNotePass1.setVisibility(View.VISIBLE);
        } else {
            String note = checkPassword(etPass1.getText().toString());
            if (!note.equals("")) {
                tvNotePass1.setText(note);
                check = false;
            }
        }

        if (etPass2.getText().toString().equals("") || etPass2.getText().toString().equals(null) || etPass2.getText().toString().equals(" ")) {
            tvNotePass2.setText("Please enter your confirm password!");
            tvNotePass2.setVisibility(View.VISIBLE);
            check = false;
            checkEmptyConfirmPass = true;
        }

        if (!checkEmptyPass && !checkEmptyConfirmPass && !etPass1.getText().toString().equals(etPass2.getText().toString())) {
            tvNotePass2.setText("Password and confirm password must be similar");
            tvNotePass2.setVisibility(View.VISIBLE);
            check = false;
        }
        return check;
    }

    String checkPassword(String password) {
        if (password.length() < 6)
            return "Password length must be equal or more than 6 characters";
        return "";
    }

    void sendEmail() {
        final ProgressDialog dialog = new ProgressDialog(ChangePassword.this);
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
                            currentUser.getEmail());
                    dialog.dismiss();


                } catch (Exception e) {
                    Log.e("mylog", "Error: " + e.getMessage());
                }
            }
        });
        sender.start();
    }

    void setTextChangedListener() {
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
}