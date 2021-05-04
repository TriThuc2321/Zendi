package com.example.zendi_application.searchfragment.men_fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.zendi_application.R;
import com.example.zendi_application.searchfragment.ElementOfRecycModel;
import com.example.zendi_application.searchfragment.RecycleAdapter;
import com.example.zendi_application.searchfragment.listnavigation.SearchListNavigationActivity;

import java.util.ArrayList;

public class MenFragment extends Fragment {
    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private Button bt;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_search_men, container, false);

        ArrayList<ElementOfRecycModel> elementOfRecycModels = new ArrayList<>();
        elementOfRecycModels.add(new ElementOfRecycModel(R.drawable.ic_baseline_face_24, "100.000","Áo nam","Originals"));
        elementOfRecycModels.add(new ElementOfRecycModel(R.drawable.ic_baseline_face_24, "100.000","Áo nam","Originals"));
        elementOfRecycModels.add(new ElementOfRecycModel(R.drawable.ic_baseline_face_24, "100.000","Áo nam","Originals"));
        elementOfRecycModels.add(new ElementOfRecycModel(R.drawable.ic_baseline_face_24, "100.000","Áo nam","Originals"));
        elementOfRecycModels.add(new ElementOfRecycModel(R.drawable.ic_baseline_face_24, "100.000","Áo nam","Originals"));
        elementOfRecycModels.add(new ElementOfRecycModel(R.drawable.ic_baseline_face_24, "100.000","Áo nam","Originals"));
        elementOfRecycModels.add(new ElementOfRecycModel(R.drawable.ic_baseline_face_24, "100.000","Áo nam","Originals"));
        elementOfRecycModels.add(new ElementOfRecycModel(R.drawable.ic_baseline_face_24, "100.000","Áo nam","Originals"));
        elementOfRecycModels.add(new ElementOfRecycModel(R.drawable.ic_baseline_face_24, "100.000","Áo nam","Originals"));
        elementOfRecycModels.add(new ElementOfRecycModel(R.drawable.ic_baseline_face_24, "100.000","Áo nam","Originals"));
        elementOfRecycModels.add(new ElementOfRecycModel(R.drawable.ic_baseline_face_24, "100.000","Áo nam","Originals"));
        elementOfRecycModels.add(new ElementOfRecycModel(R.drawable.ic_baseline_face_24, "100.000","Áo nam","Originals"));
        recyclerView = view.findViewById(R.id.newArrivals_recyc);
        recyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this.getContext(),LinearLayoutManager.HORIZONTAL,false);
        mAdapter = new RecycleAdapter(elementOfRecycModels);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setAdapter(mAdapter);

        bt = view.findViewById(R.id.shoes_button);
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(view.getContext(), SearchListNavigationActivity.class);
                startActivity(intent);
            }
        });
        return view;
    }
}
