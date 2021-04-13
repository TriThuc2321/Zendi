package com.example.zendi_application.dropFragment.product;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

public class product implements Parcelable {
    private String productId,productName,productPrice;
    private List<Integer> ResourceID; // Image list of product
    private List<Integer> remainingAmount;
    private int type;



    protected product(Parcel in) {
        productId = in.readString();
        productName = in.readString();
        ResourceID = in.readArrayList(Integer.class.getClassLoader());
        remainingAmount = in.readArrayList(Integer.class.getClassLoader());
        type = in.readInt();
        productPrice = in.readString();
    }

    public product(String productId, String productName, String productPrice, List<Integer> resourceID, List<Integer> remainingAmount, int type) {
        this.productId = productId;
        this.productName = productName;
        this.productPrice = productPrice;
        ResourceID = resourceID;
        this.remainingAmount = remainingAmount;
        this.type = type;
    }


    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(productId);
        dest.writeString(productName);
        dest.writeString(productPrice);
        dest.writeList(ResourceID);
        dest.writeList(remainingAmount);
        dest.writeInt(type);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<product> CREATOR = new Creator<product>() {
        @Override
        public product createFromParcel(Parcel in) {
            return new product(in);
        }

        @Override
        public product[] newArray(int size) {
            return new product[size];
        }
    };
    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public List<Integer> getResourceID() {
        return ResourceID;
    }

    public void setResourceID(List<Integer> resourceID) {
        ResourceID = resourceID;
    }

    public List<Integer> getRemainingAmount() {
        return remainingAmount;
    }

    public void setRemainingAmount(List<Integer> remainingAmount) {
        this.remainingAmount = remainingAmount;
    }

    public String getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(String productPrice) {
        this.productPrice = productPrice;
    }
}
