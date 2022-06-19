package com.example.zendi_application.ActivityAccount;

import static com.example.zendi_application.ActivityAccount.SettingActivity.isConfirm;
import static com.example.zendi_application.ActivityAccount.SettingActivity.lockEmailIcon;
import static com.example.zendi_application.ActivityAccount.SettingActivity.openConfirmDialogBtn;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.zendi_application.ActivityAccount.ConfirmEmail.GmailSender;
import com.example.zendi_application.DataManager;
import com.example.zendi_application.R;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;
import java.util.Random;

public class ConfirmRegister extends Dialog {
    private DatabaseReference dataBase;
    Button confirmBtn;
    Button resendBtn;
    EditText number1, number2, number3, number4, number5, number6;
    TextView note;

    String email;
    int verifyCode;
    Context mContext;
    User currentUser;

    List<User> listUsers = DataManager.listUsers;

    public ConfirmRegister(@NonNull Context context, String Email, int randomCode, User u) {
        super(context);
        mContext = context;
        email = Email;
        verifyCode = randomCode;
        currentUser = u;
        dataBase = FirebaseDatabase.getInstance().getReference();
        Log.d("ConfirmRegister", u.getEmail());
        Log.d("ConfirmRegister sendcode", Integer.toString(verifyCode));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_confirm_register);

        confirmBtn = findViewById(R.id.btnConfirmRegister);
        resendBtn = findViewById(R.id.btnResendRegister);
        number1 = findViewById(R.id.num1);
        number2 = findViewById(R.id.num2);
        number3 = findViewById(R.id.num3);
        number4 = findViewById(R.id.num4);
        number5 = findViewById(R.id.num5);
        number6 = findViewById(R.id.num6);
        note = findViewById(R.id.txtConfirmRegisterNote);
        note.setVisibility(View.GONE);

        setTextChangedListener();

        confirmBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String b = number1.getText().toString()
                        + number2.getText().toString() +
                        number3.getText().toString() +
                        number4.getText().toString() +
                        number5.getText().toString() +
                        number6.getText().toString();
                Log.d("ConfirmRegister userentercode", b);
                int c = Integer.parseInt(b);
                String a = verifyCode + "";

                if (c == verifyCode) {
                    isConfirm = true;
                    Log.d("ConfirmRegister", "correct code");
                    //   Toast.makeText(mContext,"Successful Confirmation",Toast.LENGTH_LONG).show();

                    String string = currentUser.getEmail();
                    String[] parts = string.split("@");
                    Log.d("aaaaa", parts[0]);
                    dataBase.child("Users").child(parts[0]).setValue(currentUser)
                            .addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {
                                    Intent newIntent = new Intent(mContext, Account_Activity.class);
                                    mContext.startActivity(newIntent);
                                }
                            });
                    dismiss();
                } else {
                    Log.d("ConfirmRegister", "incorrect code");
                    //Toast.makeText(mContext,"Incorrect verify code",Toast.LENGTH_LONG).show();
                    note.setVisibility(View.VISIBLE);
                    note.setText("Incorrect verify code!");
                }
            }
        });

        resendBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendEmail();
            }
        });
    }

    void sendEmail() {
        final ProgressDialog dialog = new ProgressDialog(mContext);
        dialog.setTitle("Sending Email");
        dialog.setMessage("Please wait");
        dialog.show();
        Thread sender = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    int randomCode = new Random().nextInt(900000) + 100000;
                    verifyCode = randomCode;
                    GmailSender sender = new GmailSender("zendiapplication@gmail.com", "yovmsjtkpwwfgbbv");
                    sender.sendMail("Verify code",
                            "Thank for using Zendi Application, this is you verify code: " + randomCode,
                            "zendiapplication@gmail.com",
                            currentUser.getEmail());
                    dialog.dismiss();
                } catch (Exception e) {
                    Log.e("mylog", "Error: " + e.getMessage());
                }
            }
        });
        sender.start();
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
}