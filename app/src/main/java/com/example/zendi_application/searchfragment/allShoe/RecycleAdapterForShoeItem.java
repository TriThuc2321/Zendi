package com.example.zendi_application.searchfragment.allShoe;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.zendi_application.R;
import com.example.zendi_application.searchfragment.ElementOfRecycModel;

import java.util.ArrayList;

public class RecycleAdapterForShoeItem extends RecyclerView.Adapter<RecycleAdapterForShoeItem.RecycleViewHolderForShoeItem> {
    private ArrayList<ShoeItemModel> elementOfRecycModelArrayList;
    public static class RecycleViewHolderForShoeItem extends RecyclerView.ViewHolder{
        public ImageView imageView;
        public TextView NameOfElement;
        public TextView Charge;
        public TextView Originals;
        public RecycleViewHolderForShoeItem(View view)
        {
            super(view);
            imageView = view.findViewById(R.id.imageView2);
            NameOfElement = view.findViewById(R.id.tenSanPham_textview2);
            Charge = view.findViewById(R.id.soTien_textview2);
            Originals = view.findViewById(R.id.originals_textview2);
        }
    }
    public RecycleAdapterForShoeItem(ArrayList<ShoeItemModel> elementOfRecycModelArrayList)
    {
        this.elementOfRecycModelArrayList = elementOfRecycModelArrayList;
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
        holder.imageView.setImageResource(currentItem.getImageRes());
        holder.Charge.setText(currentItem.getmCharge());
        holder.Originals.setText(currentItem.getmOriginals());
        holder.NameOfElement.setText(currentItem.getmName());
    }

    @Override
    public int getItemCount() {
        return elementOfRecycModelArrayList.size();
    }
}
