package com.example.zendi_application;

import android.content.ContentResolver;
import android.content.Context;
import android.net.Uri;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.ItemTouchHelper;

import com.example.zendi_application.ActivityAccount.User;
import com.example.zendi_application.addProductPackage.AddDrop;
import com.example.zendi_application.addProductPackage.ProductList;
import com.example.zendi_application.addProductPackage.ProductNameList;
import com.example.zendi_application.addProductPackage.uploadData;
import com.example.zendi_application.dropFragment.ModelSupportLoad;
import com.example.zendi_application.dropFragment.category_drop.category;
import com.example.zendi_application.dropFragment.drop.drop;
import com.example.zendi_application.dropFragment.drop.drop2;
import com.example.zendi_application.dropFragment.product_package.product;
import com.example.zendi_application.dropFragment.product_package.product2;
import com.example.zendi_application.shopFragment.ShoeInBag;
import com.example.zendi_application.shopFragment.ShopFragment;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class DataManager {
    // Instance

    public static DataManager instance;
    public static List<product2> listProduct = new ArrayList<>(); // All products
    public static List<drop2> listDrop = new ArrayList<>(); // All drops
    public static HashMap<String,List<drop2>> listCategory = new HashMap<>(); // All categories
    public static User host ;
    public static List<ShoeInBag> list = new ArrayList<>();
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
    public static void Push_Image(String path_Storage,String catogoryName, String document, List<Uri> listURI)
    {
        FirebaseStorage firebaseStorage = FirebaseStorage.getInstance(); // Init reference
        StorageReference storageReference_ = firebaseStorage.getReference();
        Integer ordinalNumber = 0;
        for (Uri a : listURI)
        {
            String temp = "Drop_" + ordinalNumber.toString();
            StorageReference processPutFile = storageReference_.child( path_Storage + temp );
            ordinalNumber++;
            processPutFile.putFile(a).addOnCompleteListener(new OnCompleteListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onComplete(@NonNull Task<UploadTask.TaskSnapshot> task) {
                    processPutFile.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                        @Override
                        public void onSuccess(Uri uri) {
                            // After getting Url of images, we add Urls to resource field in product.

                            FirebaseFirestore firestonedb1 = FirebaseFirestore.getInstance();
                            DocumentReference documentReference = firestonedb1.collection("Collection")
                                    .document(document).collection("DropList").document(temp);

                        }
                    });
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                }
            }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onProgress(@NonNull UploadTask.TaskSnapshot snapshot) {
                }
            });
        }
    }

    public static void push_drop_To_FireStone(AddDrop parent, String dropname, String ordinal , drop2 object, List<Uri> listURI )
    {
        /// Push List Image to Storage and Get List of ImageURL
        parent.progressBar_adddrop.setVisibility(View.VISIBLE);
        FirebaseStorage firebaseStorage = FirebaseStorage.getInstance(); // Init reference
        StorageReference storageReference_ = firebaseStorage.getReference();
            String temp = "Drop_" + ordinal.toString();
            StorageReference processPutFile = storageReference_.child("Collection/" + dropname);
            processPutFile.putFile(listURI.get(0)).addOnCompleteListener(new OnCompleteListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onComplete(@NonNull Task<UploadTask.TaskSnapshot> task) {
                    processPutFile.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                        @Override
                        public void onSuccess(Uri uri) {
                            //// After getting Url of images, we add Urls to Image
                            object.setImage( uri.toString() );
                            FirebaseFirestore firestonedb1 = FirebaseFirestore.getInstance();
                            String path2 = "Collection/";
                            DocumentReference documentReference = firestonedb1.collection(path2).document(dropname);
                            documentReference.update("image", FieldValue.arrayUnion(uri.toString()));
                        }
                    });
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                }
            }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onProgress(@NonNull UploadTask.TaskSnapshot snapshot) {
                }
            });

        /// After converting  List of ImageURI to List of ImageURL,we push data to Firestone with path : collectionName/productName.

        FirebaseFirestore firestonePutProduct = FirebaseFirestore.getInstance();
        firestonePutProduct.collection("Collection/" ).document(dropname).set(object)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        parent.progressBar_adddrop.setVisibility(View.INVISIBLE);
                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {

            }
        });

    }

    public static void push_Object_To_FireStone(uploadData parent, String collectionName, String productName , product2 object, List<Uri> listURI )
    {
        /// Push List Image to Storage and Get List of ImageURL
        parent.progressBar.setVisibility(View.VISIBLE);
        FirebaseStorage firebaseStorage = FirebaseStorage.getInstance(); // Init reference
        StorageReference storageReference_ = firebaseStorage.getReference();
        Integer ordinalNumber = 0;
        for (Uri a : listURI)
        {
            String temp = "img" + ordinalNumber.toString();
            StorageReference processPutFile = storageReference_.child(collectionName +"/" + productName +"/"
                    + temp );
            ordinalNumber++;
            processPutFile.putFile(a).addOnCompleteListener(new OnCompleteListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onComplete(@NonNull Task<UploadTask.TaskSnapshot> task) {
                    processPutFile.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                        @Override
                        public void onSuccess(Uri uri) {
                            //// After getting Url of images, we add Urls to resource field in product.
                            object.getResourceID().add(uri.toString());
                            FirebaseFirestore firestonedb1 = FirebaseFirestore.getInstance();
                            DocumentReference documentReference = firestonedb1.collection(collectionName).document(productName);
                            documentReference.update("resourceID", FieldValue.arrayUnion(uri.toString()));
                        }
                    });
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                }
            }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onProgress(@NonNull UploadTask.TaskSnapshot snapshot) {
                }
            });
        }
        /// After converting  List of ImageURI to List of ImageURL,we push data to Firestone with path : collectionName/productName.

        FirebaseFirestore firestonePutProduct = FirebaseFirestore.getInstance();
        firestonePutProduct.collection(collectionName).document(productName).set(object)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        parent.progressBar.setVisibility(View.INVISIBLE);
                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {

            }
        });

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
                            List<Integer> b = new ArrayList<>();
                            b.add(1);
                            b.add(5);


                            /// them 1 product
                            product mproduct = new product("111","ok","333",null,b,3);
                            firestonedb.collection(folderName).document("productlist").set(mproduct)
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
    public static void LoadProductInformation(String path, List<product2> productList)
    {
        FirebaseFirestore firestoneGetProduct = FirebaseFirestore.getInstance();
        firestoneGetProduct.collection(path).get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {

                productList.clear();
                for (DocumentSnapshot documentSnapshot : task.getResult())
                {

                    // U have to need default constructor in product2 class to use the sequence below
                    product2 temp = documentSnapshot.toObject(product2.class);
                    productList.add(temp);
                    int d = 1;
                }
                AddDrop.imagedropAdapter_.notifyDataSetChanged();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {

            }
        });
    }

    public static void LoadDropInformation(String path, List<drop2> listDrop)
    {
        FirebaseFirestore firestoneGetDrop = FirebaseFirestore.getInstance();
        firestoneGetDrop.collection(path).get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                DataManager.listDrop.clear();
                for (DocumentSnapshot documentSnapshot : task.getResult()) {
                    // U have to need default constructor in drop2 class to use the sequence below
                    //List<Map<String, Object>> productList = (List<Map<String, Object>>) documentSnapshot.get("productList");
                    List<product2> productList_ = documentSnapshot.toObject(ProductList.class).productList;
                   String image_ = documentSnapshot.get("image").toString();
                   String processed_image;
                    processed_image = image_.replace("[","");
                    processed_image.replace("]","");
                   //image_.indexOf(1,image_.length()-2);
                   String caption_ = documentSnapshot.get("caption").toString();
                   String status_ = documentSnapshot.get("status").toString();
                   String type_ = documentSnapshot.get("type").toString();
                   String categoryName_ = documentSnapshot.get("categoryNumber").toString();
                   List<String> productNameList = documentSnapshot.toObject(ProductNameList.class).listProductName;
                    drop2 sample = new drop2(processed_image,caption_,status_,type_,categoryName_,productNameList,productList_);
                    listDrop.add(sample);
                    if (listCategory.get(categoryName_) == null)
                    {
                        List<drop2> temp = new ArrayList<>();
                        temp.add(sample);
                        listCategory.put(categoryName_,temp);
                    }
                    else
                    {
                        listCategory.get(categoryName_).add(sample);
                    }
                    int a = 2;
                }

               int a = 0;

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {

            }
        });
    }
    public static void getImgUrlFromFirestone1(Context parent,String collection, List<product2> productList) {
        ((uploadData)parent).progressBar.setVisibility(View.VISIBLE);
        FirebaseFirestore firestoneGetProduct = FirebaseFirestore.getInstance();
        firestoneGetProduct.collection(collection).get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {

                productList.clear();
                for (DocumentSnapshot documentSnapshot : task.getResult())
                {
                    // U have to need default constructor in product2 class to use the sequence below
                    product2 temp = documentSnapshot.toObject(product2.class);
                    productList.add(temp);
                    ((uploadData)parent).imageAdapter_.notifyDataSetChanged();
                }
                int c = 2;
                ((uploadData)parent).progressBar.setVisibility(View.INVISIBLE);
//                ((uploadData)parent).txt1.setText(productList.get(0).getProductId());
//                ((uploadData)parent).txt2.setText(productList.get(1).getProductId());
//                ((uploadData)parent).txt3.setText(productList.get(2).getProductId());
                ((uploadData)parent).imageAdapter_.notifyDataSetChanged();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {

            }
        });
    }


    public void getImgUrlFromFirestone(Context parent,String collection, List<product> productList) {
        ((uploadData)parent).progressBar.setVisibility(View.VISIBLE);
       firestonedb.collection(collection).get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                productList.clear();
                for (DocumentSnapshot documentSnapshot : task.getResult())
                {
                    product temp = documentSnapshot.toObject(product.class);
                    productList.add(temp);
                }
                int c = 2;
//                ((uploadData)parent).progressBar.setVisibility(View.INVISIBLE);
//                ((uploadData)parent).txt1.setText(productList.get(0).getProductId());
//                ((uploadData)parent).txt2.setText(productList.get(1).getProductId());
//                ((uploadData)parent).txt3.setText(productList.get(2).getProductId());

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
    public static String getFileExtension(ContentResolver contentResolver,Uri uri)
    {
        MimeTypeMap mimeTypeMap = MimeTypeMap.getSingleton();
        return mimeTypeMap.getExtensionFromMimeType(contentResolver.getType(uri));
    }

            //endregion//

    //Huynh//
    public static void getShoeInBagFromFirestone(String collection, List<ShoeInBag> productList) {
        FirebaseFirestore firestoneGetProduct = FirebaseFirestore.getInstance();
        firestoneGetProduct.collection(collection).get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                productList.clear();
                for (DocumentSnapshot documentSnapshot : task.getResult())
                {
                    // U have to need default constructor in ShIB class to use the sequence below
                    ShoeInBag temp = documentSnapshot.toObject(ShoeInBag.class);
                    productList.add(temp);
                    //((ShopFragment)parent).shoeInBagAdapter.notifyDataSetChanged();
                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {

            }
        });
    }


    //Tang giam amount trong BAG cua User
    public void increaseAmount(List<ShoeInBag> list, String collection){


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
