package com.example.zendi_application.shopFragment;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;


import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.zendi_application.DataManager;
import com.example.zendi_application.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static android.widget.Toast.LENGTH_LONG;

public class ShoeInBagAdapter extends RecyclerView.Adapter<ShoeInBagAdapter.ShoeInBagViewHolder> {

    Context context;
    List<ShoeInBag> shoeInBagList;

    public void setData(List<ShoeInBag> list) {
        this.shoeInBagList = list;
        notifyDataSetChanged();
    }

    public ShoeInBagAdapter() {
    }

    ;

    public ShoeInBagAdapter(Context context, List<ShoeInBag> shoeInBagList) {
        this.context = context;
        this.shoeInBagList = shoeInBagList;
    }

    @NonNull
    @Override
    public ShoeInBagViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.shop_item, parent, false);
        return new ShoeInBagViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ShoeInBagViewHolder holder, int position) {

        Glide.with(holder.shoeimg).load(shoeInBagList.get(position).getResourceID().get(0)).into(holder.shoeimg);
        holder.name.setText(shoeInBagList.get(position).getProductName());
        holder.status.setText(shoeInBagList.get(position).getShoeStatus());
        holder.size.setText(shoeInBagList.get(position).getShoeSize());
        holder.price.setText(new StringBuilder("$").append(shoeInBagList.get(position).getProductPrice()));
        holder.amount.setText(shoeInBagList.get(position).getShoeAmount());
    }

    @Override
    public int getItemCount() {
        if (shoeInBagList != null) return shoeInBagList.size();
        return 0;
    }


    public class ShoeInBagViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView shoeimg;
        Button wishbtn;
        TextView name, status, size, amount, price;

        public ShoeInBagViewHolder(@NonNull View itemView) {
            super(itemView);
            shoeimg = itemView.findViewById(R.id.shoe_img);
            name = itemView.findViewById(R.id.shoe_name);
            status = itemView.findViewById(R.id.shoe_status);
            size = itemView.findViewById(R.id.shoe_size);
            price = itemView.findViewById(R.id.shoe_price);
            amount = itemView.findViewById(R.id.shoe_amount);
            wishbtn = itemView.findViewById(R.id.wish_btn);
            wishbtn.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            String docName = shoeInBagList.get(getAdapterPosition()).getProductId() + "_" + shoeInBagList.get(getAdapterPosition()).getShoeSize();
            FirebaseFirestore db = FirebaseFirestore.getInstance();
            Map<String, Object> s = new HashMap<>();
           s.put("ResourceID",shoeInBagList.get(getAdapterPosition()).getResourceID());
           s.put("productId",shoeInBagList.get(getAdapterPosition()).getProductId());
           s.put("productName",shoeInBagList.get(getAdapterPosition()).getProductName());
           s.put("productPrice",shoeInBagList.get(getAdapterPosition()).getProductPrice());
           s.put("shoeAmount",shoeInBagList.get(getAdapterPosition()).getShoeAmount());
           s.put("shoeStatus",shoeInBagList.get(getAdapterPosition()).getShoeStatus());
           s.put("shoeSize",shoeInBagList.get(getAdapterPosition()).getShoeSize());
            db.collection("InBag/aaa/ShoeList").document(docName)
                    .delete().addOnSuccessListener(new OnSuccessListener<Void>() {
                @Override
                public void onSuccess(Void aVoid) {
                }
            });
           shoeInBagList.remove(getAdapterPosition());
           notifyDataSetChanged();
           DataManager.shoeInWish.clear();
           DataManager.getShoeInBagFromFirestone("InWish",DataManager.shoeInWish);
           db.collection("InWish").document(docName).set(s).addOnSuccessListener(new OnSuccessListener<Void>() {
               @Override
               public void onSuccess(Void aVoid) {
               }
           });
        }
    }
}
