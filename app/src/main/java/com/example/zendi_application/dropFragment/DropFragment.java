package com.example.zendi_application.dropFragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.zendi_application.R;
import com.example.zendi_application.dropFragment.category_drop.category;
import com.example.zendi_application.dropFragment.category_drop.categoryAdapter;
import com.example.zendi_application.dropFragment.drop.drop;
import com.example.zendi_application.dropFragment.product.product;

import java.util.ArrayList;
import java.util.List;

public class DropFragment extends Fragment implements View.OnClickListener {
    Button btn_type ;
    Button btn_type1 ;
    Button btn_type2;
    private RecyclerView rcvCategory;
    private categoryAdapter CategoryAdapter;
    LinearLayoutManager linearLayoutManager;
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
        rcvCategory.setHasFixedSize(true); // add to project make scroll smoothly

        linearLayoutManager = new LinearLayoutManager(this.getContext(),RecyclerView.VERTICAL,false);
        rcvCategory.setLayoutManager(linearLayoutManager);

        CategoryAdapter.SetData(getListCategory());
        rcvCategory.setAdapter(CategoryAdapter);

        //
        btn_type.setOnClickListener(this::onClick);
        btn_type1.setOnClickListener(this::onClick);
        btn_type2.setOnClickListener(this::onClick);

        ///


        return view;
    }

    private List<category> getListCategory()
    {
        // Test product
        List<Integer> imglist = new ArrayList<>();
        imglist.add(R.drawable.categorytest);
        imglist.add(R.drawable.categorytest1);
        imglist.add(R.drawable.categorytest3);

        List<Integer> imglist1 = new ArrayList<>();
        imglist1.add(R.drawable.nike_collection);
        imglist1.add(R.drawable.nikeshos1);
        imglist1.add(R.drawable.categorytest3);

        List<Integer> remainningAmount = new ArrayList<>();
        remainningAmount.add(5);
        remainningAmount.add(6);
        remainningAmount.add(3);
        remainningAmount.add(2);
        remainningAmount.add(1);
        ArrayList<product> productList = new ArrayList<>();
        productList.add(new product("UIT123","ZX 2K BOOTS PURE SHOES","1.400.000 VND",imglist,remainningAmount,1));
        productList.add(new product("UIT122","ZX 3K BOOTS PURE SHOES","1.500.000 VND",imglist1,remainningAmount,2));
        productList.add(new product("UIT121","ZX 4K BOOTS PURE SHOES","2.000.000 VND",imglist,remainningAmount,3));
        productList.add(new product("UIT120","ZX 5K BOOTS PURE SHOES","2.500.000 VND",imglist1,remainningAmount,1));
        //1
        List<category> mcategoryList = new ArrayList<>();

        List<drop> listDrop = new ArrayList<>();
        listDrop.add(new drop(R.drawable.categorytest,"STAN SMITH, FOREVER"+"","JUST DROPPED","ORIGINALS",productList));
        listDrop.add(new drop(R.drawable.categorytest1,"RUNNER, FASTER"+"","JUST DROPPED","SPORT",productList));
        listDrop.add(new drop(R.drawable.categorytest3,"HYPEBEAST, BUMP"+"","JUST DROPPED","ORIGINALS",productList));
        listDrop.add(new drop(R.drawable.categorytest2,"SNEAKER, FASHION"+"","JUST DROPPED","PARTY",productList));

        listDrop.add(new drop(R.drawable.categorytest,"STAN SMITH, FOREVER1"+"","JUST DROPPED","ORIGINALS",productList));
        listDrop.add(new drop(R.drawable.categorytest1,"RUNNER, FASTER1"+"","JUST DROPPED","SPORT",productList));
        listDrop.add(new drop(R.drawable.categorytest3,"HYPEBEAST, BUMP1"+"","JUST DROPPED","ORIGINALS",productList));
        listDrop.add(new drop(R.drawable.categorytest2,"SNEAKER, FASHION1"+"","JUST DROPPED","PARTY",productList));

        mcategoryList.add(new category(listDrop));
        mcategoryList.add(new category(listDrop));
        mcategoryList.add(new category(listDrop));
        mcategoryList.add(new category(listDrop));
        mcategoryList.add(new category(listDrop));
        mcategoryList.add(new category(listDrop));
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
            Toast.makeText(this.getContext(),"C",Toast.LENGTH_SHORT).show();
            rcvCategory.smoothScrollToPosition(1);

        }
        if (v.getId() == R.id.btn_type2)
        {
            Toast.makeText(this.getContext(),"CAI CC ME M",Toast.LENGTH_SHORT).show();
            rcvCategory.smoothScrollToPosition(2);
        }
    }

}
