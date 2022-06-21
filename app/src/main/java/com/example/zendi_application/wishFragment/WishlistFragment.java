package com.example.zendi_application.wishFragment;

import android.content.Context;
import android.graphics.Canvas;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.zendi_application.DataManager;
import com.example.zendi_application.HomeScreen;
import com.example.zendi_application.R;
import com.example.zendi_application.dropFragment.DetailProductFragment;
import com.example.zendi_application.shopFragment.RecyclerViewClickInterface;
import com.example.zendi_application.shopFragment.ShoeInBag;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

import it.xabaras.android.recyclerview.swipedecorator.RecyclerViewSwipeDecorator;

import static android.widget.Toast.LENGTH_LONG;

public class WishlistFragment extends Fragment implements RecyclerViewClickInterface {
    RecyclerView recyclerView;
    TextView emptyText;
    ImageView deleteFav;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_wishlist, container, false);
        emptyText = view.findViewById(R.id.emptyText);
        deleteFav = view.findViewById(R.id.btn_delete_fav);
        DataManager.shoeInWishAdapter = new ShoeInWishAdapter();

        recyclerView = view.findViewById(R.id.wishListrcv);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this.getContext(), recyclerView.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setHasFixedSize(true);
        DataManager.shoeInWishAdapter = new ShoeInWishAdapter(deleteFav,emptyText,DataManager.shoeInWish, new ShoeInWishAdapter.DeleteFavListener() {

                    @Override
                    public void onDeleteFavBtnClick(ShoeInBag shoe) {
                        deleteItem(shoe);
                        //thang
                        String docName3 = shoe.getProductId();
                        FirebaseFirestore db3 = FirebaseFirestore.getInstance();
                        db3.collection("InWish/" + DataManager.host.getId() + "/ShoeinWish").document(docName3)
                                .delete().addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                            }
                        });
                        DataManager.shoeInWish.remove(shoe);
                        DataManager.shoeInWishAdapter.notifyDataSetChanged();
                        Toast.makeText(getContext(), "Deleted it", LENGTH_LONG).show();
                    }
                }, new ShoeInWishAdapter.ItemClickListener() {
                    @Override
                    public void onItemClick(ShoeInBag shoe) {
                        AppCompatActivity activity = (AppCompatActivity)getContext();
                        FragmentDialogBox myFragment = new FragmentDialogBox();
                        ((FragmentDialogBox)myFragment).recieveDrop(shoe);
                        ((HomeScreen)activity).appBarLayout.setVisibility(View.INVISIBLE);
                        ((HomeScreen)activity).mNavigationView.setVisibility(View.INVISIBLE);
                        activity.getSupportFragmentManager().beginTransaction().replace(R.id.home_screen, myFragment).addToBackStack(null).commit();
                    }

                });
        DataManager.shoeInWishAdapter.setData(DataManager.shoeInWish);

        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(simpleCallback);
        itemTouchHelper.attachToRecyclerView(recyclerView);
        recyclerView.setAdapter(DataManager.shoeInWishAdapter);
        return view;
    }

    ItemTouchHelper.SimpleCallback simpleCallback = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT|ItemTouchHelper.DOWN| ItemTouchHelper.RIGHT) {
        @Override
        public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
            return false;
        }

        @Override
        public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
            int position = viewHolder.getAdapterPosition();
            switch (direction){
                case ItemTouchHelper.LEFT:
                    deleteItem(DataManager.shoeInWish.get(position));
                    //thang
                    String docName = DataManager.shoeInWish.get(position).getProductId();
                    FirebaseFirestore db = FirebaseFirestore.getInstance();
                    db.collection("InWish/" + DataManager.host.getId() + "/ShoeinWish").document(docName)
                            .delete().addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                        }
                    });
                    //
                    DataManager.shoeInWish.remove(DataManager.shoeInWish.get(position));
                    DataManager.shoeInWishAdapter.notifyDataSetChanged();
                    Toast.makeText(getContext(), "Deleted it", LENGTH_LONG).show();
                case ItemTouchHelper.RIGHT:
                    deleteItem(DataManager.shoeInWish.get(position));
                    //thang
                    String docName2 = DataManager.shoeInWish.get(position).getProductId();
                    FirebaseFirestore db2 = FirebaseFirestore.getInstance();
                    db2.collection("InWish/" + DataManager.host.getId() + "/ShoeinWish").document(docName2)
                            .delete().addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                        }
                    });
                    //
                    DataManager.shoeInWish.remove(DataManager.shoeInWish.get(position));
                    DataManager.shoeInWishAdapter.notifyDataSetChanged();
                    Toast.makeText(getContext(), "Deleted it", LENGTH_LONG).show();
                    break;
                case ItemTouchHelper.DOWN:
                    deleteItem(DataManager.shoeInWish.get(position));
                    //thang
                    String docName3 = DataManager.shoeInWish.get(position).getProductId();
                    FirebaseFirestore db3 = FirebaseFirestore.getInstance();
                    db3.collection("InWish/" + DataManager.host.getId() + "/ShoeinWish").document(docName3)
                            .delete().addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                        }
                    });
                    //
                    DataManager.shoeInWish.remove(DataManager.shoeInWish.get(position));
                    DataManager.shoeInWishAdapter.notifyDataSetChanged();
                    Toast.makeText(getContext(), "Deleted it", LENGTH_LONG).show();
            }
        }
    };

    @Override
    public void onLongItemClick(int position) {

    }

    public void addNewShoeSize(ShoeInBag shoe, String newsize){
        String olddocName = shoe.getProductId() + "_" + shoe.getShoeSize() ; //path the old size
        String newdocName = shoe.getProductId() + "_" + newsize ;
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        Map<String, Object> s = new HashMap<>();
        s.put("ResourceID",shoe.getResourceID());
        s.put("productId",shoe.getProductId());
        s.put("productName",shoe.getProductName());
        s.put("productPrice",shoe.getProductPrice());
        s.put("shoeAmount","1");
        s.put("shoeSize",newsize);
        db.collection("InBag/"+DataManager.host.getId()+"/ShoeList").document(newdocName).set(s).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
            }
        });
        db.collection("InWish/"+DataManager.host.getId()+"/ShoeinWish").document(olddocName)
                .delete().addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
            }
        });
        DataManager.shoeInWish.remove(shoe);
        DataManager.shoeInWishAdapter.notifyDataSetChanged();
    }

    public void upSize(ShoeInBag shoe, String size){
        String docName = shoe.getProductId() + "_" + shoe.getShoeSize();
        final DocumentReference docRef = FirebaseFirestore.getInstance().collection("InWish/"+DataManager.host.getId()+"/ShoeinWish")
                .document(docName);
        Map<String, Object> map = new HashMap<>();
        map.put("shoeSize",size);
        docRef.update(map).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {

            }
        });
       // DataManager.shoeInWish.remove(shoe);
        DataManager.shoeInWishAdapter.notifyDataSetChanged();
    }
    public  void deleteItem(final ShoeInBag shoe){
        String docName = shoe.getProductId() + "_" + shoe.getShoeSize();
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection("InWish/"+DataManager.host.getId()+"/ShoeinWish").document(docName)
                .delete().addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
            }
        });
    }
}
