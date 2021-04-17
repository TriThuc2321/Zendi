package com.example.zendi_application.dropFragment.drop;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.zendi_application.HomeScreen;
import com.example.zendi_application.R;
import com.example.zendi_application.dropFragment.DetailDropFragment;
import com.example.zendi_application.dropFragment.category_drop.testclass;
import com.example.zendi_application.dropFragment.collection_drop;
import com.example.zendi_application.dropFragment.product.product;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
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

        holder.shopbtnDrop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int a = 5;
//                Intent intent = new Intent(v.getContext(), collection_drop.class);
//                Bundle bundle = new Bundle();
//                //bundle.putInt("data", dropp.getResourceId());
//                bundle.putParcelable("data",dropp);
//                intent.putExtras(bundle);
//                v.getContext().startActivity(intent);
//                // ((HomeScreen) v.getContext()).OpenDetailDropFragment(dropp);
                AppCompatActivity activity = (AppCompatActivity) v.getContext();
                DetailDropFragment myFragment = new DetailDropFragment();
                ((DetailDropFragment)myFragment).recieveDrop(dropp);
                ((HomeScreen)activity).appBarLayout.setVisibility(View.INVISIBLE);
                activity.getSupportFragmentManager().beginTransaction().replace(R.id.home_screen, myFragment).addToBackStack(null).commit();
            }
        });



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
