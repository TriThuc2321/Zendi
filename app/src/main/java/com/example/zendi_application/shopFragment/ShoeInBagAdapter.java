package com.example.zendi_application.shopFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.zendi_application.R;

import java.util.List;

public class ShoeInBagAdapter extends RecyclerView.Adapter<ShoeInBagAdapter.ShoeInBagViewHolder> {

    private List<ShoeInBag> shoeInBagList;
    public void setData(List<ShoeInBag> list){
        this.shoeInBagList = list;
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public ShoeInBagViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.shop_item,parent,false);
        return new ShoeInBagViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ShoeInBagViewHolder holder, int position) {

        holder.shoeimg.setImageResource(shoeInBagList.get(position).getResourceID().get(1));
        holder.name.setText(shoeInBagList.get(position).getProductName());
        holder.status.setText(shoeInBagList.get(position).getShoeStatus());
        holder.size.setText(shoeInBagList.get(position).getShoeSize());
        holder.price.setText(new StringBuilder("$").append(shoeInBagList.get(position).getProductPrice()));
        holder.amount.setText(shoeInBagList.get(position).getShoeAmount());
    }

    @Override
    public int getItemCount() {
        if(shoeInBagList != null) return shoeInBagList.size();
        return 0;
    }
    public class ShoeInBagViewHolder extends RecyclerView.ViewHolder {
        ImageView shoeimg;
        TextView name, status, size, amount, price;
        public ShoeInBagViewHolder(@NonNull View itemView) {
            super(itemView);

            shoeimg = itemView.findViewById(R.id.shoe_img);
            name = itemView.findViewById(R.id.shoe_name);
            status = itemView.findViewById(R.id.shoe_status);
            size = itemView.findViewById(R.id.shoe_size);
            price = itemView.findViewById(R.id.shoe_price);
            amount = itemView.findViewById(R.id.shoe_amount);
        }
    }
}
