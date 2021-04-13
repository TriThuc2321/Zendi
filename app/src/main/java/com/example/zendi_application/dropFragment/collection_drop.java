package com.example.zendi_application.dropFragment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.zendi_application.R;
import com.example.zendi_application.dropFragment.drop.drop;

import java.util.List;

public class collection_drop extends AppCompatActivity {
    TextView test;
    ImageView img;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_collection_drop);
        // get bundle from drop
        Bundle bundle = getIntent().getExtras();
        drop dropp = bundle.getParcelable("data");
        test = findViewById(R.id.textView);
        img = findViewById(R.id.img);
        int a =dropp.getProductList().get(0).getResourceID().get(1);
        img.setImageResource(a);



    }
}