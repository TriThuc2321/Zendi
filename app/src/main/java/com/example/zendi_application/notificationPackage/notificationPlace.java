package com.example.zendi_application.notificationPackage;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.zendi_application.DataManager;
import com.example.zendi_application.HomeScreen;
import com.example.zendi_application.R;
import com.example.zendi_application.dropFragment.product_package.productAdapter;
import com.example.zendi_application.notificationPackage.notification.notification;
import com.example.zendi_application.notificationPackage.notification.notificationAdapter;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class notificationPlace extends AppCompatActivity {
    Button backBtn;
    RecyclerView rcv;
    public static notificationAdapter adapter = new notificationAdapter();;
    List<notification> listNoti = new ArrayList<>();

    public void getNotiList() {
        DataManager.LoadNotification();
        listNoti = DataManager.listNoti;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification_place);
        backBtn = findViewById(R.id.back_notificationPlace);
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               finish();
            }
        });

        rcv = findViewById(R.id.rcv_notification);

        getNotiList();

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(rcv.getContext(),RecyclerView.VERTICAL,false);
        rcv.setLayoutManager(linearLayoutManager);

//        firstVisibleInListview = linearLayoutManager.findFirstVisibleItemPosition();  //init for variable position

        adapter.SetData(listNoti);
        rcv.setAdapter(adapter);

    }
}