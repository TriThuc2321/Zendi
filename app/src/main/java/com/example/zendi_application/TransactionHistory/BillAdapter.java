package com.example.zendi_application.TransactionHistory;

import android.content.Context;
import android.content.Intent;
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

import com.example.zendi_application.ActivityAccount.Admin.Statistic.DetailOrdered;
import com.example.zendi_application.ActivityAccount.Admin.Statistic.Ordered;
import com.example.zendi_application.ActivityAccount.Admin.Statistic.OrderedAdapter;
import com.example.zendi_application.R;

import java.util.ArrayList;
import java.util.List;

public class BillAdapter extends RecyclerView.Adapter<BillAdapter.ViewHolder>{

    private Context mContext;
    private List<Ordered> mlist = new ArrayList<>();

    public BillAdapter(Context mContext) {
        this.mContext = mContext;
    }
    public void SetData(List<Ordered> list){
        this.mlist = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public BillAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View StatisticAdapter = inflater.inflate(R.layout.statistic_custom, parent, false);
        BillAdapter.ViewHolder viewHolder = new BillAdapter.ViewHolder(StatisticAdapter);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull BillAdapter.ViewHolder holder, int position) {
        Ordered ordered = mlist.get(position);

        holder.nameTxt.setText(ordered.getName().trim());
        holder.phoneNumberTxt.setText(ordered.getContact());
        holder.addressTxt.setText(ordered.getAddress());
        holder.emailTxt.setText(ordered.getEmail());
        holder.totalTxt.setText(ordered.getTotal());
        holder.billDateTxt.setText(ordered.getBillDate());

        String a = ordered.getBillStatus();
        if(a.compareTo("0") == 0) holder.billStatusTxt.setText("Not yet delivered");
        else if(a.compareTo("1") == 0) holder.billStatusTxt.setText("Delivered");

        holder.mOrderedAdapter = new DetailBillAdapter(mContext);
        holder.mOrderedAdapter.SetData(ordered.getShoeList());
        holder.mRecyclerView.setAdapter(holder.mOrderedAdapter);
        holder.mRecyclerView.setLayoutManager(new LinearLayoutManager(mContext,RecyclerView.HORIZONTAL,false));

        holder.detailOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, DetailOrdered.class);
                intent.putExtra("id", ordered.getBillId());
                mContext.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        if(mlist == null) return 0;
        return mlist.size();
    }



    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView emailTxt;
        TextView addressTxt;
        TextView nameTxt;
        TextView phoneNumberTxt;
        TextView totalTxt;
        TextView billStatusTxt;
        TextView billDateTxt;
        LinearLayout infoLayout;
        View detailOrder;

        Animation rotateRight;
        Animation rotateLeft;
        Animation topToBottom;
        Animation bottomToTop;

        LinearLayout show;
        View dropDown;

        RecyclerView mRecyclerView;
        DetailBillAdapter mOrderedAdapter;
        boolean flag=true;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            emailTxt = itemView.findViewById(R.id.emailOrderedTxt);
            addressTxt = itemView.findViewById(R.id.addressOrderedTxt);
            nameTxt = itemView.findViewById(R.id.nameOrdered);
            phoneNumberTxt = itemView.findViewById(R.id.phoneNumberOrderedTxt);
            totalTxt = itemView.findViewById(R.id.totalOrderedTxt);
            billDateTxt = itemView.findViewById(R.id.billDateOrderedTxt);
            billStatusTxt = itemView.findViewById(R.id.billStatusOrderedTxt);

            mRecyclerView = itemView.findViewById(R.id.productOrdered);
            infoLayout = itemView.findViewById(R.id.infoOrderedLayout);
            show = itemView.findViewById(R.id.show_order);
            dropDown = itemView.findViewById(R.id.drop_down);
            detailOrder = itemView.findViewById(R.id.detailOrdered);
            detailOrder.setVisibility(View.GONE);

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