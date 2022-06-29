package com.example.zendi_application.ActivityAccount.edit_deleteProductPackage;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Toast;
import android.widget.Toolbar;

import com.example.zendi_application.DataManager;
import com.example.zendi_application.R;
import com.example.zendi_application.addProductPackage.uploadData;
import com.example.zendi_application.dropFragment.product_package.product2;
import com.example.zendi_application.searchfragment.Transactor;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.List;

public class edit_deleteProduct extends AppCompatActivity {
    Spinner productList_spinner;
    private Toolbar toolbar_editproduct;
    public ProgressBar progressBar_editproduct;
    List<String> productName = new ArrayList<>();
    List<product2> processedList = new ArrayList<>();
    List<String> changedProductList = new ArrayList<>();
    Button deletebtn,savebtn;
    EditText nameedit, priceedit,  remaining55edit, remaining6edit,
            remaining65edit, remaining7edit, remaining75edit, remaining8edit, remaining85edit,
            remaining9edit,  remaining95edit,  remaining10edit,  remaining105edit,  remaining11edit,
            remaining115edit,  remaining12edit;
    String selectedProduct,selectedProductId;

    List<String> typeSpinnerList = new ArrayList<>();
    List<String> brandSpinnerList = new ArrayList<>();
    Spinner brandSpinner, typeSpinner;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_edit_delete_product);

        nameedit = findViewById(R.id.nameproduct_editproduct);
        brandSpinner = findViewById(R.id.brandproduct_editproduct);
        priceedit = findViewById(R.id.priceproduct_editproduct);
        typeSpinner = findViewById(R.id.typeproduct_editproduct);
        progressBar_editproduct = findViewById(R.id.progress_bar_editproduct);

        remaining55edit = findViewById(R.id.remaining5_5_editproduct);
        remaining6edit = findViewById(R.id.remaining6_editproduct);
        remaining65edit = findViewById(R.id.remaining6_5_editproduct);
        remaining7edit = findViewById(R.id.remaining7_editproduct);
        remaining75edit = findViewById(R.id.remaining7_5_editproduct);
        remaining8edit = findViewById(R.id.remaining8_editproduct);
        remaining85edit = findViewById(R.id.remaining8_5_editproduct);
        remaining9edit = findViewById(R.id.remaining9_editproduct);
        remaining95edit = findViewById(R.id.remaining9_5_editproduct);
        remaining10edit = findViewById(R.id.remaining10_editproduct);
        remaining105edit = findViewById(R.id.remaining10_5_editproduct);
        remaining11edit = findViewById(R.id.remaining11_editproduct);

        createSpinnerType();
        createSpinnerBrand();

        deletebtn = findViewById(R.id.deletebtn_editproduct);
        savebtn = findViewById(R.id.savebtn_editproduct);
        productList_spinner = (Spinner) findViewById(R.id.spinner_productlist_editproduct);
        remaining115edit = findViewById(R.id.remaining11_5_editproduct);
        remaining12edit = findViewById(R.id.remaining12_editproduct);


        for (product2 temp : DataManager.listProduct)
        {
            productName.add(temp.getProductName());
            processedList.add(temp);
        }


        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,productName);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        productList_spinner.setAdapter(arrayAdapter);

        productList_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedProduct = processedList.get(position).getProductName();
                selectedProductId = processedList.get(position).getProductId();
                nameedit.setText(processedList.get(position).getProductName());
                priceedit.setText(processedList.get(position).getProductPrice());
                remaining55edit.setText(processedList.get(position).getRemainingAmount().get(0).toString());
                remaining6edit.setText(processedList.get(position).getRemainingAmount().get(1).toString());
                remaining65edit.setText(processedList.get(position).getRemainingAmount().get(2).toString());
                remaining7edit.setText(processedList.get(position).getRemainingAmount().get(3).toString());
                remaining75edit.setText(processedList.get(position).getRemainingAmount().get(4).toString());
                remaining8edit.setText(processedList.get(position).getRemainingAmount().get(5).toString());
                remaining85edit.setText(processedList.get(position).getRemainingAmount().get(6).toString());
                remaining9edit.setText(processedList.get(position).getRemainingAmount().get(7).toString());
                remaining95edit.setText(processedList.get(position).getRemainingAmount().get(8).toString());
                remaining10edit.setText(processedList.get(position).getRemainingAmount().get(9).toString());
                remaining105edit.setText(processedList.get(position).getRemainingAmount().get(10).toString());
                remaining11edit.setText(processedList.get(position).getRemainingAmount().get(11).toString());
                remaining115edit.setText(processedList.get(position).getRemainingAmount().get(12).toString());
                remaining12edit.setText(processedList.get(position).getRemainingAmount().get(13).toString());

                int i;
                String temp = processedList.get(position).getProductBrand();
                for(i =0; i< brandSpinnerList.size(); i++){
                    if(temp.compareTo(brandSpinnerList.get(i)) == 0){
                        break;
                    }
                }
                brandSpinner.setSelection(i);

                if(processedList.get(position).getProductType() == "1"){
                    typeSpinner.setSelection(1);
                }

                else if(processedList.get(position).getProductType() == "2"){
                    typeSpinner.setSelection(2);
                }
                else typeSpinner.setSelection(0);


                /*brandedit.setText(processedList.get(position).getProductBrand());
                typeedit.setText(processedList.get(position).getProductType());*/
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        savebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (!(CheckValidEditText(remaining6edit.getText().toString()) == true &&
                CheckValidEditText(remaining7edit.getText().toString()) == true &&
                CheckValidEditText(remaining8edit.getText().toString()) == true &&
                CheckValidEditText(remaining9edit.getText().toString()) == true &&
                CheckValidEditText(remaining10edit.getText().toString()) == true &&
                CheckValidEditText(remaining11edit.getText().toString()) == true &&
                CheckValidEditText(remaining12edit.getText().toString()) == true &&
                CheckValidEditText(remaining55edit.getText().toString())== true &&
                CheckValidEditText(remaining65edit.getText().toString()) == true &&
                        CheckValidEditText(remaining75edit.getText().toString()) == true &&
                        CheckValidEditText(remaining85edit.getText().toString()) == true &&
                        CheckValidEditText(remaining95edit.getText().toString()) == true &&
                        CheckValidEditText(remaining105edit.getText().toString()) == true &&
                        CheckValidEditText(remaining115edit.getText().toString()) == true &&
                        CheckValidEditText(priceedit.getText().toString()) == true &&
                        /*CheckValidEditText(typeedit.getText().toString()) == true &&*/
                        !nameedit.getText().toString().isEmpty()
                        /*!brandedit.getText().toString().isEmpty()*/
                ))
                {
                    Toast.makeText(v.getContext(),"Invalid information, please check", Toast.LENGTH_SHORT).show();
                    return;
                }

                for (product2 temp : processedList)
                {
                    if (temp.getProductName() == selectedProduct)
                    {
                        //thang
                        Transactor.getInstance().oldID = temp.getProductId().toLowerCase();
                        Transactor.getInstance().oldBranch = temp.getProductBrand().toLowerCase();
                        Transactor.getInstance().oldType = temp.getProductType().toLowerCase();
                        //
                        temp.setProductName(nameedit.getText().toString());
                        temp.setProductBrand(brandSpinner.getSelectedItem().toString());
                        temp.setProductPrice(priceedit.getText().toString());
                        String tempType;
                        if(typeSpinner.getSelectedItem().toString() == "Male") tempType = "1";
                        else if(typeSpinner.getSelectedItem().toString() == "Female") tempType = "2";
                        else tempType = "3";
                        temp.setProductType(tempType);
                        temp.getRemainingAmount().set(0,Integer.parseInt(remaining55edit.getText().toString()));
                        temp.getRemainingAmount().set(1,Integer.parseInt(remaining6edit.getText().toString()));
                        temp.getRemainingAmount().set(2,Integer.parseInt(remaining65edit.getText().toString()));
                        temp.getRemainingAmount().set(3,Integer.parseInt(remaining7edit.getText().toString()));
                        temp.getRemainingAmount().set(4,Integer.parseInt(remaining75edit.getText().toString()));
                        temp.getRemainingAmount().set(5,Integer.parseInt(remaining8edit.getText().toString()));
                        temp.getRemainingAmount().set(6,Integer.parseInt(remaining85edit.getText().toString()));
                        temp.getRemainingAmount().set(7,Integer.parseInt(remaining9edit.getText().toString()));
                        temp.getRemainingAmount().set(8,Integer.parseInt(remaining95edit.getText().toString()));
                        temp.getRemainingAmount().set(9,Integer.parseInt(remaining10edit.getText().toString()));
                        temp.getRemainingAmount().set(10,Integer.parseInt(remaining105edit.getText().toString()));
                        temp.getRemainingAmount().set(11,Integer.parseInt(remaining11edit.getText().toString()));
                        temp.getRemainingAmount().set(12,Integer.parseInt(remaining115edit.getText().toString()));
                        temp.getRemainingAmount().set(13,Integer.parseInt(remaining12edit.getText().toString()));
                        if (changedProductList.contains(temp.getProductId()) == false)
                                changedProductList.add(temp.getProductId());
                        //thang
                        Transactor.getInstance().notifyEdit(temp);
                        //
                        for (product2 temp1 : DataManager.listProduct)
                        {
                            if ( temp1.getProductId() == temp.getProductId())
                              {
//                                DataManager.listProduct.remove(temp1);
//                                DataManager.listProduct.add(temp);
                                temp1.setProductName(nameedit.getText().toString());
                                temp1.setProductBrand(brandSpinner.getSelectedItem().toString());
                                temp1.setProductPrice(priceedit.getText().toString());
                                  String tempType2;
                                  if(typeSpinner.getSelectedItem().toString() == "Male") tempType2 = "1";
                                  else if(typeSpinner.getSelectedItem().toString() == "Female") tempType2 = "2";
                                  else tempType2 = "3";
                                  temp.setProductType(tempType2);
                                temp1.getRemainingAmount().set(0,Integer.parseInt(remaining55edit.getText().toString()));
                                temp1.getRemainingAmount().set(1,Integer.parseInt(remaining6edit.getText().toString()));
                                temp1.getRemainingAmount().set(2,Integer.parseInt(remaining65edit.getText().toString()));
                                temp1.getRemainingAmount().set(3,Integer.parseInt(remaining7edit.getText().toString()));
                                temp1.getRemainingAmount().set(4,Integer.parseInt(remaining75edit.getText().toString()));
                                temp1.getRemainingAmount().set(5,Integer.parseInt(remaining8edit.getText().toString()));
                                temp1.getRemainingAmount().set(6,Integer.parseInt(remaining85edit.getText().toString()));
                                temp1.getRemainingAmount().set(7,Integer.parseInt(remaining9edit.getText().toString()));
                                temp1.getRemainingAmount().set(8,Integer.parseInt(remaining95edit.getText().toString()));
                                temp1.getRemainingAmount().set(9,Integer.parseInt(remaining10edit.getText().toString()));
                                temp1.getRemainingAmount().set(10,Integer.parseInt(remaining105edit.getText().toString()));
                                temp1.getRemainingAmount().set(11,Integer.parseInt(remaining11edit.getText().toString()));
                                temp1.getRemainingAmount().set(12,Integer.parseInt(remaining115edit.getText().toString()));
                                temp1.getRemainingAmount().set(13,Integer.parseInt(remaining12edit.getText().toString()));
                                DataManager.edit_Object_To_FireStone((edit_deleteProduct)v.getContext(), "Product", temp.getProductId(), temp);
                                DataManager.Update_drop_after_edit_product((edit_deleteProduct) v.getContext(),temp1);
                                DataManager.Edit_Product_ShoeinBag(temp1,"edit");
                                DataManager.Edit_Product_InWish(temp1,"edit");
                                for (int i = 0; i < DataManager.list.size();i ++)
                                {
                                    if (DataManager.list.get(i).getProductId().compareTo(selectedProductId) == 0)
                                    {
                                        DataManager.list.get(i).setProductName(temp1.getProductName());
                                        DataManager.list.get(i).setProductBrand(temp1.getProductBrand());
                                        DataManager.list.get(i).setProductPrice(temp1.getProductPrice());
                                        DataManager.list.get(i).setProductType(temp1.getProductType());
                                        DataManager.list.get(i).getRemainingAmount().set(0,temp1.getRemainingAmount().get(0));
                                        DataManager.list.get(i).getRemainingAmount().set(1,temp1.getRemainingAmount().get(1));
                                        DataManager.list.get(i).getRemainingAmount().set(2,temp1.getRemainingAmount().get(2));
                                        DataManager.list.get(i).getRemainingAmount().set(3,temp1.getRemainingAmount().get(3));
                                        DataManager.list.get(i).getRemainingAmount().set(4,temp1.getRemainingAmount().get(4));
                                        DataManager.list.get(i).getRemainingAmount().set(5,temp1.getRemainingAmount().get(5));
                                        DataManager.list.get(i).getRemainingAmount().set(6,temp1.getRemainingAmount().get(6));
                                        DataManager.list.get(i).getRemainingAmount().set(7,temp1.getRemainingAmount().get(7));
                                        DataManager.list.get(i).getRemainingAmount().set(8,temp1.getRemainingAmount().get(8));
                                        DataManager.list.get(i).getRemainingAmount().set(9,temp1.getRemainingAmount().get(9));
                                        DataManager.list.get(i).getRemainingAmount().set(10,temp1.getRemainingAmount().get(10));
                                        DataManager.list.get(i).getRemainingAmount().set(11,temp1.getRemainingAmount().get(11));
                                        DataManager.list.get(i).getRemainingAmount().set(12,temp1.getRemainingAmount().get(12));
                                        DataManager.list.get(i).getRemainingAmount().set(13,temp1.getRemainingAmount().get(13));

                                        DataManager.shoeInBagAdapter.setData(DataManager.list);
                                        DataManager.shoeInBagAdapter.notifyDataSetChanged();

                                    }
                                }
                                for (int i = 0; i < DataManager.shoeInWish.size();i ++)
                                {
                                    if (DataManager.shoeInWish.get(i).getProductId().compareTo(selectedProductId) == 0)
                                    {
                                        DataManager.shoeInWish.get(i).setProductName(temp1.getProductName());
                                        DataManager.shoeInWish.get(i).setProductBrand(temp1.getProductBrand());
                                        DataManager.shoeInWish.get(i).setProductPrice(temp1.getProductPrice());
                                        DataManager.shoeInWish.get(i).setProductType(temp1.getProductType());
                                        DataManager.shoeInWish.get(i).getRemainingAmount().set(0,temp1.getRemainingAmount().get(0));
                                        DataManager.shoeInWish.get(i).getRemainingAmount().set(1,temp1.getRemainingAmount().get(1));
                                        DataManager.shoeInWish.get(i).getRemainingAmount().set(2,temp1.getRemainingAmount().get(2));
                                        DataManager.shoeInWish.get(i).getRemainingAmount().set(3,temp1.getRemainingAmount().get(3));
                                        DataManager.shoeInWish.get(i).getRemainingAmount().set(4,temp1.getRemainingAmount().get(4));
                                        DataManager.shoeInWish.get(i).getRemainingAmount().set(5,temp1.getRemainingAmount().get(5));
                                        DataManager.shoeInWish.get(i).getRemainingAmount().set(6,temp1.getRemainingAmount().get(6));
                                        DataManager.shoeInWish.get(i).getRemainingAmount().set(7,temp1.getRemainingAmount().get(7));
                                        DataManager.shoeInWish.get(i).getRemainingAmount().set(8,temp1.getRemainingAmount().get(8));
                                        DataManager.shoeInWish.get(i).getRemainingAmount().set(9,temp1.getRemainingAmount().get(9));
                                        DataManager.shoeInWish.get(i).getRemainingAmount().set(10,temp1.getRemainingAmount().get(10));
                                        DataManager.shoeInWish.get(i).getRemainingAmount().set(11,temp1.getRemainingAmount().get(11));
                                        DataManager.shoeInWish.get(i).getRemainingAmount().set(12,temp1.getRemainingAmount().get(12));
                                        DataManager.shoeInWish.get(i).getRemainingAmount().set(13,temp1.getRemainingAmount().get(13));

                                        DataManager.shoeInWishAdapter.setData(DataManager.shoeInWish);
                                        DataManager.shoeInWishAdapter.notifyDataSetChanged();

                                    }
                                }
                                break;
                            }
                        }



                        productName.clear();
                        for (product2 temp2 : DataManager.listProduct)
                        {
                            productName.add(temp2.getProductName());
                        }


                        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>((edit_deleteProduct)v.getContext(), android.R.layout.simple_spinner_item,productName);
                        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        productList_spinner.setAdapter(arrayAdapter);
                        arrayAdapter.notifyDataSetChanged();
                        Toast.makeText(v.getContext(), "Edit Successfully !", Toast.LENGTH_SHORT).show();
                        break;
                    }


                    
                }
