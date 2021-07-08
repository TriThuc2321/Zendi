package com.example.zendi_application.ActivityAccount.Admin.StaffManager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

import com.example.zendi_application.ActivityAccount.User;
import com.example.zendi_application.DataManager;
import com.example.zendi_application.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

import static com.example.zendi_application.DataManager.listUsers;

public class StaffManager extends AppCompatActivity {

    private DatabaseReference dataBase;
    public static List<User> listStaff = new ArrayList<>();
    public static List<User> listCustomer = new ArrayList<>();;

    private RecyclerView mRecyclerStaff;
    private StaffAdapter mStaffAdapter ;

    private RecyclerView mRecyclerCustomer;
    private CustomerAdapter mCustomerAdapter;

    public static Button saveBtn;
    private View turnBackBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_staff_manager);

        Init();


        turnBackBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setResult(RESULT_CANCELED);
                listCustomer.clear();
                listStaff.clear();
                mStaffAdapter.SetData();
                mCustomerAdapter.SetData();
                finish();
            }
        });

        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveBtn.setVisibility(View.GONE);
                updateData();
            }
        });
    }
    void Init(){

        dataBase = FirebaseDatabase.getInstance().getReference();
        turnBackBtn = findViewById(R.id.turn_back_staff_manager);

        mRecyclerStaff = findViewById(R.id.list_staff_recyclerView);
        mRecyclerCustomer = findViewById(R.id.list_customer_recyclerView);

        getList();

        mStaffAdapter = new StaffAdapter(this);
        mStaffAdapter.SetData();
        mRecyclerStaff.setAdapter(mStaffAdapter);
        mRecyclerStaff.setLayoutManager(new LinearLayoutManager(this));

        mCustomerAdapter = new CustomerAdapter(this);
        mCustomerAdapter.SetData();
        mRecyclerCustomer.setAdapter(mCustomerAdapter);
        mRecyclerCustomer.setLayoutManager(new LinearLayoutManager((this)));

        saveBtn = findViewById(R.id.saveStaffManagerBtn);
    }
    void getList(){
        listCustomer.clear();
        listStaff.clear();

        for(int i = 0; i < listUsers.size(); i++){
            if(listUsers.get(i).getShopOwner()==0) {
                listCustomer.add(listUsers.get(i));
            }
            else if(listUsers.get(i).getShopOwner()==1){
                listStaff.add(listUsers.get(i));
            }
        }

    }
    void updateData(){

        for(int i=0; i<listStaff.size(); i++){
            listStaff.get(i).setShopOwner(1);
            dataBase.child("Users").child(listStaff.get(i).getId()).setValue(listStaff.get(i));
        }
        for(int i=0; i<listCustomer.size(); i++){
            listCustomer.get(i).setShopOwner(0);
            dataBase.child("Users").child(listCustomer.get(i).getId()).setValue(listCustomer.get(i));
        }
    }
    protected void onStop() {
        setResult(0);
        super.onStop();
    }
    @Override
    protected void onDestroy() {
        setResult(0);
        super.onDestroy();
    }

}