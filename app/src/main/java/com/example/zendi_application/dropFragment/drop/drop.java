package com.example.zendi_application.dropFragment.drop;

public class drop {
    private int ResourceId;
    private String caption,satus,type;

    public drop(int resourceId, String caption, String satus, String type) {
        ResourceId = resourceId;
        this.caption = caption;
        this.satus = satus;
        this.type = type;
    }


    public int getResourceId() {
        return ResourceId;
    }

    public void setResourceId(int resourceId) {
        ResourceId = resourceId;
    }

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    public String getSatus() {
        return satus;
    }

    public void setSatus(String satus) {
        this.satus = satus;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}