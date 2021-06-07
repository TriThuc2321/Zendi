package com.example.zendi_application.shopFragment;

import android.graphics.Canvas;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.zendi_application.DataManager;
import com.example.zendi_application.HomeScreen;
import com.example.zendi_application.R;
import com.example.zendi_application.wishFragment.FragmentDialogBox;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import it.xabaras.android.recyclerview.swipedecorator.RecyclerViewSwipeDecorator;

import static android.widget.Toast.LENGTH_LONG;

public class ShopFragment extends Fragment implements RecyclerViewClickInterface, View.OnClickListener{

    Button settle;
    RecyclerView recyclerView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_shop, container, false);
        DataManager.shoeInBagAdapter = new ShoeInBagAdapter();
        settle = view.findViewById(R.id.settle_place);
        settle.setText(total());
        recyclerView = view.findViewById(R.id.shop_fragment_rcv);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this.getContext(),recyclerView.VERTICAL,false);
        recyclerView.setLayoutManager(linearLayoutManager);;
        recyclerView.setHasFixedSize(true);

        DataManager.shoeInBagAdapter.setData(DataManager.list);

        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(simpleCallback);
        itemTouchHelper.attachToRecyclerView(recyclerView);
        recyclerView.setAdapter(DataManager.shoeInBagAdapter);
        settle.setOnClickListener(this);
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
                    DataManager.shoeInBagAdapter.notifyItemChanged(position);
                    settle.setText(total());
                    upAmount(DataManager.list.get(position),DataManager.list.get(position).getShoeAmount());
                    break;
                 case ItemTouchHelper.RIGHT:
                    DataManager.list.get(position).decreaseAmountView();
                     if (DataManager.list.get(position).getShoeAmount().compareTo("0") == 0) {
                         deleteItem(DataManager.list.get(position));
                         DataManager.list.remove(position);
                         DataManager.shoeInBagAdapter.notifyDataSetChanged();
                         settle.setText(total());
                         break;
                     }
                     DataManager.shoeInBagAdapter.notifyDataSetChanged();
                     settle.setText(total());
                     upAmount(DataManager.list.get(position),DataManager.list.get(position).getShoeAmount());
                    break;
                case ItemTouchHelper.DOWN:
                    deleteItem(DataManager.list.get(position));
                    DataManager.list.remove(DataManager.list.get(position));
                    DataManager.shoeInBagAdapter.notifyDataSetChanged();
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
                    .addSwipeRightBackgroundColor(R.color.com_facebook_messenger_blue)
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
        String docName = shoe.getProductId() + "_" + shoe.getShoeSize();
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection("InBag/"+DataManager.host.getId()+"/ShoeList").document(docName)
                .delete().addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
            }
        });
    }
    public void upAmount(ShoeInBag shoe, String amount){
        String docName = shoe.getProductId() + "_" + shoe.getShoeSize();
        final DocumentReference docRef = FirebaseFirestore.getInstance().collection("InBag/"+DataManager.host.getId()+"/ShoeList")
                .document(docName);
        Map<String, Object> map = new HashMap<>();
        map.put("shoeAmount",amount);
        docRef.update(map).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {

            }
        });
    }

    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.settle_place){
                openDialog();
        }
    }

    private void openDialog() {
        OrderInfoDialog dia = new OrderInfoDialog();
        dia.show(getFragmentManager(),"Order Information");
    }

//    @Override
//    public void applyTexts(String address, String contact) {
//      //  upBilltoFireStore(address,contact);
//    }
//    public void upBilltoFireStore(String address, String contact){
//        FirebaseFirestore db = FirebaseFirestore.getInstance();
//        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
//        Date date = new Date();
//        String datetime = formatter.format(date);
//        String docName = "Bill" + DataManager.host.getId() + datetime;
//        Map<String, Object> s1 = new HashMap<>();
//        s1.put("Address",address);
//        s1.put("Contact", contact);
//        db.collection("Ordered/").document(docName).set(s1).addOnSuccessListener(new OnSuccessListener<Void>() {
//            @Override
//            public void onSuccess(Void aVoid) {
//
//            }
//        });
//        String docName2 = docName + "/ShoeList";
//        for(ShoeInBag ite: DataManager.list){
//            String docShoe = ite.getProductId() + "_" + ite.getShoeSize();
//            Map<String, Object> s = new HashMap<>();
//            s.put("ResourceID",ite.getResourceID());
//            s.put("productId",ite.getProductId());
//            s.put("productName",ite.getProductName());
//            s.put("productPrice",ite.getProductPrice());
//            s.put("shoeAmount",ite.getShoeAmount());
//            s.put("shoeSize",ite.getShoeSize());
//            s.put("remainingAmount",ite.getRemainingAmount());
//            s.put("type",ite.getType());
//            s.put("productType",ite.getProductType());
//            s.put("productBrand",ite.getProductBrand());
//            db.collection("Ordered/" + docName + "/ShoeList").document(docShoe).set(s).addOnSuccessListener(new OnSuccessListener<Void>() {
//                @Override
//                public void onSuccess(Void aVoid) {
//
//                }
//            });
//        }
//       DataManager.list.clear();
//       DataManager.shoeInBagAdapter.notifyDataSetChanged();
//       String docc = "ShoeList";
//        db.collection("InBag/" + DataManager.host.getId()).document(docc).delete().addOnSuccessListener(new OnSuccessListener<Void>() {
//            @Override
//            public void onSuccess(Void aVoid) {
//
//            }
//        });
//    }
}
