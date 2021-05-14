package com.example.zendi_application;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Instrumentation;
import android.content.ContentResolver;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.bumptech.glide.Glide;
import com.google.firebase.firestore.FirebaseFirestore;

import java.io.InputStream;
import java.net.URL;

public class uploadData extends AppCompatActivity {
    private Button btnload;
    public ImageView imgview;
    public ImageView imgview1;
    public DataManager dataManager;
    private static  final  int IMAGE_REQUEST = 2;
    public ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload_data);
        btnload = findViewById(R.id.btnloadiamge);
        imgview = findViewById(R.id.img_load);
        imgview1 = findViewById(R.id.img_load1);
        progressBar = findViewById(R.id.progress_bar);
        progressBar.setVisibility(View.INVISIBLE);

        btnload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OpenImage();
              //  dataManager.getInstance();
               // dataManager.instance.getImgUrlFromFirestone(v.getContext(),"Document");
//                dataManager.instance.getImageFromStorage("testfolder","img7");
//                Glide.with(v.getContext()).load(dataManager.instance.getImageUri()).into(imgview1);
                ///////////////////////////
//                OpenImage();

                int b = 2;
            }
        });
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
//            Uri imageUri = data.getData();
//            imgview.setImageURI(imageUri);
//            Glide.with(this).load("https://firebasestorage.googleapis.com/v0/b/zendi-1e684.appspot.com/o/testfolder%2Fimg4.jpg?alt=media&token=32f90648-76f8-4ce2-90bd-e90ef6ef052e").into(imgview1);
            dataManager.getInstance().pushImageToStorage( "testfolder","img8",data.getData());
            dataManager.getInstance().pushImageToStorage1(uploadData.this,"testfolder","img8",data.getData());
            }

    }
}