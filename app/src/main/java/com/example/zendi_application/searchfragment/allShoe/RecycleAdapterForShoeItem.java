package com.example.zendi_application.searchfragment.allShoe;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.zendi_application.DataManager;
import com.example.zendi_application.HomeScreen;
import com.example.zendi_application.R;
import com.example.zendi_application.dropFragment.DetailProductFragment;
import com.example.zendi_application.dropFragment.product_package.product2;
import com.example.zendi_application.dropFragment.product_package.productAdapter;
import com.example.zendi_application.newac;
import com.example.zendi_application.searchfragment.ElementOfRecycModel;
import com.example.zendi_application.searchfragment.MyDetailProduct;
import com.example.zendi_application.searchfragment.Transactor;
import com.example.zendi_application.shopFragment.ShoeInBag;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class RecycleAdapterForShoeItem extends RecyclerView.Adapter<RecycleAdapterForShoeItem.RecycleViewHolderForShoeItem> {
    private ArrayList<ShoeItemModel> elementOfRecycModelArrayList = new ArrayList<>();
    private List<product2> listProduct = new ArrayList<>();
    public static class RecycleViewHolderForShoeItem extends RecyclerView.ViewHolder{
        public ImageView imageView;
        public ImageView heartView;
        public TextView NameOfElement;
        public TextView Charge;
        public ConstraintLayout constraintLayout;
        public RecycleViewHolderForShoeItem(View view)
        {
            super(view);
            imageView = view.findViewById(R.id.imageView2);
            heartView = view.findViewById(R.id.heart_imageView);
            NameOfElement = view.findViewById(R.id.tenSanPham_textview2);
            Charge = view.findViewById(R.id.soTien_textview2);
        }
    }
    public RecycleAdapterForShoeItem(MyEnum.Sex sex)
    {
        String sexstring;
        switch (sex)
        {
            case MEN: sexstring = "1";break;
            default: sexstring = "2";
        }
        for(product2 product : DataManager.listProduct)
        {
            if (product.getProductType().equals(sexstring) || product.getProductType().equals("3"))
            {
                listProduct.add(product);
                int index = Transactor.ExistInShoeWish(product);
                if (index != -1)
                {
                    elementOfRecycModelArrayList.add(new ShoeItemModel(true,index));
                }
                else
                {
                    elementOfRecycModelArrayList.add(new ShoeItemModel(false,index));
                }
            }
        }
    }
    @Override
    public RecycleViewHolderForShoeItem onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_view_for_all_shoe_item,parent,false);
        RecycleViewHolderForShoeItem RVH = new RecycleViewHolderForShoeItem(view);
        return RVH;
    }

    @Override
    public void onBindViewHolder(@NonNull RecycleViewHolderForShoeItem holder, int position) {
        ShoeItemModel currentItem = elementOfRecycModelArrayList.get(position);
        product2 currentItemProDuct = listProduct.get(position);
        if (currentItem.isLike())
            holder.heartView.setImageResource(R.drawable.ic_baseline_favorite_24);
        else
            holder.heartView.setImageResource(R.drawable.ic_baseline_favorite_border_24);
        Picasso.get().load(currentItemProDuct.getResourceID().get(0)).into(holder.imageView);
        holder.Charge.setText(currentItemProDuct.getProductPrice());
        holder.NameOfElement.setText(currentItemProDuct.getProductName());
        holder.heartView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (currentItem.isLike())
                {
                    currentItem.setLike(false);

                    DataManager.shoeInWish.remove(currentItem.getIndexInShoeWish());
                    currentItem.setIndexInShoeWish(-1);

                    holder.heartView.setImageResource(R.drawable.ic_baseline_favorite_border_24);
                }
                else
                {
                    currentItem.setLike(true);

                    DataManager.shoeInWish.add(new ShoeInBag(currentItemProDuct.getProductId(),currentItemProDuct.getProductName(),currentItemProDuct.getProductPrice(),currentItemProDuct.getProductBrand(),currentItemProDuct.getProductType(),currentItemProDuct.getResourceID(),currentItemProDuct.getRemainingAmount(),currentItemProDuct.getType(),"0","0","0") );
                    currentItem.setIndexInShoeWish(DataManager.shoeInWish.size()-1);

                    holder.heartView.setImageResource(R.drawable.ic_baseline_favorite_24);
                }
            }
        });
        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Transactor.getInstance().getArrayList().add(currentItemProDuct);
                Intent intent = new Intent(v.getContext(), MyDetailProduct.class);
                v.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return elementOfRecycModelArrayList.size();
    }
}
