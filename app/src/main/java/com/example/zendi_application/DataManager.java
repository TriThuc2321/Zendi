package com.example.zendi_application;

public class DataManager {
    private static DataManager instance;


    public static DataManager getInstance() {
         if (instance == null) instance = new DataManager();
         return instance;
    }

    private static void setInstance(DataManager instance) {
        DataManager.instance = instance;
    }
}
