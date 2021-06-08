package com.example.zendi_application.ActivityAccount;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;

import com.example.zendi_application.DataManager;
import com.example.zendi_application.R;

import java.util.ArrayList;
import java.util.List;

public class StaffManager extends AppCompatActivity {

    private List<User> mlistUsers;
    private  List<User> listStaff;
    private  List<User> listCustomer;

    private RecyclerView mRecyclerStaff;
    private StaffAdapter mStaffAdapter ;

    private RecyclerView mRecyclerCustomer;
    private CustomerAdapter mCustomerAdapter;

      View turnbackBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_staff_manager);

        Init();

        turnbackBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setResult(RESULT_CANCELED);
                finish();
            }
        });
    }
    void Init(){
        turnbackBtn = findViewById(R.id.turn_back_staff_manaher);

        mRecyclerStaff = findViewById(R.id.list_staff_recyclerView);
        mRecyclerCustomer = findViewById(R.id.list_customer_recyclerView);

        mlistUsers = DataManager.listUsers;
        getList();

        mStaffAdapter = new StaffAdapter(this);
        mStaffAdapter.SetData(listStaff);
        mRecyclerStaff.setAdapter(mStaffAdapter);
        mRecyclerStaff.setLayoutManager(new LinearLayoutManager(this));

        mCustomerAdapter = new CustomerAdapter(this);
        mCustomerAdapter.SetData(listCustomer);
        mRecyclerCustomer.setAdapter(mCustomerAdapter);
        mRecyclerCustomer.setLayoutManager(new LinearLayoutManager((this)));
    }
    void getList(){
        for(int i = 0; i<mlistUsers.size(); i++){
            if(mlistUsers.get(i).getShopOwner()==0) {
                listCustomer.add(mlistUsers.get(i));
            }
            else if(mlistUsers.get(i).getShopOwner()==1){
                listStaff.add(mlistUsers.get(i));
            }
        }
    }
}