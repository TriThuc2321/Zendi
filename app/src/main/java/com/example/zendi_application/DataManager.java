package com.example.zendi_application;

import android.content.ContentResolver;
import android.content.Context;
import android.net.Uri;
import android.util.Log;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.ItemTouchHelper;

import com.example.zendi_application.ActivityAccount.Admin.Ordered;
import com.example.zendi_application.ActivityAccount.Admin.ShoeOrderedList;
import com.example.zendi_application.ActivityAccount.User;
import com.example.zendi_application.ActivityAccount.edit_deleteDropPackage.edit_deleteDrop;
import com.example.zendi_application.ActivityAccount.edit_deleteProductPackage.edit_deleteProduct;
import com.example.zendi_application.addProductPackage.AddDrop;
import com.example.zendi_application.addProductPackage.ProductList;
import com.example.zendi_application.addProductPackage.ProductNameList;
import com.example.zendi_application.addProductPackage.uploadData;
import com.example.zendi_application.dropFragment.DetailProductFragment;
import com.example.zendi_application.dropFragment.DropFragment;
import com.example.zendi_application.dropFragment.ModelSupportLoad;
import com.example.zendi_application.dropFragment.category_drop.category;
import com.example.zendi_application.dropFragment.drop.drop;
import com.example.zendi_application.dropFragment.drop.drop2;
import com.example.zendi_application.dropFragment.product_package.product;
import com.example.zendi_application.dropFragment.product_package.product2;
import com.example.zendi_application.shopFragment.ShoeInBag;
import com.example.zendi_application.shopFragment.ShoeInBagAdapter;
import com.example.zendi_application.shopFragment.ShopFragment;
import com.example.zendi_application.wishFragment.ShoeInWishAdapter;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
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
    public static ShoeInBagAdapter shoeInBagAdapter;
    public static ShoeInWishAdapter shoeInWishAdapter;
    public static DataManager instance;
    public static List<product2> listProduct = new ArrayList<>(); // All products
    public static List<drop2> listDrop = new ArrayList<>(); // All drops
    public static HashMap<String,List<drop2>> listCategory = new HashMap<>(); // All categories
    public static User host = new User();
    public static List<User> listUsers = new ArrayList<>();
    public static HashMap<String,Integer> sizeConvert = new HashMap<>();
    public static List<ShoeInBag> list = new ArrayList<>();
    public static List<ShoeInBag> shoeInWish = new ArrayList<>();
    public static List<Ordered> orderedList = new ArrayList<>();
    // Attributes
    private Uri imageUri;
    private FirebaseStorage storage;
    private StorageReference storageReference;
    private DatabaseReference root = FirebaseDatabase.getInstance().getReference();
    private static FirebaseFirestore firestonedb = FirebaseFirestore.getInstance();
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
    public static void push_drop_To_FireStone_editdrop(edit_deleteDrop parent, String dropname, drop2 object, Uri imageUri )
    {

        /// Push List Image to Storage and Get List of ImageURL
//        parent.progressBar_adddrop.setVisibility(View.VISIBLE);
        if (imageUri != null) {
            FirebaseStorage firebaseStorage = FirebaseStorage.getInstance(); // Init reference
            StorageReference storageReference_ = firebaseStorage.getReference();
            StorageReference processPutFile = storageReference_.child("Collection/" + dropname);
            processPutFile.putFile(imageUri).addOnCompleteListener(new OnCompleteListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onComplete(@NonNull Task<UploadTask.TaskSnapshot> task) {
                    processPutFile.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                        @Override
                        public void onSuccess(Uri uri) {
                            //// After getting Url of images, we add Urls to Image
                            object.setImage(uri.toString());
                            FirebaseFirestore firestonedb1 = FirebaseFirestore.getInstance();
                            String path2 = "Collection/";
                            DocumentReference documentReference = firestonedb1.collection(path2).document(dropname);
                            documentReference.update("image", FieldValue.arrayUnion(uri.toString()));
                            String temp = uri.toString();
                            for (drop2 drop_ : DataManager.listDrop) {
                            if (drop_.getCategoryNumber().compareTo(edit_deleteDrop.selectedDrop_categoryNumber) == 0 && drop_.getDropNumber().compareTo(edit_deleteDrop.selectedDrop_dropNumber) == 0)
                            {
                                drop_.setImage(temp);
                                edit_deleteDrop.dropAdapter_editdrop.SetData(DataManager.listDrop,parent);
                                edit_deleteDrop.dropAdapter_editdrop.notifyDataSetChanged();
                                edit_deleteDrop.URLimage = temp;
                                break;
                            }
                            }
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
        firestonePutProduct.collection("Collection/" ).document(dropname).set(object)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
//                        parent.progressBar_adddrop.setVisibility(View.INVISIBLE);
                        Toast.makeText(parent, "Update drop successfully !", Toast.LENGTH_SHORT).show();
                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {

            }
        });

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

    public static void Update_Amount(ShoeInBag selectedShoe)
    {
        FirebaseFirestore firestonedb1 = FirebaseFirestore.getInstance();
        DocumentReference documentReference = firestonedb1.collection("Product/").document(selectedShoe.getProductId());
        documentReference.update("remainingAmount", selectedShoe.getRemainingAmount());
    }
    public static void update_Amount_Of_Shoe_In_Bag(ShoeInBag selectedShoe,ShoeInBag shoe_need_to_increase_amount,User user,Context mContext)
    {
        FirebaseFirestore firestonedb1 = FirebaseFirestore.getInstance();
        DocumentReference documentReference = firestonedb1.collection("InBag/" + user.getId() +"/ShoeList/").document(selectedShoe.getProductId() + "_" + selectedShoe.getShoeSize());
//        Integer total_amount = Integer.parseInt(shoe_need_to_increase_amount.getShoeAmount()) + Integer.parseInt(selectedShoe.getShoeAmount());
        Integer new_amount = 1;
        for (ShoeInBag ite : DataManager.list){
            if (ite.getProductId().compareTo(selectedShoe.getProductId()) == 0 && ite.getShoeSize().compareTo(selectedShoe.getShoeSize()) == 0 )
            {
                new_amount = Integer.parseInt(ite.getShoeAmount()) + 1;
                ite.setShoeAmount(new_amount.toString());
                break;
            }
        }
        String a = new_amount.toString();
        documentReference.update("shoeAmount",a);
        Toast.makeText(mContext,"Product is Added !!",Toast.LENGTH_SHORT).show();
        /// Update admount
       //Update_Amount(selectedShoe);
    }
    public static void push_Shoe_To_Bag(ShoeInBag selectedShoe,User user,Context mContext)
    {
        FirebaseFirestore firestonePutProduct = FirebaseFirestore.getInstance();
        firestonePutProduct.collection("InBag/" + user.getId() +"/ShoeList").document(selectedShoe.getProductId() + "_" + selectedShoe.getShoeSize()).set(selectedShoe)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
//                      Update_Amount(selectedShoe);
                        DataManager.list.add(selectedShoe);
                        Toast.makeText(mContext,"Product is Added !!",Toast.LENGTH_SHORT).show();
                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(mContext,"Adding Failed !!",Toast.LENGTH_SHORT).show();
            }
        });
        Map<String, Object> info = new HashMap<>();
        info.put("Name", user.getName());
        info.put("Id", user.getId());
        firestonePutProduct.collection("InBag").document(user.getId()).set(info)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(mContext,"Adding Failed !!",Toast.LENGTH_SHORT).show();
            }
        });

    }
    public static void edit_Object_To_FireStone(edit_deleteProduct parent, String collectionName, String productName , product2 object)
    {
        /// Push List Image to Storage and Get List of ImageURL
        parent.progressBar_editproduct.setVisibility(View.VISIBLE);
        /// After converting  List of ImageURI to List of ImageURL,we push data to Firestone with path : collectionName/productName.
        FirebaseFirestore firestonePutProduct = FirebaseFirestore.getInstance();
        firestonePutProduct.collection(collectionName).document(productName).set(object)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        parent.progressBar_editproduct.setVisibility(View.INVISIBLE);
                        //
                        Toast.makeText(parent,"Thêm Sản Phẩm Thành Công !",Toast.LENGTH_SHORT);
                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {

            }
        });

    }
    public static void Edit_Product_ShoeinBag(product2 shoe, String which)
    {
        List<String> listUser = new ArrayList<>();
        FirebaseFirestore firestoneGetProduct = FirebaseFirestore.getInstance();
        firestoneGetProduct.collection("InBag").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                for (DocumentSnapshot documentSnapshot : task.getResult()) {
                    listUser.add(documentSnapshot.getId());
                }
/////////////////////////////////////////
                for (String temp : listUser) {
                    firestoneGetProduct.collection("InBag/" + temp + "/ShoeList").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<QuerySnapshot> task) {
                            for (DocumentSnapshot documentSnapshot : task.getResult()) {
                                ShoeInBag temp1 = documentSnapshot.toObject(ShoeInBag.class);
                                String ShoeinbagID = documentSnapshot.getId();
                                if (which == "delete")
                                {
                                    if (temp1.getProductId().compareTo(shoe.getProductId()) == 0)
                                    {
                                        FirebaseFirestore db = FirebaseFirestore.getInstance();
                                        db.collection("InBag/" + temp + "/ShoeList").document(ShoeinbagID)
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
                                    }

                                }
                                else if (which == "edit")
                                {
                                    if (temp1.getProductId().compareTo(shoe.getProductId()) == 0) {
                                        temp1.setProductName(shoe.getProductName());
                                        temp1.setProductBrand(shoe.getProductBrand());
                                        temp1.setProductPrice(shoe.getProductPrice());
                                        temp1.setProductType(shoe.getProductType());
                                        temp1.getRemainingAmount().set(0,shoe.getRemainingAmount().get(0));
                                        temp1.getRemainingAmount().set(1,shoe.getRemainingAmount().get(1));
                                        temp1.getRemainingAmount().set(2,shoe.getRemainingAmount().get(2));
                                        temp1.getRemainingAmount().set(3,shoe.getRemainingAmount().get(3));
                                        temp1.getRemainingAmount().set(4,shoe.getRemainingAmount().get(4));
                                        temp1.getRemainingAmount().set(5,shoe.getRemainingAmount().get(5));
                                        temp1.getRemainingAmount().set(6,shoe.getRemainingAmount().get(6));
                                        temp1.getRemainingAmount().set(7,shoe.getRemainingAmount().get(7));
                                        temp1.getRemainingAmount().set(8,shoe.getRemainingAmount().get(8));
                                        temp1.getRemainingAmount().set(9,shoe.getRemainingAmount().get(9));
                                        temp1.getRemainingAmount().set(10,shoe.getRemainingAmount().get(10));
                                        temp1.getRemainingAmount().set(11,shoe.getRemainingAmount().get(11));
                                        temp1.getRemainingAmount().set(12,shoe.getRemainingAmount().get(12));
                                        temp1.getRemainingAmount().set(13,shoe.getRemainingAmount().get(13));
                                        FirebaseFirestore firestonePutProduct = FirebaseFirestore.getInstance();
                                        firestonePutProduct.collection("InBag/" + temp + "/ShoeList").document(ShoeinbagID).set(temp1)
                                                .addOnCompleteListener(new OnCompleteListener<Void>() {
                                                    @Override
                                                    public void onComplete(@NonNull Task<Void> task) {
//
                                                    }
                                                }).addOnFailureListener(new OnFailureListener() {
                                            @Override
                                            public void onFailure(@NonNull Exception e) {

                                            }
                                        });
                                    }
                                }

                            }


                        }

                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {

                        }
                    });