//                for (product2 temp2 : DataManager.listProduct)
//                {
//                    productName.add(temp2.getProductName());
//                }
            }
        });
        toolbar_editproduct = findViewById(R.id.toorbar_editproduct);

        toolbar_editproduct.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
                finish();

            }
        });

        deletebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which){
                            case DialogInterface.BUTTON_POSITIVE: { //Yes button clicked
                                product2 delete_product = new product2();
                                // Xoa khoi list tren edit_deleteProduct
                                for (product2 temp : processedList) {
                                    if (temp.getProductId() == selectedProductId) {
                                        DataManager.Delete_product_to_Firestone((edit_deleteProduct) v.getContext(), temp);
                                        DataManager.Edit_Product_ShoeinBag(temp,"delete");
                                        DataManager.Edit_Product_InWish(temp,"delete");
                                        Toast.makeText(v.getContext(), "Delete product successfuly!", Toast.LENGTH_SHORT).show();
                                        processedList.remove(temp);
                                        break;
                                    }
                                }
                                // xoa tren list tong
                                for (product2 temp : DataManager.listProduct) {
                                    if (temp.getProductId() == selectedProductId) {
                                        //thang
                                        Transactor.getInstance().notifyDeleteProduct(temp);
                                        //
                                        DataManager.listProduct.remove(temp);
                                        break;
                                    }
                                }

                                for (int i = 0; i < DataManager.list.size();i ++)
                                {
                                    if (DataManager.list.get(i).getProductId().compareTo(selectedProductId) == 0)
                                    {
                                        DataManager.list.remove(i);
                                        DataManager.shoeInBagAdapter.setData(DataManager.list);
                                        DataManager.shoeInBagAdapter.notifyDataSetChanged();
                                        i--;
                                    }
                                }
                                for (int i = 0; i < DataManager.shoeInWish.size();i ++)
                                {
                                    if (DataManager.shoeInWish.get(i).getProductId().compareTo(selectedProductId) == 0)
                                    {
                                        //THANG
                                        String docName = selectedProductId;
                                        FirebaseFirestore db = FirebaseFirestore.getInstance();
                                        db.collection("InWish/" + DataManager.host.getId() + "/ShoeinWish").document(docName)
                                                .delete().addOnSuccessListener(new OnSuccessListener<Void>() {
                                            @Override
                                            public void onSuccess(Void aVoid) {
                                            }
                                        });
                                        //
                                        DataManager.shoeInWish.remove(i);
                                        DataManager.shoeInWishAdapter.setData(DataManager.shoeInWish);
                                        DataManager.shoeInWishAdapter.notifyDataSetChanged();
                                        i--;
                                    }
                                }


                                productName.clear();
                                for (product2 temp2 : DataManager.listProduct) {
                                    productName.add(temp2.getProductName());
                                }
                                ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>((edit_deleteProduct) v.getContext(), android.R.layout.simple_spinner_item, productName);
                                arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                                productList_spinner.setAdapter(arrayAdapter);
                                arrayAdapter.notifyDataSetChanged();

                                break;
                            }


                            case DialogInterface.BUTTON_NEGATIVE:
                                //No button clicked
                                break;
                        }
                    }
                };

                AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
                builder.setMessage("Are you sure delete " + selectedProduct + " ?").setPositiveButton("Yes", dialogClickListener)
                        .setNegativeButton("No", dialogClickListener).show();
            }
        });

    }
    private void createSpinnerType() {
        typeSpinnerList.add("Both");
        typeSpinnerList.add("Male");
        typeSpinnerList.add("Female");
        typeSpinner.setSelection(0);
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, R.layout.spinner_custom, typeSpinnerList);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        typeSpinner.setAdapter(arrayAdapter);
    }

    private void createSpinnerBrand() {
        brandSpinnerList.add("ADIDAS");
        brandSpinnerList.add("NIKE");
        brandSpinnerList.add("PUMA");
        brandSpinnerList.add("CONVERSE");
        brandSpinnerList.add("REEBOK");
        brandSpinnerList.add("NEW BALANCE");
        brandSpinnerList.add("VANS");
        brandSpinnerList.add("Other");
        brandSpinner.setSelection(0);
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, R.layout.spinner_custom, brandSpinnerList);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        brandSpinner.setAdapter(arrayAdapter);
    }
    public boolean CheckValidEditText(String object)
    {
        String a = object;
        return (!object.isEmpty() && android.text.TextUtils.isDigitsOnly(object));
    }
}