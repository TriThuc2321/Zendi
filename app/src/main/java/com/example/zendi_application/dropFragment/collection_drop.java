package com.example.zendi_application.dropFragment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.WindowManager;
import android.widget.ImageView;

import com.example.zendi_application.R;
import com.example.zendi_application.dropFragment.drop.drop;
import com.example.zendi_application.dropFragment.product_package.productAdapter;

public class collection_drop extends AppCompatActivity {
    private ImageView img;
    private RecyclerView rcv_collection;
    private productAdapter ProductAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_collection_drop);
        // get bundle from drop
        Bundle bundle = getIntent().getExtras();
        drop dropp = bundle.getParcelable("data");
//        img = findViewById(R.id.img);
//        img.setImageResource();
//
//        rcv_collection = findViewById(R.id.rcv_collection);
//        ProductAdapter = new productAdapter();
//
//        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this,RecyclerView.VERTICAL,false);
//        rcv_collection.setLayoutManager(linearLayoutManager);
//
//        ProductAdapter.setData(dropp.getProductList());
//        rcv_collection.setAdapter(ProductAdapter);






    }

}