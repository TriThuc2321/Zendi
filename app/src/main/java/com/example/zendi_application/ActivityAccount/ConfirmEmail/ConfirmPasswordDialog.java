package com.example.zendi_application.ActivityAccount.ConfirmEmail;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.zendi_application.ActivityAccount.SettingActivity;
import com.example.zendi_application.ActivityAccount.User;
import com.example.zendi_application.DataManager;
import com.example.zendi_application.R;
import com.google.protobuf.StringValue;

import java.util.List;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.example.zendi_application.ActivityAccount.SettingActivity.isConfirm;
import static com.example.zendi_application.ActivityAccount.SettingActivity.lockEmailIcon;
import static com.example.zendi_application.ActivityAccount.SettingActivity.openConfirmDialogBtn;
import static com.facebook.FacebookSdk.getApplicationContext;

public class ConfirmPasswordDialog extends Dialog {

    Button confirmBtn;
    Button resendBtn;
    EditText emailEdt;
    EditText verifyCodeEdt;

    String email;
    int verifyCode;
    Context mContext;

    List<User> listUsers= DataManager.listUsers;

    public ConfirmPasswordDialog(@NonNull Context context, String Email, int randomCode) {
        super(context);
        mContext = context;
        email = Email;
        verifyCode = randomCode;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.confirm_password_layout);

        emailEdt = findViewById(R.id.emailEdtDialog);
        verifyCodeEdt = findViewById(R.id.verifyEdtDialog);
        confirmBtn = findViewById(R.id.confirmBtnDialog);
        resendBtn = findViewById(R.id.resendBtnDialog);

        emailEdt.setText(email);

        confirmBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String b = verifyCodeEdt.getText().toString();
                String a = verifyCode + "";
                if( b.compareTo(a) == 0){
                    isConfirm = true;

                    openConfirmDialogBtn.setVisibility(View.GONE);
                    lockEmailIcon.setVisibility(View.VISIBLE);

                    Toast.makeText(mContext,"Successful Confirmation",Toast.LENGTH_LONG).show();
                    dismiss();
                }
                else {
                    Toast.makeText(mContext,"Incorrect verify code",Toast.LENGTH_LONG).show();
                }
            }
        });

        resendBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(emailEdt.getText() == null || emailEdt.getText().toString() == ""){
                    Toast.makeText(getApplicationContext(), "Enter email to continue", Toast.LENGTH_LONG).show();
                }
                else if(existEmail(emailEdt.getText().toString())){
                    Toast.makeText(getApplicationContext(), "Email already used", Toast.LENGTH_LONG).show();
                }
                else{
                    sendEmail();
                }
            }
        });
    }
    void sendEmail(){
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
                    GmailSender sender = new GmailSender("zendiapplication@gmail.com", "ThucThienThangHuynh123");
                    sender.sendMail("Verify code",
                            "Thank for using Zendi Application, this is you verify code: " + randomCode,
                            "zendiapplication@gmail.com",
                            emailEdt.getText().toString());
                    dialog.dismiss();
                } catch (Exception e) {
                    Log.e("mylog", "Error: " + e.getMessage());
                }
            }
        });
        sender.start();
    }
    public boolean isValidEmail(String email)
    {
        return !TextUtils.isEmpty(email) && Patterns.EMAIL_ADDRESS.matcher(email).matches();
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
}
