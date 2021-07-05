package com.example.zendi_application.ActivityAccount.Admin.Statistic;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.zendi_application.R;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.FirebaseFirestore;

import static com.example.zendi_application.DataManager.orderedList;

public class DetailOrdered extends AppCompatActivity {

    TextView emailTxt;
    TextView addressTxt;
    TextView nameTxt;
    TextView phoneNumberTxt;
    TextView totalTxt;
    TextView billStatusTxt;
    TextView billDateTxt;
    Button status;
    Button setBill;
    boolean flag;
    int position;

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

    private void updateBillFireStore(){
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        String temp;
        if(flag == true) temp = "1";
        else temp = "0";

        db.collection("Ordered/" ).document(ordered.getBillId()).update("BillStatus", temp).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {

            }
        });
        orderedList.get(position).setBillStatus(temp);

        Toast.makeText(getApplicationContext(), "Update successful", Toast.LENGTH_SHORT).show();
        setBill.setVisibility(View.GONE);
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
                if(ordered.getBillStatus().compareTo("0") == 0) flag = false;
                else flag = true;
                setFlag();

                position = i;
                break;
            }
        }
    }

    @SuppressLint("ResourceType")
    void setFlag(){
        if(flag == false) {
            status.setBackground(getDrawable(R.drawable.ic_outline_cancel_24));
            billStatusTxt.setText("Not yet delivered");
        }
        else{
            status.setBackground(getDrawable(R.drawable.ic_baseline_check_24));
            billStatusTxt.setText("Delivered");
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
        status = findViewById(R.id.statusBtn);
        turnBack = findViewById(R.id.turnBack_detail);
        setBill = findViewById(R.id.setBill);

        mRecyclerView = findViewById(R.id.productDetailOrdered);

        turnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        status.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                flag = !flag;
                setFlag();
                setBill.setVisibility(View.VISIBLE);
            }
        });

        setBill.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateBillFireStore();
            }
        });
    }

}