/////////////
                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {

            }
        });
    }
    public static void Edit_Product_InWish(product2 shoe, String which)
    {
        List<String> listUser = new ArrayList<>();
        FirebaseFirestore firestoneGetProduct = FirebaseFirestore.getInstance();
        firestoneGetProduct.collection("InWish").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                for (DocumentSnapshot documentSnapshot : task.getResult()) {
                    listUser.add(documentSnapshot.getId());
                }
/////////////////////////////////////////
                for (String temp : listUser) {
                    firestoneGetProduct.collection("InWish/" + temp + "/ShoeinWish").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<QuerySnapshot> task) {
                            for (DocumentSnapshot documentSnapshot : task.getResult()) {
                                ShoeInBag temp1 = documentSnapshot.toObject(ShoeInBag.class);
                                String ShoeinbagID = documentSnapshot.getId();
                                if (which == "delete")
                                {
                                    if (temp1.getProductId().compareTo(shoe.getProductId()) == 0)
                                    {
                                        FirebaseFirestore db = FirebaseFirestore.getInstance();
                                        db.collection("InWish/" + temp + "/ShoeinWish").document(ShoeinbagID)
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
                                    }

                                }
                                else if (which == "edit")
                                {
                                    if (temp1.getProductId().compareTo(shoe.getProductId()) == 0) {
                                        temp1.setProductName(shoe.getProductName());
                                        temp1.setProductBrand(shoe.getProductBrand());
                                        temp1.setProductPrice(shoe.getProductPrice());
                                        temp1.setProductType(shoe.getProductType());
                                        temp1.getRemainingAmount().set(0,shoe.getRemainingAmount().get(0));
                                        temp1.getRemainingAmount().set(1,shoe.getRemainingAmount().get(1));
                                        temp1.getRemainingAmount().set(2,shoe.getRemainingAmount().get(2));
                                        temp1.getRemainingAmount().set(3,shoe.getRemainingAmount().get(3));
                                        temp1.getRemainingAmount().set(4,shoe.getRemainingAmount().get(4));
                                        temp1.getRemainingAmount().set(5,shoe.getRemainingAmount().get(5));
                                        temp1.getRemainingAmount().set(6,shoe.getRemainingAmount().get(6));
                                        temp1.getRemainingAmount().set(7,shoe.getRemainingAmount().get(7));
                                        temp1.getRemainingAmount().set(8,shoe.getRemainingAmount().get(8));
                                        temp1.getRemainingAmount().set(9,shoe.getRemainingAmount().get(9));
                                        temp1.getRemainingAmount().set(10,shoe.getRemainingAmount().get(10));
                                        temp1.getRemainingAmount().set(11,shoe.getRemainingAmount().get(11));
                                        temp1.getRemainingAmount().set(12,shoe.getRemainingAmount().get(12));
                                        temp1.getRemainingAmount().set(13,shoe.getRemainingAmount().get(13));
                                        FirebaseFirestore firestonePutProduct = FirebaseFirestore.getInstance();
                                        firestonePutProduct.collection("InWish/" + temp + "/ShoeinWish").document(ShoeinbagID).set(temp1)
                                                .addOnCompleteListener(new OnCompleteListener<Void>() {
                                                    @Override
                                                    public void onComplete(@NonNull Task<Void> task) {
//
                                                    }
                                                }).addOnFailureListener(new OnFailureListener() {
                                            @Override
                                            public void onFailure(@NonNull Exception e) {

                                            }
                                        });
                                    }
                                }

                            }


                        }

                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {

                        }
                    });

