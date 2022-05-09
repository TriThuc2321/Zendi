package com.example.zendi_application.ActivityAccount;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.ViewPager;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.zendi_application.DataManager;
import com.example.zendi_application.R;
import com.example.zendi_application.ViewPagerAdapter;
import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FacebookAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.database.annotations.Nullable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Executor;

import static com.example.zendi_application.DataManager.listUsers;

import org.json.JSONException;
import org.json.JSONObject;

public class Account_Activity extends AppCompatActivity  {

    private  String TAG = "Thuc";
    private FirebaseAuth mAuth;
    private DatabaseReference dataBase;

    //google
    private GoogleSignInClient mGoogleSignInClient;
    private int RC_SIGN_IN = 1;

    //facebook
    CallbackManager mCallbackManager;
    private static final String EMAIL = "email";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);

        mAuth = FirebaseAuth.getInstance();
        dataBase = FirebaseDatabase.getInstance().getReference();
//--------------------------------------google------------------------//
//        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
//                .requestIdToken(getString(R.string.default_web_client_id))
//                .requestEmail()
//                .build();
//
//        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);
        loginGoogle();
        loginFacebook();

    }
    private  void loginGoogle(){
        findViewById(R.id.googleTxt).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signIn();
            }

        });
        findViewById(R.id.googleBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signIn();
            }
        });

        /*GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();*/

        GoogleSignInOptions gso = new GoogleSignInOptions
                .Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken("761078127076-no8v16g7j2afutvg6cdmmleffhmslqgd.apps.googleusercontent.com")
                .requestEmail()
                .build();

        mGoogleSignInClient = GoogleSignIn.getClient(this,gso);
    }
    private void signIn(){
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    private void firebaseAuthWithGoogle(String idToken) {
        AuthCredential credential = GoogleAuthProvider.getCredential(idToken, null);
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "signInWithCredential:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            Toast.makeText(getApplicationContext(),"login sucess", Toast.LENGTH_LONG).show();
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "signInWithCredential:failure", task.getException());

                        }
                    }
                });
    }

    private void loginFacebook(){
        mCallbackManager = CallbackManager.Factory.create();
        LoginButton loginButton = findViewById(R.id.button_sign_in);
        //loginButton.setReadPermissions(Arrays.asList(EMAIL));

        loginButton.registerCallback(mCallbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                Log.d(TAG, "facebook:onSuccess:" + loginResult);
                handleFacebookAccessToken(loginResult.getAccessToken());
            }

            @Override
            public void onCancel() {
                Log.d(TAG, "facebook:onCancel");
            }

            @Override
            public void onError(FacebookException error) {
                Log.d(TAG, "facebook:onError", error);
            }
        });

        findViewById(R.id.facebookTxt).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginButton.callOnClick();
            }
        });

        findViewById(R.id.facebookBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginButton.callOnClick();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == RC_SIGN_IN){
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                // Google Sign In was successful, authenticate with Firebase
                GoogleSignInAccount account = task.getResult(ApiException.class);
                Log.d(TAG, "firebaseAuthWithGoogle:" + account.getId());
                firebaseAuthWithGoogle(account.getIdToken());
            } catch (ApiException e) {
                // Google Sign In failed, update UI appropriately
                Toast.makeText(getApplicationContext(),"Google sign in failed",Toast.LENGTH_LONG).show();
                Log.w(TAG, "Google sign in failed", e);
            }
        }
        else{
            mCallbackManager.onActivityResult(requestCode, resultCode, data);

        }
    }



    private void openProfile(JSONObject object) throws JSONException {
        if(!existUser(object)){
            setData("","DD/ MM/ YY", "",2, object.getString("id"),object.getString("name"),"","","","", 0);

        }
        //DataManager.getInstance().host = new User("","DD/ MM/ YY", "",2, object.getString("id"),object.getString("name"),"","","","", 0);
        startActivity(new Intent(Account_Activity.this, SettingActivity.class));
        finish();
    }
    boolean existUser(JSONObject object) throws JSONException {
        //FirebaseUser currentUser = mAuth.getCurrentUser();
        for(int i=0; i< listUsers.size(); i++){
            //String b = currentUser.getUid();

            String b = object.getString("id");
            String c = listUsers.get(i).getId();
            int d = b.compareTo(c);
            if(d == 0) return true;
        }
        return false;
    }

    public void setData(String address, String DOB, String email, int gender, String id, String name, String phoneNumber, String profilePic, String size, String total, int isShopOwner){
        User mUser =  new User(address, DOB, email, gender, id, name,phoneNumber,profilePic,size,total, isShopOwner);
        dataBase.child("Users").child(id).setValue(mUser);
    }


    private void handleFacebookAccessToken(AccessToken token) {

//        AuthCredential credential = FacebookAuthProvider.getCredential(token.getToken());
//        mAuth.signInWithCredential(credential)
//                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
//                    @Override
//                    public void onComplete(@NonNull Task<AuthResult> task) {
//                        if (task.isSuccessful()) {
//                            // Sign in success, update UI with the signed-in user's information
//                            Log.d(TAG, "signInWithCredential:success");
//                            //setData("","DD/ MM/ YY", mAuth.getCurrentUser().getEmail(),2, mAuth.getCurrentUser().getUid(),mAuth.getCurrentUser().getDisplayName(),"","","","", 0);
//                               openProfile();
//                        } else {
//                            // If sign in fails, display a message to the user.
//                            Log.w(TAG, "signInWithCredential:failure", task.getException());
//                            Toast.makeText(Account_Activity.this, "Authentication failed.",Toast.LENGTH_SHORT).show();
//                        }
//                    }
//                });

        GraphRequest request = GraphRequest.newMeRequest (
                token ,
                new GraphRequest .GraphJSONObjectCallback ( ) {
                    @Override
                    public void onCompleted (JSONObject object , GraphResponse response ) {
                        try {
                            openProfile(object);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    } });
                         Bundle parameters = new Bundle ();
                    parameters . putString ( "fields" , "id,name,link" );

                    request . setParameters ( parameters );
                    request . executeAsync ();
    }
    @Override
    protected void onStart() {
        super.onStart();
//        AccessToken accessToken = AccessToken.getCurrentAccessToken();
//        boolean isLoggedIn = accessToken != null && !accessToken.isExpired();
//        FirebaseUser currentUser = mAuth.getCurrentUser();
//        if(currentUser!=null) openProfile(object);
    }
}


