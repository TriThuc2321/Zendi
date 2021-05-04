package com.example.zendi_application.ActivityAccount;

import android.net.Uri;

public class User {
    private String Id;
    private String Email;
    private String Password;
    private String Name;
    private String DOB;
    private String Gender;
    private int PhoneNumber;
    private Uri ProfilePic;
    private Size size;

    public User(String id) {
        Id = id;
    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getDOB() {
        return DOB;
    }

    public void setDOB(String DOB) {
        this.DOB = DOB;
    }

    public String getGender() {
        return Gender;
    }

    public void setGender(String gender) {
        Gender = gender;
    }

    public int getPhoneNumber() {
        return PhoneNumber;
    }

    public void setPhoneNumber(int phoneNumber) {
        PhoneNumber = phoneNumber;
    }

    public Uri getProfilePic() {
        return ProfilePic;
    }

    public void setProfilePic(Uri profilePic) {
        ProfilePic = profilePic;
    }

    public Size getSize() {
        return size;
    }

    public void setSize(Size size) {
        this.size = size;
    }
}
