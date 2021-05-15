package com.example.zendi_application;

import android.os.AsyncTask;

import androidx.appcompat.app.AppCompatActivity;

public class DownloadAsynctask extends AsyncTask<String,String,Void> {
    private AppCompatActivity contextActivity;
    private String query;

    public DownloadAsynctask(AppCompatActivity contextActivity, String query) {
        this.contextActivity = contextActivity;
        this.query = query;
    }


    @Override
    protected Void doInBackground(String... strings) {
        return null;
    }
}
