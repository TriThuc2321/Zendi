package com.example.zendi_application.ActivityAccount;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.zendi_application.R;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.annotations.Nullable;

public class LoginRegisterFragment extends Fragment {

    private static final String TAG = "Thuc";
    Account_Activity parent;
    private FirebaseAuth mAuth;
    Button btnLogin;

    public LoginRegisterFragment(Account_Activity parent) {
        this.parent = parent;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_login_register, container, false);

        btnLogin = view.findViewById(R.id.btnLogin);
        btnLogin.setEnabled(true);

        mAuth = FirebaseAuth.getInstance();

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG,"LoginFrament");
                parent.addFragment(new LoginFragment(parent), true);
                /*activityCallback.onButtonClick();
                btnLogin.setEnabled(false);*/
               /* Intent intent = new Intent(Account_Activity.this, LoginActivity.class);
                startActivity(intent);*/
                //overridePendingTransition(R.anim.slide_from_right_account,R.anim.slide_to_left_account);

            }
        });
        return view;
    }


}