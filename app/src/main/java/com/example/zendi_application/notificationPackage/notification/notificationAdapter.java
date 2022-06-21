package com.example.zendi_application.notificationPackage.notification;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.zendi_application.HomeScreen;
import com.example.zendi_application.R;
import com.example.zendi_application.dropFragment.DetailDropFragment;
import com.squareup.picasso.Picasso;

import java.util.List;

public class notificationAdapter extends  RecyclerView.Adapter<notificationAdapter.notificationViewHolder>{

    private List<notification> mNoti;

    public notificationAdapter() {
    }

    public void SetData(List<notification> list)
    {
        this.mNoti = list;
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public notificationViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.notification_item,parent,false);
        return new notificationViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull notificationViewHolder holder, int position) {
        notification noti = mNoti.get(position);
        if (noti == null)
        {
            return;
        }
        holder.body_txt.setText(noti.getBody());
    }

    @Override
    public int getItemCount() {
        if (mNoti != null) return mNoti.size();
        return 0;
    }

    public class notificationViewHolder extends RecyclerView.ViewHolder{

        private TextView body_txt;


        public notificationViewHolder(@NonNull View itemView) {
            super(itemView);

            body_txt = itemView.findViewById(R.id.notification_item_txt);
        }
    }
}
