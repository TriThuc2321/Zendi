package com.example.zendi_application.searchfragment.allShoe;

public class ShoeItemModel {
    private int ImageRes;
    private int HeartImageRes;
    private String mCharge;
    private String mOriginals;
    private String mName;
    private boolean isLike;
    public ShoeItemModel(boolean isLike)
    {
        this.isLike = isLike;
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

    public boolean isLike() {
        return isLike;
    }
    public int getHeartImageRes() {
        return HeartImageRes;
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

    public void setHeartImageRes(int heartImageRes) {
        HeartImageRes = heartImageRes;
    }

    public void setLike(boolean like) {
        isLike = like;
    }
}
