package com.example.zendi_application;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.SystemClock;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.login.LoginManager;

public class DownloadAsynctask extends AsyncTask<Void, Integer, Void> {

    Activity contextParent;

    public DownloadAsynctask(Activity contextParent) {
        this.contextParent = contextParent;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();

    }

    @Override
    protected Void doInBackground(Void... params) {
        DataManager.LoadDropInformation("Collection/",DataManager.listDrop);  // load products
        DataManager.LoadProductInformation("Product",DataManager.listProduct); // load categors

        DataManager.loadUser();
        DataManager.GetUser();
        DataManager.getListOrderedFromFirestone();
        for (int i = 0; i <= 100; i=i+3) {
            SystemClock.sleep(100);
            //khi gọi hàm này thì onProgressUpdate sẽ thực thi
            publishProgress(i);
        }
        return null;
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        //Hàm thực hiện update giao diện khi có dữ liệu từ hàm doInBackground gửi xuống
        super.onProgressUpdate(values);
        //Thông qua contextCha để lấy được control trong MainActivity
        ProgressBar progressBar = (ProgressBar) contextParent.findViewById(R.id.progress_bar_introduction);
        //vì publishProgress chỉ truyền 1 đối số
        //nên mảng values chỉ có 1 phần tử
        int number = values[0];
        //tăng giá trị của Progressbar lên
        progressBar.setProgress(number);
        //đồng thời hiện thị giá trị là % lên TextView
        TextView textView = (TextView) contextParent.findViewById(R.id.textview_progress);
        textView.setText(number + "%");
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        Intent intent = new Intent((Introduction)contextParent,HomeScreen.class);
        ((Introduction)contextParent).startActivity(intent);
        ((Introduction)contextParent).finish();
        Toast.makeText(contextParent, "Welcom to Zendi", Toast.LENGTH_SHORT).show();
    }
}