package com.example.zendi_application.addProductPackage;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.zendi_application.DataManager;
import com.example.zendi_application.R;
import com.example.zendi_application.dropFragment.drop.drop;
import com.example.zendi_application.dropFragment.drop.drop2;
import com.example.zendi_application.dropFragment.product_package.product2;

import java.util.ArrayList;
import java.util.List;

public class AddDrop extends AppCompatActivity {

    private  uploadData parent;
    private List<String> listimg;
    private List<Uri> listURL;
    private static  final  int IMAGE_REQUEST = 2;
    private ImageView load_dropimage;
    private RecyclerView dropRecycleView;
    public static imageAddDropAdapter imagedropAdapter_ = new imageAddDropAdapter();
    EditText dropcaptionEdit, dropstatusEdit, droptypeEdit,category_ordinalEdit,drop_ordinalEdit;
    Button pushbtn,backbtn, addbtn,deletebtn,adddropimagebtn;
    public ProgressBar progressBar_adddrop;
    Spinner productList_spinner;
    String selectedproductId;
    List<String> selected_productlist = new ArrayList<>();
    List<String> productName = new ArrayList<>();
    List<String> productId = new ArrayList<>();

    public AddDrop()
    {

    }
    public AddDrop(uploadData parent)
    {
        this.parent= parent;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_drop);
        dropRecycleView = findViewById(R.id.recycleview_imagedrop);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this,RecyclerView.HORIZONTAL,false);
        dropRecycleView.setLayoutManager(linearLayoutManager);

        imagedropAdapter_.SetData(DataManager.listProduct);
        imagedropAdapter_.notifyDataSetChanged();
        dropRecycleView.setAdapter(imagedropAdapter_);

        dropcaptionEdit = findViewById(R.id.dropcaption_adddrop);
        dropstatusEdit = findViewById(R.id.dropstatus_adddrop);
        droptypeEdit = findViewById(R.id.droptype_adddrop);
        category_ordinalEdit = findViewById(R.id.categoryordinal_adddrop);
        drop_ordinalEdit = findViewById(R.id.dropordinal_adddrop);
        productList_spinner = (Spinner) findViewById(R.id.spinner_productlist);
        progressBar_adddrop = findViewById(R.id.progress_bar_adddrop);
        load_dropimage = findViewById(R.id.load_dropimage);


        for (product2 temp : DataManager.listProduct)
        {
            productName.add(temp.getProductName());
            productId.add(temp.getProductId());
        }

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,productName);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        productList_spinner.setAdapter(arrayAdapter);

        productList_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedproductId = productId.get(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        adddropimagebtn = findViewById(R.id.adddropimagebtn_adddrop);
        pushbtn = findViewById(R.id.pushdropbtn_adddrop);
        backbtn = findViewById(R.id.back_adddrop);
        addbtn = findViewById(R.id.addproduct_adddrop);
        deletebtn = findViewById(R.id.delete_adddrop);
        deletebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (selectedproductId != null)
                    selected_productlist.remove(selectedproductId);
            }
        });

        addbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (selectedproductId != null)
                    selected_productlist.add(selectedproductId);
            }
        });
        pushbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (dropstatusEdit.getText().length() != 0 && dropcaptionEdit.getText().length() != 0
                && droptypeEdit.getText().length() != 0 && drop_ordinalEdit.getText().length() != 0
                && category_ordinalEdit.getText().length() != 0 && listimg != null && selected_productlist != null) {
                    List<product2> productList = new ArrayList<>();
                    for (int i = 0; i < DataManager.listProduct.size(); i++) {
                        for (int j = 0; j < selected_productlist.size(); j++) {
                            if (DataManager.listProduct.get(i).getProductId().compareTo(selected_productlist.get(j)) == 0) {
                                productList.add(DataManager.listProduct.get(i));
                                continue;
                            }
                        }
                    }
                    drop2 a = new drop2(null, dropcaptionEdit.getText().toString(), dropstatusEdit.getText().toString(), droptypeEdit.getText().toString()
                            , category_ordinalEdit.getText().toString(), selected_productlist, productList);
                    String temp = "Drop_" + category_ordinalEdit.getText().toString() + "_" + drop_ordinalEdit.getText().toString() + "/";
//                DataManager.Push_Image(temp,"Collection/Category_1/","",listURL);
                    /// txt1 chua stt category, txt2 chua ten drop, txt3 chua stt cua drop
                    DataManager.push_drop_To_FireStone((AddDrop) v.getContext(), temp, drop_ordinalEdit.getText().toString(), a, listURL);

                    listURL.clear();
                    listimg.clear();
                    selected_productlist.clear();
                    drop_ordinalEdit.setText("");
                    dropcaptionEdit.setText("");
                    droptypeEdit.setText("");
                    dropstatusEdit.setText("");
                    category_ordinalEdit.setText("");
                    load_dropimage.setImageURI(null);
                    Toast.makeText(v.getContext(),"Drop is Loaded !!",Toast.LENGTH_SHORT);
                }
                else
                {
                    Toast.makeText(v.getContext(),"Please fill in all the information !!",Toast.LENGTH_SHORT);
                }
            }
        });

        backbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
                finish();

            }
        });
        adddropimagebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OpenImage();
            }
        });

    }
    private void OpenImage() {
        if (listimg == null) listimg = new ArrayList<>();
        if (listURL == null) listURL = new ArrayList<>();
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent,IMAGE_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==IMAGE_REQUEST && resultCode ==RESULT_OK && data!=null && data.getData()!=null){
            listURL.add(data.getData());
            listimg.add(data.getData().toString());
            load_dropimage.setImageURI(data.getData());
        }

    }
}