package com.example.zendi_application.searchfragment.allShoe;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.zendi_application.R;
import com.example.zendi_application.dropFragment.product_package.product2;
import com.example.zendi_application.searchfragment.ElementOfRecycModel;
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
        public RecycleViewHolderForShoeItem(View view)
        {
            super(view);
            imageView = view.findViewById(R.id.imageView2);
            heartView = view.findViewById(R.id.heart_imageView);
            NameOfElement = view.findViewById(R.id.tenSanPham_textview2);
            Charge = view.findViewById(R.id.soTien_textview2);
        }
    }
    public RecycleAdapterForShoeItem(ArrayList<ShoeItemModel> elementOfRecycModelArrayList, List<product2> listProduct)
    {
        this.elementOfRecycModelArrayList = elementOfRecycModelArrayList;
        this.listProduct = listProduct;
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
                    holder.heartView.setImageResource(R.drawable.ic_baseline_favorite_border_24);
                }
                else
                {
                    currentItem.setLike(true);
                    holder.heartView.setImageResource(R.drawable.ic_baseline_favorite_24);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return elementOfRecycModelArrayList.size();
    }
}
