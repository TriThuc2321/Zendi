package com.example.zendi_application.ActivityAccount.Admin;

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

public class OrderedAdapter extends RecyclerView.Adapter<OrderedAdapter.ViewHolder> {

    private Context mContext;
    private List<ShoeInBag> mlist = new ArrayList<>();

    public OrderedAdapter(Context mContext) {
        this.mContext = mContext;
    }
    public void SetData(List<ShoeInBag> list){
        this.mlist = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View OrderedAdapter = inflater.inflate(R.layout.order_product, parent, false);
        OrderedAdapter.ViewHolder viewHolder = new OrderedAdapter.ViewHolder(OrderedAdapter);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ShoeInBag ordered = mlist.get(position);

        Glide.with(holder.shoeImg).load(ordered.getResourceID().get(0)).into(holder.shoeImg);
        holder.name.setText(ordered.getProductName());
        holder.size.setText(ordered.getShoeSize());
        holder.price.setText(new StringBuilder("$").append(ordered.getProductPrice()));
    }

    @Override
    public int getItemCount() {
        if(mlist != null) return mlist.size();
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView shoeImg;
        TextView name,  size, price;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            shoeImg = itemView.findViewById(R.id.shoe_img_ordered);
            name = itemView.findViewById(R.id.shoe_name_ordered);
            size = itemView.findViewById(R.id.shoe_size_ordered);
            price = itemView.findViewById(R.id.shoe_price_ordered);
        }
    }
}
