package com.example.zendi_application.searchfragment.allShoe;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.zendi_application.R;

import java.util.ArrayList;

public class AllShoeActivity extends AppCompatActivity {
    RecyclerView recv;
    RecyclerView.Adapter recAdt;
    RecyclerView.LayoutManager layoutManager;
    Toolbar tb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.all_shoe_activity);
        tb = findViewById(R.id.shoesBar2);
        tb.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        ArrayList<ShoeItemModel> shoeItemModelArrayList = new ArrayList<>();
        shoeItemModelArrayList.add(new ShoeItemModel(R.drawable.ic_baseline_face_24,"100000","Nike","Originals"));
        shoeItemModelArrayList.add(new ShoeItemModel(R.drawable.ic_baseline_face_24,"100000","Nike","Originals"));
        shoeItemModelArrayList.add(new ShoeItemModel(R.drawable.ic_baseline_face_24,"100000","Nike","Originals"));
        shoeItemModelArrayList.add(new ShoeItemModel(R.drawable.ic_baseline_face_24,"100000","Nike","Originals"));
        shoeItemModelArrayList.add(new ShoeItemModel(R.drawable.ic_baseline_face_24,"100000","Nike","Originals"));
        
        recv = findViewById(R.id.rcv_all_shoe);
        recv.setHasFixedSize(true);
        recAdt = new RecycleAdapterForShoeItem(shoeItemModelArrayList);
        layoutManager = new GridLayoutManager(this,2,GridLayoutManager.VERTICAL,false);
        recv.setLayoutManager(layoutManager);
        recv.setAdapter(recAdt);
    }
}