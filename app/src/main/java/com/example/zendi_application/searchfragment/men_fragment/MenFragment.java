package com.example.zendi_application.searchfragment.men_fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.zendi_application.R;
import com.example.zendi_application.searchfragment.ElementOfRecycModel;
import com.example.zendi_application.searchfragment.RecycleAdapter;

import java.util.ArrayList;

public class MenFragment extends Fragment {
    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    public static MenFragment getInstance()
    {
        MenFragment menFragment = new MenFragment();
        return menFragment;
    }

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
        recyclerView = view.findViewById(R.id.newArrivals_recyc);
        recyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(view.getContext(),LinearLayoutManager.HORIZONTAL,false);
        mAdapter = new RecycleAdapter(elementOfRecycModels);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setAdapter(mAdapter);
        return view;
    }
}
