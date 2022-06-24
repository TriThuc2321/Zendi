package com.example.zendi_application.ActivityAccount;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import com.example.zendi_application.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.DataSnapshot;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import static com.example.zendi_application.DataManager.listUsers;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Base64;

public class ForgotPassword_2 extends AppCompatActivity {
    String email;
    private DatabaseReference dataBase;

    Button btnChange;
    EditText etPass1, etPass2;
    TextView tvNotePass1, tvNotePass2;
    User currentUser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password2);

        initView();

        email = getIntent().getExtras().getString("email");

        dataBase = FirebaseDatabase.getInstance().getReference();

        getUser();

        btnChange.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View view) {
                if (validatePassword()){
                    String newEncodePassword = Base64.getEncoder().encodeToString(etPass1.getText().toString().getBytes());
                    currentUser.setPassword(newEncodePassword);

                    String[] parts = email.split("@");

                    dataBase.child("Users").child(parts[0]).setValue(currentUser)
                            .addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {
                                    for (int i = 0; i< listUsers.size(); i++){
                                        if (listUsers.get(i).getEmail().equals(currentUser.getEmail())){
                                            listUsers.get(i).setPassword(currentUser.getPassword());
                                            break;
                                        }
                                    }
                                    Toast.makeText(ForgotPassword_2.this,"Change password successfully!",Toast.LENGTH_LONG).show();
                                    Intent newIntent = new Intent(ForgotPassword_2.this, Account_Activity.class);
                                    startActivity(newIntent);
                                }
                            });
                }
            }
        });
    }

    void getUser(){
                    String[] parts = email.split("@");
                    dataBase.child("Users").child(parts[0]).get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<DataSnapshot> task) {
                            if (task.isSuccessful()) {
                                if(task.getResult().getValue() != null)
                                {
                                    currentUser = task.getResult().getValue(User.class);
                                }
                                else {
                                    Toast.makeText(ForgotPassword_2.this, "Sorry, there are some error. Please try again!", Toast.LENGTH_SHORT).show();
                                }

                            }
                        }
                    });
    }

    boolean validatePassword(){
        boolean checkEmptyPass = false;
        boolean checkEmptyConfirmPass = false;
        boolean check = true;

        if (etPass1.getText().toString().equals("") || etPass1.getText().toString().equals(null) || etPass1.getText().toString().equals(" ")) {
            tvNotePass1.setText("Please enter your password!");
            tvNotePass1.setVisibility(View.VISIBLE);
            check = false;
            checkEmptyPass = true;
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

    void initView(){
        btnChange = findViewById(R.id.btnForgotPassChangePass);
        etPass1 = findViewById(R.id.txtForgotPassPass1);
        etPass2 = findViewById(R.id.txtForgotPassPass2);
        tvNotePass1 = findViewById(R.id.txtForgotPassPass1Note);
        tvNotePass2 = findViewById(R.id.txtForgotPassPass2Note);

        setVisibleGone();
    }

    void setVisibleGone(){
        tvNotePass1.setVisibility(View.GONE);
        tvNotePass2.setVisibility(View.GONE);
    }
}