package com.example.zendi_application.ActivityAccount.Admin.Statistic;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.zendi_application.DataManager;
import com.example.zendi_application.R;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import static com.example.zendi_application.DataManager.orderedList;
import static com.example.zendi_application.DataManager.orderedListByDay;

public class StatisticActivity extends AppCompatActivity {

    private RecyclerView mRecyclerStatistic;
    private StatisticAdapter mStatisticAdapter;
    LayoutAnimationController leftToRight;
    View turnBackBtn;

    Button showAll;
    TextView dateShow;
    Spinner orderBySpinner;
    Spinner monthSpinner;
    Spinner yearSpinner;
    TextView totalTxt;

    LinearLayout monthSpinnerLayout;
    LinearLayout yearSpinnerLayout;
    LinearLayout dateShowLayout;

    List<String> listSp = new ArrayList<>();
    List<String> listMonthSp = new ArrayList<>();
    List<String> listYearSp = new ArrayList<>();

    int total = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_statistic);

        Init();
        setButton();
        createListSpinner();
        setAdapterSpinner();

    }

    private void setAdapterSpinner() {
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, listSp);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        orderBySpinner.setAdapter(arrayAdapter);
        orderBySpinner.setSelection(3);
        orderBySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (orderBySpinner.getSelectedItem().toString()){
                    case "Show All":
                        monthSpinnerLayout.setVisibility(View.GONE);
                        yearSpinnerLayout.setVisibility(View.GONE);
                        dateShowLayout.setVisibility(View.GONE);

                        total = 0;
                        for (int i =0; i<orderedList.size(); i++){
                            String priceString = orderedList.get(i).getTotal().substring(1);
                            int price = Integer.parseInt(priceString);
                            total += price;
                        }
                        totalTxt.setText("$" + total);

                        mRecyclerStatistic.setLayoutAnimation(leftToRight);
                        mStatisticAdapter.SetData(orderedList);
                        break;
                    case "Month":
                        monthSpinnerLayout.setVisibility(View.VISIBLE);
                        yearSpinnerLayout.setVisibility(View.VISIBLE);
                        dateShowLayout.setVisibility(View.GONE);

                        loadList();
                        /*mRecyclerStatistic.setLayoutAnimation(leftToRight);
                        mStatisticAdapter.SetData(orderedListByDay);*/
                        break;
                    case "Date":
                        monthSpinnerLayout.setVisibility(View.GONE);
                        yearSpinnerLayout.setVisibility(View.GONE);
                        dateShowLayout.setVisibility(View.VISIBLE);

                        loadList();
                        /*mRecyclerStatistic.setLayoutAnimation(leftToRight);
                        mStatisticAdapter.SetData(orderedListByDay);*/
                        break;
                    case "Year":
                        monthSpinnerLayout.setVisibility(View.GONE);
                        yearSpinnerLayout.setVisibility(View.VISIBLE);
                        dateShowLayout.setVisibility(View.GONE);

                        loadList();
                        /*mRecyclerStatistic.setLayoutAnimation(leftToRight);
                        mStatisticAdapter.SetData(orderedListByDay);*/
                        break;
                    default: break;
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        ArrayAdapter<String> arrayMonthAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, listMonthSp);
        arrayMonthAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        monthSpinner.setAdapter(arrayMonthAdapter);
        int m = Calendar.getInstance().get(Calendar.MONTH);
        monthSpinner.setSelection(m);
        monthSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                loadList();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        ArrayAdapter<String> arrayYearAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, listYearSp);
        arrayYearAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        yearSpinner.setAdapter(arrayYearAdapter);
        int y = Calendar.getInstance().get(Calendar.YEAR) - 2020;
        yearSpinner.setSelection(y);
        yearSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                loadList();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void setButton() {
        turnBackBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        showAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadList();
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
                                loadList();
                            }
                        }, mYear, mMonth, mDay);
                datePickerDialog.show();
            }
        });
    }

    private void createListSpinner() {
        listSp.add("Show All");
        listSp.add("Year");
        listSp.add("Month");
        listSp.add("Date");

        /*listMonthSp.add("January");
        listMonthSp.add("February");
        listMonthSp.add("March");
        listMonthSp.add("April");
        listMonthSp.add("May");
        listMonthSp.add("June");
        listMonthSp.add("July");
        listMonthSp.add("August");
        listMonthSp.add("September");
        listMonthSp.add("October");
        listMonthSp.add("November");
        listMonthSp.add("December");*/

        listMonthSp.add("01");
        listMonthSp.add("02");
        listMonthSp.add("03");
        listMonthSp.add("04");
        listMonthSp.add("05");
        listMonthSp.add("06");
        listMonthSp.add("07");
        listMonthSp.add("08");
        listMonthSp.add("09");
        listMonthSp.add("10");
        listMonthSp.add("11");
        listMonthSp.add("12");

        for(int i =2020; i< 2100; i++){
            listYearSp.add(i + "");
        }
    }

    void loadList(){
        if(orderBySpinner.getSelectedItem().toString().equals("Date")) {
            getListOrderedByDay(dateShow.getText().toString(), 0);
        }
        else if(orderBySpinner.getSelectedItem().toString().equals("Year")){
            getListOrderedByDay("01/01/" + yearSpinner.getSelectedItem().toString(), 1);
        }
        else if(orderBySpinner.getSelectedItem().toString().equals("Month")){
            getListOrderedByDay("01/" + monthSpinner.getSelectedItem().toString() + "/" + yearSpinner.getSelectedItem().toString(), 2);
        }
        else if(orderBySpinner.getSelectedItem().toString().equals("Show All")){

            orderedListByDay = new ArrayList<>(orderedList);
            mStatisticAdapter.SetData(orderedListByDay);

        }
    }

    public void getListOrderedByDay(String date, int type){
        orderedListByDay.clear();
        total = 0;
        if(type == 0){
            for (int i =0; i<orderedList.size(); i++){
                if(date.compareTo(orderedList.get(i).getBillDate()) == 0){
                    orderedListByDay.add(orderedList.get(i));
                    String priceString = orderedList.get(i).getTotal().substring(1);
                    int price = Integer.parseInt(priceString);
                    total += price;
                }
            }
        }
        else if(type == 1){
            for(int i =0; i<orderedList.size(); i++){
                String[] temp = orderedList.get(i).getBillDate().split("/");
                if(temp[2].compareTo(yearSpinner.getSelectedItem().toString()) == 0) {
                    orderedListByDay.add(orderedList.get(i));
                    String priceString = orderedList.get(i).getTotal().substring(1);
                    int price = Integer.parseInt(priceString);
                    total += price;
                }
            }
        }
        else if(type == 2){
            for(int i =0; i<orderedList.size(); i++){
                String[] temp = orderedList.get(i).getBillDate().split("/");
                if(temp[1].compareTo(monthSpinner.getSelectedItem().toString()) == 0 && temp[2].compareTo(yearSpinner.getSelectedItem().toString()) == 0) {
                    orderedListByDay.add(orderedList.get(i));
                    String priceString = orderedList.get(i).getTotal().substring(1);
                    int price = Integer.parseInt(priceString);
                    total += price;
                }
            }
        }
        mRecyclerStatistic.setLayoutAnimation(leftToRight);
        mStatisticAdapter.SetData(orderedListByDay);

        totalTxt.setText("$" + total);
    }

    private void Init() {
        mRecyclerStatistic = findViewById(R.id.list_statistic_recyclerView);
        turnBackBtn = findViewById(R.id.turnBack_statistic);
        showAll = findViewById(R.id.show_all_ordered_btn);
        dateShow = findViewById(R.id.bill_date_statistic_txt);
        orderBySpinner = findViewById(R.id.orderBy_spinner);
        monthSpinner = findViewById(R.id.month_spinner);
        yearSpinner = findViewById(R.id.year_spinner);
        totalTxt = findViewById(R.id.total);

        monthSpinnerLayout = findViewById(R.id.month_spinner_layout);
        yearSpinnerLayout = findViewById(R.id.year_spinner_layout);
        dateShowLayout = findViewById(R.id.bill_date_statistic_layout);

        dateShow.setText(DataManager.getCurrentDay());

        mStatisticAdapter = new StatisticAdapter(this);
        mRecyclerStatistic.setLayoutManager(new LinearLayoutManager(this));

        leftToRight = AnimationUtils.loadLayoutAnimation(this,R.anim.layout_animation_left_to_right);
        mRecyclerStatistic.setLayoutAnimation(leftToRight);

        //getListOrderedByDay(DataManager.getCurrentDay(), 0);  // 0 date   1 year   2 month year
        mStatisticAdapter.SetData(null);
        mRecyclerStatistic.setAdapter(mStatisticAdapter);
    }

    @Override
    protected void onResume() {
        super.onResume();

        loadList();
    }
}