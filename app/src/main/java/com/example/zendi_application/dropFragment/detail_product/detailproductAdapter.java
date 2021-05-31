package com.example.zendi_application.dropFragment.detail_product;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.zendi_application.R;
import com.example.zendi_application.addProductPackage.imageAddDropAdapter;
import com.example.zendi_application.dropFragment.drop.drop2;
import com.example.zendi_application.dropFragment.product_package.product;
import com.example.zendi_application.dropFragment.product_package.product2;
import com.squareup.picasso.Picasso;

import java.util.List;
public class detailproductAdapter extends RecyclerView.Adapter<detailproductAdapter.detailproductViewHolder> {
    private Context mContext;
    private product2 mproduct;
    private drop2 dropParent;

    public detailproductAdapter(Context mContext) {
        this.mContext = mContext;
    }

    public detailproductAdapter() {
    }

    public void SetData(product2 selectedproduct, drop2 parent)
    {
        mproduct = selectedproduct;
        dropParent = parent;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public detailproductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.detail_product_item,parent,false);
        return new detailproductAdapter.detailproductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull detailproductViewHolder holder, int position) {
        if (mproduct == null) return;
        List<String> imageList;
        imageList = mproduct.getResourceID();
        String String_ = imageList.get(position);
            if (String_ != null) {
//            Glide.with(mContext).load(String_).into(holder.image_detailproduct);
                Picasso.get().load(String_).into(holder.image_detailproduct);
            }
    }

    @Override
    public int getItemCount() {
        if (mproduct !=null) return mproduct.getResourceID().size();
        return 0;
    }

    public class detailproductViewHolder extends RecyclerView.ViewHolder{
        ImageView image_detailproduct;
        public detailproductViewHolder(@NonNull View itemView) {
            super(itemView);
            image_detailproduct = itemView.findViewById(R.id.image_detailproduct);
        }
    }


}