/////////////
                }
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
                        //
                        Toast.makeText(parent,"Thêm Sản Phẩm Thành Công !",Toast.LENGTH_SHORT);
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
                AddDrop.imageproductlistAdapter_.notifyDataSetChanged();

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
                if (listCategory != null) listCategory.clear();
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
                   String dropName_ = documentSnapshot.get("dropNumber").toString();
                   List<String> productNameList = documentSnapshot.toObject(ProductNameList.class).listProductName;
                    drop2 sample = new drop2(processed_image,caption_,status_,type_,categoryName_,dropName_,productNameList,productList_);
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
                for (String key : DataManager.listCategory.keySet())
                {
                    for (int i = 0; i < listCategory.get(key).size()-1; i++)
                    {
                        if (Integer.parseInt(listCategory.get(key).get(i).getDropNumber()) > Integer.parseInt(listCategory.get(key).get(i+1).getDropNumber()))
                        {
                            drop2 a = new drop2(listCategory.get(key).get(i).getImage(),
                                    listCategory.get(key).get(i).getCaption(),listCategory.get(key).get(i).getStatus(),
                                    listCategory.get(key).get(i).getType(), listCategory.get(key).get(i).getCategoryNumber(),
                                    listCategory.get(key).get(i).getDropNumber(),listCategory.get(key).get(i).getListProductName(),
                                    listCategory.get(key).get(i).getProductList());
                            listCategory.get(key).set(i,listCategory.get(key).get(i+1));
                            listCategory.get(key).set(i+1,a);
                        }
                    }
                }
               int a = 0;

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {

            }
        });
    }
    public static void Delete_product_to_Firestone(edit_deleteProduct parent,product2 temp)
    {
        parent.progressBar_editproduct.setVisibility(View.VISIBLE);
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection("Product").document(temp.getProductId())
                .delete()
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        parent.progressBar_editproduct.setVisibility(View.INVISIBLE);
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {

                    }
                });
        for (Map.Entry<String, List<drop2>> entry : DataManager.listCategory.entrySet()) {
            for (Integer i = 0; i < entry.getValue().size();i ++)
            {
                for (int j = 0; j< entry.getValue().get(i).getProductList().size(); j++)
                {
                    String a = entry.getValue().get(i).getProductList().get(j).getProductId();
                    if ( a.compareTo(temp.getProductId()) == 0 ) {
                        entry.getValue().get(i).getProductList().remove(j);
                        if (  entry.getValue().get(i).getListProductName().get(j) != null)
                        entry.getValue().get(i).getListProductName().remove(j);
                        Integer dropNUmber = i + 1;
                        String temp_address = "Drop_" + entry.getValue().get(i).getCategoryNumber() + "_" + dropNUmber.toString() + "/";
                        DataManager.push_drop_To_FireStone_1(parent, temp_address, entry.getValue().get(i));
                    }
                }
            }
        }

    }
    public static void Update_drop_after_delete_product(edit_deleteProduct parent,product2 temp)
    {

    }
    public static void Update_drop_after_edit_product(edit_deleteProduct parent,product2 temp)
    {
        for (Map.Entry<String, List<drop2>> entry : DataManager.listCategory.entrySet()) {
           for (Integer i = 0; i < entry.getValue().size();i ++)
           {
               for (int j = 0; j< entry.getValue().get(i).getProductList().size(); j++)
               {
                   String a = entry.getValue().get(i).getProductList().get(j).getProductId();
                  if ( a.compareTo(temp.getProductId()) == 0 ) {
                      entry.getValue().get(i).getProductList().set(j, temp);
                      Integer dropNUmber = i + 1;
                      String temp_address = "Drop_" + entry.getValue().get(i).getCategoryNumber() + "_" + dropNUmber.toString() + "/";
                      DataManager.push_drop_To_FireStone_1(parent, temp_address, entry.getValue().get(i));
                  }
               }
           }
        }
    }
    public static void push_drop_To_FireStone_1(edit_deleteProduct parent, String dropname, drop2 object)
    {
        /// Push List Image to Storage and Get List of ImageURL
        parent.progressBar_editproduct.setVisibility(View.VISIBLE);
        /// After converting  List of ImageURI to List of ImageURL,we push data to Firestone with path : collectionName/productName.

        FirebaseFirestore firestonePutProduct = FirebaseFirestore.getInstance();
        firestonePutProduct.collection("Collection/" ).document(dropname).set(object)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        parent.progressBar_editproduct.setVisibility(View.INVISIBLE);
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
                }
                int c = 2;
                ((uploadData)parent).progressBar.setVisibility(View.INVISIBLE);
