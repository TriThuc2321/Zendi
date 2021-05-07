package com.example.zendi_application;

import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.net.Uri;
import android.webkit.MimeTypeMap;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.net.URI;
import java.util.UUID;

public class DataManager {
    // Instance
    private static DataManager instance;
    // Attributes
    private Uri imageUri;
    private FirebaseStorage storage;
    private StorageReference storageReference;



    public static DataManager getInstance() {
         if (instance == null) instance = new DataManager();
         return instance;
    }

    public static void setInstance(DataManager instance) {
        DataManager.instance = instance;
    }
    public void addDataForDrogFragment(int whichType)
    {

    }
    public void readDataForDrogFragment()
    {

    }
    //Thien//
    public void pushImage( Uri imageUri){
        this.imageUri = imageUri;
        if (this.imageUri != null)
        {
            storage = FirebaseStorage.getInstance();
            storageReference = storage.getReference();
//            String a = imageName + "." + extension;
//           final StorageReference fileRef = FirebaseStorage.getInstance().getReference().child(folderName).child(imageName + "." + extension);
//            fileRef.putFile(imageUri);
            final String randomKey = UUID.randomUUID().toString();
            StorageReference riversRef = storageReference.child("images/" + randomKey);
            riversRef.putFile(imageUri);
        }
    //endregion//

}

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
