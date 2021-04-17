package com.example.zendi_application.dropFragment.product;

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
import java.util.zip.Inflater;

public class productAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private final int FINAL_TYPE1 = 1;
    private final int FINAL_TYPE2 = 2;
    private final int FINAL_TYPE3 = 3;

    private List<product> mlistProduct;

    public void setData(List<product> list)
    {
        this.mlistProduct = list;
        notifyDataSetChanged();
    }
    //////////////////////////
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (FINAL_TYPE1 == viewType)
        {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.collection_item_type1,parent,false);
            return  new productType1ViewHolder(view);
        }
        else if (FINAL_TYPE2 == viewType)
        {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.collection_item_type2,parent,false);
            return  new productType2ViewHolder(view);
        }
        else if (FINAL_TYPE3 == viewType)
        {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.collection_item_type3,parent,false);
            return  new productType3ViewHolder(view);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        product mproduct = mlistProduct.get(position);
        if(mproduct == null)
        {
            return;
        }
        else if (FINAL_TYPE1 == holder.getItemViewType())
        {
            productType1ViewHolder productType1ViewHolder = (productAdapter.productType1ViewHolder) holder;
            productType1ViewHolder.imgbig_type1.setImageResource(mproduct.getResourceID().get(1));
            productType1ViewHolder.imgsmall_type1.setImageResource(mproduct.getResourceID().get(0));
            //productType1ViewHolder.background_type1.setBackgroundColor(#c1b378);
            productType1ViewHolder.nameproduct_type1.setText(mproduct.getProductName());
            productType1ViewHolder.priceproduct_type1.setText(mproduct.getProductPrice());
        }
        else if (FINAL_TYPE2 == holder.getItemViewType())
        {
            productType2ViewHolder productType2ViewHolder = (productAdapter.productType2ViewHolder) holder;
            productType2ViewHolder.imgbig_type2.setImageResource(mproduct.getResourceID().get(1));
            productType2ViewHolder.imgsmall_type2.setImageResource(mproduct.getResourceID().get(0));
            //productType1ViewHolder.background_type1.setBackgroundColor(#c1b378);
            productType2ViewHolder.nameproduct_type2.setText(mproduct.getProductName());
            productType2ViewHolder.priceproduct_type2.setText(mproduct.getProductPrice());
        }
        else if (FINAL_TYPE3 == holder.getItemViewType())
        {
            productType3ViewHolder productType3ViewHolder = (productAdapter.productType3ViewHolder) holder;
            productType3ViewHolder.imgbig_type3.setImageResource(mproduct.getResourceID().get(1));
            //productType1ViewHolder.background_type1.setBackgroundColor(#c1b378);
            productType3ViewHolder.nameproduct_type3.setText(mproduct.getProductName());
            productType3ViewHolder.priceproduct_type3.setText(mproduct.getProductPrice());
        }
    }

    @Override
    public int getItemViewType(int position) {
        product mproduct = mlistProduct.get(position);
        if(mproduct.getType() == 1) {
            return 1;
        }
        else if(mproduct.getType() == 2)
        {
            return 2;
        }
        else return 3;
    }

    @Override
    public int getItemCount() {
        if (mlistProduct!= null ) return mlistProduct.size();
        return 0;
    }
    /////////////////////////
    public class productType1ViewHolder extends RecyclerView.ViewHolder{
        ImageView imgbig_type1,imgsmall_type1,background_type1;
        TextView priceproduct_type1,nameproduct_type1;
        Button btn_type1;

        public productType1ViewHolder(@NonNull View itemView) {
            super(itemView);
            imgbig_type1 = itemView.findViewById(R.id.img_collection_type1_big);
            imgsmall_type1 = itemView.findViewById(R.id.img_collection_type1_small);
            background_type1 = itemView.findViewById(R.id.background_collection_type1);
            priceproduct_type1 = itemView.findViewById(R.id.price_collection_type1);
            nameproduct_type1 = itemView.findViewById(R.id.nameproduct_collection_type1);
            btn_type1 = itemView.findViewById(R.id.btn_collection_type1);
        }
    }

    public class productType2ViewHolder extends RecyclerView.ViewHolder{
        ImageView imgbig_type2,imgsmall_type2;
        TextView priceproduct_type2,nameproduct_type2;
        Button btn_type2;
        public productType2ViewHolder(@NonNull View itemView) {
            super(itemView);
            imgbig_type2 = itemView.findViewById(R.id.img_collection_type2_big);
            imgsmall_type2 = itemView.findViewById(R.id.img_collection_type2_small);
            priceproduct_type2 = itemView.findViewById(R.id.price_collection_type2);
            nameproduct_type2 = itemView.findViewById(R.id.nameproduct_collection_type2);
            btn_type2 = itemView.findViewById(R.id.btn_collection_type2);

        }
    }
    public class productType3ViewHolder extends RecyclerView.ViewHolder{
        ImageView imgbig_type3;
        TextView priceproduct_type3,nameproduct_type3;
        Button btn_type3;
        public productType3ViewHolder(@NonNull View itemView) {
            super(itemView);
            imgbig_type3 = itemView.findViewById(R.id.img_collection_type3);
            priceproduct_type3 = itemView.findViewById(R.id.price_collection_type3);
            nameproduct_type3 = itemView.findViewById(R.id.nameproduct_collection_type3);
            btn_type3 = itemView.findViewById(R.id.btn_collection_type3);

        }
    }
}
