package com.example.zendi_application.searchfragment.listnavigation;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.zendi_application.R;

import java.util.ArrayList;

public class RecycleAdapterForArtical extends RecyclerView.Adapter<RecycleAdapterForArtical.RecycleViewHolderForArtical> {
    private ArrayList<ArticalModel> articalModelArrayList;
    public static class RecycleViewHolderForArtical extends RecyclerView.ViewHolder{
        public ImageView imageView;
        public TextView NameOfArtical;
        public RecycleViewHolderForArtical(View view)
        {
            super(view);
            imageView = view.findViewById(R.id.artical_image);
            NameOfArtical = view.findViewById(R.id.artical_textview);
        }
    }
    public RecycleAdapterForArtical (ArrayList<ArticalModel> articalModelArrayList)
    {
        this.articalModelArrayList = articalModelArrayList;
    }
    @Override
    public RecycleViewHolderForArtical onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_for_activity_search_listnavigation,parent,false);
        RecycleViewHolderForArtical RVH = new RecycleViewHolderForArtical(view);
        return RVH;
    }

    @Override
    public void onBindViewHolder(@NonNull RecycleViewHolderForArtical holder, int position) {
        ArticalModel currentItem = articalModelArrayList.get(position);
        holder.imageView.setImageResource(currentItem.getImage());
        holder.NameOfArtical.setText(currentItem.getTitle());
    }

    @Override
    public int getItemCount() {
        return articalModelArrayList.size();
    }
}
