package com.example.zendi_application.ActivityAccount.Admin;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.zendi_application.R;

import java.util.ArrayList;
import java.util.List;

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
        holder.mRecyclerView.setLayoutManager(new LinearLayoutManager(mContext,RecyclerView.HORIZONTAL,false));
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

        Animation rotateRight;
        Animation rotateLeft;
        Animation topToBottom;
        Animation bottomToTop;

        LinearLayout show;
        View dropDown;

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
            show = itemView.findViewById(R.id.show_order);
            dropDown = itemView.findViewById(R.id.drop_down);

            rotateRight = AnimationUtils.loadAnimation(mContext, R.anim.rotate_right_animation);
            rotateLeft = AnimationUtils.loadAnimation(mContext, R.anim.rotate_left_animation);
            topToBottom = AnimationUtils.loadAnimation(mContext, R.anim.slide_top_to_bottom);
            bottomToTop = AnimationUtils.loadAnimation(mContext, R.anim.slide_bottom_to_top);


            rotateRight.setFillAfter(true);
            rotateLeft.setFillAfter(true);

            show.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(flag){
                        infoLayout.setVisibility(View.VISIBLE);
                        infoLayout.startAnimation(topToBottom);
                        dropDown.startAnimation(rotateRight);
                        flag = false;
                    }
                    else{
                        infoLayout.setVisibility(View.GONE);
                        dropDown.startAnimation(rotateLeft);
                        flag = true;

                    }

                }
            });


        }
    }
}
