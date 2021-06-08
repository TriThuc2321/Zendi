package com.example.zendi_application.ActivityAccount;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.zendi_application.R;
import com.google.gson.internal.$Gson$Preconditions;

import java.util.ArrayList;
import java.util.List;

public class StaffAdapter extends RecyclerView.Adapter<StaffAdapter.ViewHolder>{

    private Context mContext;
    private List<User> listStaffs;

    public StaffAdapter(Context mContext) {
        this.mContext = mContext;
    }
    public void SetData(List<User> staffList){
        this.listStaffs = staffList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View staffView = inflater.inflate(R.layout.staff_custom, parent, false);
        ViewHolder viewHolder = new ViewHolder(staffView);
        return viewHolder;

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        User user = listStaffs.get(position);
        holder.nameTxt.setText(user.getName());
    }

    @Override
    public int getItemCount() {
        if(listStaffs == null) return 0;
        return listStaffs.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView nameTxt;
        Button subtractBtn;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            nameTxt = itemView.findViewById(R.id.name_staff);
            subtractBtn = (Button) itemView.findViewById(R.id.subtract_staff_btn);

            subtractBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    listStaffs.remove(getAdapterPosition());
                    SetData(listStaffs);
                }
            });
        }
    }
}
