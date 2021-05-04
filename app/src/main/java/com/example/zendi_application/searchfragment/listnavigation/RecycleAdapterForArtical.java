package com.example.zendi_application.searchfragment.listnavigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.zendi_application.R;
import com.example.zendi_application.searchfragment.ElementOfRecycModel;
import com.example.zendi_application.searchfragment.RecycleAdapter;

import java.util.ArrayList;
import java.util.List;

public class RecycleAdapterForArtical extends RecyclerView.Adapter<RecycleAdapterForArtical.RecycleViewHolder2> {
    private ArrayList<ArticalModel> articalModelArrayList;
    public static class RecycleViewHolder2 extends RecyclerView.ViewHolder{
        public ImageView poster;
        public TextView NameOfElement;
        public ImageView navigation;
        public RecycleViewHolder2(View view)
        {
            super(view);
            poster = view.findViewById(R.id.artical_image);
            NameOfElement = view.findViewById(R.id.artical_textview);
            navigation = view.findViewById(R.id.artical_navigation);
        }
    }
    public RecycleAdapterForArtical(ArrayList<ArticalModel> articalModelArrayList)
    {
        this.articalModelArrayList = articalModelArrayList;
    }
    @Override
    public RecycleAdapterForArtical.RecycleViewHolder2 onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_for_activity_search_listnavigation,parent,false);
        RecycleAdapterForArtical.RecycleViewHolder2 RVH = new RecycleAdapterForArtical.RecycleViewHolder2(view);
        return RVH;
    }

    @Override
    public void onBindViewHolder(@NonNull RecycleAdapterForArtical.RecycleViewHolder2 holder, int position) {
        ArticalModel currentItem = articalModelArrayList.get(position);
        holder.poster.setImageResource(currentItem.getImage());
        holder.NameOfElement.setText(currentItem.getTitle());
        holder.navigation.setImageResource(R.drawable.ic_outline_arrow_forward_ios_24);
    }

    @Override
    public int getItemCount() {
        return articalModelArrayList.size();
    }
}
