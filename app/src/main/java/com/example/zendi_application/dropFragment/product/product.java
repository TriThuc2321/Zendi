package com.example.zendi_application.dropFragment.product;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

public class product implements Parcelable {
    private String productId,productName,productPrice;
    private List<Integer> ResourceID; // Image list of product
    private List<Integer> remainingAmount;
    private Integer type;


    public product()
    {

    }

    protected product(Parcel in) {
        productId = in.readString();
        productName = in.readString();
        if (in.readByte() == 0x01) {
            ResourceID = new ArrayList<Integer>();
            in.readList(ResourceID, Integer.class.getClassLoader());
        } else {
            ResourceID = null;
        }
        if (in.readByte() == 0x01) {
            remainingAmount = new ArrayList<Integer>();
            in.readList(remainingAmount, Integer.class.getClassLoader());
        } else {
            remainingAmount = null;
        }


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
        if (ResourceID == null) {
            dest.writeByte((byte) (0x00));
        } else {
            dest.writeByte((byte) (0x01));
            dest.writeList(ResourceID);
        }
        if (remainingAmount == null) {
            dest.writeByte((byte) (0x00));
        } else {
            dest.writeByte((byte) (0x01));
            dest.writeList(remainingAmount);
        }
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
