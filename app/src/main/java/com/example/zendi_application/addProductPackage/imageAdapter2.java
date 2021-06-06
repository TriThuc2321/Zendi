package com.example.zendi_application.addProductPackage;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.zendi_application.R;
import com.example.zendi_application.dropFragment.product_package.product2;

import java.util.ArrayList;
import java.util.List;

public class imageAdapter2 extends RecyclerView.Adapter<imageAdapter2.imageViewHolder2>{
    private Context mContext;
    private List<Uri> mListString;

    @NonNull
    @Override
    public imageViewHolder2 onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_newimageofproduct2,parent,false);
        return new imageAdapter2.imageViewHolder2(view);

    }
    public  imageAdapter2(Context mContext) {
        this.mContext = mContext;
    }
    public void SetData(List<Uri> ImageList)
    {
      mListString = ImageList;
        notifyDataSetChanged();
    }
    @Override
    public void onBindViewHolder(@NonNull imageViewHolder2 holder, int position) {
        Uri String_ = mListString.get(position);
        if (String_ == null) return;
       // Glide.with(mContext).load(String_).into(holder.img2);
        holder.img2.setImageURI(String_);
    }

    @Override
    public int getItemCount() {
        if (mListString!= null) return mListString.size();
        return 0;
    }

    public class imageViewHolder2 extends RecyclerView.ViewHolder{
        private ImageView img2;
        public imageViewHolder2(@NonNull View itemView) {
            super(itemView);
            img2 = itemView.findViewById(R.id.imageofnewproduct2_uploaddata);
        }
    }
}
