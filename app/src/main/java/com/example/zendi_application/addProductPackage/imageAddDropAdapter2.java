package com.example.zendi_application.addProductPackage;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.zendi_application.R;
import com.example.zendi_application.dropFragment.product_package.product2;
import com.squareup.picasso.Picasso;

import java.util.List;

public class imageAddDropAdapter2 extends RecyclerView.Adapter<imageAddDropAdapter2.imageViewHolder2>{
    private List<product2> mListproduct;

    public imageAddDropAdapter2() {

    }

    public void SetData(List<product2> Listproduct)
    {
        mListproduct = Listproduct;
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public imageViewHolder2 onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_newimageofproduct_adddrop,parent,false);
        return new imageAddDropAdapter2.imageViewHolder2(view);
    }

    @Override
    public void onBindViewHolder(@NonNull imageViewHolder2 holder, int position) {
        product2 product2_ = mListproduct.get(position);
        if (product2_ == null) return;
        Picasso.get().load(product2_.getResourceID().get(0)).into(holder.img2);
        holder.imgName.setText(product2_.getProductName());
    }

    @Override
    public int getItemCount() {
        if (mListproduct!= null) return mListproduct.size();
        return 0;
    }

    public class imageViewHolder2 extends RecyclerView.ViewHolder{
        TextView imgName;
        ImageView img2;
        public imageViewHolder2(@NonNull View itemView) {
            super(itemView);
            imgName = itemView.findViewById(R.id.imgname_adddrop);
            img2 = itemView.findViewById(R.id.imageofnewproduct_adddrop);


        }
    }
}
