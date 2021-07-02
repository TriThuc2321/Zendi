package com.example.zendi_application.ActivityAccount.Admin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.zendi_application.ActivityAccount.SettingActivity;
import com.example.zendi_application.ActivityAccount.User;
import com.example.zendi_application.DataManager;
import com.example.zendi_application.R;
import com.example.zendi_application.shopFragment.ShoeInBag;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.badge.BadgeUtils;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static com.example.zendi_application.DataManager.orderedList;
import static com.example.zendi_application.DataManager.orderedListByDay;

public class StatisticActivity extends AppCompatActivity {

    private RecyclerView mRecyclerStatistic;
    private StatisticAdapter mStatisticAdapter;
    View turnBackBtn;

    Button showAll;
    TextView dateShow;
    Spinner orderBySpinner;

    List<String> listSp = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_statistic);

        Init();

        turnBackBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        dateShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar c = Calendar.getInstance();
                int mYear = c.get(Calendar.YEAR);
                int mMonth = c.get(Calendar.MONTH);
                int mDay = c.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog datePickerDialog = new DatePickerDialog(StatisticActivity.this, android.R.style.Theme_Holo_Dialog,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                                String day, month;

                                if(dayOfMonth<10) day = "0" + dayOfMonth;
                                else  day = dayOfMonth + "";

                                if(monthOfYear + 1 <10) month = "0" + (monthOfYear + 1);
                                else  month = (monthOfYear + 1) + "";

                                dateShow.setText(day + "/" + month + "/" + year);
                                loadList(1);
                            }
                        }, mYear, mMonth, mDay);
                datePickerDialog.show();
            }
        });

        showAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadList(0);
            }
        });
        createListSpinner();
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,listSp);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        orderBySpinner.setAdapter(arrayAdapter);
        orderBySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                /*if (mproduct.getRemainingAmount().get(DataManager.sizeConvert.get(selectedSize)) != 0)
                {
                    selectedSize = sizeSpinner.getSelectedItem().toString();
                    shoeInBag = new ShoeInBag(mproduct.getProductId(), mproduct.getProductName(), mproduct.getProductPrice()
                            , mproduct.getProductBrand(), mproduct.getProductType(), mproduct.getResourceID(), mproduct.getRemainingAmount()
                            , mproduct.getType(), selectedSize, "1");
                }
                else
                    Toast.makeText(mview.getContext(),"Sold out !!",Toast.LENGTH_SHORT).show();*/


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void createListSpinner() {
        listSp.add("Show All");
        listSp.add("Order by Day");
        listSp.add("Order by Month");
        listSp.add("Order by Year");
    }

    void loadList(int isShowAll){
        if(isShowAll == 1){
            getListOrderedByDay(dateShow.getText().toString());
            mStatisticAdapter.SetData(orderedListByDay);
        }
        else{
            mStatisticAdapter.SetData(orderedList);
            dateShow.setText("DD/MM/YY");
        }
    }

    public void getListOrderedByDay(String date){
        orderedListByDay.clear();
        for (int i =0; i<orderedList.size(); i++){
            if(date.compareTo(orderedList.get(i).getBillDate()) == 0){
                orderedListByDay.add(orderedList.get(i));
            }
        }

    }

    private void Init() {
        mRecyclerStatistic = findViewById(R.id.list_statistic_recyclerView);
        turnBackBtn = findViewById(R.id.turnBack_statistic);
        showAll = findViewById(R.id.show_all_ordered_btn);
        dateShow = findViewById(R.id.bill_date_statistic_txt);
        orderBySpinner = findViewById(R.id.orderBy_spinner);

        dateShow.setText(DataManager.getCurrentDay());

        mStatisticAdapter = new StatisticAdapter(this);
        mRecyclerStatistic.setLayoutManager(new LinearLayoutManager(this));

        LayoutAnimationController leftToRight = AnimationUtils.loadLayoutAnimation(this,R.anim.layout_animation_left_to_right);
        mRecyclerStatistic.setLayoutAnimation(leftToRight);

        getListOrderedByDay(DataManager.getCurrentDay());
        mStatisticAdapter.SetData(orderedListByDay);
        mRecyclerStatistic.setAdapter(mStatisticAdapter);
    }



}