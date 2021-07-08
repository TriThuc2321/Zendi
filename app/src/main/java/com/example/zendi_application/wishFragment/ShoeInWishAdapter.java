package com.example.zendi_application.wishFragment;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.zendi_application.DataManager;
import com.example.zendi_application.HomeScreen;
import com.example.zendi_application.R;
import com.example.zendi_application.dropFragment.DetailProductFragment;
import com.example.zendi_application.dropFragment.drop.drop2;
import com.example.zendi_application.dropFragment.product_package.product2;
import com.example.zendi_application.shopFragment.RecyclerViewClickInterface;
import com.example.zendi_application.shopFragment.ShoeInBag;
import com.example.zendi_application.shopFragment.ShoeInBagAdapter;
import com.example.zendi_application.shopFragment.ShopFragment;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.security.AccessController.getContext;

public class ShoeInWishAdapter extends RecyclerView.Adapter<ShoeInWishAdapter.ShoeInWishViewHolder> {


    ItemClickListener itemClickListener;
    List<ShoeInBag> shoeInWishList;
    TextView emptyNotify;
    public void setData(List<ShoeInBag> list){
        this.shoeInWishList = list;
        notifyDataSetChanged();
    }

    public ShoeInWishAdapter(){};
    public ShoeInWishAdapter( TextView emptyNotify,List<ShoeInBag> shoeInWishList, ItemClickListener listener) {
        this.itemClickListener = listener;
        this.shoeInWishList = shoeInWishList;
        this.emptyNotify = emptyNotify;
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
        holder.price.setText(new StringBuilder("Price: $").append(shoeInWishList.get(position).getProductPrice()));
        holder.itemView.setOnClickListener(view ->{
            itemClickListener.onItemClick(shoeInWishList.get(position));
        });
    }


    @Override
    public int getItemCount() {
        if(shoeInWishList == null || shoeInWishList.size() == 0)
        {
            emptyNotify.setVisibility(View.VISIBLE);
        }
        else
            emptyNotify.setVisibility(View.GONE);
        if(shoeInWishList != null) return shoeInWishList.size();
        return 0;
    }

    public interface ItemClickListener{
        void onItemClick(ShoeInBag shoe);
    }
    public class ShoeInWishViewHolder extends RecyclerView.ViewHolder  { //implements View.OnClickListener
        ImageView shoeimg;
        TextView name,  price;

        public ShoeInWishViewHolder(@NonNull View itemView) {
            super(itemView);
            shoeimg = itemView.findViewById(R.id.shoe_img);
            name = itemView.findViewById(R.id.shoe_name);
            price = itemView.findViewById(R.id.shoe_price);
            
    }

    }
}
