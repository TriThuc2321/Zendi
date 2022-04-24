package com.example.zendi_application.dropFragment.product_package;

import java.util.List;

public class product2 {
    protected String productId,productName,productPrice,productBrand,productType;
    protected List<String> ResourceID; // Image list of product
    private List<Integer> remainingAmount; // Cong thuc tinh 5,5 + 0,5*position
    private Integer type;

    public String getProductBrand() {
        return productBrand;
    }

    public void setProductBrand(String productBrand) {
        this.productBrand = productBrand;
    }

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }

    public product2(String productId, String productName, String productPrice, String productBrand, String productType, List<String> resourceID, List<Integer> remainingAmount, Integer type) {
        this.productId = productId;
        this.productName = productName;
        this.productPrice = productPrice;
        this.productBrand = productBrand;
        this.productType = productType;
        ResourceID = resourceID;
        this.remainingAmount = remainingAmount;
        this.type = type;
    }

    public product2(){

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
