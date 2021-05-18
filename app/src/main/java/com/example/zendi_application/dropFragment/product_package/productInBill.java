package com.example.zendi_application.dropFragment.product_package;

import java.util.List;

public class productInBill extends product2 {
    private String size;
    private String amount;

    public productInBill(String productId_, String productName_,String productPrice_, List<String> ImgProduct, String size_, String amount_) {
        this.productId = productId_;
        this.productName = productName_;
        this.productPrice = productPrice_;
        this.ResourceID = ImgProduct;
        this.size = size_;
        this.amount = amount_;
    }

    public productInBill() {
    }
}
