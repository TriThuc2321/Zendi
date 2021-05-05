package com.example.zendi_application.dropFragment.drop;

import android.os.Parcel;
import android.os.Parcelable;


import com.example.zendi_application.dropFragment.product_package.product;

import java.util.ArrayList;
import java.util.List;

public class drop implements Parcelable {
    private int ResourceId;
    private String caption, satus, type;
    private List<product> productList;

    public drop() {

    }

    public drop(int resourceId, String caption, String satus, String type, List<product> productList) {
        this.ResourceId = resourceId;
        this.caption = caption;
        this.satus = satus;
        this.type = type;
        this.productList = productList;
    }


    protected drop(Parcel in) {
        ResourceId = in.readInt();
        caption = in.readString();
        satus = in.readString();
        type = in.readString();
        if (in.readByte() == 0x01) {
            productList = new ArrayList<product>();
            //productList = in.readArrayList(product.class.getClassLoader());
            // in.readList(productList,product.class.getClassLoader());
            in.readParcelableList(productList, product.class.getClassLoader());
        } else {
            productList = null;
        }
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(ResourceId);
        dest.writeString(caption);
        dest.writeString(satus);
        dest.writeString(type);
        if (productList != null)
//        dest.writeList(productList);
            dest.writeParcelableList(productList, flags);

    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<drop> CREATOR = new Creator<drop>() {
        @Override
        public drop createFromParcel(Parcel in) {
//            final drop dropp = new drop();
//            dropp.setResourceId(in.readInt());
//            dropp.setCaption(in.readString());
//            dropp.setSatus(in.readString());
//            dropp.setType(in.readString());
//            List<product> myList = new ArrayList<>();
//            if (in.readByte() == 0x01) {
//                //productList = in.readArrayList(product.class.getClassLoader());
//                in.readList(myList,product.class.getClassLoader());
//            } else {
//                myList = null;
//            }
//            dropp.setProductList(myList);
//            return dropp;
            //
            return new drop(in);
        }

        @Override
        public drop[] newArray(int size) {
            return new drop[size];
        }
    };

    public int getResourceId() {
        return ResourceId;
    }

    public void setResourceId(int resourceId) {
        ResourceId = resourceId;
    }

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    public String getSatus() {
        return satus;
    }

    public void setSatus(String satus) {
        this.satus = satus;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<product> getProductList() {
        return productList;
    }

    public void setProductList(List<product> productList) {
        this.productList = productList;
    }
}