package com.example.zendi_application.searchfragment;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.zendi_application.DataManager;
import com.example.zendi_application.R;
import com.example.zendi_application.dropFragment.product_package.product2;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Locale;

public class AdapterForSuggestion extends RecyclerView.Adapter<AdapterForSuggestion.AdapterForSuggestionViewHolder> {
    private ArrayList<product2> arraylist = new ArrayList<>();
    @NonNull
    @Override
    public AdapterForSuggestionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_for_suggest_item,parent,false);
        return new AdapterForSuggestionViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterForSuggestion.AdapterForSuggestionViewHolder holder, int position) {
        product2 currentItem = arraylist.get(position);
        holder.name.setText(currentItem.getProductName());
        if (currentItem.getProductType().equals("1"))
            holder.sexType.setText("Men");
        else if (currentItem.getProductType().equals("2"))
            holder.sexType.setText("Women");
        else
            holder.sexType.setText("Both");

        holder.constraintLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Transactor.getInstance().getArrayList().add(currentItem);
                Intent intent = new Intent(v.getContext(), MyDetailProduct.class);
                v.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return arraylist.size();
    }

    public class AdapterForSuggestionViewHolder extends RecyclerView.ViewHolder {
        private TextView name;
        private TextView sexType;
        private ConstraintLayout constraintLayout;
        public AdapterForSuggestionViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.item_name_textView);
            sexType = itemView.findViewById(R.id.sex_type_textView);
            constraintLayout = itemView.findViewById(R.id.cons_represent_suggest);
        }
    }

    public void filter(String charText) {
        arraylist.clear();
        if (charText.length() == 0)
        {
            //arraylist.addAll(DataManager.listProduct);
        }
        else
        {
            for (product2 product : DataManager.listProduct) {
                if (product.getProductName().toLowerCase(Locale.getDefault()).contains(charText.toLowerCase())) {
                    arraylist.add(product);
                }
            }
        }
        notifyDataSetChanged();
    }
}
