package com.example.zendi_application.shopFragment;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDialogFragment;

import com.example.zendi_application.ActivityAccount.ConfirmEmail.GmailSender;
import com.example.zendi_application.DataManager;
import com.example.zendi_application.R;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.textfield.TextInputEditText;
import com.google.common.util.concurrent.internal.InternalFutureFailureAccess;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.FirebaseFirestore;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class OrderInfoDialog extends AppCompatDialogFragment {
    private TextInputEditText editaddress;
    private TextInputEditText editcontact;
    private TextInputEditText editName;
    public String add;
    public String con;
    public String reciever;
    public String total = total();
    public String ss = ListShoeBought();
    public String totalHost = addTotalToHost();
    TextView totaltv;
    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.order_info_dialog, null);

        builder.setView(view)
                .setTitle("Ordering information")
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .setPositiveButton("Place an order", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if(TextUtils.isEmpty(editaddress.getText().toString()) || TextUtils.isEmpty(editcontact.getText().toString()))
                        {
                            Toast.makeText(getContext(),"Please give fully your information",Toast.LENGTH_SHORT).show();
                        }else {
                            add = editaddress.getText().toString();
                            con = editcontact.getText().toString();
                            reciever = editName.getText().toString();
                            sendEmail(DataManager.host.getEmail());
                            DataManager.host.setTotal(totalHost);
                            upTotalToFirebase(totalHost);
                            upBilltoFireStore(add, con, DataManager.host.getEmail(), reciever);
                            Toast.makeText(getContext(), "Ordered successfully.", Toast.LENGTH_SHORT).show();
                            ShopFragment.settle.setText(ShopFragment.total());
                        }
                    }
                });
        totaltv = view.findViewById(R.id.totaltxt);
        totaltv.setText(total());
        editaddress = view.findViewById(R.id.edit_address);
        editaddress.setText(DataManager.host.getAddress());
        editcontact = view.findViewById(R.id.edit_contact);
        editcontact.setText(DataManager.host.getPhoneNumber());
        editName = view.findViewById(R.id.edit_receiver);
        editName.setText(DataManager.host.getName());

        AlertDialog dialog = builder.create();
        Window window = dialog.getWindow();
        WindowManager.LayoutParams layoutParams = window.getAttributes();
        layoutParams.gravity = Gravity.BOTTOM;
        window.setAttributes(layoutParams);

        return dialog;
    }

    public void upBilltoFireStore(String address, String contact, String email, String name){
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        SimpleDateFormat formatter = new SimpleDateFormat("ddMMyyyyHHmmss");
        SimpleDateFormat formatter2 = new SimpleDateFormat("dd/MM/yyyy");
        Date date = new Date();
        Date date2 = new Date();
        String dated = formatter2.format(date2);
        String datetime = formatter.format(date);
        String docName = DataManager.host.getId() + datetime;
        Map<String, Object> s1 = new HashMap<>();
        s1.put("BillId", docName);
        s1.put("BillDate", dated);
        s1.put("Email", email);
        s1.put("Name", name);
        s1.put("Address",address);
        s1.put("Contact", contact);
        s1.put("Total",total());
        s1.put("BillStatus","0"); //0: not yet delivered 1: delivered
        db.collection("Ordered" ).document(docName).set(s1).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {

            }
        });
        for(ShoeInBag ite: DataManager.list){
            String docShoe = ite.getProductId() + "_" + ite.getShoeSize();
            Map<String, Object> s = new HashMap<>();
            s.put("ResourceID",ite.getResourceID());
            s.put("productId",ite.getProductId());
            s.put("productName",ite.getProductName());
            s.put("productPrice",ite.getProductPrice());
            s.put("shoeAmount",ite.getShoeAmount());
            s.put("shoeSize",ite.getShoeSize());
            s.put("remainingAmount",ite.getRemainingAmount());
            s.put("type",ite.getType());
            s.put("productType",ite.getProductType());
            s.put("productBrand",ite.getProductBrand());
            db.collection("Ordered/" + docName + "/ShoeList" ).document(docShoe).set(s).addOnSuccessListener(new OnSuccessListener<Void>() {
                @Override
                public void onSuccess(Void aVoid) {

                }
            });
            String doc1 = ite.getProductId() + "_" + ite.getShoeSize();
            db.collection("InBag/" + DataManager.host.getId() + "/ShoeList").document(doc1).delete().addOnSuccessListener(new OnSuccessListener<Void>() {
                @Override
                public void onSuccess(Void aVoid) {
                }
            });
        }
        DataManager.list.clear();
        DataManager.shoeInBagAdapter.notifyDataSetChanged();
    }

    public String ListShoeBought(){
        String var = "";
        for(ShoeInBag ite: DataManager.list){
            var = var + ite.getProductName() + " Size: " + ite.getShoeSize() + " Quantity: " + ite.getShoeAmount() + "\n";
        }
        return var;
    }
    public void sendEmail(String email){
        Thread sender = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    GmailSender sender = new GmailSender("zendiapplication@gmail.com", "ThucThienThangHuynh123");
                    sender.sendMail("Order in Zendi",
                             "Dear " + reciever + ";\n" +
                                   "Thanks for ordering our product and choosing Zendi as your best Shoe Shop.\n" +
                                   "You have just bought:\n" +
                                   ss +
                                   "Your bill's value is: " + total + ".\n" +
                                   "Please check your phone or email to be announced within the next days.\n" +
                                   "To get more information of your bill, please Contact us with this email address.\n" +
                                   "Faithfully you !",
                            "zendiapplication@gmail.com",
                            email);
                } catch (Exception e) {
                    Log.e("mylog", "Error: " + e.getMessage());
                }
            }
        });
        sender.start();
    }
    public String addTotalToHost(){
        Integer temp;
        String tempTotal =  DataManager.host.getTotal();
        if(tempTotal == null || tempTotal.compareTo("") == 0){
            temp = 0;
        }
        else temp = Integer.parseInt(DataManager.host.getTotal());

        for (ShoeInBag a : DataManager.list)
        {
            temp += Integer.parseInt(a.getShoeAmount())*Integer.parseInt(a.getProductPrice());
        }
       String last = temp.toString();
        return last;
    }
    public void upTotalToFirebase(String totalHost){
        DatabaseReference dt = FirebaseDatabase.getInstance().getReference();
        dt.child("Users").child(DataManager.host.getId()).child("total").setValue(totalHost);
    }
    public static String total(){
        String total_ ="$";
        Integer temp = 0;
        for (ShoeInBag a : DataManager.list)
         {
            temp += Integer.parseInt(a.getShoeAmount())*Integer.parseInt(a.getProductPrice());
        }
        total_+= temp.toString();
        return total_ ;
    }
}
