package com.example.zendi_application.wishFragment;

import static android.widget.Toast.LENGTH_LONG;

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
    DeleteFavListener deleteFavListener;
    List<ShoeInBag> shoeInWishList;
    TextView emptyNotify;
    ImageView deleteFavBtn;
    public void setData(List<ShoeInBag> list){
        this.shoeInWishList = list;
        notifyDataSetChanged();
    }

    public ShoeInWishAdapter(){};
    public ShoeInWishAdapter(ImageView deleteFavBtn, TextView emptyNotify,List<ShoeInBag> shoeInWishList,DeleteFavListener deleteFavListener, ItemClickListener listener) {
        this.itemClickListener = listener;
        this.deleteFavListener = deleteFavListener;
        this.shoeInWishList = shoeInWishList;
        this.emptyNotify = emptyNotify;
        this.deleteFavBtn = deleteFavBtn;
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
        holder.deleteFav.setOnClickListener(view -> {
            deleteFavListener.onDeleteFavBtnClick(shoeInWishList.get(position));
        });

    }
    public  void deleteItem(final ShoeInBag shoe){
        String docName = shoe.getProductId() + "_" + shoe.getShoeSize();
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection("InWish/"+DataManager.host.getId()+"/ShoeinWish").document(docName)
                .delete().addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
            }
        });
    }

    public interface DeleteFavListener{
        void onDeleteFavBtnClick(ShoeInBag shoe);
    }
    public interface ItemClickListener{
        void onItemClick(ShoeInBag shoe);
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



    public class ShoeInWishViewHolder extends RecyclerView.ViewHolder  { //implements View.OnClickListener
        ImageView shoeimg;
        TextView name,  price;
        ImageView deleteFav;

        public ShoeInWishViewHolder(@NonNull View itemView) {
            super(itemView);
            shoeimg = itemView.findViewById(R.id.shoe_img);
            name = itemView.findViewById(R.id.shoe_name);
            price = itemView.findViewById(R.id.shoe_price);
            deleteFav = itemView.findViewById(R.id.btn_delete_fav);
            
    }

    }
}
