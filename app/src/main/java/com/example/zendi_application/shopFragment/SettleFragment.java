package com.example.zendi_application.shopFragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.zendi_application.DataManager;
import com.example.zendi_application.HomeScreen;
import com.example.zendi_application.R;
import com.example.zendi_application.dropFragment.detail_product.detailproductAdapter;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.firestore.FirebaseFirestore;
import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class SettleFragment extends Fragment {

    ShoeInBag shoeInBag;
    private Button backbtn,orderbtn;
    private TextInputEditText editAddress, editContact;
    private TextView total;
    private ImageView img;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View mview = inflater.inflate(R.layout.settle_fragment, container, false);
       backbtn = mview.findViewById(R.id.backbtn113);
       orderbtn = mview.findViewById(R.id.orderbtn);
       img = mview.findViewById(R.id.imgShoe113);


        backbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AppCompatActivity activity = (AppCompatActivity)v.getContext();
                getFragmentManager().popBackStack();
                ((HomeScreen)activity).appBarLayout.setVisibility(View.VISIBLE);
                ((HomeScreen)activity).mNavigationView.setVisibility(View.VISIBLE);
            }
        });
        total.setText("Total: " + getTotal(DataManager.list));
        Picasso.get().load(shoeInBag.getResourceID().get(0)).into(img);

        return mview;
    }
    public void recieveDrop(ShoeInBag selected_product)
    {
        this.shoeInBag = selected_product;
    }
    public String getTotal(List<ShoeInBag> list)
    {
        String total_ ="$";
        Integer temp = 0;
        for (ShoeInBag a : list)
        {
            temp += Integer.parseInt(a.getShoeAmount())*Integer.parseInt(a.getProductPrice());
        }
        total_+= temp.toString();
        return total_ ;
    }
}
