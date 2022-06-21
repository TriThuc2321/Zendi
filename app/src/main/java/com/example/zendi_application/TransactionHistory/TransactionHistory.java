package com.example.zendi_application.TransactionHistory;

import static com.example.zendi_application.DataManager.orderedList;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;

import com.example.zendi_application.DataManager;
import com.example.zendi_application.R;

import java.util.stream.Collectors;


public class TransactionHistory extends AppCompatActivity {

    private RecyclerView mRecyclerBill;
    private BillAdapter mBillAdapter;
    LayoutAnimationController leftToRight;
    View turnBackBtn;

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_transaction_history2);

        Init();
        setButton();
    }


    private void setButton() {
        turnBackBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private void Init() {
        mRecyclerBill = findViewById(R.id.transactionHistory_recyclerView);
        turnBackBtn = findViewById(R.id.turnBack_transactionHistory);
        mBillAdapter = new BillAdapter(this);
        mRecyclerBill.setLayoutManager(new LinearLayoutManager(this));

        leftToRight = AnimationUtils.loadLayoutAnimation(this, R.anim.layout_animation_left_to_right);
        mRecyclerBill.setLayoutAnimation(leftToRight);

        //getListOrderedByDay(DataManager.getCurrentDay(), 0);  // 0 date   1 year   2 month year
        mBillAdapter.SetData(
                        orderedList
                        .stream()
                        .filter(ite -> ite.getEmail().compareTo(DataManager.host.getEmail()) == 0)
                        .collect(Collectors.toList()));
        mRecyclerBill.setAdapter(mBillAdapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }
}