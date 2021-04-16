package com.example.zendi_application.home.model;

import androidx.lifecycle.MutableLiveData;

public class ItemMenuInBar {
    MutableLiveData<String> Name = new MutableLiveData<>();
    MutableLiveData<Boolean> IsFocus = new MutableLiveData<Boolean>();
    public ItemMenuInBar()
    {
        this.Name.setValue("");
        this.IsFocus.setValue(false);
    }
    public ItemMenuInBar(String Name, Boolean IsTrue)
    {
        this.Name.setValue(Name);
        this.IsFocus.setValue(IsTrue);
    }
    public MutableLiveData<String> getName()
    {
        return this.Name;
    }
    public MutableLiveData<Boolean> getIsFocus()
    {
        return this.IsFocus;
    }
}
