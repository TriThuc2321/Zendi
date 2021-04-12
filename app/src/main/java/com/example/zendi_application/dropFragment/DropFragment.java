package com.example.zendi_application.dropFragment;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.zendi_application.R;
import com.example.zendi_application.dropFragment.category_drop.category;
import com.example.zendi_application.dropFragment.category_drop.categoryAdapter;
import com.example.zendi_application.dropFragment.drop.drop;

import java.util.ArrayList;
import java.util.List;

public class DropFragment extends Fragment implements View.OnClickListener {
    Button btn_type ;
    Button btn_type1 ;
    Button btn_type2;
    private RecyclerView rcvCategory;
    private categoryAdapter CategoryAdapter;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_drops, container, false);
        btn_type = (Button)view.findViewById(R.id.btn_type);
        btn_type1 = (Button)view.findViewById(R.id.btn_type1);
        btn_type2 = (Button)view.findViewById(R.id.btn_type2);

        //RecycleView Category Drop
        rcvCategory = view.findViewById(R.id.rcv_category);
        CategoryAdapter = new categoryAdapter(this.getContext());

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this.getContext(),RecyclerView.VERTICAL,false);
        rcvCategory.setLayoutManager(linearLayoutManager);

        CategoryAdapter.SetData(getListCategory());
        rcvCategory.setAdapter(CategoryAdapter);

        //
        btn_type.setOnClickListener(this::onClick);
        btn_type1.setOnClickListener(this::onClick);
        btn_type2.setOnClickListener(this::onClick);
        return view;
    }

    private List<category> getListCategory()
    {
        List<category> mcategoryList = new ArrayList<>();

        List<drop> listDrop = new ArrayList<>();
        listDrop.add(new drop(R.drawable.categorytest,"STAN SMITH, FOREVER"+"","JUST DROPPED","ORIGINALS"));
        listDrop.add(new drop(R.drawable.categorytest1,"RUNNER, FASTER"+"","JUST DROPPED","SPORT"));
        listDrop.add(new drop(R.drawable.categorytest3,"HYPEBEAST, BUMP"+"","JUST DROPPED","ORIGINALS"));
        listDrop.add(new drop(R.drawable.categorytest2,"SNEAKER, FASHION"+"","JUST DROPPED","PARTY"));

        listDrop.add(new drop(R.drawable.categorytest,"STAN SMITH, FOREVER1"+"","JUST DROPPED","ORIGINALS"));
        listDrop.add(new drop(R.drawable.categorytest1,"RUNNER, FASTER1"+"","JUST DROPPED","SPORT"));
        listDrop.add(new drop(R.drawable.categorytest3,"HYPEBEAST, BUMP1"+"","JUST DROPPED","ORIGINALS"));
        listDrop.add(new drop(R.drawable.categorytest2,"SNEAKER, FASHION1"+"","JUST DROPPED","PARTY"));

        mcategoryList.add(new category(listDrop));
        mcategoryList.add(new category(listDrop));
        mcategoryList.add(new category(listDrop));
        return mcategoryList;
    }
    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btn_type)
        {
            Toast.makeText(this.getContext(),"CAI CC BA M",Toast.LENGTH_SHORT).show();
        }
        if (v.getId() == R.id.btn_type1)
        {
            Toast.makeText(this.getContext(),"CAI CC ME M",Toast.LENGTH_SHORT).show();


        }
        if (v.getId() == R.id.btn_type2)
        {
            Toast.makeText(this.getContext(),"CAI CC ME M",Toast.LENGTH_SHORT).show();
        }
    }
}
