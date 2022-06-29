package com.example.zendi_application.ActivityAccount.Admin.AccountManager;

import static com.example.zendi_application.ActivityAccount.SettingActivity.isConfirm;
import static com.example.zendi_application.ActivityAccount.SettingActivity.lockEmailIcon;
import static com.example.zendi_application.ActivityAccount.SettingActivity.openConfirmDialogBtn;
import static com.example.zendi_application.DataManager.listUsers;

import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;

import com.example.zendi_application.ActivityAccount.ConfirmEmail.GmailSender;
import com.example.zendi_application.ActivityAccount.User;
import com.example.zendi_application.DataManager;
import com.example.zendi_application.R;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;
import java.util.Random;


public class ConfirmDeleteDialog extends Dialog {

    Button confirmBtn;
    Button cancelBtn;

    Context mContext;
    String id;

    public ConfirmDeleteDialog(@NonNull Context context, String _id) {
        super(context);
        mContext = context;
        id = _id;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.fragment_confirm_alert);

        confirmBtn = findViewById(R.id.confirm_button);
        cancelBtn = findViewById(R.id.cancel_btn);


        confirmBtn.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View v) {

                FirebaseDatabase.getInstance().getReference().child("Users").child(id).removeValue();
                listUsers.removeIf(e -> e.getId().equals(id));
                for(int i =0; i< listUsers.size() -1 ; i++){
                    for(int j=i+1; j< listUsers.size(); j++){
                        if(listUsers.get(i).getEmail().equals(listUsers.get(j).getEmail())){
                            listUsers.remove(j);
                            j--;
                        }
                    }
                }
                DataManager.mAccountAdapter.SetData(listUsers);
                DataManager.mAccountAdapter.notifyDataSetChanged();
                dismiss();
            }
        });

        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    dismiss();
            }
        });
    }

}
