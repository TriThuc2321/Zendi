package com.example.zendi_application.shopFragment;

import com.example.zendi_application.dropFragment.product_package.product;

public class ShoeInBag extends product {
     private String shoeStatus, shoeSize, shoeAmount;

    public ShoeInBag(){};

    public String getShoeStatus() {
        return shoeStatus;
    }

    public void setShoeStatus(String shoeStatus) {
        this.shoeStatus = shoeStatus;
    }

    public String getShoeSize() {
        return shoeSize;
    }

    public void setShoeSize(String shoeSize) {
        this.shoeSize = shoeSize;
    }

    public String getShoeAmount() {
        return shoeAmount;
    }

    public void setShoeAmount(String shoeAmount) {
        this.shoeAmount = shoeAmount;
    }
}
