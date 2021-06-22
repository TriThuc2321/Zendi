package com.example.zendi_application.ActivityAccount.edit_deleteDropPackage;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.zendi_application.R;
import com.example.zendi_application.dropFragment.drop.drop2;
import com.example.zendi_application.dropFragment.product_package.product2;

import java.util.List;

public class productAdapter_editdrop  extends RecyclerView.Adapter<productAdapter_editdrop.productlistViewHolder>{
    edit_deleteDrop mcontext;
    List<product2> mListproduct;
    public void SetData(List<product2> mListproduct_, edit_deleteDrop mContext_)
    {
        mcontext = mContext_;
        mListproduct = mListproduct_;
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public productlistViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.product_item_editdrop,parent,false);
        return new productlistViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull productlistViewHolder holder, int position) {
        product2 product_ = mListproduct.get(position);
        if (product_ != null)
        {
            Glide.with(mcontext).load(product_.getResourceID().get(0)).into(holder.imageproduct_editdropitem);
            holder.nameproduct_editdropitem.setText(product_.getProductName());
        }
    }

    @Override
    public int getItemCount() {
        if (mListproduct!= null)
            return mListproduct.size();
        return 0;
    }

    public class productlistViewHolder extends RecyclerView.ViewHolder{
        Button deletebtn_editdropitem;
        ImageView imageproduct_editdropitem;
        TextView nameproduct_editdropitem;
        public productlistViewHolder(@NonNull View itemView) {
            super(itemView);

            deletebtn_editdropitem = itemView.findViewById(R.id.deletebtn_product_editdropitem);
            imageproduct_editdropitem = itemView.findViewById(R.id.imageproduct_editdropitem);
            nameproduct_editdropitem = itemView.findViewById(R.id.nameproduct_editdropitem);


        }
    }
}
