package com.example.zendi_application.ActivityAccount;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.zendi_application.R;
import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FacebookAuthProvider;
import com.google.firebase.auth.FirebaseAuth;

import java.util.concurrent.Executor;

public class LoginFragment extends Fragment {
    Account_Activity parent;
    private LoginButton loginButton;

    private static final String TAG = "Thuc";
    private CallbackManager callbackManager;
    private FirebaseAuth mAuth;

    public LoginFragment(Account_Activity parent) {
        this.parent = parent;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_login, container, false);

        callbackManager = CallbackManager.Factory.create();
        loginButton = view.findViewById(R.id.login_button);
        mAuth = FirebaseAuth.getInstance();

        view.findViewById(R.id.facebookBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginButton.callOnClick();
            }
        });
        view.findViewById(R.id.facebookTxt).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginButton.callOnClick();
            }
        });

        loginButton.setReadPermissions("public_profile", "email");

        //loginButton.setReadPermissions(Arrays.asList(EMAIL));


        // Callback registration
        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {


            @Override
            public void onSuccess(LoginResult loginResult) {
                // App code
//                txtEmail.setText("User: "+ loginResult.getAccessToken().getUserId());
//                txtName.setText("");
//                loginResult.getAccessToken().getSource().name();
//                Picasso.get().load("https://graph.facebook.com/"+loginResult.getAccessToken().getUserId()+"/picture?type=normal").into(profilePic);
                Log.d(TAG, "signInWithCredentialq: success");
                handleFacebookAccessToken(loginResult.getAccessToken());

//
            }

            @Override
            public void onCancel() {
                // App code
            }

            @Override
            public void onError(FacebookException exception) {
                // App code
                Log.d(TAG,"onErrorL: "+ exception.getMessage());
            }
        });

        return view;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        callbackManager.onActivityResult(requestCode, resultCode, data);
        super.onActivityResult(requestCode, resultCode, data);
    }
    private void handleFacebookAccessToken(AccessToken token) {

        AuthCredential credential = FacebookAuthProvider.getCredential(token.getToken());
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener((Executor) this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            //FirebaseUser user = mAuth.getCurrentUser();
                            Log.d(TAG, "signInWithCredential: success");
                            openProfile();

                        } else {
                            // If sign in fails, display a message to the user.


                        }
                    }
                });


    }
    private void openProfile(){
        //this.parent.addFragment(new SettingFragment(), true);
        startActivity(new Intent(parent, SettingActivity.class));
        //finish();
    }

    @Override
    public void onStart() {
        super.onStart();
        if(mAuth.getCurrentUser() != null){
            openProfile();
        }
    }
}