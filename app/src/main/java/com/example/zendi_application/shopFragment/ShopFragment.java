package com.example.zendi_application.shopFragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.zendi_application.DataManager;
import com.example.zendi_application.R;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.List;

public class ShopFragment extends Fragment {

    List<ShoeInBag> shoeInBagList = new ArrayList<>();
    public ShoeInBagAdapter shoeInBagAdapter;
    RecyclerView recyclerView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_shop, container, false);

        shoeInBagAdapter = new ShoeInBagAdapter();
        //DataManager.getShoeInBagFromFirestone(this,"InBag",shoeInBagList);

        recyclerView = view.findViewById(R.id.shop_fragment_rcv);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this.getContext(),recyclerView.VERTICAL,false);
        recyclerView.setLayoutManager(linearLayoutManager);;
        recyclerView.setHasFixedSize(true);
        shoeInBagAdapter.setData(DataManager.list);
        recyclerView.setAdapter(shoeInBagAdapter);

        return view;
    }
}
