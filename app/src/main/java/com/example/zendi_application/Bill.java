package com.example.zendi_application;

import com.example.zendi_application.shopFragment.ShoeInBag;

import java.util.List;

public class Bill {
    private List<ShoeInBag> listProduct;
    private String total;
    private  String address, contact;


    public Bill(List<ShoeInBag> listProduct, String total, String address, String contact) {
        this.listProduct = listProduct;
        this.total = total;
        this.address = address;
        this.contact = contact;
    }

    public List<ShoeInBag> getListProduct() {
        return listProduct;
    }

    public void setListProduct(List<ShoeInBag> listProduct) {
        this.listProduct = listProduct;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
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

