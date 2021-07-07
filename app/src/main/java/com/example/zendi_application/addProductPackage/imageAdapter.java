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

import java.util.ArrayList;
import java.util.List;

public class imageAdapter extends RecyclerView.Adapter<imageAdapter.imageViewHolder>{

    private Context mContext;
    private List<product2> mListproduct;
    private List<String> mListString;
    private int mtype;

    public  imageAdapter(Context mContext) {
        this.mContext = mContext;
    }

    public void SetData(List<product2> Listproduct,int type)
    {
        mListproduct = Listproduct;
        if (mListproduct.size() > type){
            mListString = mListproduct.get(type).getResourceID();
        }
        else
        {
            mListString = new ArrayList<>();
        }
        mtype = type;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public imageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_newimageofproduct,parent,false);
        return new imageViewHolder(view);
    }

    @Override
    public int getItemCount() {
        if (mListString != null && mtype != 999) return 4;
        if (mListproduct!= null && mtype == 999) return mListproduct.size();
        return 0;
    }

    @Override
    public void onBindViewHolder(@NonNull imageViewHolder holder, int position) {
        /// load tam dau tien cua tat ca product
        if (mtype == 999) {
            product2 product2_ = mListproduct.get(position);
            if (product2_ == null) return;

            Glide.with(mContext).load(product2_.getResourceID().get(0)).into(holder.img);
            holder.imgname.setText(product2_.getProductName());
        }
        /// load tat ca anh cua 1 product chinh dinh
        else if (mtype != 999) // mtype la so product do
        {
            if (mListproduct.size() > mtype){
                mListString = mListproduct.get(mtype).getResourceID();
            }
            if (position >= mListString.size()) return;
            String String_ = mListString.get(position);
            if (String_ == null) return;
            Glide.with(mContext).load(String_).into(holder.img);
//            holder.imgname.setText("0");
        }
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
