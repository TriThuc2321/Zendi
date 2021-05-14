package com.example.zendi_application;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.util.Log;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.zendi_application.dropFragment.ModelSupportLoad;
import com.example.zendi_application.dropFragment.product_package.product;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class DataManager {
    // Instance
    public static DataManager instance;
    // Attributes
    private Uri imageUri;
    private FirebaseStorage storage;
    private StorageReference storageReference;
    private DatabaseReference root = FirebaseDatabase.getInstance().getReference();
    private FirebaseFirestore firestonedb = FirebaseFirestore.getInstance();
    private String temp;



    public static DataManager getInstance() {
        if (instance == null) instance = new DataManager();
        return instance;
    }

    public static void setInstance(DataManager instance) {
        DataManager.instance = instance;
    }

    public void addDataForDrogFragment(int whichType) {

    }

    public void readDataForDrogFragment() {

    }

    //Thien//
    public void pushImageToStorage(String folderName, String imgName, Uri imageUri) {
        this.imageUri = imageUri;
        if (this.imageUri != null) {
            storage = FirebaseStorage.getInstance();
            storageReference = storage.getReference();
            StorageReference riversRef = storageReference.child(folderName + "/" + imgName);
            riversRef.putFile(imageUri);
        }
    }
    public void pushImageToStorage1(uploadData parent,String folderName, String imgName, Uri imageUri) {
        this.imageUri = imageUri;
        if (this.imageUri != null) {
            storage = FirebaseStorage.getInstance();
            storageReference = storage.getReference();
            // add img to Storage Firebase with folder and img name.
            StorageReference riversRef = storageReference.child(folderName + "/" + imgName  +"."
                    + instance.getFileExtension(parent.getContentResolver(),imageUri));
            riversRef.putFile(imageUri).addOnCompleteListener(new OnCompleteListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onComplete(@NonNull Task<UploadTask.TaskSnapshot> task) {
                    riversRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                        @Override
                        public void onSuccess(Uri uri) {

                            ModelSupportLoad modelSupportLoad = new ModelSupportLoad(uri.toString());
                            // add image URL to real-time database with folder and img name.
                            root.child(folderName + "/" +imgName).setValue(modelSupportLoad);

//                            Map<String,Object> map = new HashMap<>();
//                            map.put("imgURL1",modelSupportLoad.getImgURl());


                            /// them 1 product
                            product mproduct = new product("111","ok","333",null,null,3);
                            firestonedb.collection(folderName).document("productlist1").set(mproduct)
                                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
                                            Toast.makeText((uploadData)parent,"Upload Successed",Toast.LENGTH_SHORT);
                                        }
                                    }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Toast.makeText((uploadData)parent,"Upload Failed",Toast.LENGTH_SHORT);
                                }
                            });

                            parent.progressBar.setVisibility(View.INVISIBLE);
                        }
                    });
                }
            }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onProgress(@NonNull UploadTask.TaskSnapshot snapshot) {
                    parent.progressBar.setVisibility(View.VISIBLE);

                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText((uploadData)parent,"Upload Failed",Toast.LENGTH_SHORT);
                    parent.progressBar.setVisibility(View.INVISIBLE);
                }
            });
        }
    }
    public void getImgUrlFromFirestone(Context parent,String collection) {
       firestonedb.collection(collection).get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                for (DocumentSnapshot documentSnapshot : task.getResult())
                {
                    ModelSupportLoad modelSupportLoad = new ModelSupportLoad(documentSnapshot.getString("imgURL1"));
                    Glide.with(parent).load(modelSupportLoad.getImgURl()).into(((uploadData)parent).imgview);

                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {

            }
        });
    }
        public Uri getImageFromStorage(String folderName, String imgNameNeedToGet) {
        storage = FirebaseStorage.getInstance();
        storageReference = storage.getReference().child(folderName + "/" + imgNameNeedToGet);
        Task<Uri> task = storageReference.getDownloadUrl();
        while (task.isSuccessful() == false);
            imageUri = task.getResult();
        return ((imageUri == null) ? null : imageUri);
    }
    public String getFileExtension(ContentResolver contentResolver,Uri uri)
    {
        MimeTypeMap mimeTypeMap = MimeTypeMap.getSingleton();
        return mimeTypeMap.getExtensionFromMimeType(contentResolver.getType(uri));
    }

            //endregion//

    public Uri getImageUri() {
        return imageUri;
    }

    public void setImageUri(Uri imageUri) {
        this.imageUri = imageUri;
    }

    public FirebaseStorage getStorage() {
        return storage;
    }

    public void setStorage(FirebaseStorage storage) {
        this.storage = storage;
    }

    public StorageReference getStorageReference() {
        return storageReference;
    }

    public void setStorageReference(StorageReference storageReference) {
        this.storageReference = storageReference;
    }

    public String getTemp() {
        return temp;
    }

    public void setTemp(String temp) {
        this.temp = temp;
    }
}
