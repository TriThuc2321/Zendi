package com.example.zendi_application.dropFragment.drop;

import com.example.zendi_application.dropFragment.product_package.product;
import com.example.zendi_application.dropFragment.product_package.product2;

import java.util.ArrayList;
import java.util.List;

public class drop2 {
    private String image;
    private String caption;
    private String status;
    private String type;
    private String categoryNumber;
    private List<String> listProductName;
    private List<product2> productList ;

    public drop2(String image, String caption, String status, String type, String categoryNumber, List<String> listProductName, List<product2> productList) {
        this.image = image;
        this.caption = caption;
        this.status = status;
        this.type = type;
        this.categoryNumber = categoryNumber;
        this.listProductName = listProductName;
        this.productList = productList;
    }
    public String getCategoryNumber() {
        return categoryNumber;
    }

    public void setCategoryNumber(String categoryNumber) {
        this.categoryNumber = categoryNumber;
    }


    public String getImage() {
        return image;
    }

    public drop2() {
    }

    public void setImage(String image) {
        this.image = image;
    }

    public List<String> getListProductName() {
        return listProductName;
    }

    public void setListProductName(List<String> listProductName) {
        this.listProductName = listProductName;
    }

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<product2> getProductList() {
        return productList;
    }

    public void setProductList(List<product2> productList) {
        this.productList = productList;
    }





}
