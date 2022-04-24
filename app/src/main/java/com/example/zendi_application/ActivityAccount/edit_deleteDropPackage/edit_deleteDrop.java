package com.example.zendi_application.ActivityAccount.edit_deleteDropPackage;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;
import android.widget.Toolbar;

import com.example.zendi_application.DataManager;
import com.example.zendi_application.R;
import com.example.zendi_application.addProductPackage.AddDrop;
import com.example.zendi_application.dropFragment.drop.drop2;
import com.example.zendi_application.dropFragment.product_package.product;
import com.example.zendi_application.dropFragment.product_package.product2;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.FirebaseFirestore;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class edit_deleteDrop extends AppCompatActivity {
    private static final int IMAGE_REQUEST = 2;
    public EditText caption_editdrop, status_editdrop, dropnumber_editdrop, categorynumber_editdrop, type_editdrop;
    public RecyclerView rcv_drop;
    public RecyclerView rcv_product;
    public Spinner productlist_spinner;
    public ImageView image_editdrop;
    public Toolbar toolbar;
    public List<String> productName = new ArrayList<>();
    public String selectedproductId;
    public Button addproductbtn, savebtn, loadbtn;
    public static  productAdapter_editdrop productAdapter_editdrop = new productAdapter_editdrop();
    public static dropAdapter_editdrop dropAdapter_editdrop = new dropAdapter_editdrop();
    public static List<product2> selectedListProduct = new ArrayList<>();
    public static List<String> selectedListProductName = new ArrayList<>();
    public static List<String> selected_producidtlist = new ArrayList<>();
    public static String URLimage;
    public static String selectedDrop_categoryNumber, selectedDrop_dropNumber;
    public static Uri URIimage;


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
        addproductbtn = findViewById(R.id.addproductindrop_editdrop);
        loadbtn = findViewById(R.id.loadimagedrop_editdrop);
        savebtn = findViewById(R.id.savebtn1_editdrop);
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
                URIimage = null;
                URLimage = null;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        toolbar = findViewById(R.id.toorbar_editdrop);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
                finish();
            }
        });

        addproductbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (selectedproductId != null && selectedDrop_categoryNumber != null && selectedDrop_dropNumber != null)
                {
                    for (product2 temp : DataManager.listProduct)
                    {
                        if (selectedproductId.compareTo(temp.getProductId())== 0) {

                            product2 selectedproduct = new product2(temp.getProductId(), temp.getProductName(), temp.getProductPrice()
                                    , temp.getProductBrand(), temp.getProductType(), temp.getResourceID(), temp.getRemainingAmount(), temp.getType());
                            if (selectedListProductName.contains(selectedproduct.getProductName()) == false) {
                                selected_producidtlist.add(selectedproduct.getProductId());
                                selectedListProduct.add(selectedproduct);
                                selectedListProductName.add(selectedproduct.getProductName());
                                edit_deleteDrop.productAdapter_editdrop.SetData(selectedListProduct,(edit_deleteDrop) v.getContext());
                                Toast.makeText(v.getContext(), "Add Product Successfully !", Toast.LENGTH_SHORT).show();
                                break;
                            }
                            else
                                Toast.makeText(v.getContext(), "Selected product has been in drop !", Toast.LENGTH_SHORT).show();


                        }
                    }

                }
                else
                {
                    Toast.makeText(v.getContext(), "Please choose the drop !", Toast.LENGTH_SHORT).show();

                }
            }
        });

        savebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (!(CheckValidEditText(dropnumber_editdrop.getText().toString()) == true &&
                        CheckValidEditText(categorynumber_editdrop.getText().toString()) == true &&
                        !caption_editdrop.getText().toString().isEmpty() == true &&
                        !status_editdrop.getText().toString().isEmpty() == true &&
                        !type_editdrop.getText().toString().isEmpty() == true))
                {
                    Toast.makeText(v.getContext(),"Invalid information, please check",Toast.LENGTH_SHORT).show();
                    return;
                }

                Integer count = 0;
                String temp1 = dropnumber_editdrop.getText().toString();
                String temp2 = categorynumber_editdrop.getText().toString();
                if (temp1.compareTo(selectedDrop_dropNumber) == 0 && temp2.compareTo(selectedDrop_categoryNumber) == 0 )
                {
                    Upload_Drop();
                    selectedDrop_categoryNumber = temp2;
                    selectedDrop_dropNumber = temp1;
                    return;
                }
                else
                for (drop2 drop_ : DataManager.listDrop)
                {
                    if (drop_.getCategoryNumber().compareTo(temp2) == 0 && drop_.getDropNumber().compareTo(temp1) == 0)
                    {
                        count = 1;
                            DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    switch (which) {
                                        case DialogInterface.BUTTON_POSITIVE: { //Yes button clicked
                                            Upload_Drop();
                                            DataManager.listDrop.remove(drop_);
                                            selectedDrop_categoryNumber = temp2;
                                            selectedDrop_dropNumber = temp1;
                                            return;
                                        }


                                        case DialogInterface.BUTTON_NEGATIVE:
                                            return;


                                        //No button clicked

                                    }
                                }
                            };

                            AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
                            builder.setMessage("Here already have drop with the same Drop Number and Category Number, Are you sure you want to change, the process of changing may cause data loss ??").setPositiveButton("Yes", dialogClickListener)
                                    .setNegativeButton("No", dialogClickListener).show();
                    }
                }
                if (count == 0)
                {
                    Upload_Drop();
                    selectedDrop_categoryNumber = temp2;
                    selectedDrop_dropNumber = temp1;
                }



            }
        });

        loadbtn.findViewById(R.id.loadimagedrop_editdrop);
        loadbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OpenImage();
            }
        });




    }
    public void Upload_Drop()
    {
        for (drop2 ite : DataManager.listDrop)
        {
            if (selectedDrop_dropNumber.compareTo(ite.getDropNumber()) == 0 && selectedDrop_categoryNumber.compareTo(ite.getCategoryNumber()) == 0)
            {
                ite.setCaption(caption_editdrop.getText().toString());
                ite.setStatus(status_editdrop.getText().toString());
                ite.setCategoryNumber(categorynumber_editdrop.getText().toString());
                ite.setDropNumber(dropnumber_editdrop.getText().toString());
                ite.setType(type_editdrop.getText().toString());
                ite.setProductList(selectedListProduct);
                //ite.setListProductName(selectedListProductName);
                ite.setListProductName(selected_producidtlist);
                dropAdapter_editdrop.SetData(DataManager.listDrop,this);
                dropAdapter_editdrop.notifyDataSetChanged();
                rcv_drop.setAdapter(dropAdapter_editdrop);
                /// upload to database
                String olddrop = "Drop_" + selectedDrop_categoryNumber + "_" + selectedDrop_dropNumber + "/";
                String temp = "Drop_" + ite.getCategoryNumber() + "_" + ite.getDropNumber() + "/";
                FirebaseFirestore db = FirebaseFirestore.getInstance();
                db.collection("Collection/").document(olddrop)
                        .delete()
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                            }
                        });
                DataManager.push_drop_To_FireStone_editdrop(this, temp, ite, URIimage);
                URLimage = null;
                URIimage = null;
            }
        }

    }
    private void OpenImage() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent,IMAGE_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==IMAGE_REQUEST && resultCode ==RESULT_OK && data!=null && data.getData()!=null){
            URLimage = data.getData().toString();
            URIimage = data.getData();
            image_editdrop.setImageURI(data.getData());
        }

    }
    public boolean CheckValidEditText(String object)
    {
        return (!object.isEmpty() && android.text.TextUtils.isDigitsOnly(object));
    }
}