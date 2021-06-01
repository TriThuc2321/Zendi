package com.example.zendi_application.wishFragment;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.zendi_application.DataManager;
import com.example.zendi_application.R;
import com.example.zendi_application.shopFragment.ShoeInBag;
import com.example.zendi_application.shopFragment.ShoeInBagAdapter;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ShoeInWishAdapter extends RecyclerView.Adapter<ShoeInWishAdapter.ShoeInWishViewHolder> {

    Context context;
    List<ShoeInBag> shoeInWishList;
    public void setData(List<ShoeInBag> list){
        this.shoeInWishList = list;
        notifyDataSetChanged();
    }

    public ShoeInWishAdapter(){};
    public ShoeInWishAdapter(Context context, List<ShoeInBag> shoeInWishList) {
        this.context = context;
        this.shoeInWishList = shoeInWishList;
    }
    @NonNull
    @Override
    public ShoeInWishViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.wish_item,parent,false);
        return new ShoeInWishAdapter.ShoeInWishViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ShoeInWishViewHolder holder, int position) {

        Glide.with(holder.shoeimg).load(shoeInWishList.get(position).getResourceID().get(0)).into(holder.shoeimg);
        holder.name.setText(shoeInWishList.get(position).getProductName());
        holder.status.setText(shoeInWishList.get(position).getShoeStatus());
        holder.size.setText(shoeInWishList.get(position).getShoeSize());
        holder.price.setText(new StringBuilder("$").append(shoeInWishList.get(position).getProductPrice()));
        holder.amount.setText(shoeInWishList.get(position).getShoeAmount());
    }


    @Override
    public int getItemCount() {
        if(shoeInWishList != null) return shoeInWishList.size();
        return 0;
    }

    public class ShoeInWishViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView shoeimg;
        TextView name, status, size, price, amount;
        Button shopBtn;
        public ShoeInWishViewHolder(@NonNull View itemView) {
            super(itemView);
            shoeimg = itemView.findViewById(R.id.shoe_img);
            name = itemView.findViewById(R.id.shoe_name);
            status = itemView.findViewById(R.id.shoe_status);
            size = itemView.findViewById(R.id.shoe_size);
            price = itemView.findViewById(R.id.shoe_price);
            amount = itemView.findViewById(R.id.shoe_amount);
            shopBtn = itemView.findViewById(R.id.shop_btn);
            shopBtn.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            String docName = shoeInWishList.get(getAdapterPosition()).getProductId() + "_" + shoeInWishList.get(getAdapterPosition()).getShoeSize() ;
            FirebaseFirestore db = FirebaseFirestore.getInstance();
            Map<String, Object> s = new HashMap<>();
            s.put("ResourceID",shoeInWishList.get(getAdapterPosition()).getResourceID());
            s.put("productId",shoeInWishList.get(getAdapterPosition()).getProductId());
            s.put("productName",shoeInWishList.get(getAdapterPosition()).getProductName());
            s.put("productPrice",shoeInWishList.get(getAdapterPosition()).getProductPrice());
            s.put("shoeAmount",shoeInWishList.get(getAdapterPosition()).getShoeAmount());
            s.put("shoeStatus",shoeInWishList.get(getAdapterPosition()).getShoeStatus());
            s.put("shoeSize",shoeInWishList.get(getAdapterPosition()).getShoeSize());
            db.collection("InWish").document(shoeInWishList.get(getAdapterPosition()).getProductId())
                    .delete().addOnSuccessListener(new OnSuccessListener<Void>() {
                @Override
                public void onSuccess(Void aVoid) {
                }
            });
            shoeInWishList.remove(getAdapterPosition());
            notifyDataSetChanged();
            DataManager.list.clear();
            DataManager.getShoeInBagFromFirestone("InBag/aaa/ShoeList",DataManager.list);
            db.collection("InBag/aaa/ShoeList").document(docName).set(s).addOnSuccessListener(new OnSuccessListener<Void>() {
                @Override
                public void onSuccess(Void aVoid) {
                }
            });
        }
    }
}
