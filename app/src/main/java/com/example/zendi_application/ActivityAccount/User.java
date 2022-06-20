package com.example.zendi_application.ActivityAccount;

public class User {
    private String Address;
    private String DOB;
    private String Email;
    private String Password;
    private int Gender;
    private String Id;
    private String Name;
    private String PhoneNumber;
    private String ProfilePic;
    private String Size;
    private String Total;
    private int ShopOwner;  //0 là k, 1 là chủ shop


    public User(){

    }

    public User(String address, String DOB, String email, String password, int gender, String id, String name, String phoneNumber, String profilePic, String size, String total, int shopOwner) {
        Address = address;
        this.DOB = DOB;
        Email = email;
        Password = password;
        Gender = gender;
        Id = id;
        Name = name;
        PhoneNumber = phoneNumber;
        ProfilePic = profilePic;
        Size = size;
        Total = total;
        ShopOwner = shopOwner;
    }


    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public String getDOB() {
        return DOB;
    }

    public void setDOB(String DOB) {
        this.DOB = DOB;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public int getGender() {
        return Gender;
    }

    public void setGender(int gender) {
        Gender = gender;
    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getPhoneNumber() {
        return PhoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        PhoneNumber = phoneNumber;
    }

    public String getProfilePic() {
        return ProfilePic;
    }

    public void setProfilePic(String profilePic) {
        ProfilePic = profilePic;
    }

    public String getSize() {
        return Size;
    }

    public void setSize(String size) {
        Size = size;
    }

    public String getTotal() {
        return Total;
    }

    public void setTotal(String total) {
        Total = total;
    }

    public int getShopOwner() {
        return ShopOwner;
    }

    public void setShopOwner(int shopOwner) {
        ShopOwner = shopOwner;
    }

    public void setPassword(String password){Password = password;}
    public String getPassword(){return Password;}
}