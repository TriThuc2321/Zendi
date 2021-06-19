package com.example.zendi_application.shopFragment;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.EditText;
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
                            upBilltoFireStore(add,con,DataManager.host.getEmail(),reciever);
                            Toast.makeText(getContext(),"Ordered successfully.",Toast.LENGTH_SHORT).show();
                        }
                    }
                });
        editaddress = view.findViewById(R.id.edit_address);
        editcontact = view.findViewById(R.id.edit_contact);
        editName = view.findViewById(R.id.edit_receiver);
        return builder.create();
    }
    public void upBilltoFireStore(String address, String contact, String email, String name){
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        SimpleDateFormat formatter = new SimpleDateFormat("ddMMyyyyHHmmss"); //Bill document se duoc luu duoi dang  userIDngay thang nam gio phut giay dat hang
        Date date = new Date();
        String datetime = formatter.format(date);
        String docName = DataManager.host.getId() + datetime;
        Map<String, Object> s1 = new HashMap<>();
        s1.put("BillId", docName);
        s1.put("Email", email);
        s1.put("Name", name);
        s1.put("Address",address);
        s1.put("Contact", contact);
        s1.put("Total",total());
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

    public void sendEmail(String email){
        Thread sender = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    GmailSender sender = new GmailSender("zendiapplication@gmail.com", "ThucThienThangHuynh123");
                    sender.sendMail("Order in Zendi",
                            "Thanks for ordering our product and choosing Zendi as your best Shoe Shop." +
                                    " Your bill's value is: " + total + ". " +
                                    "Please check your phone or email to be announced within the next days. " +
                                    "To get more information of your bill, please Contact us with this email address. " +
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
