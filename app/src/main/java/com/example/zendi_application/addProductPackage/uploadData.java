package com.example.zendi_application.addProductPackage;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import com.bumptech.glide.Glide;
import com.example.zendi_application.DataManager;
import com.example.zendi_application.R;
import com.example.zendi_application.dropFragment.product_package.product2;

import java.util.ArrayList;
import java.util.List;

public class uploadData extends AppCompatActivity {
    private Button btnload,btnloadimg,btngetdata;
    public ImageView imgview;
    public EditText txt1,txt2,txt3,txt4,txtposition;
    public DataManager dataManager;
    private static  final  int IMAGE_REQUEST = 2;
    public ProgressBar progressBar;
    private List<String> listimg;
    private List<Uri> listURL;
    ///
    private RecyclerView imageRecycleView;
    public imageAdapter imageAdapter_;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload_data);
        btnload = findViewById(R.id.btnloaddata);
        btnloadimg = findViewById(R.id.btnloadiamge);
        btngetdata = findViewById(R.id.btngetdata);
        imgview = findViewById(R.id.img_load);
        txt1 = findViewById(R.id.edit1);
        txt2 = findViewById(R.id.edit2);
        txt3 = findViewById(R.id.edit3);
        txt4 = findViewById(R.id.edit4);
        txtposition = (EditText) findViewById(R.id.position_);
        progressBar = findViewById(R.id.progress_bar);
        progressBar.setVisibility(View.INVISIBLE);
        ////
        imageRecycleView = findViewById(R.id.recycleview_imageofnewproduct);
        imageAdapter_ = new imageAdapter(this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this,RecyclerView.HORIZONTAL,false);
        imageRecycleView.setLayoutManager(linearLayoutManager);

        /////

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

                dataManager.getInstance();
//                dataManager.instance.getImgUrlFromFirestone(v.getContext(),"testfolder",c);
                List<Integer> b = new ArrayList<>();
                b.add(1);
                b.add(5);
                product2 mproduct = new product2(txt1.getText().toString(),txt2.getText().toString(),txt3.getText().toString(),new ArrayList<String>(),b,3);
                DataManager.push_Object_To_FireStone(uploadData.this,"Product","ProductName4",mproduct,listURL);
                listURL.clear();
                listimg.clear();
                imgview.setImageURI(null);

//                dataManager.instance.getImageFromStorage("testfolder","img7");
//                Glide.with(v.getContext()).load(dataManager.instance.getImageUri()).into(imgview1);

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
                imgview.setImageURI(data.getData());
//            Uri imageUri = data.getData();
//            imgview.setImageURI(imageUri);
//            Glide.with(this).load("https://firebasestorage.googleapis.com/v0/b/zendi-1e684.appspot.com/o/testfolder%2Fimg4.jpg?alt=media&token=32f90648-76f8-4ce2-90bd-e90ef6ef052e").into(imgview1);
//            dataManager.getInstance().pushImageToStorage( "testfolder","img8",data.getData());

 //          dataManager.getInstance().pushImageToStorage1(uploadData.this,"testfolder","img8",data.getData());

            }

    }
}