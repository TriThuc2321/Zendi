package com.example.zendi_application.searchfragment.allShoe;

import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.zendi_application.DataManager;
import com.example.zendi_application.R;
import com.example.zendi_application.dropFragment.product_package.product2;

import java.util.ArrayList;

public class AllShoeActivity extends AppCompatActivity {
    RecyclerView recv;
    RecyclerView.Adapter recAdt;
    RecyclerView.LayoutManager layoutManager;
    Toolbar tb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.all_shoe_activity);
        tb = findViewById(R.id.shoesBar2);
        tb.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        ArrayList<ShoeItemModel> shoeItemModelArrayList = new ArrayList<>();
        for(product2 product2 : DataManager.listProduct)
        {
            shoeItemModelArrayList.add(new ShoeItemModel(false));
        }

        recv = findViewById(R.id.rcv_all_shoe);
        recv.setHasFixedSize(true);
        recAdt = new RecycleAdapterForShoeItem(shoeItemModelArrayList, DataManager.listProduct);
        layoutManager = new GridLayoutManager(this,2,GridLayoutManager.VERTICAL,false);
        recv.setLayoutManager(layoutManager);
        recv.setAdapter(recAdt);
    }
}