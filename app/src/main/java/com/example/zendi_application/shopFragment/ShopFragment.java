package com.example.zendi_application.shopFragment;

import android.graphics.Canvas;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.zendi_application.DataManager;
import com.example.zendi_application.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.List;

import it.xabaras.android.recyclerview.swipedecorator.RecyclerViewSwipeDecorator;

import static android.widget.Toast.LENGTH_LONG;

public class ShopFragment extends Fragment implements RecyclerViewClickInterface{

    Button settle;
    //List<ShoeInBag> shoeInBagList = new ArrayList<>();
    public ShoeInBagAdapter shoeInBagAdapter;
    RecyclerView recyclerView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_shop, container, false);

        shoeInBagAdapter = new ShoeInBagAdapter();
        //DataManager.getShoeInBagFromFirestone(this,"InBag",shoeInBagList);

        settle = view.findViewById(R.id.settle_place);
        settle.setText(total());
        recyclerView = view.findViewById(R.id.shop_fragment_rcv);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this.getContext(),recyclerView.VERTICAL,false);
        recyclerView.setLayoutManager(linearLayoutManager);;
        recyclerView.setHasFixedSize(true);
        shoeInBagAdapter.setData(DataManager.list);
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(simpleCallback);
        itemTouchHelper.attachToRecyclerView(recyclerView);
        recyclerView.setAdapter(shoeInBagAdapter);

        return view;
    }

    String increaseAmount = "One more or swipe down to delete";
    String decreaseAmount = "One less";
    ItemTouchHelper.SimpleCallback simpleCallback = new ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.LEFT|ItemTouchHelper.RIGHT| ItemTouchHelper.DOWN)
    {
        @Override
        public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
            return false;
        }

        @Override
        public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {

            int position = viewHolder.getAdapterPosition();

            switch (direction){
                case ItemTouchHelper.LEFT:
                    DataManager.list.get(position).increaseAmountView();
                    shoeInBagAdapter.notifyItemChanged(position);
                    settle.setText(total());
                    break;
                 case ItemTouchHelper.RIGHT:
                    DataManager.list.get(position).decreaseAmountView();
                    shoeInBagAdapter.notifyItemChanged(position);
                    settle.setText(total());
                    break;
                case ItemTouchHelper.DOWN:
                    deleteItem(DataManager.list.get(position));
                    DataManager.list.remove(position);
                    shoeInBagAdapter.notifyDataSetChanged();
                    settle.setText(total());
                    Toast.makeText(getContext(), "You have just dragged this shoe out of bag", LENGTH_LONG).show();
            }
        }
        @Override
        public void onChildDraw(@NonNull Canvas c, @NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive) {
            new RecyclerViewSwipeDecorator.Builder(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive)
                    .addSwipeRightActionIcon(R.drawable.ic_baseline_exposure_neg_1_24)
                    .addSwipeRightLabel(decreaseAmount)
                    .addSwipeLeftBackgroundColor(R.color.com_facebook_messenger_blue)
                    .addSwipeLeftActionIcon(R.drawable.ic_baseline_plus_one_24)
                    .addSwipeLeftLabel(increaseAmount)
                    .create()
                    .decorate();
            super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);
        }
    };

    @Override
    public  void onLongItemClick(int position) {

    }
    public static String total(){
        String total_ ="SETTLE $";
        Integer temp = 0;
        for (ShoeInBag a : DataManager.list)
        {
            temp += Integer.parseInt(a.getShoeAmount())*Integer.parseInt(a.getProductPrice());
        }
        total_+= temp.toString();
        return total_ ;
    }
    //Delete from FireStore
    public  void deleteItem(final ShoeInBag shoe){
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection("InBag").document(shoe.getProductId())
                .delete().addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
            }
        });
    }
}
