package com.example.zendi_application.ActivityAccount.ConfirmEmail;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
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

public class ConfirmPasswordDialog extends Dialog {

    Button confirmBtn;
    Button resendBtn;
    EditText emailEdt;
    EditText verifyCodeEdt;

    String email, verifyCode;
    Context mContext;

    List<User> listUsers= DataManager.listUsers;

    public ConfirmPasswordDialog(@NonNull Context context, String Email, int randomCode) {
        super(context);
        mContext = context;
        email = Email;
        verifyCode = new String(randomCode+"");
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
                String a = verifyCode;
                String b = verifyCodeEdt.getText().toString();
                if( a.compareTo(b)==0){
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
                if(emailEdt.getText()!= null && emailEdt.getText().toString()!="" && isValidEmail(emailEdt.getText().toString())){
                    sendEmail();
                }
                else {
                    Toast.makeText(mContext,"Invalid email",Toast.LENGTH_LONG).show();
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
        String expression = "^[\\w\\.]+@([\\w]+\\.)+[A-Z]{2,7}$";
        CharSequence inputString = email;
        Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(inputString);
        if (matcher.matches())
        {
            return true;
        }
        else{
            return false;
        }
    }
}
