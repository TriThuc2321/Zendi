package com.example.zendi_application.ActivityAccount.Admin.AccountManager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.example.zendi_application.ActivityAccount.User;
import com.example.zendi_application.R;

import java.util.ArrayList;
import java.util.List;

import static com.example.zendi_application.DataManager.listUsers;

public class AccountManager extends AppCompatActivity {

    public static List<User> listAccount = new ArrayList<>();

    private RecyclerView mRecyclerAccount;
    private AccountAdapter mAccountAdapter ;

    private View turnBackBtn;
    Spinner orderBySpinner;
    List<String> listSp = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_staff_manager);

        Init();
        createListSpinner();
        setAdapterSpinner();

        turnBackBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setResult(RESULT_CANCELED);
                listAccount.clear();
                mAccountAdapter.SetData(listAccount);
                finish();
            }
        });

    }
    void Init(){
        turnBackBtn = findViewById(R.id.turnBack);
        mRecyclerAccount = findViewById(R.id.list_account_recyclerView);
        orderBySpinner = findViewById(R.id.filter_account_spinner);

        mAccountAdapter = new AccountAdapter(this);
        mRecyclerAccount.setAdapter(mAccountAdapter);
        mRecyclerAccount.setLayoutManager(new LinearLayoutManager(this));

    }

    void getList(String type){
        listAccount.clear();
        switch (type){
            case "Show all":
                listAccount.addAll(listUsers);
                break;
            case "Admin":
                for(int i =0; i< listUsers.toArray().length; i++){
                    if(listUsers.get(i).getShopOwner() == 2){
                        listAccount.add(listUsers.get(i));
                    }
                }

                break;
            case "Manager":
                for(int i =0; i< listUsers.toArray().length; i++){
                    if(listUsers.get(i).getShopOwner() == 1){
                        listAccount.add(listUsers.get(i));
                    }
                }
                break;
            case "Customer":
                for(int i =0; i< listUsers.toArray().length; i++){
                    if(listUsers.get(i).getShopOwner() == 0){
                        listAccount.add(listUsers.get(i));
                    }
                }
                break;
            default: break;
        }
        mAccountAdapter.SetData(listAccount);
    }

    private void createListSpinner() {
        listSp.add("Show all");
        listSp.add("Admin");
        listSp.add("Manager");
        listSp.add("Customer");
    }

    private void setAdapterSpinner() {
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, listSp);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        orderBySpinner.setAdapter(arrayAdapter);
        orderBySpinner.setSelection(0);
        orderBySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                getList(orderBySpinner.getSelectedItem().toString());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }
}