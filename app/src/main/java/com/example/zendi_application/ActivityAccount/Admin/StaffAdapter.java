package com.example.zendi_application.ActivityAccount.Admin;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.zendi_application.ActivityAccount.User;
import com.example.zendi_application.R;

import static com.example.zendi_application.ActivityAccount.Admin.StaffManager.listCustomer;
import static com.example.zendi_application.ActivityAccount.Admin.StaffManager.listStaff;
import static com.example.zendi_application.ActivityAccount.Admin.StaffManager.saveBtn;

public class StaffAdapter extends RecyclerView.Adapter<StaffAdapter.ViewHolder>{

    private Context mContext;


    public StaffAdapter(Context mContext) {
        this.mContext = mContext;
    }
    public void SetData(){
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
        User user = listStaff.get(position);
        holder.nameTxt.setText(user.getName());
        holder.phoneNumberTxt.setText(user.getPhoneNumber());
        holder.addressTxt.setText(user.getAddress());
        holder.birthdayTxt.setText(user.getDOB());

        if(user.getGender() == 0) holder.genderTxt.setText("Male");
        else if(user.getGender() == 1) holder.genderTxt.setText("Female");
        else holder.genderTxt.setText("Other");
    }

    @Override
    public int getItemCount() {
        if(listStaff == null) return 0;
        return listStaff.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView nameTxt;
        Button subtractBtn;
        TextView emailTxt;
        TextView phoneNumberTxt;
        TextView addressTxt;
        TextView birthdayTxt;
        TextView genderTxt;
        LinearLayout infoLayout;

        boolean flag=true;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            nameTxt = itemView.findViewById(R.id.name_staff);
            subtractBtn = (Button) itemView.findViewById(R.id.subtract_staff_btn);
            emailTxt = itemView.findViewById(R.id.emailStaffTxt);
            birthdayTxt = itemView.findViewById(R.id.birthdayStaffTxt);
            addressTxt = itemView.findViewById(R.id.addressStaffTxt);
            genderTxt = itemView.findViewById(R.id.genderStaffTxt);
            phoneNumberTxt = itemView.findViewById(R.id.phoneNumberStaffTxt);
            infoLayout = itemView.findViewById(R.id.infoStaffLayout);

            subtractBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    saveBtn.setVisibility(View.VISIBLE);
                    listCustomer.add(listStaff.get(getAdapterPosition()));
                    listStaff.remove(getAdapterPosition());
                    SetData();
                }
            });

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
