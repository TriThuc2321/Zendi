package com.example.zendi_application.searchfragment;

import com.example.zendi_application.DataManager;
import com.example.zendi_application.dropFragment.product_package.product2;

import java.util.ArrayList;

public class Transactor {
    private static ArrayList<product2> arrayList = new ArrayList<>();
    public static Transactor instance;
    public static Transactor getInstance() {
        if (instance == null) instance = new Transactor();
        return instance;
    }
    public ArrayList<product2> getArrayList()
    {return arrayList;}
}
