package com.example.zendi_application.ActivityAccount;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.zendi_application.R;

import java.util.List;

import static com.example.zendi_application.ActivityAccount.StaffManager.listCustomer;
import static com.example.zendi_application.ActivityAccount.StaffManager.listStaff;
import static com.example.zendi_application.ActivityAccount.StaffManager.saveBtn;

public class CustomerAdapter extends RecyclerView.Adapter<CustomerAdapter.ViewHolder>{

    private Context mContext;

    public CustomerAdapter(Context mContext) {
        this.mContext = mContext;
    }
    public void SetData(){
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View customerView = inflater.inflate(R.layout.customer_custom, parent, false);
        CustomerAdapter.ViewHolder viewHolder = new CustomerAdapter.ViewHolder(customerView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        User user = listCustomer.get(position);
        holder.nameTxt.setText(user.getName());
        holder.emailTxt.setText(user.getEmail());
        holder.phoneNumberTxt.setText(user.getPhoneNumber());
        holder.addressTxt.setText(user.getAddress());
        holder.birthdayTxt.setText(user.getDOB());

        if(user.getGender() == 0) holder.genderTxt.setText("Male");
        else if(user.getGender() == 1) holder.genderTxt.setText("Female");
        else holder.genderTxt.setText("Other");
    }

    @Override
    public int getItemCount() {
        if(listCustomer == null) return 0;
        return listCustomer.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
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

            nameTxt = itemView.findViewById(R.id.name_customer);
            subtractBtn = (Button) itemView.findViewById(R.id.subtract_customer_btn);
            emailTxt = itemView.findViewById(R.id.emailCustomerTxt);
            birthdayTxt = itemView.findViewById(R.id.birthdayCustomerTxt);
            addressTxt = itemView.findViewById(R.id.addressCustomerTxt);
            genderTxt = itemView.findViewById(R.id.genderCustomerTxt);
            phoneNumberTxt = itemView.findViewById(R.id.phoneNumberCustomerTxt);

            infoLayout = itemView.findViewById(R.id.infoCustomerLayout);

            subtractBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    subtractBtn.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            saveBtn.setVisibility(View.VISIBLE);
                            listStaff.add(listCustomer.get(getAdapterPosition()));
                            listCustomer.remove(getAdapterPosition());
                            SetData();
                        }
                    });
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
