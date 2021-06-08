package com.example.zendi_application.ActivityAccount;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.zendi_application.R;

import java.util.List;

public class CustomerAdapter extends RecyclerView.Adapter<CustomerAdapter.ViewHolder>{

    private Context mContext;
    private List<User> listCustomers;

    public CustomerAdapter(Context mContext) {
        this.mContext = mContext;
    }
    public void SetData(List<User> customerList){
        this.listCustomers = customerList;
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
        User user = listCustomers.get(position);
        holder.nameTxt.setText(user.getName());
    }

    @Override
    public int getItemCount() {
        if(listCustomers == null) return 0;
        return listCustomers.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView nameTxt;
        Button subtractBtn;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            nameTxt = itemView.findViewById(R.id.name_customer);
            subtractBtn = (Button) itemView.findViewById(R.id.subtract_customer_btn);
        }
    }
}
