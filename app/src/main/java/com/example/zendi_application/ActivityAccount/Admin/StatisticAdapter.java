package com.example.zendi_application.ActivityAccount.Admin;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.zendi_application.DataManager;
import com.example.zendi_application.R;

import java.util.ArrayList;
import java.util.List;

import static com.example.zendi_application.DataManager.orderedList;

public class StatisticAdapter extends RecyclerView.Adapter<StatisticAdapter.ViewHolder>{

    private Context mContext;
    private List<Ordered> mlist = new ArrayList<>();

    public StatisticAdapter(Context mContext) {
        this.mContext = mContext;
    }
    public void SetData(List<Ordered> list){
        this.mlist = list;
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
        Ordered ordered = mlist.get(position);

        holder.nameTxt.setText(ordered.getName());
        holder.phoneNumberTxt.setText(ordered.getContact());
        holder.addressTxt.setText(ordered.getAddress());
        holder.emailTxt.setText(ordered.getEmail());
        holder.totalTxt.setText(ordered.getTotal());

        holder.mOrderedAdapter = new OrderedAdapter(mContext);
        holder.mOrderedAdapter.SetData(ordered.getShoeList());
        holder.mRecyclerView.setAdapter(holder.mOrderedAdapter);
        holder.mRecyclerView.setLayoutManager(new LinearLayoutManager(mContext));
    }

    @Override
    public int getItemCount() {
        return mlist.size();
    }



    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView emailTxt;
        TextView addressTxt;
        TextView nameTxt;
        TextView phoneNumberTxt;
        TextView totalTxt;
        LinearLayout infoLayout;

        RecyclerView mRecyclerView;
        OrderedAdapter mOrderedAdapter;
        boolean flag=true;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            emailTxt = itemView.findViewById(R.id.emailOrderedTxt);
            addressTxt = itemView.findViewById(R.id.addressOrderedTxt);
            nameTxt = itemView.findViewById(R.id.nameOrdered);
            phoneNumberTxt = itemView.findViewById(R.id.phoneNumberOrderedTxt);
            totalTxt = itemView.findViewById(R.id.totalOrderedTxt);
            mRecyclerView = itemView.findViewById(R.id.productOrdered);
            infoLayout = itemView.findViewById(R.id.infoOrderedLayout);

            nameTxt.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(flag){
                        infoLayout.setVisibility(View.VISIBLE);
                        flag = false;
                    }
                    else{
                        infoLayout.setVisibility(View.GONE);
                        flag = true;
                    }

                }
            });


        }
    }
}
