package com.example.zendi_application.searchfragment.listnavigation;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.GridLayout;

import com.example.zendi_application.R;
import com.example.zendi_application.searchfragment.RecycleAdapter;

import java.util.ArrayList;

public class SearchListNavigationActivity extends AppCompatActivity {
    RecyclerView recv;
    RecyclerView.Adapter recAdt;
    RecyclerView.LayoutManager layoutManager;
    Toolbar tb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_list_navigation);
        tb = findViewById(R.id.shoesBar);
        tb.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        ArrayList<ArticalModel> articalModelArrayList = new ArrayList<>();
        articalModelArrayList.add(new ArticalModel(R.drawable.ic_baseline_face_24,"áo"));
        articalModelArrayList.add(new ArticalModel(R.drawable.ic_baseline_face_24,"áo"));
        articalModelArrayList.add(new ArticalModel(R.drawable.ic_baseline_face_24,"áo"));
        articalModelArrayList.add(new ArticalModel(R.drawable.ic_baseline_face_24,"áo"));
        articalModelArrayList.add(new ArticalModel(R.drawable.ic_baseline_face_24,"áo"));
        articalModelArrayList.add(new ArticalModel(R.drawable.ic_baseline_face_24,"áo"));
        articalModelArrayList.add(new ArticalModel(R.drawable.ic_baseline_face_24,"áo"));
        recv = findViewById(R.id.recyc_artical);
        recv.setHasFixedSize(true);
        recAdt = new RecycleAdapterForArtical(articalModelArrayList);
        layoutManager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        recv.setLayoutManager(layoutManager);
        recv.setAdapter(recAdt);
    }
}