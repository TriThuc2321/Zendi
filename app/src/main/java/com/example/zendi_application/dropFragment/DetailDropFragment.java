package com.example.zendi_application.dropFragment;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.zendi_application.R;
import com.example.zendi_application.dropFragment.drop.drop;
import com.example.zendi_application.dropFragment.product.productAdapter;

public class DetailDropFragment extends Fragment {
    drop dropp;
    private ImageView img;
    private RecyclerView rcv_collection;
    private productAdapter ProductAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater,container,savedInstanceState);
        View mview = inflater.inflate(R.layout.fragment_detail_drop, container, false);
        // Anh xa view
        img = mview.findViewById(R.id.img_drop_collection);
        if (dropp != null) {
            img.setImageResource(dropp.getResourceId());
        }
        rcv_collection = mview.findViewById(R.id.rcv_collection);
        ProductAdapter = new productAdapter();

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mview.getContext(),RecyclerView.VERTICAL,false);
        rcv_collection.setLayoutManager(linearLayoutManager);

        ProductAdapter.setData(dropp.getProductList());
        rcv_collection.setAdapter(ProductAdapter);
        return mview;
    }
    public void recieveDrop(drop selected_drop)
    {
        this.dropp = selected_drop;
    }
}