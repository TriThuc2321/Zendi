package com.example.zendi_application.dropFragment.product_package;

import java.util.List;

public class product  {
    private String productId,productName,productPrice;
    private List<Integer> ResourceID; // Image list of product
    private List<Integer> remainingAmount;
    private Integer type;


    public product()
    {

    }

    public product(String productId, String productName, String productPrice, List<Integer> resourceID, List<Integer> remainingAmount, int type) {
        this.productId = productId;
        this.productName = productName;
        this.productPrice = productPrice;
        ResourceID = resourceID;
        this.remainingAmount = remainingAmount;
        this.type = type;
    }


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
