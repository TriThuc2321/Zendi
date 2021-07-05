package com.example.zendi_application.ActivityAccount.Admin.Statistic;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.zendi_application.R;

import static com.example.zendi_application.DataManager.orderedList;

public class DetailOrdered extends AppCompatActivity {

    TextView emailTxt;
    TextView addressTxt;
    TextView nameTxt;
    TextView phoneNumberTxt;
    TextView totalTxt;
    TextView billStatusTxt;
    TextView billDateTxt;

    RecyclerView mRecyclerView;

    Ordered ordered = new Ordered();
    DetailOrderedAdapter mDetailOrderedAdapter;

    View turnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_ordered);

        Init();
        getDetail();
        setDetail();
    }

    private void setDetail() {
        nameTxt.setText(ordered.getName());
        phoneNumberTxt.setText(ordered.getContact());
        addressTxt.setText(ordered.getAddress());
        emailTxt.setText(ordered.getEmail());
        totalTxt.setText(ordered.getTotal());
        billDateTxt.setText(ordered.getBillDate());

        String a = ordered.getBillStatus();
        if(a.compareTo("0") == 0) billStatusTxt.setText("Not yet delivered");
        else if(a.compareTo("1") == 0) billStatusTxt.setText("Delivered");


        mDetailOrderedAdapter = new DetailOrderedAdapter(this);
        mDetailOrderedAdapter.SetData(ordered.getShoeList());
        mRecyclerView.setAdapter(mDetailOrderedAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this, RecyclerView.HORIZONTAL,false));
    }

    private void getDetail() {
        String id = getIntent().getStringExtra("id");
        for(int i =0; i< orderedList.size(); i++){
            if(id.compareTo(orderedList.get(i).getBillId()) == 0){
                ordered = orderedList.get(i);
                break;
            }
        }
    }

    private void Init() {
        emailTxt = findViewById(R.id.emailDetailOrderedTxt);
        addressTxt = findViewById(R.id.addressDetailOrderedTxt);
        nameTxt = findViewById(R.id.nameDetailOrder);
        phoneNumberTxt = findViewById(R.id.phoneNumberDetailOrderedTxt);
        totalTxt = findViewById(R.id.totalDetailOrderedTxt);
        billDateTxt = findViewById(R.id.billDateDetailOrderedTxt);
        billStatusTxt = findViewById(R.id.billStatusDetailOrderedTxt);
        turnBack = findViewById(R.id.turnBack_detail);

        mRecyclerView = findViewById(R.id.productDetailOrdered);

        turnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}