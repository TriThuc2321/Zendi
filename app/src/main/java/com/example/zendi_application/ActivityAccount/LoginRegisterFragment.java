package com.example.zendi_application.ActivityAccount;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.zendi_application.R;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.annotations.Nullable;

public class LoginRegisterFragment extends Fragment {

    MaterialToolbar mAppBarTop;
    private FirebaseAuth mAuth;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_login_register, container, false);

        mAuth = FirebaseAuth.getInstance();

        view.findViewById(R.id.btnLogin).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Activity activity = getActivity();

               /* Intent intent = new Intent(Account_Activity.this, LoginActivity.class);
                startActivity(intent);*/
                //overridePendingTransition(R.anim.slide_from_right_account,R.anim.slide_to_left_account);

            }
        });

        return view;
    }


}