package com.example.zendi_application;

public class DataManager {
    private static DataManager instance;


    public static DataManager getInstance() {
         if (instance == null) instance = new DataManager();
         return instance;
    }

    public static void setInstance(DataManager instance) {
        DataManager.instance = instance;
    }
    public void addDataForDrogFragment(int whichType)
    {

    }
}
