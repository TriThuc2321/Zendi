package com.example.zendi_application;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Instrumentation;
import android.content.ContentResolver;
import android.content.Intent;
import android.graphics.Bitmap;
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

import java.io.InputStream;

public class uploadData extends AppCompatActivity {
    private Button btnload;
    private ImageView imgview;
    private ImageView imgview1;
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
//                dataManager.getInstance();
//                dataManager.instance.getImageFromStorage("testfolder","img1");
//                Uri temp = dataManager.instance.getImageUri();
//                Glide.with(v.getContext()).load(temp).into(imgview);
//                dataManager.instance.getImageFromStorage("testfolder","download.jpg");
//                temp = dataManager.instance.getImageUri();
//                Glide.with(v.getContext()).load(temp).into(imgview1);
                OpenImage();

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
            Uri imageUri = data.getData();
            imgview.setImageURI(imageUri);
            //dataManager.getInstance().pushImageToStorage( "testfolder","img3",data.getData());
            dataManager.getInstance().pushImageToStorage1(uploadData.this,"testfolder","img4",data.getData());
            }

    }
}