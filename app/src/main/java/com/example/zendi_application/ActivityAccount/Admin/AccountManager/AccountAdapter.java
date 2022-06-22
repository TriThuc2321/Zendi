package com.example.zendi_application.ActivityAccount.Admin.AccountManager;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.zendi_application.ActivityAccount.Admin.Statistic.DetailOrdered;
import com.example.zendi_application.ActivityAccount.User;
import com.example.zendi_application.R;

import java.util.ArrayList;
import java.util.List;

public class AccountAdapter extends RecyclerView.Adapter<AccountAdapter.ViewHolder>{

    private Context mContext;
    private List<User> mlist = new ArrayList<>();

    public AccountAdapter(Context mContext) {
        this.mContext = mContext;
    }
    public void SetData(List<User> list){
        this.mlist = list;
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
        User user = mlist.get(position);
        holder.nameTxt.setText(user.getName());
        holder.emailTxt.setText(user.getEmail());
        holder.phoneNumberTxt.setText(user.getPhoneNumber());
        holder.addressTxt.setText(user.getAddress());
        holder.birthdayTxt.setText(user.getDOB());

        if(user.getGender() == 0) holder.genderTxt.setText("Male");
        else if(user.getGender() == 1) holder.genderTxt.setText("Female");
        else holder.genderTxt.setText("Other");

        holder.edtBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext, EditAccountActivity.class);
                intent.putExtra("id",user.getId());
                mContext.startActivity(intent);
            }
        });

        holder.deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ConfirmDeleteDialog confirmDialog = new ConfirmDeleteDialog(mContext, user.getId());
                confirmDialog.show();
            }
        });
    }

    @Override
    public int getItemCount() {
        if(mlist == null) return 0;
        return mlist.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView nameTxt;
        TextView emailTxt;
        TextView phoneNumberTxt;
        TextView addressTxt;
        TextView birthdayTxt;
        TextView genderTxt;
        LinearLayout infoLayout;
        Button edtBtn;
        Button deleteBtn;

        boolean flag=true;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            nameTxt = itemView.findViewById(R.id.name_staff);
            emailTxt = itemView.findViewById(R.id.emailStaffTxt);
            birthdayTxt = itemView.findViewById(R.id.birthdayStaffTxt);
            addressTxt = itemView.findViewById(R.id.addressStaffTxt);
            genderTxt = itemView.findViewById(R.id.genderStaffTxt);
            phoneNumberTxt = itemView.findViewById(R.id.phoneNumberStaffTxt);
            infoLayout = itemView.findViewById(R.id.infoStaffLayout);
            edtBtn = itemView.findViewById(R.id.edit_account);
            deleteBtn = itemView.findViewById(R.id.delete_account);

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
