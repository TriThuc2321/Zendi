package com.example.zendi_application.dropFragment.product_package;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.zendi_application.HomeScreen;
import com.example.zendi_application.R;
import com.example.zendi_application.dropFragment.DetailDropFragment;
import com.example.zendi_application.dropFragment.DetailProductFragment;
import com.example.zendi_application.dropFragment.drop.drop2;

import java.util.List;



public class productAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private final int FINAL_TYPE1 = 1;
    private final int FINAL_TYPE2 = 2;
    private final int FINAL_TYPE3 = 3;

    private List<product2> mlistProduct;
    private Context mContext;
    private drop2 parent;

    public productAdapter(Context mContext) {
        this.mContext = mContext;
    }


    public void setData(List<product2> list,drop2 parent)
    {
        this.mlistProduct = list;
        this.parent = parent;
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
        product2 mproduct = mlistProduct.get(position);
        if(mproduct == null)
        {
            return;
        }
        else if (FINAL_TYPE1 == holder.getItemViewType())
        {
            productType1ViewHolder productType1ViewHolder = (productAdapter.productType1ViewHolder) holder;

            Glide.with(mContext).load(mproduct.getResourceID().get(1)).into(productType1ViewHolder.imgbig_type1);
            Glide.with(mContext).load(mproduct.getResourceID().get(0)).into(productType1ViewHolder.imgsmall_type1);
//            productType1ViewHolder.imgbig_type1.setImageResource(mproduct.getResourceID().get(1));
//            productType1ViewHolder.imgsmall_type1.setImageResource(mproduct.getResourceID().get(0));
            //productType1ViewHolder.background_type1.setBackgroundColor(#c1b378);
            productType1ViewHolder.nameproduct_type1.setText(mproduct.getProductName());
            productType1ViewHolder.priceproduct_type1.setText("  " + mproduct.getProductPrice() +" $");

//            Glide.with(mContext).load(mproduct.getResourceID().get(0)).into(productType1ViewHolder.imgsmall_type1);
//            Glide.with(mContext).load(mproduct.getResourceID().get(1)).into(productType1ViewHolder.imgbig_type1);

//            Set click event type1

            ((productType1ViewHolder) holder).btn_type1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    OpenDetailProductFragment(v,mproduct,parent);
                }
            });

        }
        else if (FINAL_TYPE2 == holder.getItemViewType())
        {
            productType2ViewHolder productType2ViewHolder = (productAdapter.productType2ViewHolder) holder;
            Glide.with(mContext).load(mproduct.getResourceID().get(1)).into(productType2ViewHolder.imgbig_type2);
            Glide.with(mContext).load(mproduct.getResourceID().get(0)).into(productType2ViewHolder.imgsmall_type2);
            //productType1ViewHolder.background_type1.setBackgroundColor(#c1b378);
            productType2ViewHolder.nameproduct_type2.setText(mproduct.getProductName());
            productType2ViewHolder.priceproduct_type2.setText("  " + mproduct.getProductPrice()  +" $");
//            Glide.with(mContext).load(mproduct.getResourceID().get(0)).into(productType2ViewHolder.imgsmall_type2);
//            Glide.with(mContext).load(mproduct.getResourceID().get(1)).into(productType2ViewHolder.imgbig_type2);


//            Set click event type2

            ((productType2ViewHolder) holder).btn_type2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    OpenDetailProductFragment(v,mproduct,parent);
                }
            });
        }
        else if (FINAL_TYPE3 == holder.getItemViewType())
        {
            productType3ViewHolder productType3ViewHolder = (productAdapter.productType3ViewHolder) holder;
//            productType3ViewHolder.imgbig_type3.setImageResource(mproduct.getResourceID().get(1));
            Glide.with(mContext).load(mproduct.getResourceID().get(1)).into(productType3ViewHolder.imgbig_type3);
            //productType1ViewHolder.background_type1.setBackgroundColor(#c1b378);
            productType3ViewHolder.nameproduct_type3.setText(mproduct.getProductName());
            productType3ViewHolder.priceproduct_type3.setText("  " + mproduct.getProductPrice()  +" $");

//            Glide.with(mContext).load(mproduct.getResourceID().get(1)).into(productType3ViewHolder.imgbig_type3);

//            Set click event type3

          ((productType3ViewHolder) holder).btn_type3.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View v) {
                  OpenDetailProductFragment(v,mproduct, parent);

              }
          });


        }


    }
    public void OpenDetailProductFragment(View v, product2 selected_product,drop2 parent)
    {
        AppCompatActivity activity = (AppCompatActivity) v.getContext();
        DetailProductFragment myFragment = new DetailProductFragment();
        ((DetailProductFragment)myFragment).recieveDrop(selected_product,parent);
        ((HomeScreen)activity).appBarLayout.setVisibility(View.INVISIBLE);
        ((HomeScreen)activity).mNavigationView.setVisibility(View.INVISIBLE);
        activity.getSupportFragmentManager().beginTransaction().replace(R.id.home_screen, myFragment).addToBackStack(null).commit();
    }

    @Override
    public int getItemViewType(int position) {
        product2 mproduct = mlistProduct.get(position);
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
