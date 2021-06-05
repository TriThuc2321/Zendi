package com.example.zendi_application.searchfragment;

import com.example.zendi_application.DataManager;
import com.example.zendi_application.dropFragment.product_package.product2;
import com.example.zendi_application.searchfragment.allShoe.MyEnum;

import java.util.ArrayList;

public class Transactor {
    private static ArrayList<product2> arrayList = new ArrayList<>();
    public static MyEnum.Sex sex;
    public static MyEnum.Brand brand;
    public static Transactor instance;
    public static Transactor getInstance() {
        if (instance == null) instance = new Transactor();
        return instance;
    }
    public ArrayList<product2> getArrayList()
    {return arrayList;}

    public static int ExistInShoeWish(product2 product) {
        for (int i = 0; i < DataManager.shoeInWish.size(); i++) {
            if (product.getProductId().equals(DataManager.shoeInWish.get(i).getProductId()))
                return i;
        }
        return -1;
    }
}
