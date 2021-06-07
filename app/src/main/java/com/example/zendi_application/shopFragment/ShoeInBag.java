package com.example.zendi_application.shopFragment;

import android.content.Intent;

import com.example.zendi_application.dropFragment.product_package.product;
import com.example.zendi_application.dropFragment.product_package.product2;

import java.util.List;

public class ShoeInBag extends product2 {
     private String  shoeSize, shoeAmount;

    public ShoeInBag(String productId, String productName, String productPrice, String productBrand, String productType, List<String> resourceID, List<Integer> remainingAmount, Integer type, String shoeSize, String shoeAmount) {
        super(productId, productName, productPrice, productBrand, productType, resourceID, remainingAmount, type);
        this.shoeSize = shoeSize;
        this.shoeAmount = shoeAmount;
    }

    public ShoeInBag(){};

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

    public void increaseAmountView(){
        this.shoeAmount = String.valueOf((Integer.parseInt(this.shoeAmount) + 1));
    }
    public void decreaseAmountView(){
        if(Integer.parseInt(this.shoeAmount) == 0) return;
        this.shoeAmount = String.valueOf((Integer.parseInt(this.shoeAmount) - 1));
    }
}
