package com.example.zendi_application.addProductPackage;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.zendi_application.ActivityAccount.edit_deleteDropPackage.edit_deleteDrop;
import com.example.zendi_application.ActivityAccount.edit_deleteProductPackage.edit_deleteProduct;
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
    private Toolbar toolbar_adddrop;
    private RecyclerView productlistRecycleView;
    private RecyclerView productInDropRecycleView;
    public static imageAddDropAdapter imageproductlistAdapter_ = new imageAddDropAdapter();
    public static imageAddDropAdapter2 imageProductInDropAdapter_ = new imageAddDropAdapter2();
    EditText dropcaptionEdit, dropstatusEdit, droptypeEdit,category_ordinalEdit,drop_ordinalEdit;
    Button pushbtn,backbtn, addbtn,deletebtn,adddropimagebtn,editbtn;
    public ProgressBar progressBar_adddrop;
    Spinner productList_spinner;
    String selectedproductId;
    List<String> selected_productlist = new ArrayList<>();
    List<product2> selected_productlist2 = new ArrayList<>();
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
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_add_drop);
        productlistRecycleView = findViewById(R.id.recycleview_productlist_adddrop);
        productInDropRecycleView = findViewById(R.id.recycleview_productIndrop_adddrop);


        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this,RecyclerView.HORIZONTAL,false);
        LinearLayoutManager linearLayoutManager2 = new LinearLayoutManager(this,RecyclerView.HORIZONTAL,false);
        productlistRecycleView.setLayoutManager(linearLayoutManager);
        productInDropRecycleView.setLayoutManager(linearLayoutManager2);


        imageproductlistAdapter_.SetData(DataManager.listProduct);
        imageproductlistAdapter_.notifyDataSetChanged();
        productlistRecycleView.setAdapter(imageproductlistAdapter_);

        imageProductInDropAdapter_.SetData(selected_productlist2);
        imageProductInDropAdapter_.notifyDataSetChanged();
        productInDropRecycleView.setAdapter(imageProductInDropAdapter_);


        dropcaptionEdit = findViewById(R.id.dropcaption_adddrop);
        dropstatusEdit = findViewById(R.id.dropstatus_adddrop);
        droptypeEdit = findViewById(R.id.droptype_adddrop);
        category_ordinalEdit = findViewById(R.id.categoryordinal_adddrop);
        drop_ordinalEdit = findViewById(R.id.dropordinal_adddrop);
        productList_spinner = (Spinner) findViewById(R.id.spinner_productlist);

        progressBar_adddrop = findViewById(R.id.progress_bar_adddrop);
        load_dropimage = findViewById(R.id.load_dropimage);
        toolbar_adddrop = findViewById(R.id.toorbar_adddrop);
        toolbar_adddrop.setTitle("ADD DROP");

        toolbar_adddrop.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
                finish();
            }
        });


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


        addbtn = findViewById(R.id.addproduct_adddrop);
        deletebtn = findViewById(R.id.delete_adddrop);
        deletebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (selectedproductId != null)
                    selected_productlist.remove(selectedproductId);
                for (product2 ite : selected_productlist2 )
                {
                    if (ite.getProductId().compareTo(selectedproductId) == 0)
                    {
                        selected_productlist2.remove(ite);
                    Toast.makeText(v.getContext(),"Deleted Successfully",Toast.LENGTH_SHORT).show();
                    imageProductInDropAdapter_.SetData(selected_productlist2);
                    imageProductInDropAdapter_.notifyDataSetChanged();
                    selected_productlist.remove(selectedproductId);
                    return;
                    }
                }
            }
        });

        addbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (selectedproductId != null) {
                    selected_productlist.add(selectedproductId);
                    for (product2 ite : DataManager.listProduct) {
                        if (ite.getProductId() == selectedproductId)
                        {
                            Toast.makeText(v.getContext(),"Added Successfully",Toast.LENGTH_SHORT).show();
                            selected_productlist2.add(ite);
                            imageProductInDropAdapter_.SetData(selected_productlist2);
                            imageProductInDropAdapter_.notifyDataSetChanged();
                            return;
                        }
                    }

                }
            }
        });
        pushbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (android.text.TextUtils.isDigitsOnly(category_ordinalEdit.getText().toString()) == false
                || android.text.TextUtils.isDigitsOnly(drop_ordinalEdit.getText().toString()) == false)
                {
                    Toast.makeText(v.getContext(),"Invalid information, please check !! ",Toast.LENGTH_SHORT).show();
                    return;
                }
                if (listURL.size() == 0)
                {
                    Toast.makeText(v.getContext(),"Add drop's image, please",Toast.LENGTH_SHORT).show();
                    return;
                }
                Integer count = 0;
                for (drop2 temp : DataManager.listDrop)
                {
                    if (temp.getCategoryNumber().compareTo(category_ordinalEdit.getText().toString()) == 0
                    && temp.getDropNumber().compareTo(drop_ordinalEdit.getText().toString())==0)
                    {
                        count = 1;
                        DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                switch (which) {
                                    case DialogInterface.BUTTON_POSITIVE:
                                        PushDrop();
                                    case DialogInterface.BUTTON_NEGATIVE:
                                        return;
                                }
                            }
                        };
                        AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
                        builder.setMessage("Here already have  drop with the same CategoryNumber and DropNumber, Are you sure you want to change, the process of changing may cause data loss ??").setPositiveButton("Yes", dialogClickListener)
                                .setNegativeButton("No", dialogClickListener).show();
                    }
                }
                if (count == 0) PushDrop();


