package com.example.zendi_application.notificationPackage.order;

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
import com.example.zendi_application.shopFragment.ShoeInBag;

import java.util.ArrayList;
import java.util.List;

public class DetailOrderedAdapter_Notification extends RecyclerView.Adapter<DetailOrderedAdapter_Notification.ViewHolder>{

//    private Context mContext;
    private List<ShoeInBag> mlist = new ArrayList<>();

    public DetailOrderedAdapter_Notification() {}
    public void SetData(List<ShoeInBag> list){
        this.mlist = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View DetailOrderedAdapter_Notification = inflater.inflate(R.layout.order_product, parent, false);
        DetailOrderedAdapter_Notification.ViewHolder viewHolder = new DetailOrderedAdapter_Notification.ViewHolder(DetailOrderedAdapter_Notification);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ShoeInBag ordered = mlist.get(position);

        Glide.with(holder.shoeImg).load(ordered.getResourceID().get(0)).into(holder.shoeImg);
        holder.name.setText(ordered.getProductName());
        holder.size.setText(ordered.getShoeSize());
        holder.price.setText(new StringBuilder("$").append(ordered.getProductPrice()));

        if(ordered.getShoeAmount().compareTo("0") != 1){
            holder.amount.setText(ordered.getShoeAmount());

            holder.amount.setVisibility(View.VISIBLE);
            holder.x_ordered.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public int getItemCount() {
        if(mlist != null) return mlist.size();
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView shoeImg;
        TextView name,  size, price, amount, x_ordered;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            shoeImg = itemView.findViewById(R.id.shoe_img_ordered);
            name = itemView.findViewById(R.id.shoe_name_ordered);
            size = itemView.findViewById(R.id.shoe_size_ordered);
            price = itemView.findViewById(R.id.shoe_price_ordered);
            amount = itemView.findViewById(R.id.amount_ordered);
            x_ordered = itemView.findViewById(R.id.x_ordered);
        }
    }
}
