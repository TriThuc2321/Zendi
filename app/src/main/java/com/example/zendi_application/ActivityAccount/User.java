package com.example.zendi_application.ActivityAccount;

public class User {
    private String Address;
    private String DOB;
    private String Email;
    private int Gender;
    private String Id;
    private String Name;
    private String PhoneNumber;
    private String ProfilePic;
    private String Size;
    private String Total;

    public User(){

    }

    public User(String address, String DOB, String email, int gender, String id, String name, String phoneNumber, String profilePic, String size, String total) {
        Address = address;
        this.DOB = DOB;
        Email = email;
        Gender = gender;
        Id = id;
        Name = name;
        PhoneNumber = phoneNumber;
        ProfilePic = profilePic;
        Size = size;
        Total = total;
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
}