//                if (dropstatusEdit.getText().length() != 0 && dropcaptionEdit.getText().length() != 0
//                && droptypeEdit.getText().length() != 0 && drop_ordinalEdit.getText().length() != 0
//                && category_ordinalEdit.getText().length() != 0 && listimg.size() != 0 && selected_productlist != null) {
//                    List<product2> productList = new ArrayList<>();
//                    for (int i = 0; i < DataManager.listProduct.size(); i++) {
//                        for (int j = 0; j < selected_productlist.size(); j++) {
//                            if (DataManager.listProduct.get(i).getProductId().compareTo(selected_productlist.get(j)) == 0) {
//                                productList.add(DataManager.listProduct.get(i));
//                                continue;
//                            }
//                        }
//                    }
//                    drop2 a = new drop2(null, dropcaptionEdit.getText().toString(), dropstatusEdit.getText().toString(), droptypeEdit.getText().toString()
//                            , category_ordinalEdit.getText().toString(),drop_ordinalEdit.getText().toString(), selected_productlist, productList);
//                    String temp = "Drop_" + category_ordinalEdit.getText().toString() + "_" + drop_ordinalEdit.getText().toString() + "/";
////                DataManager.Push_Image(temp,"Collection/Category_1/","",listURL);
//                    /// txt1 chua stt category, txt2 chua ten drop, txt3 chua stt cua drop
//                    DataManager.push_drop_To_FireStone((AddDrop) v.getContext(), temp, drop_ordinalEdit.getText().toString(), a, listURL);
//
//                    listURL.clear();
//                    listimg.clear();
//                    selected_productlist.clear();
//                    drop_ordinalEdit.setText("");
//                    dropcaptionEdit.setText("");
//                    droptypeEdit.setText("");
//                    dropstatusEdit.setText("");
//                    category_ordinalEdit.setText("");
//                    load_dropimage.setImageURI(null);
//                    Toast.makeText(v.getContext(),"Drop is Loaded !!",Toast.LENGTH_SHORT);
//                }
//                else
//                {
//                    Toast.makeText(v.getContext(),"Please fill in all the information !!",Toast.LENGTH_SHORT);
//                }
            }
        });
        adddropimagebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OpenImage();
            }
        });

        editbtn = findViewById(R.id.editbtn_adddrop);
        editbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AddDrop.this, edit_deleteDrop.class));
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
    private void PushDrop()
    {
        if (dropstatusEdit.getText().length() != 0 && dropcaptionEdit.getText().length() != 0
                && droptypeEdit.getText().length() != 0 && drop_ordinalEdit.getText().length() != 0
                && category_ordinalEdit.getText().length() != 0 && listimg.size() != 0 && selected_productlist != null) {
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
                    , category_ordinalEdit.getText().toString(),drop_ordinalEdit.getText().toString(), selected_productlist, productList);
            String temp = "Drop_" + category_ordinalEdit.getText().toString() + "_" + drop_ordinalEdit.getText().toString() + "/";
//                DataManager.Push_Image(temp,"Collection/Category_1/","",listURL);
            /// txt1 chua stt category, txt2 chua ten drop, txt3 chua stt cua drop
            DataManager.push_drop_To_FireStone(this, temp, drop_ordinalEdit.getText().toString(), a, listURL);
            DataManager.listDrop.add(a);
            listURL.clear();
            listimg.clear();
            selected_productlist.clear();
            //
            selected_productlist2.clear();
            imageProductInDropAdapter_.SetData(selected_productlist2);
            imageProductInDropAdapter_.notifyDataSetChanged();
            //
            drop_ordinalEdit.setText("");
            dropcaptionEdit.setText("");
            droptypeEdit.setText("");
            dropstatusEdit.setText("");
            category_ordinalEdit.setText("");
            load_dropimage.setImageURI(null);
            Toast.makeText(this,"Drop is Loaded",Toast.LENGTH_SHORT).show();
        }
        else
        {
            Toast.makeText(this,"Please fill in all the information",Toast.LENGTH_SHORT).show();
        }
    }
}