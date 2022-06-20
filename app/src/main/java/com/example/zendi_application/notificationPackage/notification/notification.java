package com.example.zendi_application.notificationPackage.notification;

public class notification {
    private String body;
    private String billId;

    public notification() {

    }

    public notification(String body, String billId) {
        this.body = body;
        this.billId = billId;
    }
    public String getBody() {
        return body;
    }

    public void setBody(String _body) {
        body = _body;
    }

    public String getBillId() {
        return billId;
    }

    public void setBillId(String _billId) {
        billId = _billId;
    }
}






