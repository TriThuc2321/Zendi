package com.example.zendi_application.dropFragment.drop;

import com.example.zendi_application.dropFragment.product_package.product;
import com.example.zendi_application.dropFragment.product_package.product2;

import java.util.List;

public class drop2 {
    private String Image;

    public String getImage() {
        return Image;
    }

    public drop2(String image, List<String> listProductName, String caption, String status, String type, List<product2> productList) {
        Image = image;
        this.listProductName = listProductName;
        this.caption = caption;
        this.status = status;
        this.type = type;
        this.productList = productList;
    }

    public void setImage(String image) {
        Image = image;
    }

    public List<String> getListProductName() {
        return listProductName;
    }


    public void setListProductName(List<String> listProductName) {
        this.listProductName = listProductName;
    }

    private List<String> listProductName;

    private String caption;



    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    public String getSatus() {
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

    private String status;
    private String type;
    private List<product2> productList;

    public drop2() {

    }

}
