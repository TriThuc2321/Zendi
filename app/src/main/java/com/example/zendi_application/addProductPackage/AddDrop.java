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

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.zendi_application.DataManager;
import com.example.zendi_application.R;
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

                List<product2> productList = new ArrayList<>();
                for (int i = 0; i < DataManager.listProduct.size(); i++)
                {
                    for (int j = 0; j < selected_productlist.size(); j++)
                    {
                        if (DataManager.listProduct.get(i).getProductId().compareTo(selected_productlist.get(j)) == 0)
                        {
                            productList.add(DataManager.listProduct.get(i));
                            continue;
                        }
                    }
                }
                drop2 a = new drop2(null,selected_productlist,dropcaptionEdit.getText().toString(),dropstatusEdit.getText().toString(),droptypeEdit.getText().toString(),productList);
                String temp = "Category_" + category_ordinalEdit.getText().toString() +"/";
//                DataManager.Push_Image(temp,"Collection/Category_1/","",listURL);
                /// txt1 chua stt category, txt2 chua ten drop, txt3 chua stt cua drop
                DataManager.push_drop_To_FireStone((AddDrop)v.getContext(),temp,"Drop_" + drop_ordinalEdit.getText().toString(),drop_ordinalEdit.getText().toString(),a,listURL);
                // listURL.clear();
                //listimg.clear();
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
//            Uri imageUri = data.getData();
//            imgview.setImageURI(imageUri);
//            Glide.with(this).load("https://firebasestorage.googleapis.com/v0/b/zendi-1e684.appspot.com/o/testfolder%2Fimg4.jpg?alt=media&token=32f90648-76f8-4ce2-90bd-e90ef6ef052e").into(imgview1);
//            dataManager.getInstance().pushImageToStorage( "testfolder","img8",data.getData());

            //          dataManager.getInstance().pushImageToStorage1(uploadData.this,"testfolder","img8",data.getData());

        }

    }
}