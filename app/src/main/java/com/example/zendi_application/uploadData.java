package com.example.zendi_application;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Instrumentation;
import android.content.ContentResolver;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.ImageView;

import java.io.InputStream;

public class uploadData extends AppCompatActivity {
    private Button btnload;
    private ImageView imgview;
    public DataManager dataManager;
    private static  final  int IMAGE_REQUEST = 2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload_data);
        btnload = findViewById(R.id.btnloadiamge);
        imgview = findViewById(R.id.img_load);
        btnload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OpenImage();
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
            dataManager.getInstance().pushImage( data.getData());

            }

    }
}