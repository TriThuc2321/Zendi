package com.example.zendi_application.wishFragment;

import android.content.Context;
import android.graphics.Canvas;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.zendi_application.DataManager;
import com.example.zendi_application.R;
import com.example.zendi_application.shopFragment.RecyclerViewClickInterface;
import com.example.zendi_application.shopFragment.ShoeInBag;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.FirebaseFirestore;

import it.xabaras.android.recyclerview.swipedecorator.RecyclerViewSwipeDecorator;

import static android.widget.Toast.LENGTH_LONG;

public class WishlistFragment extends Fragment implements RecyclerViewClickInterface {
    public ShoeInWishAdapter shoeInWishAdapter;
    RecyclerView recyclerView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_wishlist, container, false);
        shoeInWishAdapter = new ShoeInWishAdapter();
        recyclerView = view.findViewById(R.id.wishListrcv);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this.getContext(), recyclerView.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
        ;
        recyclerView.setHasFixedSize(true);
        shoeInWishAdapter.setData(DataManager.shoeInWish);
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(simpleCallback);
        itemTouchHelper.attachToRecyclerView(recyclerView);
        recyclerView.setAdapter(shoeInWishAdapter);
        return view;
    }

    ItemTouchHelper.SimpleCallback simpleCallback = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.DOWN|ItemTouchHelper.LEFT) {
        @Override
        public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
            return false;
        }

        @Override
        public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
            int position = viewHolder.getAdapterPosition();
            switch (direction){
                case ItemTouchHelper.DOWN:
                    deleteItem(DataManager.shoeInWish.get(position));
                    DataManager.shoeInWish.remove(position);
                    shoeInWishAdapter.notifyDataSetChanged();
                    Toast.makeText(getContext(), "Unliked it", LENGTH_LONG).show();
                case ItemTouchHelper.LEFT:
                    break;
            }

        }

        @Override
        public void onChildDraw(@NonNull Canvas c, @NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive) {
            new RecyclerViewSwipeDecorator.Builder(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive)
                    .addSwipeLeftBackgroundColor(R.color.com_facebook_messenger_blue)
                    .addSwipeLeftActionIcon(R.drawable.ic_baseline_delete_forever_24)
                    .addSwipeLeftLabel("Delete from your favourite")
                    .create()
                    .decorate();
            super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);
        }
    };

    @Override
    public void onLongItemClick(int position) {

    }

    public  void deleteItem(final ShoeInBag shoe){
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection("InWish").document(shoe.getProductId())
                .delete().addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
            }
        });
    }
}
