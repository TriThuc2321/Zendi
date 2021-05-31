package com.example.zendi_application.dropFragment.category_drop;

import com.example.zendi_application.dropFragment.drop.drop;
import com.example.zendi_application.dropFragment.drop.drop2;

import java.util.List;

public class category {
    public List<drop2> listDrop;

    public category(List<drop2> listDrop) {
        this.listDrop = listDrop;
    }

    public List<drop2> getListDrop() {
        return listDrop;
    }

    public void setListDrop(List<drop2> listDrop) {
        this.listDrop = listDrop;
    }
}
