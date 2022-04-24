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
import com.google.android.gms.tasks.OnFailureListener;
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
    TextView emptyNotify;

    public void setData(List<ShoeInBag> list) {
        this.shoeInBagList = list;
        notifyDataSetChanged();
    }

    public ShoeInBagAdapter() {
    }
    public ShoeInBagAdapter(TextView emptyNotify)
    {
        this.emptyNotify = emptyNotify;
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
        holder.size.setText(shoeInBagList.get(position).getShoeSize());
        holder.price.setText(new StringBuilder("Price: $").append(shoeInBagList.get(position).getProductPrice()));
        holder.amount.setText(shoeInBagList.get(position).getShoeAmount());
    }
    @Override
    public int getItemCount() {
        if(shoeInBagList == null || shoeInBagList.size() == 0)
        {
            emptyNotify.setVisibility(View.VISIBLE);
        }
        else
            emptyNotify.setVisibility(View.GONE);
        if (shoeInBagList != null) return shoeInBagList.size();
        return 0;
    }


    public class ShoeInBagViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView shoeimg;
        Button wishbtn;
        TextView name, size, amount, price;

        public ShoeInBagViewHolder(@NonNull View itemView) {
            super(itemView);
            shoeimg = itemView.findViewById(R.id.shoe_img);
            name = itemView.findViewById(R.id.shoe_name);
            size = itemView.findViewById(R.id.shoe_size);
            price = itemView.findViewById(R.id.shoe_price);
            amount = itemView.findViewById(R.id.shoe_amount);
            wishbtn = itemView.findViewById(R.id.wish_btn);
            wishbtn.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {

            String docName = shoeInBagList.get(getAdapterPosition()).getProductId() + "_" + shoeInBagList.get(getAdapterPosition()).getShoeSize();
            String docName2 = shoeInBagList.get(getAdapterPosition()).getProductId() ;
            FirebaseFirestore db = FirebaseFirestore.getInstance();
            Integer test1 = 0;
            for (ShoeInBag ite : DataManager.shoeInWish)
            {
                // Process add the shoe added
                if (ite.getProductId().compareTo(shoeInBagList.get(getAdapterPosition()).getProductId()) == 0  )
                {
                    db.collection("InBag/" +DataManager.host.getId() + "/ShoeList").document(docName)
                            .delete().addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {

                        }
                    });
                    DataManager.list.remove(getAdapterPosition());
                    DataManager.shoeInWishAdapter.notifyDataSetChanged();
                    DataManager.shoeInBagAdapter.notifyDataSetChanged();
                    test1 = 1;
                    break;
                }
            }
            if(test1 == 0){
                Map<String, Object> s = new HashMap<>();
                s.put("ResourceID",shoeInBagList.get(getAdapterPosition()).getResourceID());
                s.put("productId",shoeInBagList.get(getAdapterPosition()).getProductId());
                s.put("productName",shoeInBagList.get(getAdapterPosition()).getProductName());
                s.put("productPrice",shoeInBagList.get(getAdapterPosition()).getProductPrice());
                s.put("shoeAmount",shoeInBagList.get(getAdapterPosition()).getShoeAmount());
                s.put("shoeSize",shoeInBagList.get(getAdapterPosition()).getShoeSize());
                s.put("remainingAmount",shoeInBagList.get(getAdapterPosition()).getRemainingAmount());
                s.put("type",shoeInBagList.get(getAdapterPosition()).getType());
                s.put("productType",shoeInBagList.get(getAdapterPosition()).getProductType());
                s.put("productBrand",shoeInBagList.get(getAdapterPosition()).getProductBrand());
                db.collection("InBag/"+DataManager.host.getId()+"/ShoeList").document(docName)
                        .delete().addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                    }
                });
                DataManager.shoeInWish.add(DataManager.list.get(getAdapterPosition()));
                DataManager.list.remove(getAdapterPosition());
                DataManager.shoeInWishAdapter.notifyDataSetChanged();
                DataManager.shoeInBagAdapter.notifyDataSetChanged();
                ShopFragment.settle.setText(ShopFragment.total());
                db.collection("InWish/"+DataManager.host.getId()+"/ShoeinWish").document(docName2).set(s).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Map<String, Object> info = new HashMap<>();
                        info.put("Name", DataManager.host.getName());
                        info.put("Id", DataManager.host.getId());
                        db.collection("InWish").document(DataManager.host.getId()).set(info)
                                .addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                    }
                                }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                            }
                        });
                    }
                });
            }
        }
    }
}
