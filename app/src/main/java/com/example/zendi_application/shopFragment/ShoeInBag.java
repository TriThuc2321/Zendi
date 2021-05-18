package com.example.zendi_application.shopFragment;

import com.example.zendi_application.dropFragment.product_package.product;
import com.example.zendi_application.dropFragment.product_package.product2;

import java.util.List;

public class ShoeInBag extends product2 {
     private String shoeStatus, shoeSize, shoeAmount;

    public ShoeInBag(String productId_, String productName_, String productPrice_, List<String> ImgProduct,String shoeStatus_, String size_, String amount_) {
        this.productId = productId_;
        this.productName = productName_;
        this.productPrice = productPrice_;
        this.ResourceID = ImgProduct;
        this.shoeStatus = shoeStatus_;
        this.shoeSize = size_;
        this.shoeAmount = amount_;
    }

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
