package com.example.zendi_application.searchfragment;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.zendi_application.DataManager;
import com.example.zendi_application.HomeScreen;
import com.example.zendi_application.R;
import com.example.zendi_application.dropFragment.DetailProductFragment;
import com.example.zendi_application.dropFragment.product_package.product2;
import com.example.zendi_application.searchfragment.allShoe.MyEnum;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class RecycleAdapter extends RecyclerView.Adapter<RecycleAdapter.RecycleViewHolder> {
    private List<product2> product2List = new ArrayList<>();
    public static class RecycleViewHolder extends RecyclerView.ViewHolder{
        public ImageView imageView;
        public TextView NameOfElement;
        public TextView Charge;
        public RecycleViewHolder(View view)
        {
            super(view);
            imageView = view.findViewById(R.id.imageView3);
            NameOfElement = view.findViewById(R.id.tenSanPham_textview);
            Charge = view.findViewById(R.id.soTien_textview);
        }
    }
    public RecycleAdapter(MyEnum.Brand brand, MyEnum.Sex sex)
    {
        String brandstring;
        String sexstring;
        switch (brand)
        {
            case NIKE:brandstring = "NIKE";break;
            case PUMA:brandstring = "PUMA";break;
            case CONVERSE:brandstring = "CONVERSE";break;
            case NEW_BALANCE:brandstring = "NEW BALANCE";break;
            case VANS:brandstring = "VANS";break;
            case ADDIDAS:brandstring = "ADDIDAS";break;
            default: REEBOOK:brandstring = "REEBOOK";
        }

        switch (sex)
        {
            case MEN:sexstring = "1";break;
            default: sexstring = "2";
        }
        for(product2 product : DataManager.listProduct)
        {
            if (brandstring.equals(product.getProductBrand().toUpperCase()) && (sexstring.equals(product.getProductType()) || product.getProductType().equals("3")) && product2List.size() < 8)
                product2List.add(product);
        }
    }
    @Override
    public RecycleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_for_recyc,parent,false);
        RecycleViewHolder RVH = new RecycleViewHolder(view);
        return RVH;
    }

    @Override
    public void onBindViewHolder(@NonNull RecycleViewHolder holder, int position) {
        product2 currentItem = product2List.get(position);
        Picasso.get().load(currentItem.getResourceID().get(0)).into(holder.imageView);
        holder.Charge.setText(currentItem.getProductPrice());
        holder.NameOfElement.setText(currentItem.getProductName());

        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Transactor.getInstance().getArrayList().add(currentItem);
                Intent intent = new Intent(v.getContext(), MyDetailProduct.class);
                v.getContext().startActivity(intent);
//                AppCompatActivity activity = (AppCompatActivity) v.getContext();
//                DetailProductFragment myFragment = new DetailProductFragment();
//                myFragment.recieveDrop(currentItem,null);
//                ((HomeScreen)activity).appBarLayout.setVisibility(View.INVISIBLE);
//                ((HomeScreen)activity).mNavigationView.setVisibility(View.INVISIBLE);
//                ((HomeScreen)activity).getSupportFragmentManager().beginTransaction().replace(R.id.home_screen, myFragment).addToBackStack(null).commit();
            }
        });
    }

    @Override
    public int getItemCount() {
        return product2List.size();
    }

}
