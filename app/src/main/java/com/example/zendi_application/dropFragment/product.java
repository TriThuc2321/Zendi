package com.example.zendi_application.dropFragment;

import android.content.Intent;
import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;
import java.util.List;

public class product implements Parcelable {
    private String productId,productName;
    private List<Integer> ResourceID; // Image list of product
    private List<Integer> remainingAmount;


    public product(String productId, String productName, List<Integer> resourceID, List<Integer> remainingAmount) {
        this.productId = productId;
        this.productName = productName;
        ResourceID = resourceID;
        this.remainingAmount = remainingAmount;
    }

    protected product(Parcel in) {
        productId = in.readString();
        productName = in.readString();
        ResourceID = in.readArrayList(Integer.class.getClassLoader());
        remainingAmount = in.readArrayList(Integer.class.getClassLoader());
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(productId);
        dest.writeString(productName);
        dest.writeList(ResourceID);
        dest.writeList(remainingAmount);
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
}
