package com.example.zendi_application;

import com.example.zendi_application.shopFragment.ShoeInBag;

import java.util.List;

public class Bill {
    private String userID;
    private String billID;
    private String date;
    private List<ShoeInBag> listProduct;
    private String total;


    public Bill(String userID, String billID, String date, List<ShoeInBag> listProduct, String total) {
        this.userID = userID;
        this.billID = billID;
        this.date = date;
        this.listProduct = listProduct;
        this.total = getTotal(listProduct);
    }

    public String getTotal(List<ShoeInBag> list)
    {
        String total_ ="$";
        Integer temp = 0;
        for (ShoeInBag a : list)
        {
            temp += Integer.parseInt(a.getShoeAmount())*Integer.parseInt(a.getProductPrice());
        }
        total_+= temp.toString();
        return total_ ;
    }
}

