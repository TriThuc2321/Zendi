package com.example.zendi_application.ActivityAccount;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.example.zendi_application.ActivityAccount.ConfirmEmail.ConfirmPasswordDialog;
import com.example.zendi_application.ActivityAccount.ConfirmEmail.GmailSender;
import com.example.zendi_application.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import static com.example.zendi_application.DataManager.listUsers;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.opengl.Visibility;
import android.os.Build;
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
import android.widget.Toast;

import java.util.Calendar;
import java.util.Random;
import java.util.Base64;

public class RegisterActivity extends AppCompatActivity {
    //view
    EditText etName;
    EditText etPhone;
    EditText etEmail;
    EditText etPassword;
    EditText etRepeatPassword;

    TextView tvNameNote;
    TextView tvPhoneNote;
    TextView tvEmailNote;
    TextView tvPasswordNote;
    TextView tvRepeatPasswordNote;
    TextView tvSignIn;

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
                if (validate()) {
                    setData();
                }
            }
        });

    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    void setData() {
        int mGender = 0;

        String id = etEmail.getText().toString().split("@")[0];
        String profilePic = "";
        String size = "";
        String total = "";
        int shopOwner = 0;
        String address = "";
        String birthday = "";

        String encodePassword = Base64.getEncoder().encodeToString(etPassword.getText().toString().getBytes());

        user = new User(
                address,
                birthday,
                etEmail.getText().toString(),
                encodePassword,
                mGender,
                id,
                etName.getText().toString(),
                etPhone.getText().toString(),
                profilePic,
                size,
                total,
                shopOwner);
        String string = etEmail.getText().toString();
        String[] parts = string.split("@");
        sendEmail(user);
//        dataBase.child("Users").child(parts[0]).setValue(user)
//                .addOnSuccessListener(new OnSuccessListener<Void>() {
//                    @Override
//                    public void onSuccess(Void aVoid) {
//                        Toast.makeText(RegisterActivity.this, "Sign up successfully!", Toast.LENGTH_LONG).show();
//                        Intent newIntent = new Intent(RegisterActivity.this, Account_Activity.class);
//                        startActivity(newIntent);
//                    }
//                });

    }

    boolean validate() {
        refreshNote();
        boolean check = true;

        if (etName.getText().toString().equals("") || etName.getText().toString().equals(null) || etName.getText().toString().equals(" ")) {
            tvNameNote.setText("Please enter your name!");
            tvNameNote.setVisibility(View.VISIBLE);
            check = false;
        }
        else if (!checkName(etName.getText().toString())){
            tvNameNote.setText("Name is not valid!");
            tvNameNote.setVisibility(View.VISIBLE);
            check = false;
        }

        if (etPhone.getText().toString().equals("") || etPhone.getText().toString().equals(null) || etPhone.getText().toString().equals(" ")) {
            tvPhoneNote.setText("Please enter your phone number");
            tvPhoneNote.setVisibility(View.VISIBLE);
            check = false;
        }
        else if (!checkPhone(etPhone.getText().toString())){
            tvPhoneNote.setText("Phone number is not valid!");
            tvPhoneNote.setVisibility(View.VISIBLE);
            check = false;
        }

        if (etEmail.getText().toString().equals("") || etEmail.getText().toString().equals(null) || etEmail.getText().toString().equals(" ")) {
            tvEmailNote.setText("Please enter your email");
            tvEmailNote.setVisibility(View.VISIBLE);
            check = false;
        } else if (!isValidEmail(etEmail.getText().toString())) {
            tvEmailNote.setText("Your email is invalid!");
            tvEmailNote.setVisibility(View.VISIBLE);
            check = false;
        } else {
            String string = etEmail.getText().toString();
            String[] parts = string.split("@");
            for (int i = 0; i < listUsers.size(); i++) {
                if (listUsers.get(i).getEmail().equals(etEmail.getText().toString())) {
                    check = false;
                    tvEmailNote.setVisibility(View.VISIBLE);
                    tvEmailNote.setText("Email is existed!");
                    break;
                }
            }
        }

        boolean checkEmptyPass = false;
        boolean checkEmptyConfirmPass = false;

        if (etPassword.getText().toString().equals("") || etPassword.getText().toString().equals(null) || etPassword.getText().toString().equals(" ")) {
            tvPasswordNote.setText("Please enter your password!");
            tvPasswordNote.setVisibility(View.VISIBLE);
            check = false;
            checkEmptyPass = true;
        } else {
            String note = checkPassword(etPassword.getText().toString());
            if (!note.equals("")) {
                tvPasswordNote.setText(note);
                check = false;
            }
        }

        if (etRepeatPassword.getText().toString().equals("") || etRepeatPassword.getText().toString().equals(null) || etRepeatPassword.getText().toString().equals(" ")) {
            tvRepeatPasswordNote.setText("Please enter your confirm password!");
            tvRepeatPasswordNote.setVisibility(View.VISIBLE);
            check = false;
            checkEmptyConfirmPass = true;
        }

        if (!checkEmptyPass && !checkEmptyConfirmPass && !etPassword.getText().toString().equals(etRepeatPassword.getText().toString())) {
            tvRepeatPasswordNote.setText("Password and confirm password must be similar");
            tvRepeatPasswordNote.setVisibility(View.VISIBLE);
            check = false;
        }

        return check;
    }

    void init() {
        etName = findViewById(R.id.txtRegisterName);
        etPhone = findViewById(R.id.txtRegisterPhone);
        etEmail = findViewById(R.id.txtRegisterEmail);
        etPassword = findViewById(R.id.txtRegisterPass1);
        etRepeatPassword = findViewById(R.id.txtRegisterPass2);

        tvNameNote = findViewById(R.id.txtRegisterNameNote);
        tvPhoneNote = findViewById(R.id.txtRegisterPhoneNote);
        tvEmailNote = findViewById(R.id.txtRegisterEmailNote);
        tvPasswordNote = findViewById(R.id.txtRegisterPass1Note);
        tvRepeatPasswordNote = findViewById(R.id.txtRegisterPass2Note);
        tvSignIn = findViewById(R.id.txtRegisterSignIn);

        tvNameNote.setVisibility(View.GONE);
        tvPhoneNote.setVisibility(View.GONE);
        tvEmailNote.setVisibility(View.GONE);
        tvPasswordNote.setVisibility(View.GONE);
        tvRepeatPasswordNote.setVisibility(View.GONE);

        btnRegister = findViewById(R.id.btnRegisterSubmit);

        mAuth = FirebaseAuth.getInstance();
        dataBase = FirebaseDatabase.getInstance().getReference();
    }

    void refreshNote() {
        int gone = View.GONE;
        tvPhoneNote.setVisibility(gone);
        tvPasswordNote.setVisibility(gone);
        tvNameNote.setVisibility(gone);
        tvEmailNote.setVisibility(gone);
        tvRepeatPasswordNote.setVisibility(gone);
    }

    String checkPassword(String password) {
        if (password.length() < 6)
            return "Password length must be equal or more than 6 characters";
        return "";
    }

    public boolean isValidEmail(String email) {
        return !TextUtils.isEmpty(email) && Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    boolean checkName(String name) {
        return name.matches("^[ A-Za-z]+$");
    }

    boolean checkPhone(String phone) {
        if (phone.length() != 10)
            return false;
        if (phone.charAt(0) != '0')
            return  false;
        return true;
    }

    int randomCode;

    void sendEmail(User user) {
        final ProgressDialog dialog = new ProgressDialog(RegisterActivity.this);
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
        ConfirmRegister confirmRegister = new ConfirmRegister(RegisterActivity.this, etEmail.getText().toString(), randomCode, user);
        confirmRegister.show();
    }
}