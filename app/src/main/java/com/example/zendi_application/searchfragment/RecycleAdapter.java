package com.example.zendi_application.searchfragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.zendi_application.R;

import java.util.ArrayList;

public class RecycleAdapter extends RecyclerView.Adapter<RecycleAdapter.RecycleViewHolder> {
    private ArrayList<ElementOfRecycModel> elementOfRecycModelArrayList;
    public static class RecycleViewHolder extends RecyclerView.ViewHolder{
        public ImageView imageView;
        public TextView NameOfElement;
        public TextView Charge;
        public TextView Originals;
        public RecycleViewHolder(View view)
        {
            super(view);
            imageView = view.findViewById(R.id.imageView);
            NameOfElement = view.findViewById(R.id.tenSanPham_textview);
            Charge = view.findViewById(R.id.soTien_textview);
            Originals = view.findViewById(R.id.originals_textview);
        }
    }
    public RecycleAdapter(ArrayList<ElementOfRecycModel> elementOfRecycModelArrayList)
    {
        this.elementOfRecycModelArrayList = elementOfRecycModelArrayList;
    }
    @Override
    public RecycleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_for_recyc,parent,false);
        RecycleViewHolder RVH = new RecycleViewHolder(view);
        return RVH;
    }

    @Override
    public void onBindViewHolder(@NonNull RecycleViewHolder holder, int position) {
        ElementOfRecycModel currentItem = elementOfRecycModelArrayList.get(position);
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
