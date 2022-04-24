package com.example.zendi_application.addProductPackage;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.zendi_application.R;
import com.example.zendi_application.dropFragment.product_package.product2;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class imageAddDropAdapter extends RecyclerView.Adapter<imageAddDropAdapter.imageViewHolder>{

    private List<product2> mListproduct;
    private List<String> mListString;
    private int mtype;

    public imageAddDropAdapter() {

    }

    public void SetData(List<product2> Listproduct)
    {
        mListproduct = Listproduct;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public imageAddDropAdapter.imageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_newimageofproduct,parent,false);
        return new imageAddDropAdapter.imageViewHolder(view);
    }

    @Override
    public int getItemCount() {
        if (mListproduct!= null) return mListproduct.size();
        return 0;
    }

    @Override
    public void onBindViewHolder(@NonNull imageAddDropAdapter.imageViewHolder holder, int position) {
        /// load tam dau tien cua tat ca product
            product2 product2_ = mListproduct.get(position);
            if (product2_ == null) return;
            Picasso.get().load(product2_.getResourceID().get(0)).into(holder.img);
            holder.imgname.setText(product2_.getProductName());

    }

    public class imageViewHolder extends RecyclerView.ViewHolder{
        private ImageView img;
        private TextView imgname;

        public imageViewHolder(@NonNull View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.imageofnewproduct);
            imgname = itemView.findViewById(R.id.imgname);
        }
    }
}
