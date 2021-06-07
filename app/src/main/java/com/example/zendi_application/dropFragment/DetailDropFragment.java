package com.example.zendi_application.dropFragment;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.example.zendi_application.HomeScreen;
import com.example.zendi_application.R;
import com.example.zendi_application.dropFragment.drop.drop;
import com.example.zendi_application.dropFragment.drop.drop2;
import com.example.zendi_application.dropFragment.product_package.productAdapter;
import com.squareup.picasso.Picasso;

public class DetailDropFragment extends Fragment {
    drop2 dropp;
    private ImageView img;
    private RecyclerView rcv_collection;
    private productAdapter ProductAdapter;
    private ConstraintLayout dropWallpaper;
    private Button typebtn,nameDropbtn,backbtn;

    @Override
    public void onResume() {
        super.onResume();
        AppCompatActivity activity = (AppCompatActivity) getContext();
        ((HomeScreen)activity).appBarLayout.setVisibility(View.VISIBLE);
        ((HomeScreen)activity).mNavigationView.setVisibility(View.VISIBLE);


    }

    // the variable defines the position of scrolling up of down
    private static int firstVisibleInListview;


    public void onStop() {
        super.onStop();

        Log.d("MainActivity Lifecycle", "===== Stop Detail Fragment =====");
        //onDestroy();
    }
    public void onDestroy() {
        super.onDestroy();
        Log.d("MainActivity Lifecycle", "===== Destroy Detail Fragment =====");
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater,container,savedInstanceState);
        View mview = inflater.inflate(R.layout.fragment_detail_drop, container, false);
        // Anh xa view

        img = mview.findViewById(R.id.img_drop_collection);
        dropWallpaper = mview.findViewById(R.id.dropWallpaper);
        typebtn=mview.findViewById(R.id.type_drop_collection);
        nameDropbtn=mview.findViewById(R.id.namedrop);
        backbtn=mview.findViewById(R.id.back_detailfragment);
        if (dropp != null) {
            Picasso.get().load(dropp.getImage()).into(img);
        }
        rcv_collection = mview.findViewById(R.id.rcv_collection);
        ProductAdapter = new productAdapter(getContext());

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mview.getContext(),RecyclerView.VERTICAL,false);
        rcv_collection.setLayoutManager(linearLayoutManager);

       firstVisibleInListview = linearLayoutManager.findFirstVisibleItemPosition();  //init for variable position

        ProductAdapter.setData(dropp.getProductList(),dropp);
        rcv_collection.setAdapter(ProductAdapter);

        typebtn.setText(dropp.getType());
        nameDropbtn.setText(dropp.getCaption());

        /////////////////////
//        rcv_collection.addOnScrollListener(new RecyclerView.OnScrollListener() {
//
//            @Override
//            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
//                super.onScrolled(recyclerView, dx, dy);
//                if (dy > 50) {
//
//                    dropWallpaper.animate().translationY(-2500).setDuration(1000).setStartDelay(2000);
//                } if (dy < -20) {
//                    dropWallpaper.animate().translationY(0).setDuration(1000).setStartDelay(2000);
//
//                }
//            }
//        });

        backbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (getFragmentManager()!=null)
                getFragmentManager().popBackStack();
                AppCompatActivity activity = (AppCompatActivity) v.getContext();
                ((HomeScreen)activity).appBarLayout.setVisibility(View.VISIBLE);
                ((HomeScreen)activity).mNavigationView.setVisibility(View.VISIBLE);
            }
        });
        //////
        return mview;
    }
    public void recieveDrop(drop2 selected_drop)
    {
        this.dropp = selected_drop;
    }


}