package com.example.zendi_application.dropFragment.category_drop;

import android.os.Parcel;
import android.os.Parcelable;

public class testclass implements Parcelable {
    String a;
    int b;

    protected testclass(Parcel in) {
        a = in.readString();
        b = in.readInt();
    }

    public static final Creator<testclass> CREATOR = new Creator<testclass>() {
        @Override
        public testclass createFromParcel(Parcel in) {
            return new testclass(in);
        }

        @Override
        public testclass[] newArray(int size) {
            return new testclass[size];
        }
    };

    public testclass(String a, int b) {
        this.a = a;
        this.b = b;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(a);
        dest.writeInt(b);
    }
}
