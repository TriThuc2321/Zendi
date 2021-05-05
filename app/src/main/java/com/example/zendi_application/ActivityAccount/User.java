package com.example.zendi_application.ActivityAccount;

public class User {
    public String Address;
    public String DOB;
    public String Email;
    public String Gender;
    public String Id;
    public String Name;
    public String Password;
    public String PhoneNumber;
    public String ProfilePic;
    public int Size;
    public int Total;

    public User(){

    }

    public User(String address, String DOB, String email, String gender, String id, String name, String password, String phoneNumber, String profilePic, int size, int total) {
        Address = address;
        this.DOB = DOB;
        Email = email;
        Gender = gender;
        Id = id;
        Name = name;
        Password = password;
        PhoneNumber = phoneNumber;
        ProfilePic = profilePic;
        Size = size;
        Total = total;
    }
}