package com.example.zendi_application.dropFragment.category_drop;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.zendi_application.R;
import com.example.zendi_application.dropFragment.drop.dropAdapter;

import java.util.List;

public class categoryAdapter extends RecyclerView.Adapter<categoryAdapter.categoryViewHolder>{
    private Context mContext;
    private List<category> mcategorylist;

    public categoryAdapter(Context mContext) {
        this.mContext = mContext;
    }
    public void SetData(List<category> list)
    {
        this.mcategorylist = list;
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public categoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.drop_category,parent,false);

        return new categoryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull categoryViewHolder holder, int position) {
        category mcategory = mcategorylist.get(position);
        if (mcategory == null)
        {
            return;
        }

        // LinearLayourManager
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mContext,RecyclerView.HORIZONTAL,false);
        holder.rcvDrop.setLayoutManager(linearLayoutManager);

        // khai bao adapter set du lieu
        dropAdapter DropAdapter = new dropAdapter();
        DropAdapter.SetData(mcategory.getListDrop());
        holder.rcvDrop.setAdapter(DropAdapter);

    }

    @Override
    public int getItemCount() {
        if (mcategorylist != null) return mcategorylist.size();
        return 0;
    }

    public class categoryViewHolder extends RecyclerView.ViewHolder{
        private RecyclerView rcvDrop;

        public categoryViewHolder(@NonNull View itemView) {
            super(itemView);

            rcvDrop = itemView.findViewById(R.id.recycleview_drop);
        }
    }
}
