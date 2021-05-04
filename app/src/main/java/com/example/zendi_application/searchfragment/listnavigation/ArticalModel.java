package com.example.zendi_application.searchfragment.listnavigation;

public class ArticalModel {
    private int Image;
    private String title;
    public ArticalModel (int Image, String title)
    {
        this.Image = Image;
        this.title = title;
    }

    public int getImage() {
        return Image;
    }

    public String getTitle() {
        return title;
    }

    public void setImage(int image) {
        Image = image;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
