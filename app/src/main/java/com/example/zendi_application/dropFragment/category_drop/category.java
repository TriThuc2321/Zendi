package com.example.zendi_application.dropFragment.category_drop;

import com.example.zendi_application.dropFragment.drop.drop;

import java.util.List;

public class category {
    private List<drop> listDrop;

    public category(List<drop> listDrop) {
        this.listDrop = listDrop;
    }

    public List<drop> getListDrop() {
        return listDrop;
    }

    public void setListDrop(List<drop> listDrop) {
        this.listDrop = listDrop;
    }
}
