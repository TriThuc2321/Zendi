package com.example.zendi_application.ActivityAccount.Admin;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.zendi_application.R;

import static com.example.zendi_application.DataManager.orderedList;

public class StatisticAdapter extends RecyclerView.Adapter<StatisticAdapter.ViewHolder>{

    private Context mContext;


    public StatisticAdapter(Context mContext) {
        this.mContext = mContext;
    }
    public void SetData(){
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public StatisticAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View StatisticAdapter = inflater.inflate(R.layout.statistic_custom, parent, false);
        StatisticAdapter.ViewHolder viewHolder = new StatisticAdapter.ViewHolder(StatisticAdapter);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull StatisticAdapter.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return orderedList.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        public ViewHolder(@NonNull View itemView) {
            super(itemView);


        }
    }
}
