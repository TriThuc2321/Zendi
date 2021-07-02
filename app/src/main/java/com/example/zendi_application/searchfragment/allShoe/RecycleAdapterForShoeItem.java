package com.example.zendi_application.searchfragment.allShoe;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.zendi_application.ActivityAccount.LoginRegisterActivity;
import com.example.zendi_application.DataManager;
import com.example.zendi_application.R;
import com.example.zendi_application.dropFragment.product_package.product2;
import com.example.zendi_application.searchfragment.MyDetailProduct;
import com.example.zendi_application.searchfragment.Transactor;
import com.example.zendi_application.shopFragment.ShoeInBag;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RecycleAdapterForShoeItem extends RecyclerView.Adapter<RecycleAdapterForShoeItem.RecycleViewHolderForShoeItem> {
    private ArrayList<ShoeItemModel> elementOfRecycModelArrayList = new ArrayList<>();
    private List<product2> listProduct = new ArrayList<>();
    public static class RecycleViewHolderForShoeItem extends RecyclerView.ViewHolder{
        public ImageView imageView;
        public ImageView heartView;
        public TextView NameOfElement;
        public TextView Charge;
        public ConstraintLayout constraintLayout;
        public RecycleViewHolderForShoeItem(View view)
        {
            super(view);
            imageView = view.findViewById(R.id.imageView2);
            heartView = view.findViewById(R.id.heart_imageView);
            NameOfElement = view.findViewById(R.id.tenSanPham_textview2);
            Charge = view.findViewById(R.id.soTien_textview2);
        }
    }
    public RecycleAdapterForShoeItem(MyEnum.Brand brand,MyEnum.Sex sex)
    {
        String brandstring;
        switch (brand)
        {
            case NIKE:brandstring = "NIKE";break;
            case PUMA:brandstring = "PUMA";break;
            case CONVERSE:brandstring = "CONVERSE";break;
            case NEW_BALANCE:brandstring = "NEW BALANCE";break;
            case VANS:brandstring = "VANS";break;
            case ADDIDAS:brandstring = "ADIDAS";break;
            case REEBOOK:brandstring = "REEBOOK";break;
            default: brandstring = "ALL";
        }

        String sexstring;
        switch (sex)
        {
            case MEN: sexstring = "1";break;
            default: sexstring = "2";
        }
        for(product2 product : DataManager.listProduct)
        {
            if ((product.getProductType().equals(sexstring) || product.getProductType().equals("3")) && (brandstring.equals("ALL")||brandstring.equals(product.getProductBrand())))
            {
                listProduct.add(product);
                int index = Transactor.ExistInShoeWish(product);
                if (index != -1)
                {
                    elementOfRecycModelArrayList.add(new ShoeItemModel(true,index));
                }
                else
                {
                    elementOfRecycModelArrayList.add(new ShoeItemModel(false,index));
                }
            }
        }
    }
    @Override
    public RecycleViewHolderForShoeItem onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_view_for_all_shoe_item,parent,false);
        RecycleViewHolderForShoeItem RVH = new RecycleViewHolderForShoeItem(view);
        return RVH;
    }

    @Override
    public void onBindViewHolder(@NonNull RecycleViewHolderForShoeItem holder, int position) {
        ShoeItemModel currentItem = elementOfRecycModelArrayList.get(position);
        product2 currentItemProDuct = listProduct.get(position);
        if (currentItem.isLike())
            holder.heartView.setImageResource(R.drawable.ic_baseline_favorite_24);
        else
            holder.heartView.setImageResource(R.drawable.ic_baseline_favorite_border_24);
        Picasso.get().load(currentItemProDuct.getResourceID().get(0)).into(holder.imageView);
        holder.Charge.setText(currentItemProDuct.getProductPrice());
        holder.NameOfElement.setText(currentItemProDuct.getProductName());
        holder.heartView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (DataManager.host == null || DataManager.host.getId() == null)
                {
                    Toast.makeText(view.getContext(),"Please sign in",Toast.LENGTH_LONG).show();
                }
                else {
                    if (currentItem.isLike()) {
                        currentItem.setLike(false);

                        String docName = listProduct.get(position).getProductId();
                        FirebaseFirestore db = FirebaseFirestore.getInstance();
                        db.collection("InWish/" + DataManager.host.getId() + "/ShoeinWish").document(docName)
                                .delete().addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                            }
                        });

                        int currentIndex = currentItem.getIndexInShoeWish();
                        DataManager.shoeInWish.remove(currentIndex);
                        currentItem.setIndexInShoeWish(-1);
                        for (int i = 0; i < elementOfRecycModelArrayList.size(); i++) {
                            int oldIndexInWishList = elementOfRecycModelArrayList.get(i).getIndexInShoeWish();
                            if (oldIndexInWishList > currentIndex) {
                                elementOfRecycModelArrayList.get(i).setIndexInShoeWish(oldIndexInWishList - 1);
                            }
                        }

                        DataManager.shoeInWishAdapter.notifyDataSetChanged();


                        holder.heartView.setImageResource(R.drawable.ic_baseline_favorite_border_24);
                    } else {
                        String docName = listProduct.get(position).getProductId();
                        FirebaseFirestore db = FirebaseFirestore.getInstance();
                        Map<String, Object> s = new HashMap<>();
                        s.put("ResourceID", listProduct.get(position).getResourceID());
                        s.put("productId", listProduct.get(position).getProductId());
                        s.put("productName", listProduct.get(position).getProductName());
                        s.put("productPrice", listProduct.get(position).getProductPrice());
                        s.put("shoeAmount", "1");
                        s.put("shoeSize", "5 UK");
//                    db.collection("InBag/aaa/ShoeList").document(docName)
//                            .delete().addOnSuccessListener(new OnSuccessListener<Void>() {
//                        @Override
//                        public void onSuccess(Void aVoid) {
//                        }
//                    });

                        DataManager.shoeInWish.add((new ShoeInBag(currentItemProDuct.getProductId(),
                                currentItemProDuct.getProductName(),
                                currentItemProDuct.getProductPrice(),
                                currentItemProDuct.getProductBrand(),
                                currentItemProDuct.getProductType(),
                                currentItemProDuct.getResourceID(),
                                currentItemProDuct.getRemainingAmount(),
                                currentItemProDuct.getType(), "5 UK", "1")));
                        DataManager.shoeInWishAdapter.notifyDataSetChanged();
                        db.collection("InWish/" + DataManager.host.getId() + "/ShoeinWish").document(docName).set(s).addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                            }
                        });

                        currentItem.setLike(true);
                        currentItem.setIndexInShoeWish(DataManager.shoeInWish.size() - 1);

                        holder.heartView.setImageResource(R.drawable.ic_baseline_favorite_24);
                    }
                }
            }
        });
        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Transactor.getInstance().getArrayList().add(currentItemProDuct);
                Intent intent = new Intent(v.getContext(), MyDetailProduct.class);
                v.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return elementOfRecycModelArrayList.size();
    }
}
