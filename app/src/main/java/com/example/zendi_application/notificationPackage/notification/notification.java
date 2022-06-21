package com.example.zendi_application.notificationPackage.notification;

import com.example.zendi_application.Bill;

public class notification {
    private String body;
    private Bill bill;

    public notification() {

    }

    public notification(String body, Bill bill) {
        this.body = body;
        this.bill = bill;
    }
    public String getBody() {
        return body;
    }

    public void setBody(String _body) {
        body = _body;
    }

    public Bill getBill() {
        return bill;
    }

    public void setBill(Bill _bill) {
        bill = _bill;
    }
}






