package com.example.zendi_application.dropFragment;

import android.content.Intent;

import java.io.Serializable;
import java.util.List;

public class product implements Serializable{
    private String productId,productName;
    private List<Integer> ResourceID; // Image list of product
    private List<Integer> remainingAmount;


    public product(String productId, String productName, List<Integer> resourceID, List<Integer> remainingAmount) {
        this.productId = productId;
        this.productName = productName;
        ResourceID = resourceID;
        this.remainingAmount = remainingAmount;
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
}
