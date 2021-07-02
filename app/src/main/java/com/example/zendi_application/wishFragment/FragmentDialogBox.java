package com.example.zendi_application.wishFragment;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.zendi_application.DataManager;
import com.example.zendi_application.HomeScreen;
import com.example.zendi_application.R;
import com.example.zendi_application.dropFragment.detail_product.detailproductAdapter;
import com.example.zendi_application.dropFragment.drop.drop2;
import com.example.zendi_application.dropFragment.product_package.product2;
import com.example.zendi_application.shopFragment.ShoeInBag;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class FragmentDialogBox extends Fragment {

    ShoeInBag shoeInBag, shoe;
    private TextView productName,productPrice, choose, brandname;
    private Button backbtn,shopnowbtn;
    private ImageView shoeIM;
    private Spinner sizeSpinner;
    private String selectedSize;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View mview = inflater.inflate(R.layout.choosesizee_fragment, container, false);
        productName = mview.findViewById(R.id.productname);
        productPrice = mview.findViewById(R.id.productprice);
        backbtn = mview.findViewById(R.id.backbtn);
        shopnowbtn = mview.findViewById(R.id.shopnowbtn);
        sizeSpinner = mview.findViewById(R.id.sizespinner);
        shoeIM = mview.findViewById(R.id.imgofShoe);
        choose =mview.findViewById(R.id.choose);
        brandname = mview.findViewById(R.id.brandName);
        selectedSize ="5 UK";

        DataManager.sizeConvert.put("5.5 UK",1);
        DataManager.sizeConvert.put("5 UK",0);
        DataManager.sizeConvert.put("6 UK",2);
        DataManager.sizeConvert.put("6.5 UK",3);
        DataManager.sizeConvert.put("7 UK",4);
        DataManager.sizeConvert.put("7.5 UK",5);
        DataManager.sizeConvert.put("8 UK",6);
        DataManager.sizeConvert.put("8.5 UK",7);
        DataManager.sizeConvert.put("9 UK",8);
        DataManager.sizeConvert.put("9.5 UK",9);
        DataManager.sizeConvert.put("10 UK",10);
        DataManager.sizeConvert.put("10.5 UK",11);
        DataManager.sizeConvert.put("11 UK",12);
        DataManager.sizeConvert.put("11.5 UK",13);
        DataManager.sizeConvert.put("12 UK",14);

        List<String> sizeList = new ArrayList<>();
        sizeList.add("5 UK");
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
                if (shoeInBag.getRemainingAmount().get(DataManager.sizeConvert.get(selectedSize)) != 0)
                {
                    selectedSize = sizeSpinner.getSelectedItem().toString();
                    shoe = new ShoeInBag(shoeInBag.getProductId(), shoeInBag.getProductName(), shoeInBag.getProductPrice()
                            , shoeInBag.getProductBrand(), shoeInBag.getProductType(), shoeInBag.getResourceID(), shoeInBag.getRemainingAmount()
                            , shoeInBag.getType(), selectedSize, "1");
                    Toast.makeText(mview.getContext(),"DRAGGED INTO BAG SUCCESSFULLY !!",Toast.LENGTH_SHORT);
                }
                else
                    Toast.makeText(mview.getContext(),"Sold out !!",Toast.LENGTH_SHORT);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        Picasso.get().load(shoeInBag.getResourceID().get(0)).into(shoeIM);
        choose.setText("Choose size: ");
        productPrice.setText(new StringBuilder("Price: $").append(shoeInBag.getProductPrice()));
        brandname.setText(shoeInBag.getProductBrand());
        productName.setText(shoeInBag.getProductName());
        backbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AppCompatActivity activity = (AppCompatActivity)v.getContext();
                getFragmentManager().popBackStack();
                ((HomeScreen)activity).appBarLayout.setVisibility(View.VISIBLE);
                ((HomeScreen)activity).mNavigationView.setVisibility(View.VISIBLE);
            }
        });

        shopnowbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /// Process not choose size
                if (shoe == null) {
                    Toast.makeText(v.getContext(),"Please Choose Size For This !!",Toast.LENGTH_SHORT);
                }
                else
                {
                    /// Process not log in
                    if (DataManager.host == null)
                    {
                        Toast.makeText(v.getContext(),"Please Log In To Get Product !!", Toast.LENGTH_SHORT);
                    }
                    else
                    {
                        Integer check = 0;
                        for (ShoeInBag ite : DataManager.list)
                        {
                            // Process add the shoe added
                            if (ite.getProductId().compareTo(shoe.getProductId()) == 0 && ite.getShoeSize().compareTo(shoe.getShoeSize()) == 0 )
                            {
                                DataManager.update_Amount_Of_Shoe_In_Bag(shoe,ite,DataManager.host,v.getContext());
                                String docName = shoeInBag.getProductId() + "_" + shoeInBag.getShoeSize() ;
                                //thang
                                String docName2 = shoeInBag.getProductId();
                                //
                                FirebaseFirestore db = FirebaseFirestore.getInstance();
                                db.collection("InWish/"+DataManager.host.getId()+"/ShoeinWish").document(docName)
                                        .delete().addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void aVoid) {
                                    }
                                });
                                //thang
                                db.collection("InWish/"+DataManager.host.getId()+"/ShoeinWish").document(docName2)
                                        .delete().addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void aVoid) {
                                    }
                                });
                                //
                                DataManager.shoeInWish.remove(shoeInBag);
                                DataManager.shoeInWishAdapter.notifyDataSetChanged();
                                DataManager.shoeInBagAdapter.notifyDataSetChanged();
                                check = 1;
                                Toast.makeText(v.getContext(),"Added "+ shoe.getProductName() + " to bag successfully", Toast.LENGTH_SHORT).show();
                                break;
                            }
                        }
                        if ( check == 0 ) {
                            Integer amount_of_product = shoe.getRemainingAmount().get(DataManager.sizeConvert.get(selectedSize));
                            Integer processed_amount = amount_of_product - Integer.parseInt(shoe.getShoeAmount());
                            shoe.getRemainingAmount().set(DataManager.sizeConvert.get(selectedSize),processed_amount);
                            DataManager.push_Shoe_To_Bag_in_Fragmentdialog(shoe, DataManager.host, v.getContext());
                            DataManager.shoeInBagAdapter.notifyDataSetChanged();
                            Toast.makeText(v.getContext(),"Added "+ shoe.getProductName() + " to bag successfully", Toast.LENGTH_SHORT).show();
                            String docName = shoeInBag.getProductId() + "_" + shoeInBag.getShoeSize() ;
                            FirebaseFirestore db = FirebaseFirestore.getInstance();
                            db.collection("InWish/"+DataManager.host.getId()+"/ShoeinWish").document(docName)
                                    .delete().addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {
                                }
                            });
                            //thang
                            String docName2 = shoeInBag.getProductId();
                            db.collection("InWish/"+DataManager.host.getId()+"/ShoeinWish").document(docName2)
                                    .delete().addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {
                                }
                            });
                            //
                            DataManager.shoeInWish.remove(shoeInBag);
                            DataManager.shoeInWishAdapter.notifyDataSetChanged();
                            for (ShoeInBag ite : DataManager.list)
                            {
                                if (ite.getProductId() == shoe.getProductId()) {
                                    ite.getRemainingAmount().set(DataManager.sizeConvert.get(selectedSize), processed_amount);
                                    break;
                                }
                            }
                        }
                    }
                }
            }
        });
        return mview;
    }
    public void recieveDrop(ShoeInBag selected_product)
    {
        this.shoeInBag = selected_product;
    }
}
