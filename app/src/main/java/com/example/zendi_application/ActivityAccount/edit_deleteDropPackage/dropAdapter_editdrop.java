package com.example.zendi_application.ActivityAccount.edit_deleteDropPackage;

import android.content.Context;
import android.content.DialogInterface;
import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.zendi_application.ActivityAccount.edit_deleteProductPackage.edit_deleteProduct;
import com.example.zendi_application.DataManager;
import com.example.zendi_application.R;
import com.example.zendi_application.addProductPackage.imageAdapter;
import com.example.zendi_application.dropFragment.drop.drop2;
import com.example.zendi_application.dropFragment.product_package.product2;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.FirebaseFirestore;

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

                edit_deleteDrop.selectedListProduct = new ArrayList<>();
                edit_deleteDrop.selectedListProductName = new ArrayList<>();
                edit_deleteDrop.selected_producidtlist = new ArrayList<>();
                edit_deleteDrop.URLimage = null;
                edit_deleteDrop.URIimage = null;
                edit_deleteDrop.selectedDrop_categoryNumber = drop_.getCategoryNumber();
                edit_deleteDrop.selectedDrop_dropNumber = drop_.getDropNumber();

                for (product2 ite : drop_.getProductList()) {
                    edit_deleteDrop.selectedListProduct.add(ite);
                    edit_deleteDrop.selectedListProductName.add(ite.getProductName());
                    edit_deleteDrop.selected_producidtlist.add(ite.getProductId());
                }

                mContext.caption_editdrop.setText(drop_.getCaption());
                mContext.status_editdrop.setText(drop_.getStatus());
                mContext.dropnumber_editdrop.setText(drop_.getDropNumber());
                mContext.categorynumber_editdrop.setText(drop_.getCategoryNumber());
                mContext.type_editdrop.setText(drop_.getType());
                Glide.with(mContext).load(drop_.getImage()).into(mContext.image_editdrop);

                edit_deleteDrop.productAdapter_editdrop.SetData( edit_deleteDrop.selectedListProduct,mContext);
            }
        });

        holder.deletebtn_editdrop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which) {
                            case DialogInterface.BUTTON_POSITIVE: { //Yes button clicked
                                String olddrop = "Drop_" + drop_.getCategoryNumber() + "_" + drop_.getDropNumber() + "/";
                                DataManager.listDrop.remove(drop_);
                                FirebaseFirestore db = FirebaseFirestore.getInstance();
                                db.collection("Collection/").document(olddrop)
                                        .delete()
                                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                                            @Override
                                            public void onSuccess(Void aVoid) {
                                                Toast.makeText(mContext,"Drop deleted !",Toast.LENGTH_SHORT);
                                                edit_deleteDrop.dropAdapter_editdrop.SetData(DataManager.listDrop,mContext);
                                                edit_deleteDrop.dropAdapter_editdrop.notifyDataSetChanged();
                                                mContext.caption_editdrop.setText(null);
                                                mContext.status_editdrop.setText(null);
                                                mContext.dropnumber_editdrop.setText(null);
                                                mContext.categorynumber_editdrop.setText(null);
                                                mContext.type_editdrop.setText(null);
                                                edit_deleteDrop.URLimage = null;
                                                edit_deleteDrop.URIimage = null;
                                                Glide.with(mContext).load( edit_deleteDrop.URLimage).into(mContext.image_editdrop);
                                            }
                                        })
                                        .addOnFailureListener(new OnFailureListener() {
                                            @Override
                                            public void onFailure(@NonNull Exception e) {
                                            }
                                        });
                                return;
                            }


                            case DialogInterface.BUTTON_NEGATIVE:
                                return;


                            //No button clicked

                        }
                    }
                };

                AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
                builder.setMessage("Are you sure delete this drop ?").setPositiveButton("Yes", dialogClickListener)
                        .setNegativeButton("No", dialogClickListener).show();
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
