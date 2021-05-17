package com.example.zendi_application.dropFragment.product_package;

import java.util.List;

public class product2 {
    protected String productId,productName,productPrice;
    protected List<String> ResourceID; // Image list of product
    protected List<Integer> remainingAmount; // 0 : 5 , 1 : 5,5, 2 : 6
    private Integer type;
    public product2(){

    }

    public product2(String productId, String productName, String productPrice, List<String> resourceID, List<Integer> remainingAmount, Integer type) {
        this.productId = productId;
        this.productName = productName;
        this.productPrice = productPrice;
        ResourceID = resourceID;
        this.remainingAmount = remainingAmount;
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

    public String getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(String productPrice) {
        this.productPrice = productPrice;
    }

    public List<String> getResourceID() {
        return ResourceID;
    }

    public void setResourceID(List<String> resourceID) {
        ResourceID = resourceID;
    }

    public List<Integer> getRemainingAmount() {
        return remainingAmount;
    }

    public void setRemainingAmount(List<Integer> remainingAmount) {
        this.remainingAmount = remainingAmount;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }
}
