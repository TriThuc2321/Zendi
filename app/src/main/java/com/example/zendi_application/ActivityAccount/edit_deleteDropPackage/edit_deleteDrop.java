package com.example.zendi_application.ActivityAccount.edit_deleteDropPackage;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;

import com.example.zendi_application.DataManager;
import com.example.zendi_application.R;
import com.example.zendi_application.dropFragment.product_package.product2;

import java.util.ArrayList;
import java.util.List;

public class edit_deleteDrop extends AppCompatActivity {
    public EditText caption_editdrop, status_editdrop, dropnumber_editdrop, categorynumber_editdrop, type_editdrop;
    public RecyclerView rcv_drop;
    public RecyclerView rcv_product;
    public Spinner productlist_spinner;
    public ImageView image_editdrop;
    public List<String> productName = new ArrayList<>();
    public String selectedproductId;
    public static  productAdapter_editdrop productAdapter_editdrop = new productAdapter_editdrop();
    public static dropAdapter_editdrop dropAdapter_editdrop = new dropAdapter_editdrop();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_edit_delete_drop);

        caption_editdrop = findViewById(R.id.captiondrop_editdrop);
        status_editdrop = findViewById(R.id.statusdrop_editdrop);
        categorynumber_editdrop = findViewById(R.id.categorynumber_editdrop);
        dropnumber_editdrop = findViewById(R.id.dropnumber_editdrop);
        type_editdrop = findViewById(R.id.typedrop_editdrop);
        rcv_drop = findViewById(R.id.rcv_drop_editdrop);
        rcv_product = findViewById(R.id.rcv_product_editdrop);
        image_editdrop = findViewById(R.id.imagedrop_editdrop);
        productlist_spinner = (Spinner) findViewById(R.id.spinner_productlist_editdrop);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this,RecyclerView.HORIZONTAL,false);
        LinearLayoutManager linearLayoutManager2 = new LinearLayoutManager(this,RecyclerView.HORIZONTAL,false);
        rcv_product.setLayoutManager(linearLayoutManager);
        rcv_drop.setLayoutManager(linearLayoutManager2);

        dropAdapter_editdrop.SetData(DataManager.listDrop,this);
        dropAdapter_editdrop.notifyDataSetChanged();
        rcv_drop.setAdapter(dropAdapter_editdrop);

        productAdapter_editdrop.SetData(null,this);
        productAdapter_editdrop.notifyDataSetChanged();
        rcv_product.setAdapter(productAdapter_editdrop);

        for (product2 ite : DataManager.listProduct)
        {
            productName.add(ite.getProductName());
        }

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,productName);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        productlist_spinner.setAdapter(arrayAdapter);

        productlist_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedproductId = DataManager.listProduct.get(position).getProductId();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


    }
}