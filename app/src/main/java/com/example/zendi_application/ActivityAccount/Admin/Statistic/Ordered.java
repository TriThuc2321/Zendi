package com.example.zendi_application.ActivityAccount.Admin.Statistic;

import com.example.zendi_application.shopFragment.ShoeInBag;

import java.util.List;

public class Ordered {
    private List<ShoeInBag> ShoeList;
    private String BillId;
    private String Address;
    private String Contact;
    private String Email;
    private String Name;
    private String BillDate;
    private String BillStatus;

    public Ordered(List<ShoeInBag> shoeList, String billId, String address, String contact, String email, String name, String billDate, String billStatus, String total) {
        ShoeList = shoeList;
        BillId = billId;
        Address = address;
        Contact = contact;
        Email = email;
        Name = name;
        BillDate = billDate;
        BillStatus = billStatus;
        Total = total;
    }

    public String getBillStatus() {
        return BillStatus;
    }

    public void setBillStatus(String billStatus) {
        BillStatus = billStatus;
    }

    public String getBillDate() {
        return BillDate;
    }

    public void setBillDate(String billDate) {
        BillDate = billDate;
    }

    private String Total;

    public List<ShoeInBag> getShoeList() {
        return ShoeList;
    }

    public void setShoeList(List<ShoeInBag> shoeList) {
        ShoeList = shoeList;
    }

    public String getBillId() {
        return BillId;
    }

    public void setBillId(String billId) {
        BillId = billId;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public String getContact() {
        return Contact;
    }

    public void setContact(String contact) {
        Contact = contact;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getTotal() {
        return Total;
    }

    public void setTotal(String total) {
        Total = total;
    }

    public  Ordered(){

    }
}
