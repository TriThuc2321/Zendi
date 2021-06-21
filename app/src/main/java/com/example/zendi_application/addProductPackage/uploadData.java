package com.example.zendi_application.addProductPackage;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Toast;
import android.widget.Toolbar;

import com.bumptech.glide.Glide;
import com.example.zendi_application.DataManager;
import com.example.zendi_application.HomeScreen;
import com.example.zendi_application.R;
import com.example.zendi_application.dropFragment.drop.drop2;
import com.example.zendi_application.dropFragment.product_package.product2;

import java.util.ArrayList;
import java.util.List;

public class uploadData extends AppCompatActivity {
    private Button btnload,btnloadimg,btngetdata,btnpushdrop;
    public ImageView imgview;
    public EditText idEdit,captionEdit,priceEdit,brandEdit,typeEdit,txtposition;
    public DataManager dataManager;
    private static  final  int IMAGE_REQUEST = 2;
    private Toolbar toolbar;
    public ProgressBar progressBar;
    private List<String> listimg;
    private List<Uri> listURL;
    private AddDrop addDropActivity;
    ///
    private RecyclerView imageRecycleView;
    private RecyclerView imageRecycleView2;
    public static imageAdapter imageAdapter_;
    public static imageAdapter2 imageAdapter2_;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_upload_data);
        btnload = findViewById(R.id.btnloaddata);
        btnloadimg = findViewById(R.id.btnloadiamge);
        btngetdata = findViewById(R.id.btngetdata);
        imgview = findViewById(R.id.img_load);
        idEdit = findViewById(R.id.edit1);
        captionEdit = findViewById(R.id.edit2);
        priceEdit = findViewById(R.id.edit3);
        brandEdit = findViewById(R.id.edit4);
        typeEdit = findViewById(R.id.edit5);
        btnpushdrop = findViewById(R.id.pushdrop);
        txtposition = (EditText) findViewById(R.id.position_);
        progressBar = findViewById(R.id.progress_bar);
        toolbar = findViewById(R.id.toorbar_uploaddata);
        toolbar.setTitle("Upload Product");
        progressBar.setVisibility(View.INVISIBLE);
        ////
        imageRecycleView = findViewById(R.id.recycleview_imageofproductlist);
        imageAdapter_ = new imageAdapter(this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this,RecyclerView.HORIZONTAL,false);
        imageRecycleView.setLayoutManager(linearLayoutManager);
        uploadData.imageAdapter_.SetData(DataManager.listProduct,99);
        uploadData.imageAdapter_.notifyDataSetChanged();
        imageRecycleView.setAdapter(imageAdapter_);
        /////
        imageRecycleView2 = findViewById(R.id.recycleview_imageofnewproduct);
        imageAdapter2_ = new imageAdapter2(this);
        LinearLayoutManager linearLayoutManager2 = new LinearLayoutManager(this,RecyclerView.HORIZONTAL,false);
        imageRecycleView2.setLayoutManager(linearLayoutManager2);
        listURL = new ArrayList<>();
        imageAdapter2_.SetData(listURL);
        imageAdapter2_.notifyDataSetChanged();
        imageRecycleView2.setAdapter(imageAdapter2_);
       // DataManager.GetNumberofCategory();


        /////
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setResult(RESULT_CANCELED);
                uploadData.super.onBackPressed();
                finish();
            }
        });
        btnloadimg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dataManager.getInstance();
                OpenImage();


            }
        });

        btnload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (captionEdit.getText().length()>0 && typeEdit.getText().length()>0 && brandEdit.getText().length()>0
                && priceEdit.getText().length() > 0 && idEdit.getText().length() > 0 && listURL.size() == 4) {
                    dataManager.getInstance();
                    List<Integer> b = new ArrayList<>();
                    for (int i = 0; i <= 14; i++) {
                        b.add(10);
                    }
                    product2 mproduct = new product2(idEdit.getText().toString(), captionEdit.getText().toString(),
                            priceEdit.getText().toString(), brandEdit.getText().toString(), typeEdit.getText().toString(), new ArrayList<String>(), b, 1);
                    DataManager.listProduct.add(mproduct);

                    DataManager.push_Object_To_FireStone((uploadData)v.getContext(), "Product", idEdit.getText().toString(), mproduct, listURL);
                    listURL.clear();
                    listimg.clear();
                    imgview.setImageURI(null);
                }
                else
                {
                    if (captionEdit.getText().length()== 0 || typeEdit.getText().length() == 0 || brandEdit.getText().length() == 0
                            || priceEdit.getText().length() == 0 || idEdit.getText().length() == 0 )
                    {
                            Toast.makeText(v.getContext(),"Bạn Vừa Bỏ Sót Thông Tin Sản Phẩm !!",Toast.LENGTH_SHORT);

                    }
                    else
                    if (listURL.size() < 4)
                    {
                        Toast.makeText(v.getContext(),"Mỗi Sản Phẩm Nên Có Đủ 4 Tấm Hình !!",Toast.LENGTH_SHORT);
                    }
                }


            }
        });

        btngetdata.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<product2> c = new ArrayList<>();
                int n = 99;
                DataManager.getImgUrlFromFirestone1(v.getContext(),"Product",c);
                if (Integer.parseInt(txtposition.getText().toString()) == 99){}
                else
                {
                    n = Integer.parseInt(txtposition.getText().toString());
                }
                imageAdapter_.SetData(c,n);
                imageAdapter_.notifyDataSetChanged();
                imageRecycleView.setAdapter(imageAdapter_);

                int d = 0;
            }
        });

        btnpushdrop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                   startActivity(new Intent(uploadData.this,AddDrop.class));
                   AddDrop.imageproductlistAdapter_.notifyDataSetChanged();
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
                imageAdapter2_.SetData(listURL);
                imageAdapter2_.notifyDataSetChanged();
                listimg.add(data.getData().toString());
                imgview.setImageURI(data.getData());
//            Uri imageUri = data.getData();
//            imgview.setImageURI(imageUri);
//            Glide.with(this).load("https://firebasestorage.googleapis.com/v0/b/zendi-1e684.appspot.com/o/testfolder%2Fimg4.jpg?alt=media&token=32f90648-76f8-4ce2-90bd-e90ef6ef052e").into(imgview1);
//            dataManager.getInstance().pushImageToStorage( "testfolder","img8",data.getData());

 //          dataManager.getInstance().pushImageToStorage1(uploadData.this,"testfolder","img8",data.getData());

            }

    }

    protected void onStop() {
        setResult(0);
        super.onStop();
    }
    @Override
    protected void onDestroy() {
        setResult(0);
        super.onDestroy();
    }
}