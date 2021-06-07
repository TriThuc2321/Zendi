package com.example.zendi_application.ActivityAccount;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.zendi_application.DataManager;
import com.example.zendi_application.R;

import java.util.ArrayList;
import java.util.List;

public class StaffManager extends AppCompatActivity {

    private List<User> mlistUsers;

    private RecyclerView mRecyclerStaff;
    private StaffAdapter mStaffAdapter ;

    private RecyclerView mRecyclerCustomer;
    private CustomerAdapter mCustomerAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_staff_manager);

        mRecyclerStaff = findViewById(R.id.list_staff_recyclerView);
        mRecyclerCustomer = findViewById(R.id.list_customer_recyclerView);

        mlistUsers = DataManager.listUsers;

        mStaffAdapter = new StaffAdapter(this);
        mStaffAdapter.SetData(mlistUsers);
        mRecyclerStaff.setAdapter(mStaffAdapter);
        mRecyclerStaff.setLayoutManager(new LinearLayoutManager(this));

        mCustomerAdapter = new CustomerAdapter(this);
        mCustomerAdapter.SetData(mlistUsers);
        mRecyclerCustomer.setAdapter(mCustomerAdapter);
        mRecyclerCustomer.setLayoutManager(new LinearLayoutManager((this)));

    }
}