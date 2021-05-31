package com.example.zendi_application.dropFragment;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.zendi_application.HomeScreen;
import com.example.zendi_application.R;
import com.example.zendi_application.addProductPackage.imageAdapter;
import com.example.zendi_application.dropFragment.detail_product.detailproductAdapter;
import com.example.zendi_application.dropFragment.drop.drop2;
import com.example.zendi_application.dropFragment.product_package.product2;
import com.example.zendi_application.dropFragment.product_package.productAdapter;
import com.example.zendi_application.shopFragment.ShoeInBag;

import java.util.ArrayList;
import java.util.List;


public class DetailProductFragment extends Fragment {
    product2 mproduct;
    drop2 parent;
    private TextView dropCaption,productName,productPrice;
    private Button backbtn,getbtn;
    private RecyclerView productimageRecycleView;
    private detailproductAdapter imageAdapter;
    private Spinner sizeSpinner;
    private String selectedSize;
    private ShoeInBag shoeInBag;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public void onStop() {
        super.onStop();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View mview = inflater.inflate(R.layout.fragment_detail_product, container, false);
        dropCaption = mview.findViewById(R.id.dropcaption_detailproduct);
        productName = mview.findViewById(R.id.productname_detailproduct);
        productPrice = mview.findViewById(R.id.productprice_detailproduct);
        backbtn = mview.findViewById(R.id.back_detailproduct);
        getbtn = mview.findViewById(R.id.getbtn_detailproduct);
        sizeSpinner = mview.findViewById(R.id.sizespinner_detailproduct);

          List<String> sizeList = new ArrayList<>();
        sizeList.add("5.5 UK");
        sizeList.add("6 UK");
        sizeList.add("6.5 UK");
        sizeList.add("7 UK");
        sizeList.add("7.5 UK");
        sizeList.add("8 UK");
        sizeList.add("8.5 UK");
        sizeList.add("9 UK");
        sizeList.add("9.5 UK");
        sizeList.add("10 UK");
        sizeList.add("10.5 UK");
        sizeList.add("11 UK");
        sizeList.add("11.5 UK");
        sizeList.add("12 UK");

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(mview.getContext(), android.R.layout.simple_spinner_item,sizeList);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sizeSpinner.setAdapter(arrayAdapter);
        sizeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//
//                if (mproduct.getRemainingAmount().get(Integer.parseInt(selectedSize)) != 0)
//                {
//                    shoeInBag.setShoeSize(selectedSize);
//                    Toast.makeText(mview.getContext(),"DRAGGED INTO BAG SUCCESSFULLY !!",Toast.LENGTH_SHORT);
//                }
//                else
//                    Toast.makeText(mview.getContext(),"Sold out !!",Toast.LENGTH_SHORT);


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        dropCaption.setText(parent.getCaption());
        productPrice.setText(mproduct.getProductPrice());
        productName.setText(mproduct.getProductName());


        // init recycleview
        productimageRecycleView = mview.findViewById(R.id.recycleview_detailproduct);
        imageAdapter= new detailproductAdapter(getContext());

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mview.getContext(),RecyclerView.HORIZONTAL,false);
        productimageRecycleView.setLayoutManager(linearLayoutManager);


        imageAdapter.SetData(mproduct,parent);
        productimageRecycleView.setAdapter(imageAdapter);
        //
        backbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            getFragmentManager().popBackStack();


            }
        });
        getbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                shoeInBag = new ShoeInBag(mproduct.getProductId(), mproduct.getProductName(), mproduct.getProductPrice()
                        , mproduct.getProductBrand(), mproduct.getProductType(), mproduct.getResourceID(), mproduct.getRemainingAmount()
                        , mproduct.getType(),null,"5.5","1");

            }
        });

        return mview;
    }
    public void recieveDrop(product2 selected_product, drop2 parent)
    {
        this.mproduct = selected_product;
        this.parent = parent;


    }
}