package com.example.zendi_application.dropFragment.drop;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.zendi_application.R;

import java.util.List;

public class dropAdapter extends  RecyclerView.Adapter<dropAdapter.dropViewHolder>{
    private List<drop> mdrop;
    public void SetData(List<drop> list)
    {
        this.mdrop = list;
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public dropViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.drop_item,parent,false);
        return new dropViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull dropViewHolder holder, int position) {
        drop dropp = mdrop.get(position);
        if (dropp == null)
        {
            return;
        }

        holder.imgDrop.setImageResource(dropp.getResourceId());
        holder.captionDrop.setText(dropp.getCaption());
        holder.statusDrop.setText(dropp.getSatus());
        holder.typeDrop.setText(dropp.getType());
        holder.shopbtnDrop.setText("SHOP NOW" + "");



    }

    @Override
    public int getItemCount() {
        if (mdrop != null) return mdrop.size();
        return 0;
    }

    public class dropViewHolder extends RecyclerView.ViewHolder{
        private ImageView imgDrop;
        private TextView captionDrop;
        private TextView statusDrop;
        private TextView typeDrop;
        private Button shopbtnDrop;

        public dropViewHolder(@NonNull View itemView) {
            super(itemView);

            imgDrop = itemView.findViewById(R.id.image_drop);
            captionDrop = itemView.findViewById(R.id.caption_drop);
            statusDrop = itemView.findViewById(R.id.status_drop);
            typeDrop = itemView.findViewById(R.id.type_drop);
            shopbtnDrop = itemView.findViewById(R.id.btn_drop);
        }
    }
}