//                ((uploadData)parent).txt1.setText(productList.get(0).getProductId());
//                ((uploadData)parent).txt2.setText(productList.get(1).getProductId());
//                ((uploadData)parent).txt3.setText(productList.get(2).getProductId());
                    uploadData.imageAdapter_.notifyDataSetChanged();
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
       // FirebaseFirestore firestoneGetProduct = FirebaseFirestore.getInstance();
        firestonedb.collection(collection).get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
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
    //Load shoe from InWish
    public static void getShoeInWishFromFirestone(String collection, List<ShoeInBag> productList) {
        //FirebaseFirestore firestone = FirebaseFirestore.getInstance();
        firestonedb.collection(collection).get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
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

    public static void push_Shoe_To_Bag_in_Fragmentdialog(ShoeInBag selectedshoe,User user,Context mContext)
    {
        FirebaseFirestore firestonePutProduct = FirebaseFirestore.getInstance();
        firestonePutProduct.collection("InBag/" + user.getId() +"/ShoeList").document(selectedshoe.getProductId() + "_" + selectedshoe.getShoeSize()).set(selectedshoe)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
//                      Update_Amount(selectedShoe);
                        DataManager.list.add(selectedshoe);
                        DataManager.shoeInBagAdapter.notifyDataSetChanged();
                        Toast.makeText(mContext,"Product is Added !!",Toast.LENGTH_SHORT);
                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(mContext,"Adding Failed !!",Toast.LENGTH_SHORT);
            }
        });
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
    private static DatabaseReference dataBase;
    public static void loadUser(){
        listUsers.clear();
        dataBase = FirebaseDatabase.getInstance().getReference();
        dataBase.child("Users").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                {
                    for (DataSnapshot data : snapshot.getChildren()) {
                        User temp;
                        temp = data.getValue(User.class);
                        listUsers.add(temp);
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private static FirebaseAuth  mAuth = FirebaseAuth.getInstance();;
    private static FirebaseUser currentUser = mAuth.getCurrentUser();;
    public static void GetUser(){
        dataBase = FirebaseDatabase.getInstance().getReference();
        if(currentUser!= null){

            dataBase.child("Users").child(currentUser.getUid()).addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    host = snapshot.getValue(User.class);
                    getShoeInBagFromFirestone("InBag/" +DataManager.host.getId()+"/ShoeList",DataManager.list);
                    getShoeInWishFromFirestone("InWish/"+DataManager.host.getId()+"/ShoeinWish",DataManager.shoeInWish);
                }
                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });

        }

    }
    public static List<ShoeInBag> listShoe;
    public static void getListOrderedFromFirestone() {
        firestonedb.collection("Ordered/").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                orderedList.clear();
                for (DocumentSnapshot documentSnapshot : task.getResult())
                {
                    /*Ordered temp = documentSnapshot.toObject(Ordered.class);
                    orderedList.add(temp);*/
                    /*Ordered temp = new Ordered();
                    temp.setAddress(documentSnapshot.get("Address").toString());
                    temp.setBillId(documentSnapshot.get("BillId").toString());
                    temp.setContact(documentSnapshot.get("Contact").toString());
                    temp.setEmail(documentSnapshot.get("Email").toString());
                    temp.setTotal(documentSnapshot.get("Total").toString());
                    temp.setName(documentSnapshot.get("Name").toString());*/

                    Ordered temp = documentSnapshot.toObject(Ordered.class);
                     listShoe= new ArrayList<>();
                    getListShoeOrderFromFirestone("Ordered/" + documentSnapshot.get("BillId").toString()+"/ShoeList", listShoe);
                    temp.setShoeList(listShoe);
                    orderedList.add(temp);
                }
                int a = orderedList.size();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {

            }
        });
    }
    public static void getListShoeOrderFromFirestone(String collection, List<ShoeInBag> shoeList) {
        firestonedb.collection(collection).get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                shoeList.clear();
                for (DocumentSnapshot documentSnapshot : task.getResult())
                {
                    // U have to need default constructor in ShIB class to use the sequence below
                    ShoeInBag temp = documentSnapshot.toObject(ShoeInBag.class);
                    shoeList.add(temp);
                    //((ShopFragment)parent).shoeInBagAdapter.notifyDataSetChanged();
                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {

            }
        });
    }
}
