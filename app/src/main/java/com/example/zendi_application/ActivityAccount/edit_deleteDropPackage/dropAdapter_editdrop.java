package com.example.zendi_application.ActivityAccount.edit_deleteDropPackage;

import android.content.Context;
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
import com.example.zendi_application.addProductPackage.imageAdapter;
import com.example.zendi_application.dropFragment.drop.drop2;
import com.example.zendi_application.dropFragment.product_package.product2;

import java.util.ArrayList;
import java.util.List;

public class dropAdapter_editdrop extends RecyclerView.Adapter<dropAdapter_editdrop.dropViewHolder> {
    private edit_deleteDrop mContext;
    private List<drop2> mListdrop;
    public void SetData(List<drop2> Listdrop_, edit_deleteDrop mContext_)
    {
        this.mContext = mContext_;
        mListdrop = Listdrop_;
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public dropViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.drop_item_editdrop,parent,false);
        return new dropViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull dropViewHolder holder, int position) {
        drop2 drop_ = mListdrop.get(position);
        if (drop_ == null) return;

        Glide.with(mContext).load(drop_.getImage()).into(holder.imagedrop_editdropitem);
        holder.type_editdropitem.setText(drop_.getType());
        holder.caption_editdropitem.setText(drop_.getCaption());
        holder.status_editdropitem.setText(drop_.getStatus());
        holder.editbtn_editdrop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mContext.caption_editdrop.setText(drop_.getCaption());
                mContext.status_editdrop.setText(drop_.getStatus());
                mContext.dropnumber_editdrop.setText(drop_.getDropNumber());
                mContext.categorynumber_editdrop.setText(drop_.getCategoryNumber());
                mContext.type_editdrop.setText(drop_.getType());
                Glide.with(mContext).load(drop_.getImage()).into(mContext.image_editdrop);

                edit_deleteDrop.productAdapter_editdrop.SetData(drop_.getProductList(),mContext);
            }
        });

        holder.deletebtn_editdrop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    @Override
    public int getItemCount() {
       if (mListdrop!= null) return mListdrop.size();
       return 0;
    }

    public class dropViewHolder extends RecyclerView.ViewHolder
    {
        ImageView imagedrop_editdropitem;
        TextView type_editdropitem, caption_editdropitem, status_editdropitem;
         Button editbtn_editdrop, deletebtn_editdrop;

        public dropViewHolder(@NonNull View itemView) {
            super(itemView);
            editbtn_editdrop = itemView.findViewById(R.id.editbtn_editdropitem);
            deletebtn_editdrop = itemView.findViewById(R.id.deletebtn_editdropitem);
            type_editdropitem = itemView.findViewById(R.id.type_editdropitem);
            caption_editdropitem = itemView.findViewById(R.id.caption_editdropitem);
            status_editdropitem = itemView.findViewById(R.id.status_editdropitem);
            imagedrop_editdropitem = itemView.findViewById(R.id.image_drop_editdropitem);

        }
    }
}
