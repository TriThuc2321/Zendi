package com.example.zendi_application.searchfragment.allShoe;

public class ShoeItemModel {
    private int ImageRes;
    private String mCharge;
    private String mOriginals;
    private String mName;
    public ShoeItemModel(int imageRes, String mCharge, String mName, String mOriginals)
    {
        this.ImageRes = imageRes;
        this.mCharge = mCharge;
        this.mName = mName;
        this.mOriginals = mOriginals;
    }

    public int getImageRes() {
        return ImageRes;
    }

    public String getmCharge() {
        return mCharge;
    }

    public String getmName() {
        return mName;
    }

    public String getmOriginals() {
        return mOriginals;
    }

    public void setImageRes(int imageRes) {
        ImageRes = imageRes;
    }

    public void setmCharge(String mCharge) {
        this.mCharge = mCharge;
    }

    public void setmName(String mName) {
        this.mName = mName;
    }

    public void setmOriginals(String mOriginals) {
        this.mOriginals = mOriginals;
    }
}
