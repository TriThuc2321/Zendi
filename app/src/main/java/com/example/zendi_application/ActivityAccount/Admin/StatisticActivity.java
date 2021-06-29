package com.example.zendi_application.ActivityAccount.Admin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.WindowManager;

import com.example.zendi_application.ActivityAccount.User;
import com.example.zendi_application.DataManager;
import com.example.zendi_application.R;
import com.example.zendi_application.shopFragment.ShoeInBag;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class StatisticActivity extends AppCompatActivity {

    private RecyclerView mRecyclerStatistic;
    private StatisticAdapter mStatisticAdapter ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_statistic);

        Innit();

    }

    private void Innit() {
        mRecyclerStatistic = findViewById(R.id.list_statistic_recyclerView);

        mStatisticAdapter = new StatisticAdapter(this);
        mStatisticAdapter.SetData(DataManager.orderedList);
        mRecyclerStatistic.setAdapter(mStatisticAdapter);
        mRecyclerStatistic.setLayoutManager(new LinearLayoutManager(this));
    }


}